<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE HTML>
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
   <@nav.conferenceroom />
   <@nav.mainsection />
      <#if conferenceName??>
      <h2>${conferenceName}</h2>
      </#if>
      <#if message??>
      <h2>${message}</h2>
      </#if>
      <div>
        <button id="startcall" type="button">Start Call</button>
      </div>
      <div id="conferenceroom">
      </div>
   <@nav.footingconferenceroomcall />

