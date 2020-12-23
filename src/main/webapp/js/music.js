'use strict';
(function () {

var rotate = 0;
var level  = 0;
var mx = Math.cos((210) * Math.PI / 180) * 98 + 100;
var my = Math.sin((210) * Math.PI / 180) * -98 + 100;


const context = new AudioContext();
const oscillator = context.createOscillator(); 

const notes = {
  a: 'C4',
  s: 'D4',
  d: 'E4',
  f: 'F4',
  g: 'G4',
  h: 'A4',
  j: 'B4',
  k: 'C5'
};

var tableData = [["row 1, cell 1", "row 1, cell 2"],
  ["row 2, cell 1", "row 2, cell 2"],
  ["row 3, cell 1", "row 3, cell 2"],
  ["row 4, cell 1", "row 4, cell 2"]];

var frequencies = {
0:130.81,
14:138.59,
23:146.83,
42:155.56,
46:164.81,
69:174.61,
82:185.00,
92:196.00,
108:207.65,
115:220.00,
135:233.08,
138:246.94,
161:261.63,
175:277.18,
184:293.66,
203:311.13,
207:329.63,
230:349.23,
243:369.99,
253:392.00,
269:415.30,
276:440.00,
296:466.16,
299:493.88,
322:523.25,
336:554.37,
345:587.33,
364:622.25,
368:659.25,
391:698.46,
404:739.99,
414:783.99,
430:830.61,
437:880.00,
457:932.33,
460:987.77,
483:1046.50,
506:1174.66,
529:1318.51};

var sequences = [[161,299,161,175,207],
				[0,161,175,184,203,207,230],
				[175,322,336,345,391,230,207],
				[175,299,430,243,230,270,175]];

var limiter = function(value, lower, upper) {
  if(value < lower) return lower;
  else if(value > upper) return upper;
  else return value;
}

var log = function(msg) {
	var textEl = document.createElement('p');
	textEl.innerHTML = msg;
	var logel = document.getElementById('log');
	logel.insertBefore(textEl, logel.firstChild);
	if(document.querySelector('#log p:nth-child(9)'))
	{
		var msg9 = document.querySelector('#log p:nth-child(9)');
		msg9.parentNode.removeChild(msg9);
	}
}

var namespace = "http://www.w3.org/2000/svg";

var svgs = [];
var paths = [];
var circles = [];
var rects = [];
var gs = [];
var texts = [];
var rotations = [];
var textpoints = [[20,95],[5,75],[0,50],[10,25],[30,10],[75,15],[90,30],[95,55],[90,75],[80,95]];

	
for (i=0; i<3; i++) {
	svgs.push(document.createElementNS(namespace, "svg"));
	paths.push(document.createElementNS(namespace, "path"));
	circles.push(new Array());
	texts.push(new Array());
	circles[i].push(document.createElementNS(namespace, "circle"));
	circles[i].push(document.createElementNS(namespace, "circle"));
	rects.push(document.createElementNS(namespace,"rect"));
	svgs[i].setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xlink", "http://www.w3.org/1999/xlink");
	svgs[i].setAttributeNS(null, "viewBox", "0 0 100 100");
	svgs[i].setAttributeNS(null,"width","100");
	svgs[i].setAttributeNS(null,"height","100");
	svgs[i].setAttributeNS(null,"class","knob");
	paths[0].setAttributeNS(null, "d", "M 15.39 75 A 49 49 0, 0, 1, 50 2");
	paths[0].setAttributeNS(null,"fill", "none");
	paths[0].setAttributeNS(null,"stroke-width", "4");
	paths[0].setAttributeNS(null,"stroke", "blue");
	circles[i][0].setAttributeNS(null,"cx","50");
	circles[i][0].setAttributeNS(null,"cy","50");
	circles[i][0].setAttributeNS(null,"fill","#749dda");
	circles[i][0].setAttributeNS(null,"r","40");
	circles[i][0].setAttributeNS(null,"stroke-width","4");
	circles[i][0].setAttributeNS(null,"stroke","#6990b3");
	circles[i][0].setAttributeNS(null,"id","circle"+i);
	circles[i][1].setAttributeNS(null,"cx","50");
	circles[i][1].setAttributeNS(null,"cy","17");
	circles[i][1].setAttributeNS(null,"fill","#6990b3");
	circles[i][1].setAttributeNS(null,"r","3");
	circles[i][1].setAttributeNS(null,"stroke-width","0");
	circles[i][1].setAttributeNS(null,"stroke","none");
	rects[i].setAttributeNS(null,"fill","none");
	rects[i].setAttributeNS(null,"height","100");
	rects[i].setAttributeNS(null,"width","100");
	gs.push(document.createElementNS(namespace, "g"));
	gs[i].appendChild(circles[i][0]);
	gs[i].appendChild(circles[i][1]);

	//svgs[0].appendChild(paths[0]);
	svgs[i].appendChild(gs[i]);
	svgs[i].appendChild(rects[i]);

	for (j=1;j<11;j++) {
		texts[i].push(document.createElementNS(namespace, "text"));
		texts[i][j-1].setAttributeNS(null,"x",textpoints[j-1][0]);
		texts[i][j-1].setAttributeNS(null,"y",textpoints[j-1][1]);
		texts[i][j-1].setAttributeNS(null,"stroke","#9ab6ce");
		texts[i][j-1].innerHTML = j;
		svgs[i].appendChild(texts[i][j-1]);
	}

	
	var selector2 = document.getElementById("selector2");
	selector2.appendChild(svgs[i]);

	rotations.push(0);

var updateCircle = function(e){
		//console.log(e.target);

		var rect = e.target.getBoundingClientRect();
		var x = e.clientX - rect.left; //x position within the element.
		var y = e.clientY - rect.top;  //y position within the element.

		var id = parseInt(e.target.getAttribute("id")[e.target.getAttribute("id").length - 1]);
		
		if (x < 50) {
			rotations[id] -=1;
		} else {
			rotations[id] +=1;
		}
		
		e.target.parentElement.style.transform = 'rotate(' + (rotations[id] * 45) + 'deg)';	
		e.target.parentElement.style.transformBox = 'fill-box';	
		e.target.parentElement.style.transformOrigin = 'center';
};	

circles[i][0].addEventListener("click", function(e) {updateCircle(e);});
circles[i][0].addEventListener("dblclick", function(e) {updateCircle(e);});

/*
circles[i][0].addEventListener("mousewheel", function(e) {
			e.preventDefault();
			var delta = Math.max(-1, Math.min(1, (e.wheelDelta || -e.detail)));
			log(delta );
			rotate = delta;
			if (rotate > 0) rotate = 120;
			if (rotate < 0) rotate = -120;
				
			e.target.parentElement.style.transform = 'rotate(' + rotate + 'deg)';
			var x = Math.cos((90-rotate) * Math.PI / 180) * 98 + 100;
			var y = Math.sin((90-rotate) * Math.PI / 180) * -98 + 100;
			
			var largeArc = (rotate >= 60) ? 1 : 0;
			path = e.target.parentElement.parentElement.getElementsByTagName("path")[0];
			console.log("path", path.getAttribute("d"));
			path.setAttribute("d", 'M ' + mx + ' ' + my + ' A 98 98 45, '+ largeArc + ', 1, ' + x + ' ' + y);	
	});
*/
}
	
var positions=[50,17];
var svgs2 = [];
var paths2 = [];
var circles2 = [];
var rects2 = [];
var gs2 = [];
var rotations2 = [];
var currentPaths = [];
var pathlines = ["M 50 50 L 35 35 Z", 
				"M 50 50 L 45 30 Z", 
				"M 50 50 L 55 30 Z", 
				"M 50 50 L 70 45 Z", 
				"M 50 50 L 70 50 Z",
				"M 50 50 L 70 55 Z",
				"M 50 50 L 60 65 Z",
				"M 50 50 L 50 70 Z",
				"M 50 50 L 45 70 Z",
				"M 50 50 L 40 65 Z",
				"M 50 50 L 35 60 Z",
				"M 50 50 L 30 50 Z",
				"M 50 50 L 35 40 Z"]


var knobs = document.getElementById("knobs");

for (i=0;i<3;i++) {
	svgs2.push(document.createElementNS(namespace, "svg"));
	paths2.push(document.createElementNS(namespace, "path"));
	circles2.push(document.createElementNS(namespace, "circle"));
	svgs2[i].setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xlink", "http://www.w3.org/1999/xlink");
	svgs2[i].setAttributeNS(null, "viewBox", "0 0 100 100");
	svgs2[i].setAttributeNS(null, "id", "knob"+i);
	circles2[i].setAttributeNS(null, "cx", "50");
	circles2[i].setAttributeNS(null, "cy", "50");
	circles2[i].setAttributeNS(null, "r", "20");
	circles2[i].setAttribute("fill", "#b4cce2");
	circles2[i].setAttribute("stroke", "#7d8f9e");
	circles2[i].setAttribute("stroke-width", "2");
	var pathline = "M 35 35 L 50 50 Z";
	paths2[i].setAttributeNS(null, "d", pathline);
	paths2[i].setAttribute("stroke", "#7d8f9e");
	paths2[i].setAttribute("stroke-width", "2");
	svgs2[i].appendChild(circles2[i]);
	svgs2[i].appendChild(paths2[i]); 

	currentPaths.push(i);
	svgs2[i].addEventListener("click", function(e){
		var elem;
		if (e.target.tagName == "svg"){
			elem = e.target;
		} else {
			elem = e.target.parentElement;
		}
		var id = parseInt(elem.getAttribute("id")[elem.getAttribute("id").length - 1]);
		currentPaths[id]++;
		if (currentPaths[id] >= pathlines.length) {
			currentPaths[id] = 0;
		}

		var path = elem.getElementsByTagName("path")[0];
		path.setAttributeNS(null, "d", pathlines[currentPaths[id]]);
	});
	knobs.appendChild(svgs2[i]);

}







var playNote = function( note ) {
	var ctx = new AudioContext();
	var osc = ctx.createOscillator(); 

	osc.frequency.value = frequencies[note];
      osc.type = 'sine';
      osc.connect(ctx.destination);
    
      osc.start(ctx.currentTime);
      setTimeout(() => osc.stop(ctx.currentTime), 500);

	
}

var audiosources = [];

var playSequence = function( sequence ) {
	var iter;
	for (iter=0; iter<sequence.length; iter++) {
		var key = sequence[iter];
		var freq = 	frequencies[key];
		var ctx = new AudioContext();
		var osc = ctx.createOscillator(); 
		audiosources.push(osc);
		var gainNode = ctx.createGain();
		gainNode.gain.value = 0.1;
		osc.frequency.value = frequencies[sequence[iter]];
		osc.type = 'sine';
		osc.connect(ctx.destination);
		gainNode.connect(ctx.destination);

		osc.start(ctx.currentTime);
		setTimeout(() => osc.stop(ctx.currentTime), 500);
	}		
}

var stopSequences = function() {
		for (i = 0;i < audiosources.length; i++) {
			if (audiosources[i]){
				audiosources[i].stop(0);
			}
		}
}

var testSequence = function(){
		var ctx = new AudioContext();
		var osc = ctx.createOscillator(); 
		var gainNode = ctx.createGain();
		var freq = 400.0;
		var time  = ctx.currentTime + .5;
		console.log(ctx.currentTime);
		console.log(time);
		audiosources.push(osc);
		gainNode.gain.value = 0.1;
		osc.frequency.value = freq;
		osc.type = 'triangle';
		osc.connect(ctx.destination);
		gainNode.connect(ctx.destination);
		osc.start(time);
		setTimeout(() => osc.stop(time), 500);
	
		time = time + .5;
		freq = freq * 1.5;
		osc.frequency.value = freq;
		osc = ctx.createOscillator();
		audiosources.push(osc);
		osc.connect(ctx.destination);
		gainNode.connect(ctx.destination);
		osc.start(time);
		setTimeout(() => osc.stop(time), 500);
	
		osc = ctx.createOscillator();
		audiosources.push(osc);
		time = time + .5;
		freq = freq * 4.0 / 5.0;
		osc.connect(ctx.destination);
		gainNode.connect(ctx.destination);
		osc.start(time);
		setTimeout(() => osc.stop(time), 500);
		setTimeout(() => stopSequences(), 3000);
}

var svg2 = document.createElementNS(namespace, "svg");
svg2.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xlink", "http://www.w3.org/1999/xlink");
svg2.setAttributeNS(null, "viewBox", "0 0 500 200");

for (var i = 0; i < 24; i++) {
var rect1 =  document.createElementNS(namespace, "rect");
	rect1.setAttributeNS(null, "x", i*23);	
	rect1.setAttributeNS(null, "y", 0);	
	rect1.setAttributeNS(null, "width", "23");	
	rect1.setAttributeNS(null, "height", "120");
	rect1.setAttribute("fill", "#ffffff");
	rect1.setAttribute("stroke", "#000000");
	rect1.addEventListener("mousedown",  function(e){
		e.target.setAttribute("fill", "#008800");
		playNote(e.target.getAttribute("x"));
		});
	rect1.addEventListener("mouseup",  function(e){
		e.target.setAttribute("fill", "#ffffff");
		});		
	svg2.appendChild(rect1);
}
var flats = [14,42,82,108,135,175,203,243,269,296,336,364,404,430,457];
// 161
// 345
for (var j = 0; j < 15; j++) {
var rect2 =  document.createElementNS(namespace, "rect");
	rect2.setAttributeNS(null, "x", flats[j]);
	rect2.setAttributeNS(null, "y", 0);
	rect2.setAttributeNS(null, "width", "13");
	rect2.setAttributeNS(null, "height", "80");
	rect2.setAttribute("fill", "#000000");
	rect2.setAttribute("stroke", "#000000");
	rect2.addEventListener("mousedown",  function(e){
		e.target.setAttribute("fill", "#008800");
		playNote(e.target.getAttribute("x"));
		});
	rect2.addEventListener("mouseup",  function(e){
		e.target.setAttribute("fill", "#000000");
		});		

	svg2.appendChild(rect2);
}

var keyboard = document.getElementById("keyboard1");

keyboard.appendChild(svg2);



//const context = new AudioContext();
//const oscillator = context.createOscillator();

//oscillator.frequency.value = 440;
//oscillator.type = 'sine';




document.querySelector('button').addEventListener('click', () => {

    playNote(42);
/*	
      oscillator.frequency.value = 440;
      oscillator.type = 'sine';
      oscillator.connect(context.destination);
    
      oscillator.start(context.currentTime);
      setTimeout(() => oscillator.stop(context.currentTime), 500);
	  audio.start();
*/
    });

	/*
var twoPi = 2*Math.PI;
var objectsCount = 12;
var radius = 100
var change = twoPi/objectsCount;
for (var i=0; i < twoPi; i+=change) {
  var x = radius*Math.cos(i);
  var y = radius*Math.sin(i);
  var rotation = i;
}

  var ctx = new AudioContext();
  var audio = document.getElementById('audiosource');
  var audioSrc = ctx.createMediaElementSource(audio);
  var analyser = ctx.createAnalyser();
  audioSrc.connect(analyser);
  var frequencyData = new Uint8Array(analyser.frequencyBinCount);
  function renderFrame() {
     requestAnimationFrame(renderFrame);
     analyser.getByteFrequencyData(frequencyData);
  }
  renderFrame();
*/
  
  var table = document.createElement("table");
var tableBody = document.createElement('tbody');


var sequenceCounter = 0;


var jsonnotes;

function loadJSONNotes() {   
	var xhttp = new XMLHttpRequest();
	xhttp.overrideMimeType("application/json");
	xhttp.open('GET', '/js/notes.json?v=0.0.0.2', true); 
	xhttp.onreadystatechange = function () {
		  if (xhttp.readyState == 4 && xhttp.status == "200") {
			jsonnotes = JSON.parse(xhttp.responseText);
		  }
	};
	xhttp.send(null);  
}

loadJSONNotes();

var playNotes = function( sequence ) {
	var iter;
	for (iter=0; iter<sequence.length; iter++) {
		var ctx = new AudioContext();
		var osc = ctx.createOscillator();
		
		audiosources.push(osc);
		var gainNode = ctx.createGain();
		var an=ctx.createAnalyser();
		gainNode.gain.value = 0.1;
		osc.frequency.value = jsonnotes[sequence[iter]];
		osc.type = 'sine';
		gainNode.connect(an); 
		osc.connect(ctx.destination);
		gainNode.connect(ctx.destination);
		an.connect(ctx.destination);
		visualizeSound(an);

		osc.start(ctx.currentTime);
		setTimeout(() => osc.stop(ctx.currentTime), 500);
	}		
}

tableData.forEach(function(rowData) {
	var row = document.createElement("tr");
	rowData.forEach(function(cellData) {
		var cell = document.createElement("td");
		var button = document.createElement("button");
		button.addEventListener("mousedown",  function(e){
			e.target.style.backgroundColor = "#008800";
			
			playSequence(sequences[sequenceCounter]);
			sequenceCounter++;
			if (sequenceCounter >= sequences.length) {
				sequenceCounter = 0;
			}
		});
		button.addEventListener("mouseup",  function(e){
			e.target.style.backgroundColor = "#dedede";			
		});
		button.innerHTML = cellData;
		cell.appendChild(button);
		row.appendChild(cell);
	});
	tableBody.appendChild(row);
});

table.appendChild(tableBody);
var main = document.querySelector("body > main");

main.appendChild(table);

var canvas = document.createElement("canvas");
canvas.setAttribute("width","640");
canvas.setAttribute("height","240");

main.appendChild(canvas);

function visualizeSound(analyzer){
	var visual = requestAnimationFrame(visualizeSound);
	var canctx = canvas.getContext('2d');
	var count = new Uint8Array(analyzer.frequencyBinCount);
	if (count.length == 0) return; 
	canctx.lineWidth = 10;
	canctx.beginPath();
	canctx.moveTo(320,120);
	var barWidth = 640/count.length;
	console.log("count.length=",count.length);
	for (var i=0;i < count.length; i++) {
		var value=count[i];
		var percent = value / 256;
		var height = 240 * percent;
		var offset = 240 - height - 1;
		console.log("percent=",percent,"height=",height,"offset=",offset,"barWidth=",barWidth);
		canctx.fillStyle = "#cc6161";
		canctx.fillRect = i * barWidth, offset, 1, 1;
		var domain = i/count.length * 640;
		var range = i/count.length * 240;
		if (i % 50 == 0) {
			canctx.lineTo(domain,range);
			canctx.arcTo(150, 20, 150, 70, 50);
		} else {
			canctx.moveTo(domain-640,range);
		}
		canctx.stroke();
	}
};

function visualizeSound2(analyzer) {
	var bufferLength = analyzer.frequencyBinCount;
	var dataArray = new Uint8Array(bufferLength);
	var visual2 = requestAnimationFrame(visualizeSound2);
	var canctx = canvas.getContext('2d');
	canctx.fillStyle = 'rgba(200, 200, 200, 0.1)';
	canctx.fillRect = '0,0, 240, 640';
	canctx.lineWidth = 2;
	canctx.strokeStyle = 'rgba(100, 100, 100, 0.1)';
	canctx.beginPath();
	var sliceWidth = 640 * 1.0 / bufferLength;
	var x = 50;
	
	for (var i = 0; i < bufferLength; i++) {
		var v = dataArray[i] / 128.0;
		var y = v * 240/2;
		if (i == 0) {
			canctx.moveTo(x,y);
		} else {
			if (i == 1) {
				canctx.lineTo(x,y);				
			} else {
				if (i == 2) {
					canctx.globalAlpha = 0.2;
				} else {
					if ( i % 2 == 0) {
						canctx.globalAlpha = 0.2;
						canctx.lineTo(x,y);				
					} else {
						canctx.moveTo(x,y);				
					}
				}
			}
		}
		x = x + sliceWidth;
	};
	
	//canctx.lineTo(640, 120);
	canctx.stroke();
	//canvas.fillStyle = 'rgb(200, 200, 200)';
	//canvas.fillRect = '0,0, 240, 640';
	
	
	setTimeout(function(){ 
		canctx.clearRect(0, 0, canvas.width, canvas.height);
	}, 4000);

};
	
function synthesizer(frequency,type){
	var context=new AudioContext();
	var osc=context.createOscillator();
	var gn=context.createGain();
	var an=context.createAnalyser();
	osc.type=type;
	osc.connect(gn);
	osc.frequency.value=frequency;
	gn.connect(context.destination);
	an.connect(context.destination);
	osc.start(0);
	visualizeSound(an);
	visualizeSound2(an);
	gn.gain.exponentialRampToValueAtTime(0.00001,context.currentTime+1);
};

var btn1 = document.createElement("button");
btn1.innerHTML = "Synth1";
btn1.addEventListener("click",function(e){
	e.preventDefault();
	synthesizer(164.8, 'triangle');
});
main.appendChild(btn1);


var btn2 = document.createElement("button");
btn2.innerHTML = "Synth2";
btn2.addEventListener("click",function(e){
	e.preventDefault();
	synthesizer(830.6, 'sawtooth');
});
main.appendChild(btn2);

var btn3 = document.createElement("button");
btn3.innerHTML = "Synth3";

btn3.addEventListener("click",function(e){
	e.preventDefault();
	synthesizer(349.2, 'square');
});
main.appendChild(btn3);

var btn4 = document.createElement("button");
btn4.innerHTML = "Arp1";

btn4.addEventListener("click",function(e){
	e.preventDefault();
	var counter = 0;
	var i = setInterval(function(){
		playNotes(["C4","F4","Bb4"]);
	counter++;
		if(counter === 3) {
			clearInterval(i);
		}
	}, 2000);
});
main.appendChild(btn4);

var btn5 = document.createElement("button");
btn5.innerHTML = "Arp2";

btn5.addEventListener("click",function(e){
	e.preventDefault();
	playNotes(["C4", "G4", "F4", "C5", "C4", "G4", "A4", "G4"]);
});
main.appendChild(btn5);

var btn6 = document.createElement("button");
btn6.innerHTML = "Arp3";

btn6.addEventListener("click",function(e){
	e.preventDefault();
	playNotes(["C4", "G4", "F4", "C5", "E4"]);
});
main.appendChild(btn6);


var btn7 = document.createElement("button");
btn7.innerHTML = "Test Sequence";
btn7.addEventListener("click",function(e){
	e.preventDefault();
	testSequence();
});
main.appendChild(btn7);

var options = ["CSV","SQL","TXT"];
var semitones = ["12","11","10","9","8","7","6","5","4","3","2","1","0","-1","-2","-3","-4","-5","-6","-7","-8","-9","-10","-11","-12"];
var buttonarray = ["Insert","Delete","Save","Connect","Quit"];
var tablecolors = ["rgba(238,238,238)","rgba(255,255,255)"];
var commonproperties =["length","step","step_mode","velocity","pitch","scale","octaves","order"];
var noteFrequencies = [["C3","130.81"],["D3","146.83"],["E3","164.81"],
					["F3","174.61"],["G3","196.00"],["A3","220.00"],
					["B3","246.94"],["C4","261.63"],["D4","293.66"],
					["E4","329.63"],["F4","349.23"],["G4","392.00"],
					["A4","440.00"],["B4","493.88"],["C5","523.25"]];
var instruments = ["sine","square","sawtooth","triangle"];


var recordholder = document.getElementById("records");
var records = [];

function loadDoc() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			records = processData(xhttp.responseText);
			recordholder.innerHTML = records.toString();
		}
	};
	xhttp.open("GET","/csv/example.csv?v=0.0.1", true);
	xhttp.send();
}

