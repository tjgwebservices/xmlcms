<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE HTML>
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
   <@nav.mainsection />
      <h1>Welcome</h1>
      <#if message??>
      <h2>${message}</h2>
      </#if>
      <a href="<@spring.url '/articleList'/>">Article List</a>  
   </body>
</html>
