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



var newConnection = null;
var isCaller = false;

var answer=0,localStream=null,ws=null, serverResponse=null,
    rtcpeerconnection=null, hosturl="/socket";

var videoElement = document.getElementById("liveStream");

var conferenceroomButton = document.getElementById("startcall");
var testcallButton = document.getElementById("testcall");
var testconnectionButton = document.getElementById("testconnection");
var conferenceroom = document.getElementById("conferenceroom");
var audioonlyButton = document.getElementById("audioonly");
var audiofilterButton = document.getElementById("audiofilter");

var unique = Math.floor(100000 + Math.random() * 999);


var loginInput = document.getElementById("rtcconnectname");
var loginButton = document.getElementById("rtcconnectbutton");

var rtcform = document.getElementById("rtcform");
var rtcsendbutton = document.getElementById("rtcsend");

var unique = 5555;
var myUsername = "testMyUser";
var targetUsername = "testTarget";

const buffer = new ArrayBuffer(1024);
var view;

var canvas=document.getElementById("canvas");
var context=canvas.getContext("2d");
var canvasInterval;

var sessconfig = null,sessPoll=null;

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



sessPoll = setInterval(isSessConfig,500);
function isSessConfig(){
        if(sessconfig == null){
            return false;
        } else {
            clearInterval(sessPoll);
            return true;
        }
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


let audioContext;


var sessions = {"localSession":createVideoElements("localVideo"),
                "remoteSession":createVideoElements("remoteVideo")};

var selfView = sessions["localSession"];
var remoteView = sessions["remoteSession"];
var peerConnection = null;
var dataChannel = null;


testcallButton.addEventListener("click",function(e){
   e.preventDefault();
   createPeerConnection();
    navigator.mediaDevices.getUserMedia(constraints)
            .then(function(stream){ addStream(stream);
    rtcOnLogin(true);})
            .catch(function(err){console.log(err)});

   
});

testconnectionButton.addEventListener("click",function(e){
   e.preventDefault();
   rtcOnLogin(true);
   createCallSession();
   startAsynchronousCall();
   
   
});


conferenceroomButton.addEventListener("click",function(e){
    e.preventDefault();
    createRoom();
    localCall;
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
        console.log("Audio not supported");
    }
});

var connectform = document.getElementById("connectform");
connectform.addEventListener("submit", 
	function(e){
		e.preventDefault(); 
                    testEventSourceSend("client-start","1");
                createVideoElement();
                localCall;
                enableHangUp();

   
        });
var sendmessage = document.getElementById("sendmessage");
sendmessage.addEventListener("submit", 
	function(e){
		e.preventDefault(); 
                localCall;
        });
      
var connect = document.getElementById("connect");
connect.addEventListener("click", 
	function(e){
		e.preventDefault();
                streamconnect();
   
        });
var disconnect = document.getElementById("disconnect");
disconnect.addEventListener("click", 
	function(e){
		e.preventDefault();
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
        console.log("rtcsenderror",error);
    });
    
    });    
var newConnection = null;


var conferenceroom = document.getElementById("conferenceroom");
var element = document.createElement("video");
element.setAttribute("id","videoElement");
element.setAttribute("autoplay","true");
element.setAttribute("muted","muted");

function createVideoElement(){
    conferenceroom.appendChild(element);
}

var createPeerConnection = function (){
    peerConnection = new RTCPeerConnection(configuration,{
                optional : [{
                RtpDataChannels : true
        }]
    });

    peerConnection.onicecandidate = ({candidate}) => function(event) {   
        ws.send({candidate});
        ws.send(JSON.stringify({"candidate": event.candidate}));
    };
    peerConnection.onaddstream = function(event) {
        remoteView.src = URL.createObjectURL(event.stream);
    };

    peerConnection.onnegotiationneeded = async () => {
        try {
            await peerConnection.setLocalDescription(await peerConnection.createOffer());
            ws.send({desc: peerConnection.localDescription});

        } catch (err) {
            console.log(err);
        }
    };

    peerConnection.ontrack = (event) => {
        console.log("on track", event);
        if (remoteView.srcObject) return;
        remoteView.srcObject = event.streams[0];
    };

    peerConnection.onmessage = function(message) {
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
    };

    dataChannel = peerConnection.createDataChannel("dataChannel",
            {reliable:true}
    );

    dataChannel.onerror = function(error){
        console.log("Error", error);   
    };

    dataChannel.onmessage = function(event) {
      console.log("message:",event.data);  
    };

    dataChannel.onclose = function() {
        console.log("Data channel is closed");
    };



};

