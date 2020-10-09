<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Add Student</title>
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
            <legend>Add Student</legend>
            <form name="student" action="" method="POST">
               Last Name: <@spring.formInput "studentForm.lastName" "" "text"/>    <br/>
               First Name: <@spring.formInput "studentForm.firstName" "" "text"/>    <br/>
               Course: <@spring.formInput "studentForm.courseId" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>


