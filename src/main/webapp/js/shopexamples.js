var main = document.getElementsByTagName("main")[0].getElementsByTagName("main")[0].getElementsByTagName("main")[0];

var colors={
    colornames:{
        aqua: '#00ffff', black: '#000000', blue: '#0000ff', fuchsia: '#ff00ff',
        gray: '#808080', green: '#008000', lime: '#00ff00', maroon: '#800000',
        navy: '#000080', olive: '#808000', purple: '#800080', red: '#ff0000',
        silver: '#c0c0c0', teal: '#008080', white: '#ffffff', yellow: '#ffff00'
    }};

var changeColors = function(colorname) {
		return colors.colornames[colorname];
}

var createCanvas = function() {
	var image = document.createElement("img");
	image.style.width="100px";
	image.style.height="100px";
	image.setAttribute("src","images/tjgapis0001.jpg");
	return image;
}

var createSVG = function(){
	var svgTag = document.createElement('svg');
	svgTag.style.width = "100px";
	svgTag.style.height = "100px";
	var div = document.createElement('div');
	div.appendChild(svgTag);
	return div;
}

exampledivcss = 'margin: 3px; '
					+ 'border: 1px solid black; '
					+ 'background: rgb(208, 241, 171); '
					+ 'color:#fff'
			;
itempricecss = 'text-align: center; '
					+ 'font-weight: bold; '
					+ 'color: rgb(13, 31, 17)'
			;
itempricetext = "$9.99";
itemtextcss = 'text-align: center; '
					+ 'font-weight: bold; '
					+ 'color: rgb(13, 31, 17)'
			;


var guidesItems = [
	["Software Testing",
	 "https://www.amazon.com/Software-Testing-Practical-Students-Professionals-ebook/dp/B01HJ2VFU0/",
	 "/blog/images/software_testing.jpg"],
	["Test Driven Development",
	 "https://www.amazon.com/Test-Driven-Development-Software-Evaluation-ebook/dp/B01JKDYWCY/",
	 "/blog/images/test_driven_development1.jpg"],
	["Test Driven Development",
	 "https://www.amazon.com/Test-Driven-Development-Software-Performance-Benchmarking-ebook/dp/B01JVF1BUM/",
	 "/blog/images/test_driven_development2.jpg"],
	["Big Data in Education",
	 "https://www.amazon.com/Big-Data-Education-Technology-International-ebook/dp/B01K28N3P8/",
	 "/blog/images/big_data_in_education.jpg"],
	["Challenges in Educational Software Development",
	 "https://www.amazon.com/Challenges-Educational-Software-Development-Engineering-ebook/dp/B01HD9SD5E/",
	 "/blog/images/challenges_in_educational_software_development.jpg"],
	["II Data Science - Promotional CD",
	 "https://www.amazon.com/II-Data-Science/dp/B08DSSCSBK/",
	 "/blog/images/ii_data_science.jpg"]];


var getExamples = function () {

	var examples = new Array();
	for (var i = 0; i < topics.length; i++) {
		var example = topics[i];
		for (var j = 0; j < example.length; j++) {
			examples[examples.length] = example[j];
		}
	}

	var xml = document.createElement('ul');
	xml.setAttribute('id', 'model-examples');
//	for (var i = 0; i < topics.length; i++) {
	for (var i = 0; i < guidesItems.length; i++) {
		try {
			var list_item = document.createElement('li');
			var example_div = document.createElement('div');
			example_div.style.cssText = exampledivcss;
			var itemTitle = document.createElement("p");
			itemTitle.style.cssText = itemtextcss;
			var itemStoreLink = document.createElement("a");
			itemStoreLink.setAttribute("href",guidesItems[i][1]);
			itemStoreLink.setAttribute("target","_top");
			itemStoreLink.innerHTML = guidesItems[i][0];
			itemTitle.appendChild(itemStoreLink);
			var itemImage = document.createElement("img");
			itemImage.setAttribute("src",guidesItems[i][2]);
			itemImage.setAttribute("alt",guidesItems[i][0]);
			itemImage.setAttribute("title",guidesItems[i][0]);
			var itemLink = document.createElement("a");
			itemLink.setAttribute("href",guidesItems[i][1]);
			itemLink.setAttribute("target","_top");
			itemLink.appendChild(itemImage);			
			example_div.appendChild(itemTitle);			
			example_div.appendChild(itemLink);
			//example_div.appendChild(document.createTextNode(topics[i]));
			//example_div.appendChild(createCanvas());
			list_item.appendChild(example_div);
			xml.appendChild(list_item);

		} catch(e){}
	}
	return xml;

}


var getHelpFeatures = function() {
	var xml = document.createElement('div');
	var title = document.createElement("h2");
	title.innerHTML = "Help";
	xml.appendChild(title);
	for (var i = 0; i < helptext.length; i++) {
		var para = document.createElement('p');
		para.innerHTML = helptext[i];
		xml.appendChild(para);
	}
	return xml;
}


var tabbuttons = document.getElementsByClassName("tabbuttons")[0];
var tabs = tabbuttons.getElementsByTagName("a");

