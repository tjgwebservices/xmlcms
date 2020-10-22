<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Event Administrator</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/main.css'/>"/>
    
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
            <form name="event" action="/hr/editEventAdministrator" method="POST">
               <p>Name: <input type="text" id="administratorName" name="administratorName" value=${"eventEditAdministratorForm.administratorName}" /></p><br/>
               <p>Title: <input type="text" id="title" name="title" value="${eventEditAdministratorForm.title}" /></p><br/>
               <p>Sub Title: <input type="text" id="subTitle" name="subTitle" value="${eventEditAdministratorForm.subTitle}" /></p><br/>
               <p>Phone or Email: <input type="text" id="contactInfo" name="contactInfo" value="${eventEditAdministratorForm.contactInfo}" /></p>
            <select id="eventId" name="eventId">
                <#list events as event>
                    <option value="${event.id}">${event.title}</option>
                </#list>
            </select>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
   </body>
    
</html>




