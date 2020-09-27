<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
   <@nav.mainsection />
     <h3>Subscription List</h3>
     <a href="addSubscription">Add Subscription</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>Subscription Plan</th>
               <th>Publisher</th>
               <th>Topic</th>
            </tr>
            <#list subscriptions as subscription>
            <tr>
               <td>${subscription.subscriptionPlan}</td>
               <td>${subscription.publisher}</td>
               <td>${subscription.topic}</td>
            </tr>
            </#list>
         </table>
      </div>
      <div>
      <a href="<@spring.url '/display'/>">Display Subscription List</a>  
      <a href="<@spring.url '/publish'/>">Publish to Subscription List</a>  
      </div>
   </body>
</html>