var createModelUl = function(models1) {
		var mainUl = document.createElement("ul");
		for (i=0;i<models1.length;i++) {
			var lli = document.createElement("li");
			lli.innerHTML = models1[i];
			mainUl.appendChild(lli);
		}
		return mainUl;
}

var create2ModelUl = function(models1) {
		var mainUl = document.createElement("ul");
		for (i=0;i<models1.length;i++) {
			var lli = document.createElement("li");
			lli.innerHTML = models1[i][0] + ' - ' + models1[i][1];
			mainUl.appendChild(lli);
		}
		return mainUl;
}

var create4ModelUl = function(models1) {
		var mainUl = document.createElement("ul");
		for (i=0;i<models1.length;i++) {
			var lli = document.createElement("li");
			var ph2li = document.createElement("h2");
			ph2li.innerHTML = models1[i][0];
			var ph4li = document.createElement("h4");
			ph4li.innerHTML = models1[i][1];
			var sli = document.createElement("span");
			var ili = document.createElement("img");
			ili.setAttribute("src",models1[i][2]);
			ili.setAttribute("alt",models1[i][0]);
			ili.setAttribute("title",models1[i][0]);
			var pli = document.createElement("p");
			pli.innerHTML = models1[i][3];
			lli.appendChild(ph2li);
			lli.appendChild(ph4li);
			lli.appendChild(ili);
			lli.appendChild(pli);			
			mainUl.appendChild(lli);
		}
		return mainUl;
}

var createMainHeading = function(titletext) {
		var mainP = document.createElement("h4");
		mainP.innerHTML = titletext;
		return mainP;
}

var getMainModels = function(){
	main.appendChild(createMainHeading("Products"));
	main.appendChild(create4ModelUl(products));
	var buynowel = document.createElement("div");
	buynowel.setAttribute("id","buynowitem");
	main.appendChild(buynowel);
	var paypaldiv = document.createElement("div");
	paypaldiv.setAttribute("id","paypal-button-container");
	main.appendChild(paypaldiv);
	var reportslink = document.createElement("div");
	reportslink.setAttribute("id","reportslink");
	reportslinka = document.createElement("a");
	reportslinka.innerHTML = "Reports";
	reportslinka.href = "reports/";
	reportslink.appendChild(reportslinka);
	main.appendChild(reportslink);
	main.appendChild(createMainHeading("Analysis Services"));
	main.appendChild(create2ModelUl(analyses));
	main.appendChild(createMainHeading("Training Sessions"));
	main.appendChild(create2ModelUl(sessions));
	main.appendChild(createMainHeading("Services"));
	main.appendChild(create2ModelUl(services));
	main.appendChild(createMainHeading("Additional Services"));
	main.appendChild(createModelUl(models1));
	main.appendChild(createModelUl(models2));
	main.appendChild(create2ModelUl(discussiontopics));
	var mainModels = models2;
	var modellinks = document.createElement("ul");
	for (i=0;i<mainModels.length;i++) {
		var ma = document.createElement("a");
		ma.setAttribute('href', 'javascript:showModal("'+mainModels[i]+'");');
		ma.innerHTML = mainModels[i];
		var lli = document.createElement("li");
		lli.appendChild(ma);
		modellinks.appendChild(lli);
	}
	main.appendChild(modellinks);
}

getMainModels();

tabs[0].onclick = function() {
		main.innerHTML="";
		getMainModels();
		tabs[1].parentElement.classList.remove("active");
		tabs[2].parentElement.classList.remove("active");
		arr = tabs[0].parentElement.className.split(" ");
		if (arr.indexOf("active") == -1) {
			tabs[0].parentElement.className += " " + "active";
		}
	
}


tabs[1].onclick = function() {
		main.innerHTML="";
		main.appendChild(getExamples());
		tabs[0].parentElement.classList.remove("active");
		tabs[2].parentElement.classList.remove("active");
		arr = tabs[1].parentElement.className.split(" ");
		if (arr.indexOf("active") == -1) {
			tabs[1].parentElement.className += " " + "active";
		}
	
}


tabs[2].onclick = function() {
		main.innerHTML="";
		main.appendChild(getHelpFeatures());
		tabs[0].parentElement.classList.remove("active");
		tabs[1].parentElement.classList.remove("active");
		arr = tabs[2].parentElement.className.split(" ");
		if (arr.indexOf("active") == -1) {
			tabs[2].parentElement.className += " " + "active";
		}
	
}

var appendFormType = function(type,element){
	var xhr0 = new XMLHttpRequest();
	xhr0.open('GET', 'views/pages/forms.php?formtype='+type, true);
	xhr0.onreadystatechange= function() {
			if (this.readyState!==4) return;
			if (this.status!==200) return;
			var newSpan = document.createElement("article");
			newSpan.innerHTML = this.responseText; 
			element.appendChild(newSpan);
	};
	xhr0.send();
}

//appendFormType('title',document.getElementById('formEl'));
//appendFormType('welcome',document.getElementById('formEl'));
//appendFormType('buynowitem',document.getElementById('buynowitem'));

var showModal = function (text) {
	var exampleModal = document.getElementById("viewExample");
	exampleModal.style.display='block';
	exampleModal.getElementsByTagName('p')[0].innerHTML = text;	
}
