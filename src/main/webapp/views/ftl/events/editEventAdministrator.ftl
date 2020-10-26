<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Event Administrator</title>
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
            <legend>Edit Event Administrator</legend>
            <form name="event" action="/events/editEventAdministrator" method="POST">
               <p>Name: <input type="text" id="administratorName" name="administratorName" value="${eventEditAdministratorForm.administratorName}" /></p><br/>
               <p>Title: <input type="text" id="title" name="title" value="${eventEditAdministratorForm.title}" /></p><br/>
               <p>Sub Title: <input type="text" id="subTitle" name="subTitle" value="${eventEditAdministratorForm.subTitle}" /></p><br/>
               <p>Phone or Email: <input type="text" id="contactInfo" name="contactInfo" value="${eventEditAdministratorForm.contactInfo}" /></p>
            <select id="eventId" name="eventId">
            <#if events??>

                <#list events as event>
                    <option value="${event.id}">${event.title}</option>
                </#list>
            </#if>
            </select>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
   </body>
    
</html>




