<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Edit Human Resources Group</title>
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
            <legend>Edit Human Resources Group</legend>
            <form name="hrGroup" action="" method="POST">
               Group Name: <@spring.formInput "hrGroupForm.groupName" "hrGroupEditForm.groupName" "text"/>    <br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
   </body>
</html>

