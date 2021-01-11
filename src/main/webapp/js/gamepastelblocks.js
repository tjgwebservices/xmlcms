'use strict';
(function () {

var main = document.getElementsByTagName("main")[0];

var canvas = document.createElement("canvas");
canvas.setAttribute("width","640");
canvas.setAttribute("height", "400");

var div = document.createElement("div");
div.appendChild(canvas);
main.appendChild(div);

var svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
svg.setAttribute("style", "border:1px solid black");
svg.setAttribute("width","640");
svg.setAttribute("height", "400");
svg.setAttribute("id","sliderControl");

var div = document.createElement("div");
div.appendChild(svg);
main.appendChild(div);


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

var minHeight = 20, maxHeight = 200, minGap = 50, maxGap = 200, obstacles = [], bouncingballs = [], currentScore = 0;


var stars = [];
for (var i=0, n; i<units; i++)
{
  n = {};
  resetstar(n, canvas);
  stars.push(n);
}
var cycle = 0;
   
   

var context = canvas.getContext("2d");

var rnd = Math.random;
function rgb() {
   return Math.floor(100 + rnd()*50).toString();
}

function component(x, y, width, height, type) {
	this.type;
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.update = function(){
		context.fillStyle = "rgb("+rgb()+","+rgb()+","+rgb()+")";
		context.fill();
		context.strokeStyle = radial_gradient1;
		context.lineWidth = 1;
		context.save();
		context.translate(this.x - 60, this.y - 10);

		var pathDefs= ["M 13.607143,36.196432 V 106.83 l 6.163057,1.30766 V 39.720661 Z",
					"M 19.7702,39.720661 27.127552,-0.7608683 V 93.117075 L 19.7702,108.13766 Z",
					"M 13.607143,36.196432 18.89881,-7.4771844 27.127552,-0.7608683 19.7702,39.720661 Z",
					"M 13.607143,106.83 18.89881,90.625 27.127552,93.117075 19.7702,108.13766 Z",
					"M 18.89881,-7.4771844 V 90.625 l 8.228742,2.492075 V -0.7608683 Z",
					"M 13.607143,36.196432 18.89881,-7.4771844 V 90.625 l -5.291667,16.205 z",
					"M 8.3154764,64.922621 V 79.87004 l 3.9155786,1.332408 V 66.545223 Z",
					"M 12.231055,66.545223 19.7702,16.028815 v 23.691846 l -7.539145,41.481787 z",
					"M 8.3154764,64.922621 13.607143,11.737019 19.7702,16.028815 12.231055,66.545223 Z",
					"M 8.3154764,79.87004 13.607143,36.196432 19.7702,39.720661 12.231055,81.202448 Z",
					"M 13.607143,11.737019 V 36.196432 L 19.7702,39.720661 V 16.028815 Z",
					"M 8.3154764,64.922621 13.607143,11.737019 V 36.196432 L 8.3154764,79.87004 Z"];
					
					

		for (var i=0;i<pathDefs.length;i++){
			var p = new Path2D(pathDefs[i]);
			context.stroke(p);
			context.fill(p);
		}
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
		context.save();
		context.translate(this.x - 60, this.y - 10);
					
		var pathDefs= ["m 11.688183,56.87279 v 57.87614 l 15.431175,-1.37198 V 63.10192 Z",
		"m 11.688183,114.74893 46.077838,9.45155 15.968417,-4.44902 -46.61508,-6.37451 z",
		"m 27.119358,63.10192 46.61508,-28.941932 v 85.591472 l -46.61508,-6.37451 z",
		"M 11.688183,56.87279 57.766021,13.960227 73.734438,34.159988 27.119358,63.10192 Z",
		"M 57.766021,13.960227 V 124.20048 l 15.968417,-4.44902 V 34.159988 Z",
		"M 11.688183,56.87279 57.766021,13.960227 V 124.20048 l -46.077838,-9.45155 z"];

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
      resetstar(n, canvas);
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
	var mouseX=parseInt(e.clientX);
	var mouseY=parseInt(e.clientY);
	for(var i=0;i<bouncingballs.length;i++){	
		if (pointInCircle(mouseX,mouseY,bouncingballs[i].x-10, bouncingballs[i].y+10, 20)) {
			bouncingballs[i].x = bouncingballs[i].x - 200;
			currentScore = currentScore - 1;
		}
	}
	for (i=0; i<obstacles.length;i++) {
		var distance = Math.sqrt(obstacles[i].width/2 * obstacles[i].height/2);
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
	formData.append("gameid",12);
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



})();


