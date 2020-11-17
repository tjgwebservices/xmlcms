<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
<!DOCTYPE html>
<html>
   <head>
      <title>Games Demo - Tank Blocks</title>
       <@nav.cssheading />
<style>
canvas {
    border: 1px solid #899de2;
    background-color: #acbae0;
    width: 90%;
}
</style>
    
   </head>
   <body>
   <@nav.navigation />

</head>
<body>

<main>

</main>
<div>
</div>
<footer>
<h4>TJG Web Services - Tank Blocks</h4>
<p>Click on the screen to change direction to avoid obstacles.</p>
<p>Add custom built HTML5 games to your website with developers from TJG Web Services.</p>
<p>Advertise on game sites with TJG Web Services Consulting Group.</p>

</footer>

</body>
<script>
var highscore = <#if highscore??>${highscore}</#if>;

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
ballsLabel.innerHTML = "Number of Blocks:";
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

function bullet(x, y, radius) {
	this.x = x;
	this.y = y;
	this.radius = radius;
	this.update = function(){
	context.beginPath();
	context.fillStyle = radial_gradient1;
	context.fill();
	context.strokeStyle = radial_gradient1;
	context.arc(this.x, this.y, this.radius, 0, 2 * Math.PI);
	context.stroke();
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
		var p = new Path2D("M 40.065475,15.785714 C 9.8273808,12.005952 9.8273808,12.005952 9.8273808,12.005952 H 7.5595238 11.339286 L 36.285715,15.785714 12.85119,12.761905 14.363095,8.9821426 6.8035714,13.517857 35.52976,15.029762 12.85119,9.738095 8.315476,11.25 9.0714284,18.809524 Z");
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
		var p = new Path2D("m 29.452108,26.112054 c -1.622544,6.99741 -8.979659,11.199578 -15.86798,9.501452 C 6.1405651,33.778501 1.6841607,25.911907 3.5637726,18.553244 5.5634116,10.724675 13.866189,6.0474335 21.624574,8.0756018 29.793436,10.211075 34.666842,18.896912 32.512231,27.005541 30.260281,35.48049 21.231627,40.531004 12.809702,38.265854 5.6550709,36.341554 0.60823996,29.627497 0.61991329,22.238458");
		context.stroke(p);
		context.fill(p);
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

	},
	stop: function() {
		clearInterval(this.interval);
		numberBalls.innerHTML = "Game Over";
		
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
	if (Area.frameNo == 1 || everyinterval(150)) {
		x=canvas.width;
		height = Math.floor(Math.random()*(maxHeight-minHeight+1)+minHeight);
        gap = Math.floor(Math.random()*(maxGap-minGap+1)+minGap);
        obstacles.push(new component(x, 0, 10, height));
        obstacles.push(new component(x, height + gap, 10, x - height - gap));
		bouncingballs.push(new ball(50,50,10));
	}
	if (Area.frameNo == 1 || everyinterval(25)) {
		for (i = 0; i < obstacles.length; i += 1) {
			bullets.push(new bullet(obstacles[i].x, obstacles[i].y, 3));
		}
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
	
	for (i = 0; i < bullets.length; i +=1) {
		bullets[i].x +=-5;
		if (bullets[i].x < 10) {
			bullets.splice(i,1);
		} else {
			for (j=0;j<bouncingballs.length; j++){
				if (bouncingballs[j].collidesWith(bullets[i])) {
					bouncingballs.splice(j,1);
					currentScore = currentScore - 100;
				}
			}
			bullets[i].update();
		}
	}
	if (bouncingballs == 0){
		Area.stop();
		console.log("game over 694");
		
	}
	if (currentScore > highscore) {
		updateHighScore(currentScore);
                highscore=currentScore;
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
	formData.append("gameid",10);
	formData.append("score",number);
	var xmlhttp = new XMLHttpRequest();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == XMLHttpRequest.DONE) {
			if (xmlhttp.status == 200) {
				label.innerHTML = "New High Score!";
				
			}
		}
	};
	xmlhttp.open("POST", "/game/highscore");
	xmlhttp.send(formData);
	return false;

}
</script>
</html>

