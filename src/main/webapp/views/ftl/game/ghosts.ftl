<!DOCTYPE html>
<head>
<title></title>
<style>
canvas {
	border: 1px solid #899de2;
    background-color: #acbae0;
}
</style>

</head>
<body>

<main>

</main>
<div>
</div>
<div>
</body>
<script>
var highscore = 0;

var main = document.getElementsByTagName("main")[0];

var canvas = document.createElement("canvas");
canvas.setAttribute("width","640");
canvas.setAttribute("height", "400");

var div = document.createElement("div");
div.style.float= "left";
div.style.width = "50%";

div.appendChild(canvas);
main.appendChild(div);

var svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
svg.setAttribute("style", "border:1px solid black");
svg.setAttribute("width","640");
svg.setAttribute("height", "400");
svg.setAttribute("id","sliderControl");

var div = document.createElement("div");
div.style.float= "left";
div.style.width = "50%";
div.appendChild(svg);
main.appendChild(div);


var g1 = document.createElementNS("http://www.w3.org/2000/svg","g");
g1.setAttribute("id","controlzone");
g1.setAttribute("transform","translate(40,0)");

var g2 = document.createElementNS("http://www.w3.org/2000/svg","g");
g2.setAttribute("class","rollover slider1");

var circle = document.createElementNS("http://www.w3.org/2000/svg","circle");
circle.setAttribute("cx", 10);
circle.setAttribute("cy", 10);
circle.innerHTML = "<title>Zoom out 50%</title";
circle.setAttribute("r", 8);


var line = document.createElementNS("http://www.w3.org/2000/svg","line");
line.setAttribute("x1",5);
line.setAttribute("x2",15);
line.setAttribute("y1",10);
line.setAttribute("y2",10);


g2.appendChild(circle);
g2.appendChild(line);

var line1 = document.createElementNS("http://www.w3.org/2000/svg","line");
line1.setAttribute("x1",20);
line1.setAttribute("x2",120);
line1.setAttribute("y1",9.5);
line1.setAttribute("y2",9.5);
line1.setAttribute("stroke","#666");

var line2 = document.createElementNS("http://www.w3.org/2000/svg","line");
line2.setAttribute("x1",20);
line2.setAttribute("x2",120);
line2.setAttribute("y1",10.5);
line2.setAttribute("y2",10.5);
line2.setAttribute("stroke","#ddd");

g1.appendChild(g2);
g1.appendChild(line1);
g1.appendChild(line2);



var g3 = document.createElementNS("http://www.w3.org/2000/svg","g");
g3.setAttribute("id","slidezone");
g3.setAttribute("transform","translate(24.5, 8.5)");

var g4 = document.createElementNS("http://www.w3.org/2000/svg","g");
g4.setAttribute("id","slidemover");
g4.setAttribute("transform","translate(45, 0)");

var polygon = document.createElementNS("http://www.w3.org/2000/svg","polygon");
polygon.setAttribute("id","slider");
polygon.setAttribute("points", "-4,-4 4,-4 4,5 0,9 -4,5");
polygon.innerHTML = "<title>Move left to zoom out, right to zoom in</title>";


g4.appendChild(polygon);
g3.appendChild(g4);

var g5 = document.createElementNS("http://www.w3.org/2000/svg","g");
g5.setAttribute("class", "rollover sliderr");
var circle1 = document.createElementNS("http://www.w3.org/2000/svg","circle");
circle1.setAttribute("id","plus");
circle1.setAttribute("cx", 130);
circle1.setAttribute("cy",10);
circle1.setAttribute("r", 8);
circle1.innerHTML = "<title>Zoom in 50%</title>";

var line3 = document.createElementNS("http://www.w3.org/2000/svg","line");
line3.setAttribute("x1",125);
line3.setAttribute("x2",135);
line3.setAttribute("y1",10);
line3.setAttribute("y2",10);

var line4 = document.createElementNS("http://www.w3.org/2000/svg","line");
line4.setAttribute("x1",130);
line4.setAttribute("x2",130);
line4.setAttribute("y1",5);
line4.setAttribute("y2",15);