function processData(data) {
	var lines = data.split(/\r\n|\n/);	
	var headings = lines[0].split(',');
	
	for (var i=1;i<lines.length;i++) {
		var fields = lines[i].split(',');
		if (fields.length == headings.length) {
			var eachrecord = [];
			for (var j=0;j<headings.length; j++) {
				eachrecord.push(headings[j]+":"+fields[j]);
			}
			records.push(eachrecord);
		} else {
			console.log("Invalid line" + i);
		}
	}
	return records;
}

function createSelectBox(options){
	var select = document.createElement("select");
	for (i=0;i<options.length;i++){
		var option = document.createElement("option");
		option.setAttribute("value",options[i]);
		option.innerHTML = options[i];
		select.appendChild(option);
	}
	return select;
}


function createMonitor(){
	var div = document.createElement("div");
	div.style.overflow = "scroll";
	div.style.width = "100%";
	div.style.height = "100px";
	div.style.border = "2px solid #000000";
	return div;
}

function createInstrumentFrame(){
	var div = document.createElement("div");
	div.style.width = "450px";
	div.style.height = "400px";
	div.style.float = "left";
	div.style.display = "block";
	var title = document.createElement("h1");
	title.innerHTML = "Instrument Panel";
	div.appendChild(title);
	return div;
}

