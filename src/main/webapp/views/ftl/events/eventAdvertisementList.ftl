<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
     <h3>Event Advertisements</h3>
     <a href="/events/addEventAdvertisement">Add Event Advertisement</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>Administrator Name</th>
               <th>Title</th>
               <th>Sub Title</th>
               <th>Contact Info</th>
               <th></th>
            </tr>
            <#if eventAdministrators??>
            <#list eventAdministrators as eventAdministrator>
            <tr>
               <td>${eventAdministrator.administratorName}</td>
               <td>${eventAdministrator.title}</td>
               <td>${eventAdministrator.subTitle}</td>
               <td>${eventAdministrator.contactInfo}</td>
               <td><a href="/events/editEventAdministrator/${eventAdministrator.id}">Edit</a></td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>


       </main>


   <@nav.footing />