g5.appendChild(circle1);
g5.appendChild(line3);
g5.appendChild(line4);

g1.appendChild(g3);
g1.appendChild(g5);

svg.appendChild(g1);



var text = document.createElementNS("http://www.w3.org/2000/svg","text");
text.setAttribute("x",2);
text.setAttribute("y",15);
text.style.fontFamily ="Sans-Serif";
text.style.fontSize ="9pt";
text.setAttribute("fill","gray");
text.setAttribute("id","readout");
text.innerHTML = "100%";

var score = text.cloneNode(true);
svg.appendChild(text);
score.innerHTML = 0;
score.setAttribute("x",50);
score.setAttribute("y",200);
score.style.fontSize="38px";
score.style.fontWeight="800";
svg.appendChild(score);

var label = score.cloneNode(true);
label.setAttribute("y",150);
label.setAttribute("x",50);
label.style.fontWeight="400";
label.style.fontSize="12px";
label.innerHTML = "Your Current Score:";
svg.appendChild(label);

var numberBalls = score.cloneNode(true);
numberBalls.setAttribute("x",300);
svg.appendChild(numberBalls);

var ballsLabel = label.cloneNode(true);
ballsLabel.setAttribute("x",300);
ballsLabel.innerHTML = "Number of Vehicles:";
svg.appendChild(ballsLabel);

var minHeight = 20, maxHeight = 200, minGap = 50, maxGap = 200, obstacles = [], bouncingballs = [], bullets = [], currentScore = 0;

var Rnd = Math.random,
    Sin = Math.sin,
    Floor = Math.floor;

var warpZ = 16,
    units = 200,
    Z = 0.025 + (1/25 * 2);

function resetstar(a)
{
   a.x = (Rnd() * canvas.width - (canvas.width * 0.5)) * warpZ;
   a.y = (Rnd() * canvas.height - (canvas.height * 0.5)) * warpZ;
   a.z = warpZ;
   a.px = 0;
   a.py = 0;
}

stars = [];
for (var i=0, n; i<units; i++)
{
  n = {};
  resetstar(n);
  stars.push(n);
}
cycle = 0;
   
   

context = canvas.getContext("2d");



var grd = context.createLinearGradient(0, 0, 170, 0);
grd.addColorStop(.2, "#008800");
grd.addColorStop(.8, "#080800");

var my_gradient = context.createLinearGradient(0, 0, 170, 0);
my_gradient.addColorStop(0, "black");
my_gradient.addColorStop(0.5 ,"red");
my_gradient.addColorStop(1, "white");


var grd1 = context.createLinearGradient(0, 0, 170, 0);
grd1.addColorStop(0, "#536c57");
grd1.addColorStop(1, "#6f917a");

var radial_gradient1 = context.createRadialGradient(40,50,40, 50,30,170);
radial_gradient1.addColorStop(0, '#536c57');
radial_gradient1.addColorStop(.9, '#6f917a');
radial_gradient1.addColorStop(1, '#536c57');



var rnd = Math.random;
function rgb() {
   return Math.floor(100 + rnd()*50).toString();
}

function randomInt(){
	var randomnum = Math.floor(Math.random()*99) + 1; 
	randomnum *= Math.floor(Math.random()*2) == 1 ? 1 : -1; 
	return randomnum;
}


function mixf(xy1, xy2, mix)
{
   return {
      x: xy1.x * mix + xy2.x * (1-mix),
      y: xy1.y * mix + xy2.y * (1-mix)
   };
}

function f1(t)
{
   return {
      x: rnd()*50*Math.cos(t) - rnd()*50*Math.cos(60/6*t),
      y: rnd()*50*Math.sin(t) - rnd()*50*Math.sin(50/6*t)
   };
}

function f2(t)
{
   return {
      x: 3 * (Math.cos(3*t)),
      y: 2 * (3*Math.sin(t))
   };
}