function createParametersFrame(){
	var div = document.createElement("div");
	div.style.width = "450px";
	div.style.width = "400px";
	div.style.float = "left";
	div.style.display = "block";
	var title = document.createElement("h1");
	title.innerHTML = "Parameters Panel";
	div.appendChild(title);
	return div;

}

function createButtons(buttons){
	var barray = []
	for (var i=0;i<buttons.length;i++) {
		var button = document.createElement("button");
		button.innerHTML = buttons[i];
		barray.push(button);
	}
	return barray;
}

function createXML(){
	var xmlString = "<root></root>";
	var parser = new DOMParser();
	var xmlDoc = parser.parseFromString(xmlString, "text/xml"); 
	return xmlDoc;
}

function createXMLElements(){
	var xmlString = "<track></track>";
	var parser = new DOMParser();
	var xmlDoc = parser.parseFromString(xmlString, "text/xml");
	var root = xmlDoc.getElementsByTagName("track")[0];
	root.appendChild(xmlDoc.createElement("bank"));
	root.appendChild(xmlDoc.createElement("channel"));
	root.appendChild(xmlDoc.createElement("drumkey"));
	root.appendChild(xmlDoc.createElement("program"));
	root.appendChild(xmlDoc.createElement("velocity"));
	root.appendChild(xmlDoc.createElement("pitch"));
	return xmlDoc;
}

