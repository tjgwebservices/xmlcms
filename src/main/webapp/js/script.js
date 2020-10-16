(function () {
    
var conn = new WebSocket('ws://localhost:8080/socket');
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


function handleOffer(offer){
peerConnection.setRemoteDescription(new RTCSessionDescription(offer));
peerConnection.createAnswer(function(answer){
    send({
        event: "answer",
        data : answer
    });
}, function(error){
    
});
}

function handleAnswer(answer){
    peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
}

function handleCandidate(candidate){
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
}

function sendMessage(){
    dataChannel.send("message");
    dataChannel.onmessage = function (event){
        console.log("Message", event.data);

    }
}



var messagesocket = new WebSocket('ws://localhost:8080/socket');
messagesocket.onopen = (e) =>
{
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
    stompClient.send("/topics/messages/", {}, 
    JSON.stringify({'name':document.getElementById("name").value}));
}

function streamshowgreetings(message){
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
    var greetings = document.getElementById("greetings");
    connect.setAttribute("disabled",connected);
    disconnect.setAttribute("disabled",!connected);
    if (connected) {
        conversation.style.display = "block";
    } else {
        conversation.style.display = "none";
        
    }
    greetings.innerHTML="";
    
}

function streamconnect() {
    const peerConnection = new RTCPeerConnection(configuration);
    const dataChannel = peerConnection.createDataChannel();
    var streamsocket = new WebSocket('ws://localhost:8080/topics/messages');
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
    var peersocket = new WebSocket('ws://localhost:8080/topics/messages/disconnect');
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