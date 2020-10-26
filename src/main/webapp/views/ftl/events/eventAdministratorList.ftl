<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <h3>Event Administrators</h3>
     <a href="/events/addEventAdministrator">Add Event Administrator</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>Title</th>
               <th>Sub Title</th>
               <th>Ad Image Path</th>
               <th>Contact Info</th>
               <th></th>
            </tr>
            <#if eventAdvertisements??>
            <#list eventAdvertisements as eventAdvertisement>
            <tr>
               <td>${eventAdvertisement.title}</td>
               <td>${eventAdvertisement.subTitle}</td>
               <td>${eventAdvertisement.adImagePath}</td>
               <td>${eventAdvertisement.contactInfo}</td>
               <td><a href="/events/editEventAdvertisement/${eventAdvertisement.id}">Edit</a></td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>





