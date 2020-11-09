<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
     <h3>Clients</h3>
     <a href="/hr/addClient">Add Client</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>First Name</th>
               <th>Last Name</th>
               <th>Specialty</th>
               <th>Contact</th>
            </tr>
            <#if clients??>
            <#list clients as client>
            <tr>
               <td>${client.clientFirstName}</td>
               <td>${client.clientLastName}</td>
               <td>${client.clientSpecialty}</td>
               <td>${client.clientContact}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

       </main>


   <@nav.footing />