var createPeerConnection2 = function (){

    peerConnection.setRemoteDescription(new RTCSessionDescription(peerConfiguration))
            .then(function(stream){
                createStream(stream);
            })
            .then(function() {
                        return peerConnection.createAnswer();
            }).then(function() {
                peerConnection.createAnswer(function(answer){
                    send({
                    event: "answer",
                    data : answer});
                    })
             }).then(function(){
                 console.log("Established connection");

             }).catch(offerError);
};


var localCall = function(){
                testEventSourceSend("client-connect","1");
                createTestCanvasAnimation();
                var cStream;
                navigator.mediaDevices.getUserMedia({
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
                    element.srcObject = stream;
                    cStream = stream;
                }).catch(error =>
                        function (error){
                            console.log(error);
                        });
                element.srcObject = cStream;
                var canvasStream = canvas.captureStream(25);
                element.srcObject = canvasStream;
                selfView.onplay = function() {
                    var stream = selfView.captureStream();
                    remoteView.srcObject = stream;
                };
                startSession();
                enableHangUp();
    
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
    const mediaStreamSOurce = audioContext.createMediaStreamSource(stream);
    mediaStreamSource.connect(filterNode);
    gainNode.connect(audioContext.destination);        
});
}


function createVideoElements(id){
    var element = document.createElement("video");
    element.setAttribute("id",id);
    element.setAttribute("autoplay","true");
    element.setAttribute("muted","muted");
    return element;
}

function createRoom(){
    conferenceroom.appendChild(sessions["localSession"]);
    conferenceroom.appendChild(sessions["remoteSession"]);
}

function createCallSession(){
    navigator.mediaDevices.getUserMedia({
           audio: true, 
            video: true
        }).then(function (stream) {
            
            sessions["localSession"].srcObject = stream;
            localStream = stream;

            try {
                ws = new EventSource('/socket/'+unique);
            } catch(e) {
                console.error("Could not create eventSource ",e);
            }
                ws.send = function send(message) {
                    sendMediaStream(message);
                }

                ws.onmessage = function(e) {
                    sendMultipleMessageStreams(ws,e);
                }

            startSession();
			
        }).catch(function (e) {
            console.log("AV error",e);
        });
		
                }
                
