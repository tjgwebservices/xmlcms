<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Upload Files</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>      
   </head>
   <body>
      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
       
      <div>
         <fieldset>
            <legend>Upload Files</legend>
            <form name="fileUpload" enctype="multipart/form-data" modelAttribute="fileUpload" action="" method="POST">
               File Name: <@spring.formInput "uploadForm.filename" "" "filename"/>    <br/>
               File: <@spring.formInput "uploadForm.file" "" "file"/>    <br/>
               <input type="submit" value="Upload" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>
