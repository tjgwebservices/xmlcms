<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
      <div>
     <h3>Configuration List</h3>
     <a href="/articles/addConfiguration">Add Configuration</a>
     <br><br>
          
         <table border="1">
            <tr>
               <th>Configuration</th>
               <th>Views</th>
               <th>Shares</th>
               <th>Users</th>
               <th>Date</th>
            </tr>
            <#list configurations as configuration>
            <tr>
               <td>${configuration.messages}</td>
               <td>${configuration.views}</td>
               <td>${configuration.shares}</td>
               <td>${configuration.users}</td>
               <td>${configuration.dateTime}</td>
            </tr>
            </#list>
         </table>
      </div>
       </main>


   <@nav.footing />



