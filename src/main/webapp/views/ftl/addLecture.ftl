<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Add Lecture</title>
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
            <legend>Add Lecture</legend>
            <form name="lecture" enctype="multipart/form-data" modelAttribute="fileUpload" action="" method="POST">
               File Name: <@spring.formInput "lectureForm.lectureName" "" "filename"/>    <br/>
               File: <@spring.formInput "lectureForm.lecturePoster" "" "file"/>    <br/>
               <input type="submit" value="Upload" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>