var inst1 = createInstrumentFrame();
var para1 = createParametersFrame();
var monitor1 = createMonitor();
var btns = createButtons(buttonarray);
var select1 = createSelectBox(options);
var select2 = createSelectBox(semitones);
for (i=0;i<btns.length;i++) {
	monitor1.appendChild(btns[i]);
}

var xml = new XMLSerializer().serializeToString(createXMLElements());
var p = document.createElement("p");
//var text = new DOMParser().parseFromString(xml, 'application/xml');
var xmlTextNode = document.createTextNode(xml);
//p.innerHTML = "<![CDATA["+ xmlTextNode+ "]]";
p.appendChild(xmlTextNode);
monitor1.appendChild(select1);
monitor1.appendChild(select2);
monitor1.appendChild(p);
main.appendChild(inst1);
main.appendChild(para1);
main.appendChild(monitor1);


var phase = 0;
var baseFrequency = 440.0;
var kSampleRate = 44100.0;
var kBufferSize = 1024;
var phaseIncrement = 2.0 * Math.PI * baseFrequency / kSampleRate;
var ktwoPi = 2.0 * Math.PI;
var soundEnabled = false;
var gainNode;
var processor = 0;
var audioContext = new AudioContext();
//var oscillator = audioContext.createOscillator();
var osc = audioContext.createOscillator(); 
osc.type = 'sine'; 
osc.frequency.value = baseFrequency;
var freqInput, instInput;
var real = new Float32Array(2);
var imag = new Float32Array(2);
	
