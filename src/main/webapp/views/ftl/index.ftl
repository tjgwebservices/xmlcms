<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE HTML>
<html>
   <@nav.heading />
   <body>
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
