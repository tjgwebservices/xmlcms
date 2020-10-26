<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Event Administrator</title>
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
            <legend>Add Event Administrator</legend>
            <form name="eventAdministrator" action="" method="POST">
               <p>Name: <@spring.formInput "eventAdministratorForm.administratorName" "" "text"/></p><br/>
               <p>Title: <@spring.formInput "eventAdministratorForm.title" "" "text"/></p><br/>
               <p>Sub Title: <@spring.formInput "eventAdvertisementForm.subTitle" "" "text"/></p><br/>
               <p>Email or Phone: <@spring.formInput "eventAdvertisementForm.contactInfo" "" "text"/></p>
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





