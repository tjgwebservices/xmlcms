var aside1 = document.getElementsByTagName("aside")[0];
var aside2 = document.getElementsByTagName("aside")[1];

aside1.innerHTML = "";
var lefttext = document.createElement("h2");
lefttext.innerHTML = "API Store";
var leftlinks = document.createElement("ul");
for (i=0; i<leftcomponents.length; i++) {
	var lli = document.createElement("li");
	lli.innerHTML = leftcomponents[i];
	leftlinks.appendChild(lli);
}
aside1.appendChild(lefttext);
aside1.appendChild(leftlinks);



aside2.innerHTML = "";
var righttext = document.createElement("h2");
righttext.innerHTML = "Programming";
var rightlinks = document.createElement("ul");
for (i=0;i<rightcomponents.length; i++) {
	var lli = document.createElement("li");
	lli.innerHTML = rightcomponents[i];
	rightlinks.appendChild(lli);
}
aside2.appendChild(righttext);
aside2.appendChild(rightlinks);

var verticalarray = [0,0];
var topclosel = document.createElement("span");
aside1.appendChild(topclosel);
topclosel.onclick = function(){
	if(verticalarray[0]==1) {
		lefttext.style.display ="block";
		leftlinks.style.display = "block";
		verticalarray[0]=0;
	} else {
		lefttext.style.display ="none";
		leftlinks.style.display = "none";
		verticalarray[0]=1;		
	}
}

var topcloser = document.createElement("span");
aside2.appendChild(topcloser);
topcloser.onclick = function(){
	if(verticalarray[1]==1) {
		righttext.style.display = "block";
		rightlinks.style.display = "block";
		verticalarray[1]=0;
	} else {
		righttext.style.display = "none";
		rightlinks.style.display = "none";
		verticalarray[1]=1;		
	}
}



var rightAsideEl=document.createElement("article");
rightAsideEl.setAttribute("id","formEl");
aside2.appendChild(rightAsideEl);

