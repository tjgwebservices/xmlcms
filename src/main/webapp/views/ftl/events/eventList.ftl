<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Events</title>
       <@nav.cssheading />
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/calendar.css'/>"/>

   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
     <h3>Events</h3>
     <div id="calendarDisplayContainer">
     </div>
     <a href="/events/addEvent">Add Event</a>
     <br><br>
      <div>
          
         <table id="eventDisplayTable" border="1">
            <tr>
               <th>Title</th>
               <th>Start Date</th>
               <th>End Date</th>
               <th>Location</th>
               <th>Description</th>
               <th></th>
            </tr>
            <#if events??>
            <#list events as event>
            <tr>
               <td>${event.title}</td>
               <td>${event.startDate}</td>
               <td>${event.endDate}</td>
               <td>${event.location}</td>
               <td>${event.description}</td>
               <td><a href="/events/editEvent/${event.id}">Edit</a></td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

       </main>


   <@nav.footingcalendardisplay />

