<#import "/spring.ftl" as spring/>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Error Message</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>
   </head>
    
   <body>
      <h1>Error Page</h1>
      <#if error??>
      <h2>${error}</h2>
      </#if>
     
          
       
   </body>
    
</html>