function component(x, y, width, height, type) {
	this.type;
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.update = function(){

		context.beginPath();
		context.fillStyle = radial_gradient1;
		context.fill();
		context.strokeStyle = radial_gradient1;
		context.lineWidth = 1;
		context.save();
		context.translate(this.x - 60, this.y - 10);
		context.beginPath();
		context.ellipse(50,35,45,13,Math.PI / 4, 0, 2 * Math.PI);
		context.stroke();
		context.beginPath();
		context.ellipse(50,20,20,12,Math.PI / 4, 0, 2 * Math.PI);
		context.stroke();
		var p = new Path2D("M 69.719565,35.591812 C 126.40324,-17.186382 -14.015381,-22.145201 40.427816,34.405776 94.871014,90.956752 99.26464,-46.768141 41.11843,5.5005672 -17.02778,57.769276 124.17662,61.74545 70.410939,5.9175938 16.645261,-49.910262 13.035893,88.370006 69.719565,35.591812 Z M 99.995708,93.183157 C 132.38906,145.6606 123.80666,21.242719 117.08519,82.531393 c -6.72147,61.288677 25.35944,-60.479616 -14.90335,-13.147557 -40.2628,47.332064 75.52665,-2.40569 15.25283,12.842985 -60.273826,15.248676 65.3624,5.913616 8.04493,-18.529443 -57.317471,-24.443057 24.0136,72.677502 -7.72375,18.998825 -31.737343,-53.678676 13.78299,63.448557 20.10104,1.363728 6.31807,-62.084828 -61.12122,45.564559 -19.88664,-1.663255 41.23457,-47.227812 -56.55773,34.271644 4.01751,20.329634 C 182.56299,88.784287 59.399397,59.641392 117.48649,82.489147 175.57358,105.33691 67.602357,40.705708 99.995708,93.183157 Z M 89.412377,116.61768 c 32.393353,52.47744 23.810953,-71.940439 17.089483,-10.65177 -6.721473,61.28868 25.35944,-60.479611 -14.903352,-13.147552 -40.262799,47.332062 75.526652,-2.40569 15.252832,12.842982 -60.273827,15.24868 65.3624,5.91362 8.04493,-18.52944 -57.317472,-24.443057 24.01359,72.6775 -7.72375,18.99882 -31.737344,-53.678671 13.78299,63.44856 20.10104,1.36373 6.31807,-62.084825 -61.121221,45.56456 -19.88664,-1.66325 41.23457,-47.227814 -56.557731,34.27164 4.01751,20.32963 60.57523,-13.94202 -62.588364,-43.084916 -4.50127,-20.23716 58.08709,22.84776 -49.884134,-41.78344 -17.490783,10.69401 z M 26.89206,91.322273 C 59.285411,143.79971 50.703016,19.381835 43.981543,80.670509 37.26007,141.95918 69.340987,20.190893 29.078191,67.522952 -11.184608,114.85501 104.60485,65.117262 44.33102,80.365937 -15.942804,95.614613 109.69343,86.279553 52.375952,61.836494 -4.941519,37.393437 76.389547,134.51399 44.652204,80.835319 12.914859,27.156643 58.435188,144.28387 64.753247,82.199047 71.071317,20.114219 3.632022,127.7636 44.866599,80.535792 86.101177,33.30798 -11.691128,114.80743 48.884111,100.86542 109.45935,86.923403 -13.704251,57.780508 44.382844,80.628263 102.46994,103.47602 -5.501291,38.844824 26.89206,91.322273 Z M 20.945778,120.87758 C 53.339129,173.35502 44.756734,48.937142 38.035261,110.22582 31.313788,171.51449 63.394705,49.7462 23.131909,97.078259 -17.13089,144.41032 98.658565,94.672569 38.384738,109.92124 -21.889086,125.16992 103.74715,115.83486 46.42967,91.391801 -10.887801,66.948744 70.443265,164.0693 38.705922,110.39063 6.968577,56.71195 52.488906,173.83918 58.806965,111.75435 65.125035,49.669526 -2.31426,157.31891 38.920317,110.0911 80.154895,62.863287 -17.63741,144.36274 42.937829,130.42073 103.51307,116.47871 -19.650533,87.335815 38.436562,110.18357 96.523655,133.03133 -11.447573,68.400131 20.945778,120.87758 Z M 26.89206,63.463019 81.370023,9.9598664 V 70.963603 L 26.89206,91.322266 Z M 81.370023,9.9598664 V 70.963603 L 133.83084,88.877884 V 57.039114 Z M 26.89206,91.322266 81.370023,70.963603 133.83084,88.877884 75.324729,96.369441 Z m 0,-27.859247 L 81.370023,9.9598664 133.83084,57.039114 75.324729,76.727141 Z M 75.324729,76.727141 133.83084,57.039114 v 31.83877 L 75.324729,96.369441 Z M 26.89206,63.463019 v 27.859247 l 48.432669,5.047175 v -19.6423 z M 143.62135,33.048531 a 101.67559,16.252977 0 0 1 -38.70978,12.761334 101.67559,16.252977 0 0 1 -86.654278,3.044382");
		context.stroke(p);
		context.fill(p);
		context.restore();
		context.closePath();	
		context.restore();
		context.closePath();	
	}
}

