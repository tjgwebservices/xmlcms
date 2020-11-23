'use strict';
(function () {

const connectionArray = [];
const msg = {};
msg.target = 0;

const configuration  = {
    'iceServers': [
                    { 'urls': 'stun:stun.stunprotocol.org:3478' },
                    { 'urls': 'stun:stun.l.google.com:19302' }
    ]
};

const constraints = {audio: true, video: true};

const constraints2 = {
    video : {
        frameRate : {
            ideal : 10,
            max : 15
        },
        width : 1280,
        height : 720,
        facingMode : "user"
        }
    };


let audioContext;

var conferenceroom = document.getElementById("conferenceroom");

function createVideoElement(elementId){
    var element = document.createElement("video");
    element.setAttribute("id",elementId);
    element.setAttribute("autoplay","true");
    element.setAttribute("muted","muted");
    conferenceroom.appendChild(element);
    return element;
}

var sessions = {"clientView":createVideoElement("localVideo"),
                "remoteView":createVideoElement("remoteVideo")};

var selfView = sessions["clientView"];
var remoteView = sessions["remoteView"];
var peerConnection = null, peerRemoteDescription = null;
var getPeerRemoteDescription = function(){
    return peerRemoteDescription;
}
var dataChannel = null;

var newConnection = null;
var isCaller = false;
var localSdp = null;

var answer=0,localStream=null,ws=null, serverResponse=null,
    rtcpeerconnection=null, hosturl="/socket",
    serverRemoteDescription=null, inboundStream=null,
    serverChannel=null;

var videoElement = document.getElementById("liveStream");
var sourceStream = document.getElementById("srcStream");
var peerStream = document.getElementById("peerStream");

var conferenceroomButton = document.getElementById("startcall");
var testcallButton = document.getElementById("testcall");
var testconnectionButton = document.getElementById("testconnection");
var testconnectionButton2 = document.getElementById("testconnection2");
var testconnectionButton3 = document.getElementById("testconnection3");
var testconnectionButton4 = document.getElementById("testconnection4");
var testconnectionButton5 = document.getElementById("testconnection5");
var conferenceroom = document.getElementById("conferenceroom");
var audioonlyButton = document.getElementById("audioonly");
var audiofilterButton = document.getElementById("audiofilter");
var serverCommands = document.getElementById("commands");
var greetings = document.getElementById("greetings");

var eventTable = document.getElementById("eventTable");

var loginInput = document.getElementById("rtcconnectname");
var loginButton = document.getElementById("rtcconnectbutton");

var rtcform = document.getElementById("rtcform");
var rtcsendbutton = document.getElementById("rtcsend");
var rtcremote = document.getElementById("rtcremote");
var rtcself = document.getElementById("rtcself");
var unique = Math.floor(100000 + Math.random() * 999);

var myUsername = "testMyUser";
var targetUsername = "testTarget";

const buffer = new ArrayBuffer(1024);
var view;

var canvas=document.getElementById("canvas");
var context=canvas.getContext("2d");
var canvasInterval;
var connectionDescription;

var sessconfig = null,sessPoll=null;
sessPoll = setInterval(isSessConfig,500);
function isSessConfig(){
    if(sessconfig == null){
        return false;
    } else {
        clearInterval(sessPoll);
        return true;
    }
}


var rtcconfig = {
    RTCSessionDescriptionInit: {
            sdp:  "answer",
            type: "answer"
        },
    RTCSessionDescription: {
       sdp: "answer"
    },
    init: "false"
};

var newConnection = null, openMediaDevices = null;

rtcform.addEventListener("submit", 
	function(e){
		e.preventDefault(); 
   
        });        
loginButton.addEventListener("click", 
	function(e){
		e.preventDefault();
                if (loginInput.value.length > 0) {
                    rtcOnLogin(true);
                    rtcsend({
                        type: "login",
                        name: loginInput.value
                    });
                }
        });

testcallButton.addEventListener("click",function(e){
   e.preventDefault();
   createServerConnection();
   makePeerCall(createPeerConnection());
   
});

testconnectionButton.addEventListener("click",function(e){
   e.preventDefault();
   enableServerComm();
   rtcOnLogin(true);
   startAsynchronousCall();
   
   
});
testconnectionButton2.addEventListener("click",function(e){
    e.preventDefault();
    testPeerCall();
    openMediaDevices = navigator.mediaDevices.getUserMedia(constraints)
    .catch(function(error){updateEventTableValue2("Error with media devices", error);});
    streamconnect();
    enableServerComm();
});
testconnectionButton3.addEventListener("click",function(e){
    e.preventDefault();
    triggerMessageEvent();
    startAsynchronousCall();
    openMediaDevices = navigator.mediaDevices.getUserMedia(constraints)
    .catch(function(error){updateEventTableValue2("Error with media devices", error);});
    streamconnect(openMediaDevices);
    createServerConnection();      
});

testconnectionButton4.addEventListener("click", function(e){
    e.preventDefault();
    var senders = peerConnection.getSenders();
    updateEventTableValue("Number of peer connections: ",senders.length);
    makePeerCall(peerConnection);
    startAsynchronousCall();

});

testconnectionButton5.addEventListener("click", function(e){
    e.preventDefault();
    openMediaDevices = navigator.mediaDevices.getUserMedia(constraints)
    .catch(function(error){console.log("Error with media devices", error);});    
    setAsynchronousMessage(ws);
    
});

conferenceroomButton.addEventListener("click",function(e){
    e.preventDefault();
    openMediaDevices = navigator.mediaDevices.getUserMedia(constraints)
    .catch(function(error){console.log("Error with media devices", error);});    
    streamconnect(openMediaDevices);
});

audioonlyButton.addEventListener("click",function(e){
    e.preventDefault();
    testEventSourceSend("client-offer","1");
        sendMediaStream("test button 2");
    enableHangUp();
    streamAudio();
});

audiofilterButton.addEventListener("click",function(e){
    e.preventDefault();
    if (typeof AudioContext === 'function') {
        audioContext = new AudioContext();
        createAudioFilter(audioContext)

    } else if (typeof webkitAudioContext === 'function'){
        audioContext = new webkitAudioContext();
        createAudioFilter(audioContext)
    } else {
        updateEventTableValue("Audio not supported");
    }
});

var connectform = document.getElementById("connectform");
connectform.addEventListener("submit", 
    function(e){
        e.preventDefault(); 
        testEventSourceSend("client-start","1");
        localCall();
        enableHangUp();   
    });
    
var sendmessage = document.getElementById("sendmessage");

sendmessage.addEventListener("submit", 
    function(e){
        e.preventDefault(); 
        localCall();
    });
      
var connect = document.getElementById("connect");
connect.addEventListener("click", 
    function(e){
        e.preventDefault();
        openMediaDevices = navigator.mediaDevices.getUserMedia(constraints)
                .catch(function(error){console.log("Error with media devices", error);});
        streamconnect();

    });
    
var disconnect = document.getElementById("disconnect");
disconnect.addEventListener("click", 
    function(e){
        e.preventDefault();
        openMediaDevices = navigator.mediaDevices.getUserMedia(constraints)
            .catch(function(error){console.log("Error with media devices", error);});
        streamdisconnect();

    });

var sendbutton = document.getElementById("send");
sendbutton.addEventListener("click", 
    function(e){
            e.preventDefault();
            streamsendname();

    });
 
rtcsendbutton.addEventListener("click",function(e){
    e.preventDefault(); 
    var connectname = document.getElementById("rtcconnectname").value;
    rtcpeerconnection = peerConnection;    
    rtcpeerconnection.createOffer().then(function(offer){
        sendToServer({
            name: connectname,
            target: "host",
            type: "video-offer",
            sdp: rtcpeerconnection.localDescription,
            topic: "rtc-connectoffer"

        });
        rtcpeerconnection.setLocalDescription(offer);
    }).catch(function(error){
        updateEventTableValue("rtcsenderror",error);
    });
    
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
function createTestCanvasAnimation(){
    context.stroke();    
    canvasInterval = setInterval(draw, 100);
}

function draw(){
    context.strokeStyle="green";
    context.moveTo(30,40);
    context.lineTo(145,120);
        var time = new Date(); 
        context.save();
        context.translate(150,150);
         context.rotate( ((2*Math.PI)/6)*time.getSeconds() + ( (2*Math.PI)/6000)*time.getMilliseconds() );
         context.translate(0,28.5);
         context.restore();    
}

function displayRemoteStream(e) {
    updateEventTableValue2('displayRemoteStream','');
    const remoteVideo = document.getElementById('remoteVideo');
    if (e.streams === undefined){
        updateEventTableValue2("event streams are undefined","");
    }else {
        if (remoteVideo.srcObject !== e.streams[0]) {
            remoteVideo.srcObject = e.streams[0];
            remoteVideo.play();
            updateEventTableValue2('pc2 received remote stream','');
        }
    }
};

var createPeerConnection = async function (){
    peerConnection = new RTCPeerConnection(configuration,{
                optional : [{
                RtpDataChannels : true
        }]
    });
    
    peerConnection.onicecandidate = ({candidate}) => function(event) {
        updateEventTableAll("candidate",candidate,"event",event);
        ws.send({"candidate": event.candidate});
    };
    peerConnection.onaddstream = function(event) {
        updateEventTableAll("senders",peerConnection.getSenders().length,
        "receivers",peerConnection.getReceivers().length);        
        addVideoStream(event.stream);
        updateEventTableValue2("adding stream",event.stream);
    };

    peerConnection.onaddtrack = function(event) {
        updateEventTableAll("senders",peerConnection.getSenders().length,
        peerConnection.getReceivers().length);
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
            updateEventTableValue2("Error",err);
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
            remoteView.play();
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
            updateEventTableValue2("peerConnection onmessage JSON parse error", e);
        }
    };
    
    peerConnection.addEventListener('track', displayRemoteStream);
    enableServerComm();

    dataChannel = peerConnection.createDataChannel("dataChannel",
            {reliable:true}
    );

    dataChannel.onerror = function(error){
        updateEventTableValue2("Error", error);   
    };

    dataChannel.onmessage = function(event) {
        updateEventTableValue2("data channel message", event);
        try {
            const message = JSON.parse(event);
            if (message.ice) {
              peerConnection.addIceCandidate(message.ice).catch(e => {
                updateEventTableValue2("Failure during addIceCandidate(): ", e.name);
              });
            } else {                
                    if (event.data) {
                      updateEventTableValue2("data channel message",event.data);
                    }else {
                        updateEventTableValue2("data channel message",event);                  
                    }
            }        
        } catch (error){
                  updateEventTableValue2("Json parsing error",event,error);
              }
    };

    dataChannel.onclose = function() {
        updateEventTableValue2("Data channel is closed","");
    };

    if (connectionDescription != null) {
            streamSyncPeer();
         } else {
            updateEventTableValue2("connectionDescription",connectionDescription);
         }
        createTestCanvasAnimation();
        var cStream;
        const userMediaStream = await navigator.mediaDevices.getUserMedia({
            video: {
                width: {min: 480, ideal: 480 },
                height: { min: 270, ideal: 270 },
                aspectRatio: {ideal: 1.777 }
            },
            audio: {
                sampleSize: 16,
                channelCount: 2
            }
        }).then(stream =>{
            selfView.srcObject = stream;
            selfView.play();
            cStream = stream;
            videoElement.srcObject = cStream;
            videoElement.play();
            var canvasStream = canvas.captureStream(25);
            peerStream.srcObject = canvasStream;
            peerStream.play();
            sourceStream.onplay = function() {
                var srcstream = sourceStream.captureStream();
                for (const track of srcstream.getTracks()) {
                  peerConnection.addTrack(track);
                }           
            };
            
            
        }).catch(error =>
                function (error){
                    console.log(error);
                });

        return peerConnection;


};

var triggerMessageEvent= function(){
    var event = document.createEvent("HTMLEvents");
    event.initEvent("track", true, true);
    event.eventName = "track";
    peerConnection.dispatchEvent(event);
};

var makePeerCall= async function(peerConnection){
            updateEventTableValue2("make peer call","");
    const peerCallConnectionStream = await navigator.mediaDevices.getUserMedia({
           audio: true, 
            video: true
        }).then(function (stream) {
            
            try {
                for (const track of stream.getTracks()) {
                    try {
                          peerConnection.addTrack(track);
                      }catch (e) {
                            updateEventTableValue2("Error adding track",e);
                      }
                }
            } catch (e) {
                updateEventTableValue("Unable to get tracks", e);
                updateEventTableValue2("peerCallConnectionStream", peerCallConnectionStream);
            }
            sessions["clientView"].srcObject = stream;
            sessions["clientView"].play();
            localStream = stream;

            try {
                ws = new EventSource('/socket/'+unique);
                
                ws.send = function send(message) {
                    sendMediaStream(message);
                };

                ws.onmessage = function(e) {
                    updateEventTableValue("received server message",e);
                    onsinglemessage(ws,e);
                };

                ws.addEventListener('open', function(e) {
                    updateEventTableValue("opened ws connection",e);
                }, false);

                ws.addEventListener('error', function(e) {
                    updateEventTableValue2("opened ws connection",e);
                  if (e.readyState == EventSource.CLOSED) {
                  }
                }, false);        
                
                ws.addEventListener('message',function(e){
                    updateEventTableValue("Received web socket message event",e.data);
                    updateEventTableValue2("ws message",e);
                });                
                
            } catch(e) {
                updateEventTableValue2("Could not create eventSource ",e);
            }


            startSession();
            var stream = selfView.captureStream();
            for (const track of peerCallConnectionStream.getTracks()) {
                try {
                      peerConnection.addTrack(track);
                  } catch (e) {
                    updateEventTableValue2("Error adding track",e);
                  }
            }
			
        }).catch(function (e) {
                    updateEventTableValue2("AV error",e);
        });
		
};

var streamSyncPeer=async function(){
try {
    const userMediaStream = navigator.getUserMedia({video:true, audio:true}, function() {
    peerConnection.setRemoteDescription(new RTCSessionDescription(connectionDescription))
            .then(function(stream){
                streamPeerConnection(stream);
            })
            .then(function() {
                        return peerConnection.createAnswer();
            })
            .then(function() {
                peerConnection.createAnswer(function(answer){

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
                try {
                    for (const track of userMediaStream.getTracks()) {
                        try {
                                peerConnection.addTrack(track);
                            } catch (e) {
                            updateEventTableValue2("Error adding track",e);
                        }
                    }
                } catch (e) {
                        updateEventTableAll("userMediaStream gettracks",e,
                        "userMediaStream", userMediaStream);
                }

             }).catch(offerError);
         }, function(){
            updateEventTableValue2("Remote Description","AV Error");

         });

        } catch(e) {
            updateEventTableValue2("Sync peer stream",e);

        }                 
    
}

var streamPeerConnection=function(stream){
        remoteView.src = stream;
        remoteView.play();
        peerConnection.addStream(stream);
    
}

var screenCapture=async function(){
                    const userMediaStream = navigator.mediaDevices.getDisplayMedia({video: true})
                      .then(function(stream){
                          remoteView.srcObject=stream;
                          remoteView.play();
                          peerConnection.addStream(stream);
                      },
                            function(error){console.log(error);});    
}

var addVideoStream = function(stream){
    var videoElem = document.createElement("video");
    videoElem.setAttribute("id","peercall"+Math.floor(Math.random() * Math.floor(20)));
    videoElem.setAttribute("autoplay","true");
    conferenceroom.appendChild(videoElem);
    videoElem.srcObject = stream;
    videoElem.play();
    
    
};

var testPeerCall=async function(){
      try {
        const userMediaStream = navigator.mediaDevices.getUserMedia(constraints)
                .then(function(stream){ 
                    updateEventTableValue2("Adding stream",stream);
                    try {
                        for (const track of userMediaStream.getTracks()) {
                            try {
                                  peerConnection.addTrack(track);
                          } catch (e) {
                            updateEventTableValue2("Error adding track",e);
                          }

                        }
                    } catch (e) {
                        updateEventTableAll("userMediaStream gettracks", 
                        e,"userMediaStream:", userMediaStream);
                    }
                    addVideoStream(stream);
                    rtcOnLogin(true);})
                .catch(function(err){
                    updateEventTableAll("AV Devices not detected",err, 
                    "Streaming screen","Screen capture");
                    screenCapture();


                });
        } catch(err) {
            updateEventTableValue2("Media devices not available",err);
        }
    
}

var processServerResponse = function(response){
    updateEventTableValue("Received server response",response);
    updateEventTableValue2("Received server response",response);
    try {
        JSON.parse(response);
    } catch (e) {
        updateEventTableValue("Unable to parse response received error",e);
    }
        
};

var processServerHeaders = function(headers){
    var header={};
    var headersString  = headers.split("\n");
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

var processFormMessage = async function(message){
    var formData = new FormData();
            updateEventTableValue("Processing form message","");
        if (message.desc !== undefined && message.desc !== null){
            updateEventTableValue("Sending message description","");
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
                return ['POST','/socket/'+unique,formData, true];
            } else {
                formData.append("message",message);
                return ['POST','/topic/'+unique,formData, false];                
            }            
        } else if (message.type !== null && message.type !== undefined && 
                    message.sdp !== null && message.sdp !== undefined){
                peerRemoteDescription = message;
                formData.append("message",message);
                formData.append("sdp",message.sdp);
                formData.append("type",message.type);
                connectionDescription = message;
                handleVideoOfferMsg(message);
                localSdp = message.sdp;
                return ['POST','/socket/'+unique,formData, true];            
        } else if (message.desc === null){
                    formData.append("server-message","undefined");
                    return ['POST','/stream/video',formData, false];            
        } else if (message.message !== null && message.message !== undefined &&
                message.messagesource !== null && message.messagesource !== undefined){
                    formData.append("message",message.message);
                    formData.append("server-message",message.messagesource);
                    return ['POST','/socket/'+message.messagesource,formData, true];                        
        } else if (message !== null){
            try {
                var messageobj = JSON.parse(JSON.parse(message));
                if (messageobj.message){
                    formData.append("server-message","send");
                    formData.append("message",message.desc.sdp);
                    
                    formData.append("type",message.desc.type);
                    return ['POST','/stream/media',formData, false];
                    
                }else {
                    formData.append("server-message","send");
                    formData.append("message",message);
                    return ['POST','/stream/video',formData, false];
                    
                }
                    
            } catch (err) {
                return ['POST','/topics/1111',new FormData()];
                updateEventTableValue2("json parsing error message",message);
             }
         }
    
};

var processMessageEvent = async function(message){
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
    
    
var sendGetMessage = async function(message) {
    updateEventTableValue("send media stream",message);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
            if (this.readyState!=4) {
              return;
            }
            if (this.status != 200) {
            } else {
                processServerResponse(this.responseText);
                updateEventTableValue("sendGetMessage Response: ",this.responseText);
                processServerHeaders(xhttp.getAllResponseHeaders().toLowerCase());
            }
    };
    xhttp.open('GET', '/socket', true);
    xhttp.send();
    
};




function sendMediaStream(message) {
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
                processServerResponse(this.responseText);
                updateEventTableValue("send Media Stream Response",this.responseText);
                processServerHeaders(xhttp.getAllResponseHeaders().toLowerCase());
            }
    };
    processMessageEvent(message);
    var sendPackage=processFormMessage(message);
    if (sendPackage[3]){
        xhttp.open(sendPackage[0], sendPackage[1], true);
        xhttp.send(sendPackage[2]);            
    } else {
            updateEventTableValue("Skipping...",sendPackage[2]);
    }
}


var sendToServer = function (jsonmessage){
    updateEventTableValue("send to server",jsonmessage);
    var xhttp = new XMLHttpRequest();
    var header={};
    xhttp.onreadystatechange = function() {
            if (this.readyState!=4) {
              return;
            }
            if (this.status != 200) {
            } else {
                processServerResponse(this.responseText);
                processServerHeaders(xhttp.getAllResponseHeaders().toLowerCase());
            }
    };
    processMessageEvent(jsonmessage);

    xhttp.open('POST', '/topics/'+jsonmessage.topic, true);
    var formData = new FormData();
        formData.append("jsonmessage",jsonmessage);
    xhttp.send(formData);
    
}


var addSource = function(source){
    updateEventTableValue2("add source",source);
    switch (source) {
            case 'poll-rooms':
                    updateEventTableValue2("poll-rooms","");
                break;
            case 'connect-room':
                    updateEventTableValue2("connect-room","");
                break;
            case 'create-room':
                    updateEventTableValue2("create-room","");
                break;
            case 'room-commands':
                    updateEventTableValue2("room-commands","");
                break;
            case 'post-topic-request':
                    updateEventTableValue2("post-topic-request","");
                break;
            case 'post-video-stream':
                    updateEventTableValue2("post-video-stream","");
                break;
        
    }
    var xhttp = new XMLHttpRequest();
    var header={};
    xhttp.onreadystatechange = function() {
            if (this.readyState!=4) {
              return;
            }
            if (this.status != 200) {
            } else {
                processServerResponse(this.responseText);
                processServerHeaders(xhttp.getAllResponseHeaders().toLowerCase());
            }
    };
    xhttp.open('POST', '/topics/'+unique, true);
    xhttp.setRequestHeader("Content-Type","Application/Json");
    xhttp.send(source);
    
};



var enableServerComm = function(){
    var messagesocket = new EventSource(hosturl);
    messagesocket.send = (e) =>
    {
        updateEventTableValue2("messagesocket send",e);
        if (peerConnection != null){
             updateEventTableValue2("sending message",unique);
            var socketMessage = {
                "message": "enabling server communication",
                "messagesource": unique
            };
            ws.send(socketMessage);
            sendMediaStream(e);  
        }
    };

    messagesocket.onopen = (e) =>
    {
        if (e.target.readyState !== EventSource.OPEN) return;
        const authentication = {
            msgType : 'Authenticate',
            data : {
                token : localStorage.getItem('authToken')
            }
        }
        var socketMessage = {
            "message": "enabling server communication message socket on open",
            "messagesource": unique
        }
        if (ws==null){
            makePeerCall(peerConnection);
        } else {
            ws.send(socketMessage);
            
        }
        
    };

    messagesocket.onmessage = function(message) {
        var content, data;
        if(message.data) {
            try {
                content = JSON.parse(message.data);
                data = content[0].data;
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
            } catch(e) {
             updateEventTableAll("Error parsing ",message.data,
                                        " to JSON: ",e);
            }
        } else {
            updateEventTableValue("Message data not found", "");
        }
    

    };
    

    
};

    function streamsendname(){
        if (document.getElementById("name").value) {
            ws.send("/topics/messages/", {}, 
            JSON.stringify({'name':document.getElementById("name").value}));
        } else {
             updateEventTableValue2("Form Value Empty","Enter Receiver Name");
            
        }
    }

var enableHangUp = function(){
  var hangUpButton = document.getElementById("hangup");
  hangUpButton.disabled = false;
  
  hangUpButton.addEventListener("click",function(e){
      e.preventDefault();
      hangUpCall();
      
  });
}


function createAudioFilter(audioContext){
    var filterNode = audioContext.createBiquadFilter();
    filterNode.type = "highpass";
    filterNode.frequency.value = 1000;
    var gainNode = audioContext.createGain();
    gainNode.gain.value = 0.5;
}

function streamAudio(){
    navigator.mediaDevices.getUserMedia({audio:true}, (stream) => {
    var mediaStreamSource = audioContext.createMediaStreamSource(stream);
    mediaStreamSource.connect(filterNode);
    gainNode.connect(audioContext.destination);        
});
};

function createVideoElements(id){
    var element = document.createElement("video");
    element.setAttribute("id",id);
    element.setAttribute("autoplay","true");
    element.setAttribute("muted","muted");
    return element;
};

function createRoom(){
    conferenceroom.appendChild(sessions["clientView"]);
    conferenceroom.appendChild(sessions["remoteView"]);
};

var onsinglemessage = async function (ws, data) {
        if(data!== null && data !== undefined) {
            var datapackage;
            try {
                datapackage = JSON.parse(data);
                var data = datapackage.data;

                switch (data.event) {
                    case 'client-call':
                        clientCall(localStream);
                        break;
                    case 'client-answer':
                        clientAnswer(data);
                        break;
                    case 'client-offer':
                        clientOffer(localStream,data);
                        break;
                    case 'client-candidate':
                        clientCandidate(data);
                        break;
                }
            } catch(e){
                updateEventTableValue("Error parsing onsinglemessage data",data);
            }
        }
};


function startSession() {
    sessions["clientView"].addEventListener('loadedmetadata', 
        function () {
            publish('client-call', null)
        }
    );    
}

function clientCall(stream) {
        peerConnection.createOffer({
            offerToReceiveAudio: 1,
            offerToReceiveVideo: 1
        }).then(function (desc) {
            peerConnection.setLocalDescription(desc).then(
                function () {
                    publish('client-offer', peerConnection.localDescription);
                    ws.send(desc);
                }
            ).catch(function (e) {
             updateEventTableValue("publishing client offer error: ",e);
            });
        }).catch(function (e) {
             updateEventTableValue("client-call error: ",e);
        });
}

function clientAnswer(data){
        if (peerConnection==null) {
             updateEventTableValue("client-answer with peer connection null: ","not answering");
        return false;
    }
    peerConnection.setRemoteDescription(new RTCSessionDescription(data),
    function(){}, 
        function(e) { 
             updateEventTableValue("client-answer Error: ",e);
    });
    return true;
}

function clientOffer(localStream,data){
    updateEventTableValue("client offer", localStream, data);
    publishicecandidate(localStream);
    if (data !== undefined){
        try {
            peerConnection.setRemoteDescription(new RTCSessionDescription(data), function(){
                if (!answer) {
                    peerConnection.createAnswer(function (desc) {
                            peerConnection.setLocalDescription(desc, function () {
                                publish('client-answer', peerConnection.localDescription);
                                ws.send(desc);
                            }, function(e){
                             updateEventTableValue2("Client answer error: ",e);
                            });
                        }
                    ,function(e){
                             updateEventTableValue2("Client offer error: ",e);
                    });
                    answer = 1;
                }
            }, function(e){
                    updateEventTableValue2("Client offer error: ",e);
            });  
        } catch (error) {
            updateEventTableValue("Failure constructing description with", data + error);
        }
    } else {
            updateEventTableValue("received data for client offer",data);
    }
}

function clientCandidate(data){
            updateEventTableValue("Client Candidate: ",data);
    if (peerConnection==null) {
    return false;
     }
    peerConnection.addIceCandidate(new RTCIceCandidate(data), function(){}, 
        function(e) { 
            updateEventTableValue("Problem adding ice candidate: ",e);
            
   });
    return true;
}


function publishicecandidate(ws, stream) {
    peerConnection.onicecandidate = function (event) {
        if (event.candidate) {
            publish('client-candidate', event.candidate);
        }
    publish('client-candidate', event.candidate);
    };
    try {
        if (stream.type == "video"){
            peerConnection.addStream(stream);
        } else {
            updateEventTableValue("stream is of type ",stream.type);
        }
    }catch(e){
        updateEventTableValue("Error adding stream", e);
        try {
            if (stream !== undefined) {
                var tracks = stream.getTracks();
                for(var i=0;i<tracks.length;i++){
                    peerConnection.addTrack(tracks[i], stream);
                }
            } else {
                updateEventTableValue("stream is undefined", stream);
            }
        } catch (e) {
            updateEventTableValue("stream",stream);
                  updateEventTableValue2("Error adding track",e);
        }
    }
    
    
    peerConnection.ontrack = function (e) {
        sessions["remote"].style.display="block";
        sessions["local"].style.display="none";
        sessions["remote"].srcObject = e.streams[0];
        sessions["remote"].play();
        var videoPeer = document.createElement("video");
        peerStream.appendChild(videoPeer);
        videoPeer.srcObject = e.streams[0];
    };
}

function publish(event, data) {
    
    if (ws !== null && ws !== undefined){
        ws.send({
            'event':event,
            'data':data
            });
        
    } else {
            updateEventTableValue("Event source not defined sending ", event);
    }
};


function send(message){
    sendMediaStream(message);
};

async function startAsynchronousCall() {
            updateEventTableValue("Starting Asynchronous Call","");
    if (serverRemoteDescription !== null) {
            updateEventTableValue("serverRemoteDescription",serverRemoteDescription);
            captureStream();
        
    } else {
        updateEventTableValue("serverRemoteDescription is null","");

    }
};

async function captureStream(){
        await peerConnection.setRemoteDescription(serverRemoteDescription);
        try {
            sessions["clientView"].srcObject = captureLocalVideo();
            sessions["clientView"].play();
            if (isCaller) {
                updateEventTableValue("Is Caller connection","");
                peerConnection.createOffer(receivedDescription);
            } else {
                updateEventTableValue("Is Remote connection","");
                updateEventTableValue("peerRemoteDescription",peerRemoteDescription);
                if (peerRemoteDescription !== null ){
                updateEventTableValue("Get Peer Remote Description", getPeerRemoteDescription);
                    peerConnection.createAnswer(getPeerRemoteDescription,receivedDescription);
                } else {
                    peerConnection.createAnswer(peerConnection.remoteDescription,receivedDescription);
                }
            }
        } catch (err) {
                updateEventTableValue("Start Asynchronous Call Error",err);
        }

    function receivedDescription(desc){
        updateEventTableValue("Received description", desc);
        peerConnection.setLocalDescription(desc);
        ws.send({"sdp":localSdp,"channel":"channel1"});

    }
    
}

async function captureLocalVideo(){
            var stream = await navigator.mediaDevices.getUserMedia(constraints)
                .catch(function(error){updateEventTableValue(
                    "Error with media devices", error);});
        
            try {
                stream.getTracks().forEach((track) => function()
                {
                    try {
                        peerConnection.addTrack(track,stream);
                        } catch (e) {
                          updateEventTableValue("Error adding track", e);
                      }

                    });
                    return stream;
            } catch (e) {
                updateEventTableAll("stream gettracks", e,
                            "stream", stream);

            }
}

function setAsynchronousMessage(ws){
        updateEventTableValue("Set Asynchronous Message", "");
        ws.onmessage = async ({desc,candidate}) => {
            updateEventTableValue("ws onmessage desc",desc);
            updateEventTableValue("ws onmessage", "");
            updateEventTableValue("ws onmessage",candidate);
            serverRemoteDescription = desc;
            try {
                if (desc) {
                    if (desc.type === 'offer') {
                        await peerConnection.setRemoteDescription(desc);
                        const stream = captureLocalVideo()
                            .catch(function(error){
                                updateEventTableValue("Error with media devices", error);}
                            );
                        await peerConnection.setLocalDescription(await peerConnection.createAnswer());
                        ws.send({desc: peerConnection.localDescription});
                    } else if (desc.type === 'answer') {
                        await peerConnection.setRemoteDescription(desc);
                    } else {
                        updateEventTableValue("Unsupported SDP type", "");
                    }
                } else if (candidate) {
                    await peerConnection.addIceCandidate(candidate);
                }
              } catch (err) {
                    updateEventTableValue("ws onmessage", "");
                    updateEventTableValue2("Set Asynchronous Message Error", err);
              }
            };
};

function createStream(stream){
    updateEventTableValue2("Create Stream",stream);
    //navigator.mediaDevices.getUserMedia(constraints);
    var streamVideo = document.createElement("video");
    streamVideo.setAttribute("autoplay","true");
    streamVideo.setAttribute("muted","muted");
    streamVideo.setAttribute("id",Math.floor(20 + Math.random() * 99));
    conferenceroom.appendChild(streamVideo);
    updateEventTableValue2("Creating stream",stream);
    streamVideo.srcObject = stream;
    streamVideo.play();
    peerConnection.addStream(stream);
    streamVideo.addEventListener('loadedmetadata', ()=>{
        updateEventTableValue("playing video from metadata","");
        var videoPromise = streamVideo.play();
        if (videoPromise !== undefined) {
            videoPromise.then(promise => {
            updateEventTableValue2("play started", promise);
            }).catch(error => {
            updateEventTableValue2("play start error",error);
            });
        }
    });
}

function offerError(error){
    updateEventTableValue2("Offer error",error);

}
/*
function handleOffer(offer){
    console.log("Handle Offer",offer);
}


function handleAnswer(answer){
    console.log("handleAnswer",answer);
    
    if (sessconfig != null) {
        peerConnection.setRemoteDescription(new RTCSessionDescription(sessconfig));
    } else {
        console.log("Session Configuration is null");
    }
}

function handleCandidate(candidate){
    console.log("handleCandidate",candidate);
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
}*/


var streamshowgreetings = function (message){
            updateEventTableValue("stream show greetings",message);
    var greetings = document.getElementById("greetings");
    var newMessage = document.createElement("span");
    newMessage.appendChild(message);
    greetings.appendChild(newMessage);
};


var addStream = function(stream) {
  stream.getTracks().forEach(function(track) {
      try {
    peerConnection.addTrack(track, stream);
                  } catch (e) {
                      updateEventTableValue("Error adding track", e);
                  }
      
  });
};

function setstreamconnected(connected) {
            updateEventTableValue("set stream connected",connected);
    var connect = document.getElementById("connect");
    var disconnect = document.getElementById("disconnect");
    var conversation = document.getElementById("conversation");
    var greetingselement = document.getElementById("greetings");
    connect.setAttribute("disabled",connected);
    disconnect.setAttribute("disabled",!connected);
    if (connected) {
        conversation.style.display = "block";
    } else {
        conversation.style.display = "none";
        
    }
    greetingselement.innerHTML="";
    
}

function streamconnect(openMediaDevices) {
    updateEventTableValue("stream connect","");
    var pc = new RTCPeerConnection(configuration);
    var dc = pc.createDataChannel("channel1");
    var streamsocket = new EventSource("/topics/messages");
    dc.onopen = (e) => {
        dc.send("Channel 1 open");
    };
    dc.onmessage = (e) => {
        updateEventTableValue("data channel message",e.data);
    };
    pc.ondatachannel = (e) => {
        var channel = e.channel;
        channel.onopen = (e) => {
            channel.send('channel response');
        };
        channel.onmessage = (e) => {
            updateEventTableValue("channel data",e.data);
        };
    };
    
    dc.ondatachannel = (e) => {
        if (dc.readyState == "connecting"){
            updateEventTableValue2("data channel connecting...");
        } else {
            
            updateEventTableValue2("data channel on", e);
            dc.onmessage = function (e){
                let obj = {
                  "message": e,
                  "timestamp": new Date()
                }
                dataChannel.send(JSON.stringify(obj));
            }
        }
    }

    streamsocket.onopen = async (e) =>
    {
        try {
            const userMediaStream = await navigator.mediaDevices.getUserMedia(constraints)
                .then(function(stream){
                
                    var selfStream = stream;
                    selfView.src = stream;
                    selfView.play();
                    //remoteStream = remoteView.srcObject;
                    selfStream.getTracks().forEach(
                            track => peerConnection.addTrack(
                            track,
                            userMediaStream)
                    );
                    peerConnection.addStream(userMediaStream);
                    rtcremote.srcObject = localStream;
                    rtcremote.play();
                    localStream.getTracks().forEach(track => 
                    function(){
                        try {
                            peerConnection.addTrack(track
                    , localStream);
                                      } catch (e) {
                              updateEventTableValue2("Error adding track", e);
                          }
                          });

                
                        
                    })
                .catch(function(error){updateEventTableValue2("Error with media devices", error);});

            var socketMessage = {
                "message": "streamsocket onopen",
                "messagesource": unique
                }

            ws.send(socketMessage);
            
            streamshowgreetings(JSON.parse(e.target).content);
        } catch(error) {
            updateEventTableValue2("Stream socket  error",error);
        }
    };
    
    streamsocket.addEventListener("message", function(event){
            updateEventTableValue2("stream socket message", event);
    });

    streamsocket.addEventListener("error", function(error){
            updateEventTableValue2("stream socket error", error);
    });

}

function streamdisconnect() {
    updateEventTableValue2("stream disconnect","");
    var peersocket = new EventSource('/topic/messages/disconnect');
    peersocket.onopen = (e) =>
    {
        if (e.target.readyState !== EventSource.OPEN) return;
            var socketMessage = {
                "message": "stream disconnect",
                "messagesource": unique
                }
        
    e.target.send(socketMessage);
    };
    setConnected(false);
}


function rtcOnLogin(success) {
    updateEventTableAll("RTC on login...",success,
                             "rtc on login", success);
    if (success === false) {
    } else {
        var streamconfiguration = {
            "iceServers" : [{"url": "stun:stun.1.google.com:19302"}]
        };
        rtcpeerconnection = new webkitRTCPeerConnection(streamconfiguration);
        setRtcMessage();
        rtcpeerconnection.onicecandidate = function (event) {
            if (event.candidate) {
                rtcsend({
                    type: "candidate",
                    candidate: event.candidate
                });
            }
        };
    }
};


function setRtcMessage(){
    updateEventTableValue2("set rtc message","");
    rtcpeerconnection.onmessage = function(message) {
            try {

                var data = JSON.parse(message.data);

                switch (data.type) {
                    case "login":
                        rtcOnLogin(data.success);
                        break;
                    case "offer":
                        rtcOnOffer(data.offer, data.name);
                        break;
                    case "answer":
                        rtcOnAnswer(data.answer);
                        break;
                    case "candidate":
                        rtcOnCandidate(data.candidate);
                        break;
                    default:
                        break;
                }
            } catch(e){
                updateEventTableValue2("Set RTC Message",e);
            }
    };

    rtcpeerconnection.onopen = function () {
        };

    rtcpeerconnection.onerror = function(err) {
    }

};




function rtcsend(message){
    updateEventTableValue2("rtc send",message);
    ws.send(message);
};

function rtcOnOffer(offer,name){
    updateEventTableAll("rtc on offer ",offer,"name: ",name);
    rtcpeerconnection.setRemoteDescription(new RTCSessionDescription(offer));
    rtcpeerconnection.createAnswer(function (answer){
        rtcpeerconnection.setLocalDescription(answer);
        
        rtcsend({
            type: "answer",
            answer: answer
        });
    }, function (error){
        updateEventTableValue2("on offer rtc error", error);
    
    });
};

function rtcOnAnswer(answer){
    updateEventTableValue2("rtc on answer",answer);
    rtcpeerconnection.setRemoteDescription(new RTCSessionDescription(answer));
};

function rtcOnCandidate(candidate) {
    updateEventTableValue2("rtc on candidate",candidate);
    rtcpeerconnection.addIceCandidate(new RTCIceCandidate(candidate));
};


var addTopic =function (source){
    var textNode = document.createTextNode("; new source: " + source);
    greetings.appendChild(textNode);    
};

var createServerConnection = function(){
    if (ws==null){
        ws = new EventSource('/socket/'+unique);

        ws.addEventListener("message", (event) => {
           updateEventTableValue2("message",event.data); 
        });

        ws.addEventListener("open", (event) => {
           updateEventTableValue2('connection is live','');
        });

        ws.addEventListener("error", (event) => {
           if (event.readyState == EventSource.CLOSED) {
              updateEventTableValue2('connection is closed','');
           } else {
              updateEventTableValue2("Error occured", event);
           }
           event.target.close();
        });

        ws.send = function (message){
            updateEventTableValue2("ws send", message);
            sendMediaStream(message);
        };
    } else {
        updateEventTableValue2("Web socket connection defined","");
    }
};

var testEventSourceSend = function (event,data){
    ws.send({
        event:event,
        data:data
    });
};

var sendToOneUser = function (target, msgString) {
  var isUnique = true;
  var i;

  if (msg.target && msg.target.length !== 0) {
    sendToOneUser(msg.target, msgString);
  } else {
    for (i=0; i < connectionArray.length; i++) {
      if (connectionArray[i].username === target) {
        connectionArray[i].send(msgString);
        break;
      }
    }
  }
}

var messageToServer = function (message) {
    updateEventTableValue2("message to server ",message);
    ws.send(message);
}



var handleVideoOfferMsg = function (message) {
    updateEventTableValue2("HandleVideoOfferMessage", message);
    var localStream = null;
    myUsername = "['attendee':'1']";
    targetUsername = "['room':'5555']";
    if (message !== null && message.sdp !== null && message.sdp !== undefined) {
        var desc = new RTCSessionDescription(message);

        peerConnection.setRemoteDescription(desc).then(function () {
            return navigator.mediaDevices.getUserMedia(constraints)
                .catch(function(error){
                    updateEventTableValue("Error with media devices", error);}
                );
        ws.send({desc: peerConnection.localDescription});
        })
        .then(function(stream) {
            var remoteStream = stream;
            remoteView.src = stream; 
            remoteView.play();
            remoteStream = remoteView.srcObject;
            remoteStream.getTracks().forEach(track => peerConnection.addTrack(track, stream));
            peerConnection.addStream(stream);
            rtcremote.srcObject = localStream;
            rtcremote.play();
            localStream.getTracks().forEach(track => 
            function(){
                try {
                    peerConnection.addTrack(track
            , localStream);
                              } catch (e) {
                      updateEventTableValue("Error adding track", e);
                  }
                  });

        })
        .then(function() {
          return peerConnection.createAnswer();
        })
        .then(function(answer) {
          return peerConnection.setLocalDescription(answer);
        })
        .then(function() {
          var message = {
            name: myUsername,
            target: targetUsername,
            type: "video-answer",
            sdp: peerConnection.localDescription,
            topic: "video-offer-msg"
          };
          sendToServer(message);
        })
        .catch(handleGetUserMediaError);
    } else if (message.message !== null && message.message !== undefined){
        updateEventTableValue2("video offer message is ",message.message);
    } else {
        updateEventTableValue2("message is null ",message);
        
    }
}

var handleGetUserMediaError = function (error){
    updateEventTableValue2("user media error", error);
}

var hangUpCall = function() {
  closeVideoCall();

  sendToServer({
    name: myUsername,
    target: targetUsername,
    type: "hang-up"
  });
}

function closeVideoCall() {
  var remoteVideo = document.getElementById("received_video");
  var localVideo = document.getElementById("local_video");

  if (peerConnection!= null) {
    peerConnection.ontrack = null;
    peerConnection.onremovetrack = null;
    peerConnection.onremovestream = null;
    peerConnection.onicecandidate = null;
    peerConnection.oniceconnectionstatechange = null;
    peerConnection.onsignalingstatechange = null;
    peerConnection.onicegatheringstatechange = null;
    peerConnection.onnegotiationneeded = null;

    if (remoteVideo.srcObject) {
      remoteVideo.srcObject.getTracks().forEach(track => track.stop());
    }

    if (localVideo.srcObject) {
      localVideo.srcObject.getTracks().forEach(track => track.stop());
    }

    peerConnection.close();
    peerConnection = null;
  }

  remoteVideo.removeAttribute("src");
  remoteVideo.removeAttribute("srcObject");
  localVideo.removeAttribute("src");
  remoteVideo.removeAttribute("srcObject");

  document.getElementById("hangup-button").disabled = true;
  targetUsername = null;
      clearInterval(canvasInterval);

}

function sendSignal(signal) {
    updateEventTableValue2("sending signal",signal);
    if (ws.readyState > 0) {
        ws.send(signal);
    } else {
        updateEventTableValue2("signalling state not ready","");
    }
};
 

async function displayLocalStreamAndSignal(firstTime) {
    updateEventTableValue2('Requesting local stream','');
    const localVideo = document.getElementById('localVideo');
    let localStream;
    try {
        const userMediaStream = await navigator.mediaDevices.getDisplayMedia({
            audio: true,
            video: true
        })
            .catch(function(error){
                updateEventTableValue("Error with media devices", error);
        });
        updateEventTableValue2('Received local stream','');
        localVideo.srcObject = userMediaStream;
        localVideo.play();
        localStream = userMediaStream;
        logVideoAudioTrackInfo(localStream);
        if (firstTime) {
            setTimeout(
                function() {
                    addLocalStreamToPeerConnection(localStream);
                }, 2000);
        }
        sendOfferSignal();
 
    } catch (e) {
        updateEventTableValue2('getUserMedia() error:', e);
        throw e;
    }
    updateEventTableValue2('Start complete','');
};


         
async function addLocalStreamToPeerConnection(localStream) {
    updateEventTableValue2('Starting addLocalStreamToPeerConnection','');
    localStream.getTracks().forEach(track => 

        function() {
    try {
        peerConnection.addTrack(track
    , localStream);
    }catch(e) {
        updateEventTableValue2("Error adding track",e);
    }
    });
    
    updateEventTableValue2('localStream tracks added','');
};


function sendOfferSignal() {
    peerConnection.createOffer(function(offer) {
        sendSignal(offer);
        peerConnection.setLocalDescription(offer);
    }, function(error) {
        updateEventTableValue2("Error creating an offer","");
    });
};

function handleOffer(offer) {
    updateEventTableValue2("Handle Offer", offer);
    if (offer.type !== undefined && offer.type=="offer"){
        peerConnection
            .setRemoteDescription(new RTCSessionDescription(offer));

        // create and send an answer to an offer
        updateEventTableValue2("offer answered with response",answer);
        if (answer !== null && answer.RTCSdpType !== null){
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


