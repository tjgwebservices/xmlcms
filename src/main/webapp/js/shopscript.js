var contentleft = document.getElementsByTagName("figure")[0].getElementsByTagName("div")[0];
var contentright = document.getElementsByTagName("figure")[1].getElementsByTagName("div")[0];
var main = document.getElementsByTagName("main")[0].getElementsByTagName("main")[0].getElementsByTagName("main")[0];
var toggleArray = [0,1,0,0];

var summaryWidth = function() {
	var sw = window.getComputedStyle(document.getElementsByTagName("summary")[0], ':before').content;
	sw = String(sw).replace("px","");
	sw = sw.replace(/['"]+/g, '');
	return sw;
}
var getSummaryWidth = function() {
	return parseInt(summaryWidth());
}
var getAsideWidth = function () {
	var asideWidth = 180;
	sumwidth = getSummaryWidth();
	if (sumwidth > 599) {
		asideWidth = 180;
	} else if (sumwidth > 299 && sumwidth < 599) {
		asideWidth = 75;
	} else if (sumwidth > 249 && sumwidth < 299) {
		asideWidth = 50;
	} else if (sumwidth < 249) {
		asideWidth = 40;
	}
	return asideWidth;
}

var mainWidth = function() {
		var leftContent = toggleArray[0]*getAsideWidth();
		var rightSide = (toggleArray[1]==1)?0:1;
		var rightContent = rightSide*getAsideWidth();
		var mainWidth = getSummaryWidth() - leftContent + rightContent;
		return mainWidth;
}

contentleft.onclick = function(){
	var resizer = this.parentNode.previousElementSibling;
	if (toggleArray[0]==0) {
		resizer.style.display="block";
		//resizer.style.left=""+getAsideWidth()+"px";
		//main.style.left=""+(getAsideWidth()+10)+"px";
		toggleArray[0]=1;
		width = mainWidth();
	} else {
		resizer.style.display="none";
		//resizer.style.left="0px";
		//main.style.left="10px";
		toggleArray[0]=0;	
	}
	main.style.width = mainWidth() + "px";
  };

contentleft.click();
  
contentright.onclick = function(){
	var resizer = this.parentNode.nextElementSibling;
	if (toggleArray[1]==0) {
		var leftContent = toggleArray[0]*getAsideWidth();
		var rightContent = mainWidth();
		var totalContent = leftContent + rightContent;
		var mw = mainWidth();
		var aw = getAsideWidth();
		var tw = mw - aw;
		//if (toggleArray[0]==1) {
			//resizer.style.left=""+mw+"px";
			//resizer.style.display="block";
		//} else {
			//resizer.style.left=""+tw+"px";		
		//}
		resizer.style.display="block";
		toggleArray[1]=1;
	} else {
		var leftContent = toggleArray[0]*getAsideWidth();
		var rightContent = getAsideWidth() + mainWidth();
		var totalContent = leftContent + rightContent;
		//resizer.style.left=""+totalContent+"px";
		resizer.style.display="none";
		toggleArray[1]=0;	
	}
	main.style.width = mainWidth() + "px";
  };