var linearRampPlus = .5;
var linearRampMinus = -.5;

var canvas = document.createElement("canvas");
canvas.setAttribute("width","400");
canvas.setAttribute("height","200");
var canvascontext = canvas.getContext("2d");
canvascontext.fillStyle = "#0099ff";
canvascontext.font = "20 pt Verdana";
inst1.appendChild(canvas);

var sourceButtons = document.createElement("div");
inst1.appendChild(sourceButtons);
var sButtons = [];
for (var i=0;i<4;i++){
	var btn = document.createElement("button");
	var txt = document.createTextNode(i.toString());
	btn.appendChild(txt);
	sButtons.push(btn);
	sourceButtons.appendChild(sButtons[i]);
	sButtons[i].style.padding = "5px";
	sButtons[i].style.margin = "5px";
	sButtons[i].style.border = "2px outset #dedede";
	sButtons[i].addEventListener("mouseover",function(e){
			startTone(i);
		});
	sButtons[i].addEventListener("mouseout",function(e){
			stopTone(i);
		});
}


initAudio();

function initAudio(){
	processor = audioContext.createScriptProcessor(kBufferSize, 0 , 1);
	processor.onaudioprocess = process;
	gainNode =audioContext.createGain();
	var btn = document.createElement("button");
	btn.innerHTML = "Start Audio";
	btn.addEventListener("click", function(){
		osc.connect(audioContext.destination); 
		osc.start(audioContext.currentTime);
		processor.connect(gainNode);
		gainNode.connect(audioContext.destination);
		gainNode.gain.value = 0.00;
	
	});
	var sbtn = document.createElement("button");
	sbtn.innerHTML = "Stop Audio";
	sbtn.addEventListener("click", function(){
		osc.stop(audioContext.currentTime);
	});
	
	freqInput = document.createElement("select");
	for (var i=0;i<noteFrequencies.length;i++) {
		var option = document.createElement("option");
		option.setAttribute("value",noteFrequencies[i][1]);
		option.innerHTML = noteFrequencies[i][0];
		freqInput.appendChild(option);
	}
	freqInput.addEventListener("change", function(e){
		baseFrequency = freqInput.options[freqInput.options.selectedIndex].value;
		osc.frequency.value = baseFrequency;
	});
	
	instInput = document.createElement("select");
	for (var i=0;i<instruments.length;i++) {
		var option = document.createElement("option");
		option.setAttribute("value", instruments[i]);
		option.innerHTML = instruments[i];
		instInput.appendChild(option);
	}
	instInput.addEventListener("change", function(e){
		osc.type=instInput.options[instInput.options.selectedIndex].value;
	});
	
	
	var detuText = document.createElement("span");
	detuText.appendChild(document.createElement("p")
			.appendChild(document.createTextNode(
				(osc.detune.value).toString()
			)));
			
	var freqText = document.createElement("span");
	freqText.appendChild(document.createElement("p")
			.appendChild(document.createTextNode(
				(osc.frequency.value).toString()
			)));
	
	var detuInput = document.createElement("select");
	var detuneValues = [0,10,100,1000];
	for (var i=0;i<detuneValues.length;i++) {
		var option = document.createElement("option");
		option.setAttribute("value",detuneValues[i]);
		option.innerHTML = detuneValues[i];
		detuInput.appendChild(option);
	}
	detuInput.addEventListener("change", function(e){
		var newvalue = detuInput.options[detuInput.options.selectedIndex].value;
		osc.detune.value = newvalue;
	});

	
	var wavInput = document.createElement("select");
	var waveValues = ["[[0,1],[0,0]]","[[0,1],[1,0]]",
				"[[0,2],[1,0]]","[[0,2],[2,1]]",
				"[[0,4],[2,2]]","[[0,4],[4,2]]",
				"[[4,8],[8,6]]","[[2,32],[4,2]]",
				"[[16,24],[16,16]]","[[24,32],[16,16]]",
				"[[4,8],[8,6]]","[[2,32],[4,2]]"

					];
	
	for (var i=0;i<waveValues.length;i++) {
		var option = document.createElement("option");
		option.setAttribute("value",waveValues[i]);
		option.innerHTML = waveValues[i];
		wavInput.appendChild(option);
	}
	
	wavInput.addEventListener("change", function(e){
		var newvalue = wavInput.options[wavInput.options.selectedIndex].value;
		var array = JSON.parse(newvalue);
		real[0] = array[0][0];
		real[1] = array[0][1];
		imag[0] = array[1][0];
		imag[1] = array[1][1];
		createCustomInstrument(real, imag);
	});
	
	
	var filtInput = document.createElement("select");
	var filtTypes = ["lowpass","highpass","bandpass",
				"lowshelf","highshelf","peaking",
				"notch","allpass"];
	
	for (var i=0;i<filtTypes.length;i++) {
		var option = document.createElement("option");
		option.setAttribute("value",filtTypes[i]);
		option.innerHTML = filtTypes[i];
		filtInput.appendChild(option);
	}
	
	filtInput.addEventListener("change", function(e){
		newvalue = filtInput.options[filtInput.options.selectedIndex].value;
		createCustomFilter(newvalue);
	});

	para1.appendChild(
		document.createElement("h4")
			.appendChild(
				document.createTextNode("Detune:")
			));
			
	para1.appendChild(detuText);
	
	para1.appendChild(
		document.createElement("h4")
			.appendChild(
				document.createTextNode("Change Detune:")
			));
	para1.appendChild(detuInput);

	para1.appendChild(
		document.createElement("h4")
			.appendChild(
				document.createTextNode("Frequency:")
			));
	para1.appendChild(freqText);
	
		para1.appendChild(
		document.createElement("h4")
			.appendChild(
				document.createTextNode("Custom Instrument:")
			));

	para1.appendChild(wavInput);
	
			para1.appendChild(
		document.createElement("h4")
			.appendChild(
				document.createTextNode("Filter Instrument:")
			));

	para1.appendChild(filtInput);
	
	inst1.appendChild(freqInput);
	inst1.appendChild(instInput);
	inst1.appendChild(btn);
	inst1.appendChild(sbtn);
	
}

