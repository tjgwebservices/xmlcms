<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
      <div>
     <h3>Social List</h3>
     <a href="/articles/addSocial">Add Social</a>
     <br><br>
          
         <table border="1">
            <tr>
               <th>Social</th>
            </tr>
            <#list socials as social>
            <tr>
               <td>${social.postname}</td>
            </tr>
            <tr>
               <td colspan="3">Content</td>
            </tr>
            <tr>
               <td colspan="3">${social.content}</td>
            </tr>
            </#list>
         </table>
      </div>
       </main>


   <@nav.footing />


