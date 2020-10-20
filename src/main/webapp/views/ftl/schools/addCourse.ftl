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
            <form name="course" action="" method="POST">
               Course Name: <@spring.formInput "courseForm.courseName" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>