var canvasX=0,canvasY;
var canvasX1=0,canvasY1=0;


function createCustomInstrument(wav1, wav2){
	var wave=audioContext.createPeriodicWave(wav1, wav2);
	osc.setPeriodicWave(wave);
}


function createCustomFilter(type){
	var distortion = audioContext.createWaveShaper();
	var biquadFilter = audioContext.createBiquadFilter();
	var convolver = audioContext.createConvolver();
	osc.connect(distortion);
	distortion.connect(biquadFilter);
	convolver.connect(audioContext.destination);
	biquadFilter.type = type;
	biquadFilter.frequency.setValueAtTime(1000, audioContext.currentTime);
	biquadFilter.gain.setValueAtTime(25, audioContext.currentTime);
}

function updateCanvas(phase){
	//console.log("update canvas"+phase);
	//console.log("gainNode.gain.value",gainNode.gain.value);
	canvasX +=1;
	if (canvasX == 400){
		canvasX = 0;
		canvascontext.clearRect(0,0, canvas.width, canvas.height);
	}
	if (gainNode.gain.value > 0  && canvasX % 4 == 0) {
		canvasY = parseInt(phase*10);
		//console.log("canvas",canvasX,canvasY);
		canvascontext.beginPath();
		canvascontext.moveTo(canvasX1, canvasY1);
		canvascontext.lineTo(canvasX, canvasY);
		canvasX1 = canvasX;
		canvasY1 = canvasY;
		canvascontext.stroke();
		//console.log("update canvas",canvasX,canvasY);
		//canvascontext.fillText(10,20, phase);
	} else {
	
	}
}
	

