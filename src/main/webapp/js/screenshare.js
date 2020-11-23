'use strict';

// On this codelab, you will be streaming only video (video: true).
const mediaStreamConstraints = {video: true};

// Video element where stream will be placed.
//const localVideo = document.querySelector('video');

// Set up to exchange only video.
const offerOptions = {offerToReceiveVideo: 1};

// Define initial start time of the call (defined as connection between peers).
let startTime = null;

// Define peer connections, streams and video elements.
const localVideo = document.getElementById('localVideo');
const remoteVideo = document.getElementById('remoteVideo');

let localStream;
let remoteStream;

let localPeerConnection;
let remotePeerConnection;

// Local stream that will be reproduced on the video.

var sendChannel;
var receiveChannel;
var pcConstraint;
var dataConstraint;

var isChannelReady = false;
var isInitiator = false;
var isStarted = false;
var pc;
var turnReady;

var pcConfig = {
  'iceServers': [{
    'urls': 'stun:stun.l.google.com:19302'
  }]
};

var sdpConstraints = {
  offerToReceiveAudio: true,
  offerToReceiveVideo: true
};

var room = 'foo';

var dataChannelSend = document.querySelector('textarea#dataChannelSend');
var dataChannelReceive = document.querySelector('textarea#dataChannelReceive');
var sendButton = document.querySelector('button#sendButton');
const startButton = document.getElementById('startButton');
const callButton = document.getElementById('callButton');
const hangupButton = document.getElementById('hangupButton');

startButton.onclick = createConnection;
callButton.onclick = sendData;
hangupButton.onclick = closeDataChannels;



var isInitiator;


var socket = new EventSource('/socket/1000');


socket.send = function emit(message) {
    sendMediaStream(message);
};

socket.onmessage = function(e) {
    emit(ws,e);
};

socket.addEventListener('message',function(e){
    updateEventTableValue("message",e);
    var message = e, pc = peerConnection;
  console.log('Client received message:', message);
  if (message === 'got user media') {
    maybeStart();
  } else if (message.type === 'offer') {
    if (!isInitiator && !isStarted) {
      maybeStart();
    }
    pc.setRemoteDescription(new RTCSessionDescription(message));
    doAnswer();
  } else if (message.type === 'answer' && isStarted) {
    pc.setRemoteDescription(new RTCSessionDescription(message));
  } else if (message.type === 'candidate' && isStarted) {
    var candidate = new RTCIceCandidate({
      sdpMLineIndex: message.label,
      candidate: message.candidate
    });
    pc.addIceCandidate(candidate);
  } else if (message === 'bye' && isStarted) {
    handleRemoteHangup();
  }    
});

socket.addEventListener('open',function(e){
    updateEventTableAll("message",e,"message data",e.data)
});

function sendMessage(message) {
  console.log('Client sending message: ', message);
  socket.emit('message', message);
}


var emit = function() {
    var xhttp = new XMLHttpRequest();
    var header={};
    xhttp.onreadystatechange = function() {
            if (this.readyState!=4) {
              return;
            }
            if (this.status != 200) {
            } else {
                console.log("Received Response");
                isInitiator = true;
                console.log(this.responseText);
            }
    };
    xhttp.open('POST', '/socket/1000', true);
    var formData = new FormData();
    formData.append("message",message);
    xhttp.send(formData);

}

callButton.disabled = true;
hangupButton.disabled = true;

function gotLocalMediaStream(mediaStream) {
  localStream = mediaStream;
  localVideo.srcObject = mediaStream;
}

function handleLocalMediaStreamError(error) {
  console.log('navigator.getUserMedia error: ', error);
}

navigator.mediaDevices.getUserMedia(mediaStreamConstraints)
  .then(gotLocalMediaStream).catch(handleLocalMediaStreamError);
  


