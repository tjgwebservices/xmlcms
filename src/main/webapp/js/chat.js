(function () {

var token = (Math.floor(Math.random() * 9999)).toString();
var chatdiv = document.createElement("div");
chatdiv.setAttribute("class","chat");
var requestanchor = document.createElement("a");
requestanchor.setAttribute("href","#");
requestanchor.innerHTML = "Request Service";
var chatTitle = document.createElement("h1");
chatTitle.innerHTML = "Chat Application (Experimental) - Alpha Test";
var button1 = document.createElement("button");
button1.innerHTML = "Close";
var spans = [];
spans[0] = document.createElement("span");
spans[0].innerHTML = "Online";
spans[1] = document.createElement("span");
spans[1].innerHTML = "Offline";
var form1 = document.createElement("form");
var textarea1 = document.createElement("textarea");
textarea1.setAttribute("name","messagetext");
textarea1.setAttribute("id","messagetext");
textarea1.setAttribute("disabled","disabled");
textarea1.setAttribute("placeholder","Click request to activate chat");
var button2 = document.createElement("button");
button2.innerHTML = "Send";
var div2 = document.createElement("div");
var textdiv = document.createElement("div");
textdiv.setAttribute("class","chattext");
var updatep = document.createElement("p");
updatep.setAttribute("id","updatemessage");
var textul = document.createElement("ul");
var textli1 = document.createElement("li");
textul.appendChild(textli1);
textdiv.appendChild(updatep);
textdiv.appendChild(textul);

chatdiv.appendChild(requestanchor);
chatdiv.appendChild(chatTitle);
chatdiv.appendChild(button1);
chatdiv.appendChild(spans[0]);
chatdiv.appendChild(spans[1]);
form1.appendChild(textarea1);
chatdiv.appendChild(form1);
chatdiv.appendChild(button2);
chatdiv.appendChild(div2);
var chatbody=document.querySelector("#chatBox");
chatbody.appendChild(chatdiv);
chatbody.appendChild(textdiv);
		
var ring = document.querySelector("div.chat a");
var textarea = document.querySelector("div.chat textarea");
var sendButton = document.querySelector("div.chat button:nth-of-type(2)");
var form = document.querySelector("div.chat form");
var textbox = document.querySelector("div.chattext > ul");

ring.addEventListener("click", 
function(e){
        e.preventDefault();
        var r = new Request();
        r.activatePoll();
        var formData = new FormData();
        formData.append("userchat","conversationstart");
        formData.append("conversationid",token);
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == XMLHttpRequest.DONE) {   
                        if (xmlhttp.status == 200) {
                                updateConversation(xmlhttp.responseText);
                                textarea.disabled = false;
                                textarea.setAttribute("placeholder","Please enter your message here...");
                        }
                        else if (xmlhttp.status == 400) {
                        }
                        else {
                        }
                }
        };
        xmlhttp.open("POST", "chat/newConversation");
        xmlhttp.send(formData);
        return false;
        }
,false);

sendButton.addEventListener("click", 
function(e){
        e.preventDefault(); 
        var formData = new FormData(form);
        formData.append("userchat","conversationcontinue");
        formData.append("conversationid",token);
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == XMLHttpRequest.DONE) {   
                        if (xmlhttp.status == 200) {
                                updateConversation(xmlhttp.responseText);
                                textarea.value = "";
                        }
                        else if (xmlhttp.status == 400) {
                        }
                        else {
                        }
                }
        };
        xmlhttp.open("POST", "chat/continueConversation");
        xmlhttp.send(formData);
        return false;
        }
,false);


function updateConversation(response) {
    if (response==""){
        console.log("no response");
    } else {
        var data = response.split("\n")[1];
        var dataJson = data.substring(5,data.length)
        var array1 = JSON.parse(dataJson);
        if (array1.length> 0) {
                /*
                while( textbox.firstChild ){
                  textbox.removeChild( textbox.firstChild );
                }*/
                for (var i = 0;i < array1.length; i++) {
                        var tli = document.createElement("li");
                        if (array1[i]["to"]=="0") {
                                tli.setAttribute("class","sent");
                        } else {
                                tli.setAttribute("class","from");								
                        }
                        var messageText="<p><span>"+array1[i]["time"]+"</span>"+array1[i]["message"]+"</p>";
                        tli.innerHTML = messageText;
                        textbox.appendChild(tli);
                }
        }
    }

}

function Request() {

this.poll = false;

this.activatePoll = function () {
    this.poll = true;
    this.runPoll();
};

this.disablePoll = function () {
    this.poll = false;
};

this.runPoll = function () {
    var self = this;
    var poll = setInterval(function () {
		var formData = new FormData();
		formData.append("userchat","conversationcontinue");
		formData.append("conversationid",token);
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == XMLHttpRequest.DONE) {
				if (xmlhttp.status == 200) {
					updateConversation(xmlhttp.responseText);
				}
				else if (xmlhttp.status == 400) {
				}
				else {
				}
			}
		};
		xmlhttp.open("POST", "chat/checkConversation");
		xmlhttp.send(formData);
		return false;
    }, 30000);
};
}


})();