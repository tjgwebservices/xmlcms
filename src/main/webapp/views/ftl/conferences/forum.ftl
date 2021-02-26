<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
<#import "/templatelinks.ftl" as templ/> 
<!DOCTYPE html>
<html>
    <head>
        <@nav.cssheading />
          <link rel="stylesheet"
               type="text/css" href="<@spring.url '/css/dataforum.css'/>"/>
    </head>

    <body>
    <@nav.navigation />
    <@nav.sidelinks />
    <@nav.forum />
<div class="container">
      <header class="header">
        <div class="logo-container">
          <h1 class="logo-text">
            <span class="logo-highlight"><@templ.templatetitle /></span>
          </h1>
        </div>
      </header>
      <div class="content-container">
        <div class="active-users-panel" id="active-user-container">
          <h3 class="panel-title">Active Users:</h3>
        </div>
        <div class="video-chat-container">
          <h2 class="talk-info" id="talking-with-info">
            Select active user on the left menu.
          </h2>
          <div class="video-container">
            <video autoplay="" class="remote-video" id="remote-video"></video>
            <video autoplay="" muted="" class="local-video" id="local-video"></video>
          </div>
        </div>
      </div>
    </div>

   <@nav.mainsection />
      <#if conferenceName??>
      <h2>${conferenceName}</h2>
      </#if>
      <h2>Conference Forum</h2>
      <#if message??>
      <h2>${message}</h2>
      </#if>
   <@nav.footingdataforum />



