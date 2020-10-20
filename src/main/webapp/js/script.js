(function () {
var hostconnectURL = "wss://conferenceseries.tjgwebservices.com";
var hostconnectport = "8080";
var hostconnectpath = "/socket";
var hosturl = hostconnectURL+":"+hostconnectport+hostconnectpath;

var conn = new WebSocket(hosturl);
conn.onopen = function() {
    console.log("Connected to signal server /socket");
    setstreamconnected(true);

}

conn.onmessage = function(msg) {
    console.log("Received message", msg.data);
    var content = JSON.parse(msg.data);
    var data = content.data;
    
    switch(content.event) {
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
function send(message){
    conn.send(JSON.stringify(message));
}

var peerConnection = new RTCPeerConnection(configuration, {
    optional : [{
            RtpDataChannels : true
    }]
});

var dataChannel = peerConnection.createDataChannel("dataChannel",
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

function createOffer(){
peerConnection.createOffer(function(offer){
   send({
       event : "offer",
       data: offer
   });
   peerConnection.setLocalDescription(offer);
}, function(error){
    console.log("Peer Connection",error);
});
}


peerConnection.onicecandidate = function(event) {
    if (event.candidate) {
        send({
            event : "candidate",
            data : event.candidate
        });
    }
};

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

function offerError(error){
            console.log("Offer error",error);    
}

    function handleOffer(offer){
    console.log("setting remote description", offer);
    
peerConnection.setRemoteDescription(new RTCSessionDescription(offer))
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
}

function handleAnswer(answer){
    console.log("handleAnswer",answer);
    peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
}

function handleCandidate(candidate){
    console.log("handleCandidate",candidate);
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
}

function sendMessage(){
    console.log("sendMessage");
    if (dataChannel.readyState == "connecting"){
        console.log("Unable to send message, ready state connecting");
        
    } else {
    dataChannel.send("message");
    dataChannel.onmessage = function (event){
        console.log("Message", event.data);
    }

    }
}



var messagesocket = new WebSocket(hosturl);
messagesocket.onopen = (e) =>
{
    console.log("message socket on open",e.target.readyState);
    if (e.target.readyState !== WebSocket.OPEN) return;
    const authentication = {
        msgType : 'Authenticate',
        data : {
            token : localStorage.getItem('authToken')
        }
    }
    e.target.send(JSON.stringify('{"message":"test"}'));
    createOffer();
    sendMessage();
};

const constraints = {
    video: true, audio: true
};

messagesocket.onmessage = function(msg) {
    console.log("Received message", msg.data);
    var content = JSON.parse(msg.data);
    var data = content.data;
    
    switch(content.event) {
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
    console.log("show greetings",message);
    var greetings = document.getElementById("greetings");
    var newMessage = document.createElement("span");
    newMessage.appendChild(message);
    greetings.appendChild(newMessage);
}

navigator.mediaDevices.getUserMedia(constraints)
        .then(function(stream){ addStream(stream) })
        .catch(function(err){console.log(err)});

var constraints2 = {
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

var videoElement = document.getElementById("liveStream");

function addStream(stream) {
    peerConnection.addStream(stream);
    peerConnection.onaddstream = function(event) {
        videoElement.srcObject = event.stream;
    }
}
var configuration = {
    "iceServers" : [{
      "url": "stun:stun2.1.google.com:19302"      
    }]
};


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
    const peerConnection = new RTCPeerConnection(configuration);
    const dataChannel = peerConnection.createDataChannel();
    var streamsocket = new WebSocket(hostconnectURL+':8081/topics/messages');
    const openMediaDevices = navigator.mediaDevices.getUserMedia(constraints);
    streamsocket.onopen = (e) =>
    {
        try {
            const stream = openMediaDevices({'video':true, 'audio':true});
            console.log('Received media stream:', stream);
            streamsocket.send(JSON.stringify('{"test":"test"}'));
            streamshowgreetings(JSON.parse(e.target).content);
        } catch(error) {
            console.log('Error accessing media devices.', error);
        }
    };
}

function streamdisconnect() {
    var peersocket = new WebSocket(hostconnectURL+':8081/topics/messages/disconnect');
    peersocket.onopen = (e) =>
    {
        if (e.target.readyState !== WebSocket.OPEN) return;
    e.target.send(JSON.stringify('{"message":"test"}'));
    };
    setConnected(false);
    console.log("Disconnected");
}

var connectform = document.getElementById("connectform");
connectform.addEventListener("submit", 
	function(e){
		e.preventDefault(); 
   
        });
var sendmessage = document.getElementById("sendmessage");
sendmessage.addEventListener("submit", 
	function(e){
		e.preventDefault(); 
   
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
})();