function onsinglemessage(ws, data) {
    var package = JSON.parse(data);
    var data = package.data;

    switch (package.event) {
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
};


function startSession() {
    sessions["localSession"].addEventListener('loadedmetadata', 
        function () {
            publish(ws,'client-call', null)
        }
    );    
}

function clientCall(stream) {
        icecandidate(stream);
        peerConnection.createOffer({
            offerToReceiveAudio: 1,
            offerToReceiveVideo: 1
        }).then(function (desc) {
            peerConnection.setLocalDescription(desc).then(
                function () {
                    publish('client-offer', peerConnection.localDescription);
                }
            ).catch(function (e) {
                console.log("publishing client offer error: "+e);
            });
        }).catch(function (e) {
            console.log("client-call error: "+e);
        });
}

function clientAnswer(data){
        if (peerConnection==null) {
        return false;
    }
    peerConnection.setRemoteDescription(new RTCSessionDescription(data),
    function(){}, 
        function(e) { console.log("client-answer Error: ",e);
    });
    return true;
}

function clientOffer(localStream,data){
    publishicecandidate(localStream);
    peerConnection.setRemoteDescription(new RTCSessionDescription(data), function(){
        if (!answer) {
            peerConnection.createAnswer(function (desc) {
                    peerConnection.setLocalDescription(desc, function () {
                        publish('client-answer', peerConnection.localDescription);
                    }, function(e){
                        console.log("Client answer error: ",e);
                    });
                }
            ,function(e){
                console.log("Client offer error: ",e);
            });
            answer = 1;
        }
    }, function(e){
        console.log("Client offer error: ",e);
    });    
}

function clientCandidate(data){
    if (peerConnection==null) {
    return false;
     }
    peerConnection.addIceCandidate(new RTCIceCandidate(data), function(){}, 
        function(e) { console.log("Problem adding ice candidate: "+e
   );});
    return true;
}

function sendMultipleMesageStreams(ws,event) {
    if (event.data.includes("_MULTIPLEVENTS_")) {
            var multiple = event.data.split("_MULTIPLEVENTS_");
            for (var x=0; x<multiple.length; x++) {
                    onsinglemessage(multiple[x]);
            }
    } else {
            onsinglemessage(ws,event.data);
    }
    
}

function publishicecandidate(ws, localStream) {
    peerConnection = new RTCPeerConnection(configuration);
    peerConnection.onicecandidate = function (event) {
        if (event.candidate) {
            publish(ws, 'client-candidate', event.candidate);
        }
    };
    try {
        peerConnection.addStream(localStream);
    }catch(e){
        var tracks = localStream.getTracks();
        for(var i=0;i<tracks.length;i++){
            peerConnection.addTrack(tracks[i], localStream);
        }
    }
    peerConnection.ontrack = function (e) {
        sessions["remote"].style.display="block";
        sessions["local"].style.display="none";
        sessions["remote"].srcObject = e.streams[0];
    };
}

function publish(ws, event, data) {
    ws.send(JSON.stringify({
        event:event,
        data:data
    }));
}


function send(message){
    ws.send(JSON.stringify(message));
}

async function startAsynchronousCall() {
    try {
        const stream = await navigator.mediaDevices.getUserMedia(constraints);
        stream.getTracks().forEach((track) =>
                peerConnection.addTrack(track,stream));
        sessions["localSession"].srcObject = stream;
        if (isCaller) {
            peerConnection.createOffer(receivedDescription);
        } else {
            peerConnection.createAnswer(peerConnection.remoteDescription,receivedDescription);
            
        }
        function receivedDescription(desc){
            peerConnection.setLocalDescription(desc);
            ws.send(JSON.stringify({"sdp":desc}));
            
        }
    } catch (err) {
        console.log(err);
    }
};

function setAsynchronousMessage(ws){
ws.onmessage = async ({desc,candidate}) => {
    try {
        if (desc) {
            if (desc.type === 'offer') {
                await peerConnection.setRemoteDescription(desc);
                const stream = await navigator.mediaDevices.getUserMedia(constraints);
                stream.getTracks().forEach((track) => 
                        peerConnection.addTrack(track, stream));
                await peerConnection.setLocalDescription(await peerConnection.createAnswer());
                ws.send({desc: peerConnection.localDescription});
            } else if (desc.type === 'answer') {
                await peerConnection.setRemoteDescription(desc);
            } else {
                console.log('Unsupported SDP type.');
            }
        } else if (candidate) {
            await peerConnection.addIceCandidate(candidate);
        }
      } catch (err) {
          console.log(err);
      }
    };
    }

function createStream(stream){
    navigator.mediaDevices.getUserMedia(constraints);
    var streamcanvas = document.getElementById("srcStream");
    var streamVideo = document.createElement("video");
    streamcanvas.appendChild(streamVideo);
    streamVideo.srcObject = stream;
    peerConnection.addStream(stream);
    streamVideo.addEventListener('loadedmetadata', ()=>{
        var videoPromise = streamVideo.play();
        if (videoPromise !== undefined) {
            videoPromise.then(promise => {
                console.log("play started", promise);
            }).catch(error => {
               console.log("play start error",error);
            });
        }
    });
}

var peerConfiguration = { 
   "iceServers": [{ "url": "stun:stun.1.google.com:19302" }] 
};     

function offerError(error){
}
function handleOffer(offer){
}


function handleAnswer(answer){
    
    if (sessconfig != null) {
        peerConnection.setRemoteDescription(new RTCSessionDescription(sessconfig));
    } else {

    }
}

function handleCandidate(candidate){
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
}




var messagesocket = new EventSource(hosturl);
messagesocket.send = (e) =>
{
    ws.send(JSON.stringify('[{"message":"test"}]'));
    //createOffer();
    sendMediaStream(e);    
}

messagesocket.onopen = (e) =>
{
    if (e.target.readyState !== EventSource.OPEN) return;
    const authentication = {
        msgType : 'Authenticate',
        data : {
            token : localStorage.getItem('authToken')
        }
    }
    var newMessage = JSON.stringify('[{"message":"test"}]');
    ws.send(newMessage);
};

messagesocket.onmessage = function(message) {
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
};

function streamsendname(){
    messagesocket.send("/topics/messages/", {}, 
    JSON.stringify({'name':document.getElementById("name").value}));
}

function streamshowgreetings(message){
    var greetings = document.getElementById("greetings");
    var newMessage = document.createElement("span");
    newMessage.appendChild(message);
    greetings.appendChild(newMessage);
}


function addStream(stream) {
    peerConnection.addStream(stream);
    peerConnection.onaddstream = function(event) {
        videoElement.srcObject = event.stream;
    }
}


function setstreamconnected(connected) {
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

function streamconnect() {
    peerConnection = new RTCPeerConnection(configuration);
    var dataChannel = peerConnection.createDataChannel("channel1");
    var streamsocket = new EventSource("/topics/messages");
    var openMediaDevices = navigator.mediaDevices.getUserMedia(constraints);
    dataChannel.onopen = (e) => {
        dataChannel.send("Channel 1 open");
    };
    dataChannel.onmessage = (e) => {
        console.log("data channel message"+e.data);
    };
    peerConnection.ondatachannel = (e) => {
        var channel = e.channel;
        channel.onopen = (e) => {
            channel.send('channel response');
        };
        channel.onmessage = (e) => {
            console.log('channel data'+e.data);
        };
    };
    
    dataChannel.ondatachannel = (e) => {
        if (dataChannel.readyState == "connecting"){

        } else {
        dataChannel.send("message");
            dataChannel.onmessage = function (event){
            }
        }
    }

    streamsocket.onopen = (e) =>
    {
        try {
            const stream = openMediaDevices({'video':true, 'audio':true});
            streamsocket.send(JSON.stringify('{"test":"test"}'));
            streamshowgreetings(JSON.parse(e.target).content);
        } catch(error) {
        }
    };
}

function streamdisconnect() {
    var peersocket = new EventSource('/topic/messages/disconnect');
    peersocket.onopen = (e) =>
    {
        if (e.target.readyState !== EventSource.OPEN) return;
    e.target.send(JSON.stringify('{"message":"test"}'));
    };
    setConnected(false);
}


function rtcOnLogin(success) {
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
    rtcpeerconnection.onmessage = function(message) {
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
    };

    rtcpeerconnection.onopen = function () {
        };

    rtcpeerconnection.onerror = function(err) {
    }

}


function rtcsend(message){
    ws.send(JSON.stringify(message));
}

function rtcOnOffer(offer,name){
    connectedUser = name;
    rtcpeerconnection.setRemoteDescription(new RTCSessionDescription(offer));
    rtcpeerconnection.createAnswer(function (answer){
        rtcpeerconnection.setLocalDescription(answer);
        
        rtcsend({
            type: "answer",
            answer: answer
        });
    }, function (error){
        console.log("on offer rtc error", error);
    
    });
}

function rtcOnAnswer(answer){
    rtcpeerconnection.setRemoteDescription(new RTCSessionDescription(answer));
}

function rtcOnCandidate(candidate) {
    rtcpeerconnection.addIceCandidate(new RTCIceCandidate(candidate));
}

function sendMediaStream(message) {
    var xhttp = new XMLHttpRequest();
    var header={};
    xhttp.onreadystatechange = function() {
            if (this.readyState!=4) {
              return;
            }
            if (this.status != 200) {
            } else {
                view = this.responseText;
                var headers = xhttp.getAllResponseHeaders().toLowerCase();
                var headersString  = headers.split("\n");
                for (var i=0;i<headersString.length;i++){
                    var key = headersString[i].split(":")[0];
                    var value = headersString[i].split(":")[1];
                    if (value !== undefined){
                        header[key]=value;
                    }
                }
                if (header["custom-event-source"]!==undefined){
                    addSource(header["custom-event-source"]);            
                }
            }
    };
    xhttp.open('POST', '/socket/'+unique, true);
    var formData = new FormData();
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

        formData.append("message",message);
        
    xhttp.send(formData);
}

var sendToServer = function (jsonmessage){
    var xhttp = new XMLHttpRequest();
    var header={};
    xhttp.onreadystatechange = function() {
            if (this.readyState!=4) {
              return;
            }
            if (this.status != 200) {
            } else {
                view = this.responseText;
                var headers = xhttp.getAllResponseHeaders().toLowerCase();
                var headersString  = headers.split("\n");
                for (var i=0;i<headersString.length;i++){
                    var key = headersString[i].split(":")[0];
                    var value = headersString[i].split(":")[1];
                    if (value !== undefined){
                        header[key]=value;
                    }
                }
                if (header["custom-event-source"]!==undefined){
                    addSource(header["custom-event-source"]);            
                }
            }
    };
        switch (jsonmessage.event) {
            case 'client-call':
                clientCall(localStream);
                break;
            case 'client-answer':
                clientAnswer(jsonmessage.data);
                break;
            case 'client-offer':
                clientOffer(localStream,jsonmessage.data);
                break;
            case 'client-candidate':
                clientCandidate(jsonmessage.data);
                break;
        }

    xhttp.open('POST', '/topic/'+jsonmessage.topic, true);
    var formData = new FormData();

        formData.append("jsonmessage",jsonmessage)
        
    xhttp.send(formData);
    
}

var addSource = function(source){
    var xhttp = new XMLHttpRequest();
    var header={};
    xhttp.onreadystatechange = function() {
            if (this.readyState!=4) {
              return;
            }
            if (this.status != 200) {
            } else {
                view = this.responseText;
                var headers = xhttp.getAllResponseHeaders().toLowerCase();
                var headersString  = headers.split("\n");
                for (var i=0;i<headersString.length;i++){
                    var key = headersString[i].split(":")[0];
                    var value = headersString[i].split(":")[1];
                    if (value !== undefined){
                        header[key]=value;
                    }
                }
                if (header["custom-event-source"]!==undefined){
                    addSource(header["custom-event-source"]);            
                }
            }
    };
    xhttp.open('POST', '/topics/'+unique, true);
    xhttp.setRequestHeader("Content-Type","Application/Json");
    xhttp.send(source);
    
}

var ws = new EventSource('/socket/'+unique);

ws.addEventListener("message", (event) => {
});

ws.addEventListener("open", (event) => {
});

ws.addEventListener("error", (event) => {
   if (event.readyState == EventSource.CLOSED) {
   } else {
   }
   event.target.close();
});

ws.send = function (message){
    sendMediaStream(message);
};


function testEventSourceSend(event,data){
    ws.send(JSON.stringify({
        event:event,
        data:data
    }));
    
};

function sendToOneUser(target, msgString) {
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


function sendToServer(message) {
  var msgJSON = JSON.stringify(message);

  ws.send(msgJSON);
}






function handleVideoOfferMsg(message) {
  var localStream = null;

  targetUsername = message.name;
  createPeerConnection();

  var desc = new RTCSessionDescription(message.sdp);

  newConnection.setRemoteDescription(desc).then(function () {
    return navigator.mediaDevices.getUserMedia(mediaConstraints);
  })
  .then(function(stream) {
    localStream = stream;
    document.getElementById("local_video").srcObject = localStream;

    localStream.getTracks().forEach(track => newConnection.addTrack(track, localStream));
  })
  .then(function() {
    return newConnection.createAnswer();
  })
  .then(function(answer) {
    return newConnection.setLocalDescription(answer);
  })
  .then(function() {
    var message = {
      name: myUsername,
      target: targetUsername,
      type: "video-answer",
      sdp: newConnection.localDescription,
      topic: "video-offer-msg"
    };
    sendToServer(message);
  })
  .catch(handleGetUserMediaError);
}



function hangUpCall() {
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



})();