function ball(x,y,radius) {
	this.x = x;
	this.y = y;
	this.radius = radius;
	this.speed = 1;
	this.angle = 0;
	this.update = function(){
		context.beginPath();
		context.fillStyle = radial_gradient1;
		context.fill();
		context.strokeStyle = radial_gradient1;
		context.lineWidth = 1;
		context.fillStyle = "rgb("+rgb()+","+rgb()+","+rgb()+")";
		context.fill();
		context.strokeStyle = radial_gradient1;
		context.lineWidth = 1;
		context.save();
		context.translate(this.x - 60, this.y - 10);
		var pathDefs= ["M 18.131331,28.62252 C 4.5189362,31.43319 26.09474,25.521565 12.847632,21.253647 -0.39947632,16.985729 13.444691,34.499662 4.2072769,24.109031 -5.0301376,13.7184 10.780551,29.456075 7.9590868,15.821748 5.1376233,2.1874209 -3.2042739,22.968971 1.1570352,9.7445978 5.5183445,-3.4797761 -0.04847835,18.154458 10.262239,8.8301395 20.572955,-0.49417843 -1.5248902,2.7539299 12.095613,-0.07019173 25.716116,-2.8943134 4.1495753,2.9474649 17.374746,7.2732485 30.599916,11.599032 16.684719,-5.8485033 26.015009,4.4885726 35.345298,14.825649 19.391258,-0.86529485 22.29955,12.738111 c 2.908293,13.603406 11.033422,-7.125645 6.765702,6.124646 -4.267721,13.250292 1.341462,-8.418103 -9.012299,0.864896 -10.3537613,9.282999 11.690773,6.084197 -1.921622,8.894867 z",
		"M 7.5257127,23.317987 C 6.7230872,-5.6229953 3.8980468,34.773346 23.841377,13.759345 43.784708,-7.2546586 3.4214162,-2.2153898 32.435679,-3.0919944 61.44994,-3.9685989 21.047707,-6.5907143 42.129652,13.246677 63.211599,33.084069 58.081179,-7.147398 58.926072,21.855481 59.770966,50.858357 62.54622,10.441448 42.629583,31.461231 22.712944,52.481015 63.074895,47.441776 34.083524,48.290631 5.092156,49.139486 45.52738,51.8972 24.417815,31.973652 3.3082481,12.050102 8.3283382,52.258972 7.5257127,23.317987 Z",
		"M 37.814433,32.496748 C 10.147462,71.454021 57.304567,-0.92884757 12.205891,14.546249 -32.892786,30.021346 48.453433,58.432646 2.7616357,44.413335 -42.930161,30.394025 40.515684,52.799107 11.842473,14.537757 c -28.673211,-38.261351 -30.377721,47.831603 -31.19222,0.121248 -0.8145,-47.710355 3.576483,38.496361 31.136231,-0.56875 C 39.346233,-24.974855 -43.37604,-0.37060812 1.9150834,-15.541611 47.206207,-30.712614 -33.410387,-0.13830507 12.1061,14.066423 57.622588,28.271151 8.6709819,-42.773242 37.29914,-4.622342 65.927299,33.528558 11.769236,-33.36429 12.448663,14.332064 13.12809,62.028418 65.481405,-6.4605238 37.814433,32.496748 Z",
		"M 56.734907,51.133535 28.79305,14.655389 57.850759,51.203282 84.364851,11.495941 57.39668,51.588512 103.12509,63.710895 58.020592,52.107278 60.09256,98.010272 57.717159,52.179836 13.067908,67.635646 Z"]
		for (var i=0;i<pathDefs.length;i++){
			var p = new Path2D(pathDefs[i]);
			context.stroke(p);
			context.fill(p);
		}
		context.restore();
		context.closePath();	

	};
  this.newPos = function() {
    this.x += this.speed * Math.sin(this.angle);
    this.y -= this.speed * Math.cos(this.angle);
  };
     this.hitsWall = function() {
		var wallLeft = 0;
		var wallRight = canvas.width;
		var wallTop = 0;
		var wallBottom = canvas.height;
		
		if (this.x < wallLeft) {
			this.x=Math.floor(this.x + 100 * Math.random()*1);
			this.angle = this.angle + 90 * Math.random()*1;		
			currentScore = currentScore + 5;
			this.radius = this.radius + 5;
			if (this.radius > 20) {
				this.radius = 5;
			}
		}
		
		if (this.x > wallRight) {
			this.x=Math.floor(this.x-100 * Math.random()*1);
			this.angle = this.angle - 90 * Math.random()*1;	
			currentScore = currentScore - 5;
			this.radius = this.radius - 5;
			if (this.radius < 20) {
				this.radius = 5;
			}
		}

		if (this.y < wallTop) {
			this.y=Math.floor(this.y+100 * Math.random()*1);
			this.angle = this.angle + 90 * Math.random()*1;		
		}
		
		if (this.y > wallBottom) {
			this.y=Math.floor(this.y-100 * Math.random()*1);
			this.angle = this.angle - 90 * Math.random()*1;
		}
		
		if (this.angle < -360) {
			this.angle = -360;
		}
		if (this.angle > 360) {
			this.angle = 360;		
		}
    };
  
    this.collidesWith= function(otherobj) {
        var collides;
	
		if (pointInCircle(this.x, this.y, otherobj.x, otherobj.y, 10)) {
			collides = true;
			currentScore = currentScore - 5;			
		} else {
            collides = false;		
		}
        return collides;
    };

}


