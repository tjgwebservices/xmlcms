<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Configuration</title>
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
            <legend>Edit Configuration</legend>
            <form name="social" action="/articles/editConfiguration" method="POST">
               <br/>
               <p>Content: <textarea id="messages" name="messages" value="">${configurationEditForm.messages}</textarea></p>
               <br/>
               <p>Views: <input type="text" id="views" name="views" value="${configurationEditForm.views}" /></p><br/>
               <p>Shares: <input type="text" id="shares" name="shares" value="${configurationEditForm.shares}" /></p><br/>
               <p>Users: <input type="text" id="users" name="users" value="${configurationEditForm.users}" /></p><br/>
               <p>Date: <input type="text" id="dateTime" name="dateTime" value="${configurationEditForm.dateTime}" /></p><br/>
                <p><input type="hidden" id="id" name="id" value="${configurationEditForm.id}" /></p><br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       </main>



       
       
   <@nav.footingform />



