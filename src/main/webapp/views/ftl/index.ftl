<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
<#import "/templatelinks.ftl" as templ/>
 
<!DOCTYPE HTML>
<html>
    <head>
        <@nav.cssheading />
          <link rel="stylesheet"
               type="text/css" href="<@spring.url '/css/conference.css'/>"/>
    </head>
   <body>
    <div id="modalwindow" class="modal">
      <div class="modal-content">
        <div class="backpadding">
          <i onclick="document.getElementById('modalwindow').style.display='none'">X</i>
          <h2>Conference Series</h2>
          <p><@templ.templatetitle /></p>
          <p id="message">Email for info</p>
          <button type="button" onclick="document.getElementById('modalwindow').style.display='none'">Conference Series</button>
          <a href="//tjgwebservices.com/conferences">Learn More</a>
        </div>
      </div>
    </div>
   <@nav.navigation />
   <@nav.sidelinks2 />
   <@nav.mainsection />
   <@nav.chat />
   <@nav.book />
      <h1>Welcome</h1>
      <#if message??>
      <h2>${message}</h2>
      </#if>
   <@nav.footinghome />