var Area = {
	start: function() {
		this.frameNo = 0;
		this.interval = setInterval(updateArea, 200);
		
		
		},
	clear: function() {
		context.globalAlpha = 0.3;
		context.fillStyle = "#000";
		context.clearRect(0, 0, canvas.width, canvas.height);	
		
		context.globalAlpha = 0.1;
		context.globalCompositeOperation = "lighter";
		context.lineWidth = 15;
		context.strokeStyle = grd;
		var time = Date.now() / 1600;
		var mix = Math.sin(time) * Math.cos(time);
		context.beginPath();
		for (var xy,t=0; t<Math.PI*12; t+=Math.PI/32)
		{
			xy = mixf(f1(t), f2(t), mix);
			context.lineTo(xy.x * 8 + (canvas.width/2), -xy.y * 8 + (canvas.height/2));
		}
		context.closePath();
		context.stroke();
		context.globalAlpha = 0.6;

	}
}

function updateStars() {
var cx = canvas.width / 2,
    cy = canvas.height / 2;

var sat = Floor(Z * 500);
if (sat > 100) sat = 100;
for (var i=0; i<units; i++)
{
   var n = stars[i],
       xx = n.x / n.z,
       yy = n.y / n.z,
       e = (1.0 / n.z + 1) * 2;
   
   if (n.px)
   {
      context.strokeStyle = "hsl(" + ((cycle * i) % 360) + ",80%,80%)";
      context.lineWidth = e;
      context.beginPath();
      context.moveTo(xx + cx, yy + cy);
      context.lineTo(n.px + cx, n.py + cy);
      context.stroke();
   }
   
   n.px = xx;
   n.py = yy;
   n.z -= Z;
   
   if (n.z < Z || n.px > canvas.width || n.py > canvas.height)
   {
      resetstar(n);
   }
}

cycle += 0.01;
}



