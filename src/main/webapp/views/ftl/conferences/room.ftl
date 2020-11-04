<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE HTML>
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
   <@nav.conference />
      <#if message??>
      <h2>${message}</h2>
      </#if>
      <#if conferenceName??>
      <h2>${conferenceName} Experimental, Not Functional</h2>
      </#if>

   <@nav.mainsection />
   <@nav.footingconference />