<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Configuration</title>
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
            <legend>Add Configuration</legend>
            <form name="configuration" action="" method="POST">
               <br/>
               <p>Messages: <textarea id="messages" name="messages" value=""></textarea></p>
               <br/>
               <p>Views: <@spring.formInput "configurationForm.views" "" "text"/></p><br/>
               <p>Shares: <@spring.formInput "configurationForm.shares" "" "text"/></p><br/>
               <p>Users: <@spring.formInput "configurationForm.users" "" "text"/></p><br/>
               <p>Published: <@spring.formInput "configurationForm.dateTime" "" "text"/></p><br/>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       </main>
   <@nav.footingform />


