'use strict';

const mediaStreamConstraints = {video: true};

const localVideo = document.querySelector('video');

const offerOptions = {offerToReceiveVideo: 1};

let startTime = null;

const localVideo = document.getElementById('localVideo');
const remoteVideo = document.getElementById('remoteVideo');

let localStream;
let remoteStream;

let localPeerConnection;
let remotePeerConnection;

let startTime = null;


let localStream;

var sendChannel;
var receiveChannel;
var pcConstraint;
var dataConstraint;

var isChannelReady = false;
var isInitiator = false;
var isStarted = false;
var localStream;
var pc;
var remoteStream;
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
var startButton = document.querySelector('button#startButton');
var sendButton = document.querySelector('button#sendButton');
var closeButton = document.querySelector('button#closeButton');

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
    onsinglemessage(ws,e);
};

socket.addEventListener('message',function(e){
    updateEventTableValue("Received web socket message event",e.data);
});

socket.addEventListener('open',function(e){
    updateEventTableValue("Received web socket open event",e.data);
});


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
  
