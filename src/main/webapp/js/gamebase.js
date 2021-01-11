'use strict';

var main = document.getElementsByTagName("main")[0];

var canvas = document.createElement("canvas");
canvas.setAttribute("width","640");
canvas.setAttribute("height", "400");

var div = document.createElement("div");
//div.style.float= "left";
//div.style.width = "50%";

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


var Rnd = Math.random,
    Sin = Math.sin,
    Floor = Math.floor;

var warpZ = 16,
    units = 200,
    Z = 0.025 + (1/25 * 2);


function resetstar(a, canvas)
{
   a.x = (Rnd() * canvas.width - (canvas.width * 0.5)) * warpZ;
   a.y = (Rnd() * canvas.height - (canvas.height * 0.5)) * warpZ;
   a.z = warpZ;
   a.px = 0;
   a.py = 0;
}
var context = canvas.getContext("2d");


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
   return Math.floor(100 + rnd()*10).toString();
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


function pointInCircle(x, y, cx, cy, radius) {
  var distancesquared = (x - cx) * (x - cx) + (y - cy) * (y - cy);
  return distancesquared <= radius * radius;
}

var updatePoll = new Date();

function checkPoll(){
    var currentTime = new Date();
    if (currentTime>updatePoll.getTime()+1000*5){
        updatePoll = new Date();
        return true;
    } else {
        return false;
    }
}

