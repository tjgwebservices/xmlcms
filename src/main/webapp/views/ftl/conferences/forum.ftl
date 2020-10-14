<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE HTML>
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
   <@nav.forum />
   <@nav.mainsection />
      <#if conferenceName??>
      <h2>${conferenceName}</h2>
      </#if>
      <h2>Conference Forum</h2>
      <#if message??>
      <h2>${message}</h2>
      </#if>
   <@nav.footing />



