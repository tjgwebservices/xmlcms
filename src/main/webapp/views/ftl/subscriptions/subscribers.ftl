<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
      <div>
     <h3>Subscriber List</h3>
     <a href="/subscriptions/subscribe">Add A Subscriber</a>
     <br><br>
          
         <table border="1">
            <tr>
               <th colspan="3">Name</th>
            </tr>
            <#list users as user>
            <tr>
               <td>${user.firstName} ${user.lastName}</td>
            </tr>
            <tr>
               <td colspan="3">Business or Website</td>
            </tr>
            <tr>
               <td colspan="3">
                <p>${user.businessName}</p>
                <p>${user.websiteName}</p>
                </td>
            </tr>
            </#list>
         </table>
      </div>
   </body>
</html>



