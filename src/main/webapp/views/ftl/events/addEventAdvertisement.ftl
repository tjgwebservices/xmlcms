<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Event Advertisement</title>
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
            <legend>Add Event Advertisement</legend>
            <form name="eventAdvertisement" action="" method="POST">
               <p>Title: <@spring.formInput "eventAdvertisementForm.title" "" "text"/></p><br/>
               <p>Start Date: <@spring.formInput "eventAdvertisementForm.subTitle" "" "text"/></p><br/>
               <p>End Date: <@spring.formInput "eventAdvertisementForm.adImagePath" "" "text"/></p><br/>
               <p>Location: <@spring.formInput "eventAdvertisementForm.contactInfo" "" "text"/></p>
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




