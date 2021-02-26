<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE html>
<html>
   <@nav.heading />
   <body>
      <h1>Topic List</h1>
      <#if message??>
      <h2>${message}</h2>
      </#if>
         <table border="1">
            <tr>
               <th>Topics</th>
            </tr>
            <#if id??>
                <tr>
                   <td>${id}</td>
                </tr>
            </#if>
         </table>
     
          
      <a href="<@spring.url '/index'/>">Home</a>  
       
   </body>
    
</html>