function process(event) {
	var outputArray = event.outputBuffer.getChannelData(0);
	var n = outputArray.length;
	for (var i=0;i<n;++i){
		var sample = Math.sin(phase);
		outputArray[i] = sample * 0.6;
		phase +=phaseIncrement;
		if (phase > ktwoPi) {
			phase -=ktwoPi;
		}
		updateCanvas(phase);
	}
}


function startTone(mode) {
	var now = audioContext.currentTime;
	gainNode.gain.cancelScheduledValues(now);
	gainNode.gain.setValueAtTime(gainNode.gain.value, now);
	if (mode == 1){
		gainNode.gain.linearRampToValueAtTime(1.0, now + 1.0);
	} else if (mode == 2) {
		gainNode.gain.linearRampToValueAtTime(1.0, now + 0.5);
		gainNode.gain.linearRampToValueAtTime(0.0, now + 1.0);
		gainNode.gain.linearRampToValueAtTime(1.0, now + 1.5);
		gainNode.gain.linearRampToValueAtTime(0.0, now + 2.0);
		gainNode.gain.linearRampToValueAtTime(1.0, now + 2.5);
	} else if (mode == 3) {
		gainNode.gain.linearRampToValueAtTime(1.0, now + 0.1);
		gainNode.gain.linearRampToValueAtTime(0.2, now + 0.3);	
	} else if (mode == 4) {
		gainNode.gain.linearRampToValueAtTime(1.0, now + 0.2);	
	}
}

function stopTone (mode) {
	var now = audioContext.currentTime;
	gainNode.gain.cancelScheduledValues(now);
	if (mode == 1) {
		gainNode.gain.linearRampToValueAtTime(0.0, now + 0.5);
	} else if (mode == 2) {
		gainNode.gain.linearRampToValueAtTime(0.0, now + 0.5);
	} else if (mode == 3) {
		gainNode.gain.setTargetAtTime(0.0, now, 0.7);
	} else if (mode == 4) {
		gainNode.gain.setTargetAtTime(0.0, now, 0.7);
	}
}



loadDoc();

function setModal(){};

function setContainer(){};

function setSize(){};

var isStepMode = false;
function toggleStep(){
	isStepMode = !isStepMode;
}

var isScaleMode = false;
function toggleScale(){
	isScaleMode = !isScaleMode;
}

function insertAction(){
	
}


function SongManager(){
	var Song;
	var Pattern;
	var Track;
	var step;
	var currentStep;
	
}

function Note(){
	var Velocity;
	var Roll;
	var TickPosition;
	var Pitch;
	var Measure;
	var Position;
}


//var filter = context.createBiquadFilter();
//filter.type = filter.LOWPASS;
//filter.frequency.value = 100;


})();
