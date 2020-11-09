<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Human Resources Group</title>
   <@nav.cssheading />

   </head>
   <body>
   <@nav.navigation />
   <main>
      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
       
      <div>
         <fieldset>
            <legend>Edit Human Resources Group</legend>
            <form name="hrGroup" action="/hr/editHrGroup" method="POST">
               <p>Group Name: <input type="text" id="groupName" name="groupName" value="${hrGroupEditForm.groupName}" />    <br/>
               <p><input type="hidden" id="id" name="id" value="${hrGroupEditForm.id}" />    <br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       </main>


   <@nav.footing />


