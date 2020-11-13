(function () {

const configuration  = {
    'iceServers': [
                    { 'urls': 'stun:stun.stunprotocol.org:3478' },
                    { 'urls': 'stun:stun.l.google.com:19302' }
    ]
};

const constraints = {audio: true, video: true};

var conferenceroom = document.getElementById("conferenceroom");
var connectionDescription = null, localSdp = null, localStream = null, inboundStream=null;


function createVideoElement(elementId){
    var element = document.createElement("video");
    element.setAttribute("id",elementId);
    element.setAttribute("autoplay","true");
    element.setAttribute("muted","muted");
    conferenceroom.appendChild(element);
    return element;
}

var sessions = {"localSession":createVideoElement("localVideo"),
                "remoteSession":createVideoElement("remoteVideo")};

var selfView = sessions["localSession"];
var remoteView = sessions["remoteSession"];
var peerConnection = null, peerRemoteDescription = null;
var getPeerRemoteDescription = function(){
    return peerRemoteDescription;
}
var dataChannel = null, unique = 5555;
var remoteanswer = null, inboundStream = null, serverChannel = null, ws = null;
var conferenceroomButton = document.getElementById("startcall");
var conferenceroom = document.getElementById("conferenceroom");
var eventTable = document.getElementById("eventTable");

conferenceroomButton.addEventListener("click",function(e){
    e.preventDefault();
    createPeerConnection();
});

function updateEventTable(row,cell,value){
    var row = eventTable.querySelector('tr:nth-child('+row+')');
    var cell = row.querySelector('td:nth-child('+cell+')');
    cell.innerHTML = value;
}

function updateEventTableValue(label,text){
    updateEventTable(1,1,label);
    updateEventTable(1,2,text);
}

function updateEventTableValue2(label,text){
    updateEventTable(2,1,label);
    updateEventTable(2,2,text);
}

function updateEventTableAll(label1,text1,label2,text2){
        updateEventTableValue(label1,text1);
        updateEventTableValue2(label2,text2);
    
}


function displayRemoteStream(e) {
    updateEventTableValue2('displayRemoteStream','');
    const remoteVideo = document.getElementById('remoteVideo');
    if (e.streams === undefined){
        updateEventTableValue2("event streams are undefined","");
    }else {
        if (remoteVideo.srcObject !== e.streams[0]) {
            remoteVideo.srcObject = e.streams[0];
            updateEventTableValue2('pc2 received remote stream','');
        }
    }
};

var createPeerConnection = function (){
    peerConnection = new RTCPeerConnection(configuration,{
                optional : [{
                RtpDataChannels : true
        }]
    });
    
    peerConnection.onicecandidate = ({candidate}) => function(event) {
        updateEventTableValueAll("candidate",candidate,"event",event)
        ws.send({candidate});
        ws.send(JSON.stringify({"candidate": event.candidate}));
    };
    peerConnection.onaddstream = function(event) {
        console.log("senders",peerConnection.getSenders().length);
        console.log("receivers",peerConnection.getReceivers().length);        
        addVideoStream(event.stream);
        updateEventTableValue2("adding stream",event.stream);
    };

    peerConnection.onaddtrack = function(event) {
        console.log("senders",peerConnection.getSenders().length);
        console.log("receivers",peerConnection.getReceivers().length);
        addVideoStream(event.stream);
        updateEventTableValue2("adding stream",event.stream);
    };
    
    peerConnection.onremovetrack = function(event) {
        updateEventTableValue2("removing track",event);
        
    };

    peerConnection.onremovestream = function(event) {
        updateEventTableValue2("removing stream",event);
        
    };
    
    peerConnection.oniceconnectionstatechange = function(event) {
        updateEventTableValue2("removing stream",event);
        
    };
    
    peerConnection.onsignalingstatechange = function(event) {
        updateEventTableValue2("signaling state change",event);
        
    };
    
    peerConnection.onicegatheringstatechange = function(event) {
        updateEventTableValue2("ice gathering state change",event);
        
    };

    peerConnection.onnegotiationneeded = function(){
        updateEventTableValue2("negotiation needed",peerConnection.localDescription);
        try {
            peerConnection.createOffer().then(function(offer){
            handleOffer(offer); 
            }).then(function(){
                ws.send({desc: peerConnection.localDescription});
                handleAnswer(peerConnection.localDescription);
                
            });
        }catch (err) {
            console.log(err);
        }
        //await peerConnection.setLocalDescription(await peerConnection.createOffer());
        if (peerConnection.localDescription !== null){
        } else {
            updateEventTableValue2("negotiation needed","peer connection local description is null");
        }

         
    };

    peerConnection.ontrack = (event) => {
            updateEventTableValue2("on track event",event);
          if (event.streams && event.streams[0]) {
            updateEventTableValue2("tracking...","adding 1 stream");
            addVideoStream(event.stream);                  
            remoteView.srcObject = event.streams[0];
          } else if (event.stream){
            updateEventTableValue2("tracking...","adding stream");
            addVideoStream(event.stream);
        } else {
            updateEventTableValue2("tracking...","no streams");
            if (!inboundStream) {
              inboundStream = new MediaStream();
                addVideoStream(inboundStream);
            }
            localStream = inboundStream;
          }

    };
    
    peerConnection.onmessage = function(message) {
        updateEventTableValue2("peer connection",message);
        updateEventTableValue2("senders",peerConnection.getSenders());
        updateEventTableValue2("receivers",peerConnection.getReceivers());        
        try {
            var content = JSON.parse(message.data);
            var data = content[0].data;

            switch(content[0].event) {
                case "offer":
                    handleOffer(data);
                    break;
                case "answer":

                    handleAnswer(data);
                    break;
                case "candidate":
                    handleCandidate(data);
                    break;
                default:
                    break;
            }
        } catch (e) {
            console.log("peerConnection onmessage JSON parse error", e);
        }
    };
    
    peerConnection.addEventListener('track', displayRemoteStream);

    dataChannel = peerConnection.createDataChannel("dataChannel",
            {reliable:true}
    );

    dataChannel.onerror = function(error){
        console.log("Error", error);   
    };

    dataChannel.onmessage = function(event) {
        updateEventTableValue2("data channel message",event.data);
    };

    dataChannel.onclose = function() {
        updateEventTableValue2("Data channel is closed","");
    };

        ws = new EventSource('/socket/'+unique);
        ws.send = function send(message) {
            sendMediaStream(message);
        };

        ws.onmessage = function(e) {
            sendMultipleMessageStreams(ws,e);
        };

        ws.addEventListener('message',function(e){
            updateEventTableValue("Received web socket message event",e.data);
        });

        ws.addEventListener('open',function(e){
            updateEventTableValue("Received web socket open event",e.data);
        });



try {
    navigator.getUserMedia({video:true, audio:true}, function() {
    peerConnection.setRemoteDescription(new RTCSessionDescription(getPeerRemoteDescription))
            .then(function(stream){
                streamPeerConnection(stream);
            })
            .then(function() {
                        return peerConnection.createAnswer();
            })
            .then(function() {
                peerConnection.createAnswer(function(answer){
                    remoteanswer = answer;

                peerConnection.setLocalDescription(
                        new RTCSessionDescription(answer),
                        function() {
                            send({
                            event: "answer",
                            data : answer});                                                                                
                        }, function(e) {
                        updateEventTableValue2("Description Error",e);
                        });

                });
            })
            .then(function(){
                updateEventTableValue2("Established connection","");
                var stream = selfView.captureStream();
                for (const track of stream.getTracks()) {
                    try {
                            peerConnection.addTrack(track);
                        } catch (e) {
                        updateEventTableValue2("Error adding track",e);
                    }
                }


             }).catch(offerError);
         }, function(){
            updateEventTableValue2("Remote Description","AV Error");

         });

        } catch(e) {
            updateEventTableValue2("Sync peer stream",e);

        }                 
    

        return peerConnection;


};


function handleOffer(offer) {
    updateEventTableValue2("Handle Offer", offer);
    if (offer.type !== undefined && offer.type=="offer"){
        peerConnection
            .setRemoteDescription(new RTCSessionDescription(offer));

        // create and send an answer to an offer
        updateEventTableValue2("offer answered with response",remoteanswer);
        if (remoteanswer !== null && remoteanswer.RTCSdpType !== null){
            peerConnection.createAnswer(function(answer) {
                peerConnection.setLocalDescription(answer);
                sendSignal(answer);
            }, function(error) {
                updateEventTableValue2("Error creating answer",error);
            });
        }
    } else {
        updateEventTableValue2("Received offer for",offer);
        if (offer === "channel1" || offer=== "channel2"){
                serverChannel = offer;            
        } else {
            try {
                var dc = JSON.parse(offer);
                if (dc.channel)
                {
                    serverChannel = dc.channel;
                } 
            } catch (e) {
                updateEventTableValue2("parsing error", e);

            }
        }
    }
 
};



var processServerHeaders = function(headers){
    var header={};
    var headersString  = headers.split("\n");
    //console.log("ProcessServerHeaders: ",headersString);
    for (var i=0;i<headersString.length;i++){
        var key = headersString[i].split(":")[0];
        var value = headersString[i].split(":")[1];
        if (value !== undefined){
            header[key]=value;
        }
    }
    if (header["custom-event-source"]!==undefined){
        if (header["custom-event-source"] === "poll-rooms") {
            updateEventTableValue2("received response from poll rooms.","");
        } else {
            updateEventTableValue2("received event source, ",
            header["custom-event-source"]);
            addSource(header["custom-event-source"]);
        }
    }
    return true;
}

var processFormMessage = function(message){
    var formData = new FormData();
    
        if (message.desc !== undefined && message.desc !== null){
            if (message.desc !== undefined && message.desc !== null &&
            message.desc.sdp !== undefined && message.desc.sdp !== null &&
            message.desc.type !== undefined && message.desc.type !== null) {
                peerRemoteDescription = message.desc;
                formData.append("message",message);
                formData.append("sdp",message.desc.sdp);
                formData.append("type",message.desc.type);
                connectionDescription = message.desc;
                handleVideoOfferMsg(message.desc);
                localSdp = message.desc.sdp;
                return ['POST','/socket/'+unique,formData];
            } else {
                formData.append("message",message);
                return ['POST','/topics/'+unique,formData];                
            }            
        } else if (message !== null){
            try {
                var messageobj = JSON.parse(JSON.parse(message));
                if (messageobj.message){
                    formData.append("server-message","send");
                    formData.append("message",message.desc.sdp);
                    
                    formData.append("type",message.desc.type);
                    return ['POST','/stream/media',formData];
                    
                }else {
                    formData.append("server-message","send");
                    formData.append("message",message);
                    return ['POST','/stream/video',formData];
                    
                }
                    
            } catch (err) {
                return ['POST','/topics/1111',new FormData()];
                updateEventTableValue2("json parsing error message",message);
             }
         }
    
};

var processMessageEvent = function(message){
        switch (message.event) {
            case 'client-call':
                clientCall(localStream);
                break;
            case 'client-answer':
                clientAnswer(message.data);
                break;
            case 'client-offer':
                clientOffer(localStream,message.data);
                break;
            case 'client-candidate':
                clientCandidate(message.data);
                break;
        }

}


var sendMediaStream = function(message) {
    updateEventTableValue("send media stream",message);
    var xhttp = new XMLHttpRequest();
    var header={};
    var formData = new FormData();
    xhttp.onreadystatechange = function() {
            if (this.readyState!=4) {
              return;
            }
            if (this.status != 200) {
            } else {
                console.log("response",this.responseText);
                updateEventTableValue("send Media Stream Response",this.responseText);
                processServerHeaders(xhttp.getAllResponseHeaders().toLowerCase());
            }
    };
    processMessageEvent(message);
    var sendPackage=processFormMessage(message);
    xhttp.open(sendPackage[0], sendPackage[1], true);
    xhttp.send(sendPackage[2]);            

}



function handleAnswer(answer) {
        if (answer !== null && answer.RTCSdpType !== null){
            peerConnection.setRemoteDescription(new RTCSessionDescription(
                answer));
            updateEventTableValue("connection established successfully!!","");
        }    
};

function handleCandidate(candidate) {
	updateEventTableValue("handleCandidate","");
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
};

function logVideoAudioTrackInfo(localStream) {
    const videoTracks = localStream.getVideoTracks();
    const audioTracks = localStream.getAudioTracks();
    if (videoTracks.length > 0) {
        updateEventTableValue("Using video device:",videoTracks[0].label);
    }
    if (audioTracks.length > 0) {
        updateEventTableValue("Using audio device:",audioTracks[0].label);
    }
};

})();

