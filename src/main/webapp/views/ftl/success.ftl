<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Upload Files</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>      
   </head>
   <body>
      <#if fileName??>
      <div style="font:28px;">
         The file ${fileName} was uploaded successfully.
      </div>
      </#if>
      <#if message??>
      <div style="font:28px;">
         Success:
         ${message}
      </div>
      </#if>
       
       
       
   </body>
    
</html>
