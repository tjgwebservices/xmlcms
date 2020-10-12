<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Add Lecturer</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>      
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/main.css'/>"/>
   </head>
   <body>
      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
       
      <div>
         <fieldset>
            <legend>Add Lecturer</legend>
            <form name="lecturer" action="" method="POST">
               School Name: <@spring.formInput "lecturerForm.lecturerName" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>




