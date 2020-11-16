(function() {

var figures = document.getElementsByTagName("figure");
var buttons = document.getElementsByTagName("ol")[0].getElementsByTagName("li");
buttons[0].onclick = function (){
	left();
};

buttons[2].onclick = function (){
	right();
}

var fi = 1;
var fl = figures.length;

var right = function() {
	hideall();
	fi++;
	if (fi>=fl) {
		fi=0;
	}
	figures[fi].style.display="block";
	clearInterval(changeint);	
};

var left = function() {
	hideall();
	fi--;
	if (fi<=-1) {
		fi=fl-1;
	}
	figures[fi].style.display="block";
	clearInterval(changeint);	
};

var hideall = function() {
	for (var i = 0; i <  fl; i++) {
		figures[i].style.display="none";
	}

};


var changeint = setInterval(
	function(){
		hideall();
		fi++;
		if (fi>=fl) {
			fi=0;
		}
		figures[fi].style.display="block";
	},3000);

})();

