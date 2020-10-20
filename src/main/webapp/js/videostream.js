(function () {
var hostconnectURL = "wss://conferenceseries.tjgwebservices.com";
var hostconnectport = "8080";
var hostconnectpath = "/socket";
var hosturl = hostconnectURL+":"+hostconnectport+hostconnectpath;

let audioContext;
if (typeof AudioContext === 'function') {
    audioContext = new AudioContext();
    
} else if (typeof webkitAudioContext === 'function'){
    audioContext = new webkitAudioContext();
} else {
    console.log("Audio not supported");
}

var filterNode = audioContext.createBiquadFilter();
filterNode.type = "highpass";
filterNode.frequency.value = 1000;
var gainNode = audioContext.createGain();
gainNode.gain.value = 0.5;

navigator.mediaDevices.getUserMedia({audio:true}, (stream) => {
    const mediaStreamSOurce = audioCOntext.createMediaStreamSource(stream);
    mediaStreamSource.connect(filterNode);
    gainNode.connect(audioContext.destination);        
});

const signaling = createSignalingChannel();
const constraints = {audio: true, video: true};

const configuration = {iceServers: [{
            urls: 'stuns:stun.example.org'
}]};

const pc = new RTCPeerConnection(configuration);

var remoteView = document.getElementById("rtcremote");
var selfView = document.getElementById("rtcself");
var isCaller = false;

pc.onicecandidate = ({candidate}) => function(event) {
    
    signaling.send({candidate});
    signaling.send(JSON.stringify({"candidate": event.candidate}));
};

pc.onaddstream = function(event) {
    remoteView.src = URL.createObjectURL(event.stream);
};

pc.onnegotiationneeded = async () => {
    try {
        await pc.setLocalDescription(await pc.createOffer());
        signaling.send({desc: pc.localDescription});
        
    } catch (err) {
        console.log(err);
    }
};

pc.ontrack = (event) => {
    if (remoteView.srcObject) return;
    remoteView.srcObject = event.streams[0];
};

async function start() {
    try {
        const stream = await navigator.mediaDevices.getUserMedia(constraints);
        stream.getTracks().forEach((track) =>
                pc.addTrack(track,stream));
        selfView.srcObject = stream;
        if (isCaller) {
            pc.createOffer(receivedDescription);
        } else {
            pc.createAnswer(pc.remoteDescription,receivedDescription);
            
        }
        function receivedDescription(desc){
            pc.setLocalDescription(desc);
            signaling.send(JSON.stringify({"sdp":desc}));
            
        }
    } catch (err) {
        console.log(err);
    }
};

signaling.onmessage = async ({desc,candidate}) => {
    try {
        if (desc) {
            if (desc.type === 'offer') {
                await pc.setRemoteDescription(desc);
                const stream = await navigator.mediaDevices.getUserMedia(constraints);
                stream.getTracks().forEach((track) => 
                        pc.addTrack(track, stream));
                await pc.setLocalDescription(await pc.createAnswer());
                signaling.send({desc: pc.localDescription});
            } else if (desc.type === 'answer') {
                await pc.setRemoteDescription(desc);
            } else {
                console.log('Unsupported SDP type.');
            }
        } else if (candidate) {
            await pc.addIceCandidate(candidate);
        }
      } catch (err) {
          console.log(err);
      }
    };
    
var rtcconnection = new WebSocket(hosturl);
var rtcname = "";

var loginInput = document.getElementById("rtcconnectname");
var loginButton = document.getElementById("rtcconnectbutton");

var rtcform = document.getElementById("rtcform");
rtcform.addEventListener("submit", 
	function(e){
		e.preventDefault(); 
   
        });        
loginButton.addEventListener("click", 
	function(e){
		e.preventDefault();
                if (loginInput.value.length > 0) {
                    rtcsend({
                        type: "login",
                        name: loginInput.value
                    });
                }
        });

rtcconnection.onmessage = function(message) {
    console.log("Got message", message.data);
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

var rtcsendbutton = document.getElementById("rtcsend");
rtcsendbutton.addEventListener("click",function(e){
    e.preventDefault(); 

    rtcpeerconnection.createOffer(function(error){
        console.log("rtc send create offer",offer);
        rtcsend({
            type:"offer",
            offer: offer
        });
    rtcpeerconnection.setLocalDescription(offer);
    
    }, function(error){
        console.log("rtcsend click error",error);
    })
})

function rtcOnLogin(success) {
    if (success === false) {
        console.log("onlogin connection error");
    } else {
        var streamconfiguration = {
            "iceServers" : [{"url": "stun:stun.1.google.com:19302"}]
        };
        rtcpeerconnection = new webkitRTCPeerConnection(configuration);
        console.log("RTC Peer Connection object was created");
        console.log("rtcpeerconnection");
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

rtcpeerconnection.onopen = function () {
    console.log("Connected");
    };

rtcpeerconnection.onerror = function(err) {
    console.log("RTC Connection", err);
}

function rtcsend(message){
    rtcpeerconnection.send(JSON.stringify(message));
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
    rtcpeerconnenction.setRemoteDescription(new RTCSessionDescription(answer));
}

function rtcOnCandidate(candidate) {
    rtcpeerconnection.addIceCandidate(new RTCIceCandidate(candidate));
}

})();
