<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Human Resources Group</title>
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
            <legend>Add Human Resources Group</legend>
            <form name="hrGroup" action="" method="POST">
               Group Name: <@spring.formInput "hrGroupForm.groupName" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
   </body>
</html>
