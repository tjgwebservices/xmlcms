<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE html>
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinksadmin />
   <@nav.adminsection />
      <h2>Administration Page</h2>
      <#if message??>
      <h2>${message}</h2>
      </#if>
   <@nav.footing />