function updateArea() {
    var x, height, gap;
	Area.clear();
	updateStars();
	Area.frameNo +=1;
	if (bouncingballs == 0){
		console.log("game over 646");
	}
	if (Area.frameNo == 1 || everyinterval(150)) {
		x=canvas.width;
		height = Math.floor(Math.random()*(maxHeight-minHeight+1)+minHeight);
        gap = Math.floor(Math.random()*(maxGap-minGap+1)+minGap);
        obstacles.push(new component(x, 0, 10, height));
        obstacles.push(new component(x, height + gap, 10, x - height - gap));
		bouncingballs.push(new ball(50,50,10));
	}
	numberBalls.innerHTML = bouncingballs.length;
	for (i = 0; i < bouncingballs.length; i += 1){
		bouncingballs[i].newPos();
		bouncingballs[i].update();
		if (bouncingballs[i].hitsWall()){
			bouncingballs[i].update();
		}	
	}
    for (i = 0; i < obstacles.length; i += 1) {
        obstacles[i].x += -1;
		if (obstacles[i].x < -10) {
			obstacles.splice(i,1);
		} else {
			for (j = 0; j<bouncingballs.length; j++) {
				if (bouncingballs[j].collidesWith(obstacles[i])) {
					bouncingballs[j].angle = bouncingballs[j].angle + 90 * (Math.floor(Math.random()*1));
					currentScore = currentScore - 20;
				}
			
			}
			obstacles[i].update();
		}
    }
	if (currentScore > highscore) {
		updateHighScore(currentScore);
	}

	score.innerHTML = currentScore;
}

function everyinterval(n) {
    if ((Area.frameNo / n) % 1 == 0) {return true;}
    return false;
}

Area.start();

canvas.addEventListener("mousemove", function (e){
	e.preventDefault();
	e.stopPropagation();
	mouseX=parseInt(e.clientX);
	mouseY=parseInt(e.clientY);
	for(var i=0;i<bouncingballs.length;i++){	
		if (pointInCircle(mouseX,mouseY,bouncingballs[i].x-10, bouncingballs[i].y+10, 20)) {
			bouncingballs[i].x = bouncingballs[i].x - 200;
			currentScore = currentScore - 1;
		}
	}
	for (i=0; i<obstacles.length;i++) {
		distance = Math.sqrt(obstacles[i].width/2 * obstacles[i].height/2);
		if (pointInCircle(mouseX,mouseY,obstacles[i].x-30, obstacles[i].y+30, distance)) {
			obstacles[i].x +=randomInt();
			obstacles[i].y +=randomInt();
			currentScore = currentScore - 20;

		}
		
	}
});

function pointInCircle(x, y, cx, cy, radius) {
  var distancesquared = (x - cx) * (x - cx) + (y - cy) * (y - cy);
  return distancesquared <= radius * radius;
}

function updateHighScore(number){
//	e.preventDefault();
	var formData = new FormData();
	formData.append("gameid",14);
	formData.append("score",number);
	var xmlhttp = new XMLHttpRequest();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == XMLHttpRequest.DONE) {
			if (xmlhttp.status == 200) {
				label.innerHTML = "New High Score!";
				
			}
		}
	};
	xmlhttp.open("POST", "/games/highscore");
	xmlhttp.send(formData);
	return false;

}


</script>
</html>

