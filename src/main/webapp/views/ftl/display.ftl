<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE HTML>
<html>
   <@nav.heading />
   <body>
      <h1>Socket List</h1>
      <#if message??>
      <h2>${message}</h2>
      </#if>
         <table border="1">
            <tr>
               <th>Subscription</th>
            </tr>
            <#if subscriptionList??>
                <#list subscriptionList as subscription>
                <tr>
                   <td>${subscription}</td>
                </tr>
                </#list>
            </#if>
         </table>
     
          
      <a href="<@spring.url '/index'/>">Home</a>  
       
   </body>
    
</html>
