<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Event Advertisement</title>
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
            <legend>Edit Event Advertisement</legend>
            <form name="event" action="/events/editEventAdvertisement" method="POST">
               <p>Title: <input type="text" id="title" name="title" value="${eventEventAdvertisementForm.title}" /></p><br/>
               <p>Sub Title: <input type="text" id="subtitle" name="subtitle" value="${eventEventAdvertisementForm.subtitle}" /></p><br/>
               <p>Ad Image Path: <input type="text" id="adImagePath" name="adImagePath" value="${eventEventAdvertisementForm.adImagePath}" /></p><br/>
               <p>Phone or Email: <input type="text" id="contactInfo" name="contactInfo" value="${eventEventAdvertisementForm.contactInfo}" /></p>
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





