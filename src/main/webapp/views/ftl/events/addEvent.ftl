<#import "/spring.ftl" as spring/>
 <#import "/nav.ftl" as nav/>

<html>
   <head>
      <title>Add Event</title>
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
            <legend>Add Event</legend>
            <form name="event" action="" method="POST">
               <p>Title: <@spring.formInput "eventForm.title" "" "text"/></p><br/>
               <p>Start Date: <@spring.formInput "eventForm.startDate" "" "text"/></p><br/>
               <p>End Date: <@spring.formInput "eventForm.endDate" "" "text"/></p><br/>
               <p>Location: <@spring.formInput "eventForm.location" "" "text"/></p>
               <p>Description: <@spring.formInput "eventForm.description" "" "text"/></p>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   <@nav.footingform />



