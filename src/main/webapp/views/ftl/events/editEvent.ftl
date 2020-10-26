<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Event</title>
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
            <legend>Edit Event</legend>
            <form name="event" action="/events/editEvent" method="POST">
               <p>Title: <input type="text" id="title" name="title" value="${eventEditForm.title}" /></p><br/>
               <p>Start Date: <input type="text" id="startDate" name="startDate" value="${eventEditForm.startDate}" /></p><br/>
               <p>End Date: <input type="text" id="endDate" name="endDate" value="${eventEditForm.endDate}" /></p><br/>
               <p>Location: <input type="text" id="location" name="location" value="${eventEditForm.location}" /></p>
               <p>Description: <input type="text" id="description" name="description" value="${eventEditForm.description}" /></p>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
   <@nav.footingform />



