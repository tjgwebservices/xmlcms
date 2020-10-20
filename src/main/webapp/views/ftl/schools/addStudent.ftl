<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title><#if titleMessage??>${titleMessage}</#if></title>
       <@nav.cssheading />
   </head>
   <body>
   <@nav.navigation />
      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
       
      <div>
         <fieldset>
            <legend><#if titleMessage??>${titleMessage}</#if></legend>
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


