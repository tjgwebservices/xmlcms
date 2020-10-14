<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE HTML>
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
   <@nav.report />
   <@nav.mainsection />
      <#if conferenceName??>
      <h2>${conferenceName}</h2>
      </#if>
      <h2>Reports</h2>
      <#if message??>
      <h2>${message}</h2>
      </#if>
   <@nav.footing />



