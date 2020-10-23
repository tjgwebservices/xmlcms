<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
      <div>
     <h3>Article List</h3>
     <a href="addArticle">Add Article</a>
     <br><br>
          
         <table border="1">
            <tr>
               <th>Author</th>
               <th>Date</th>
               <th>Title</th>
            </tr>
            <#list articles as article>
            <tr>
               <td>${article.author}</td>
               <td>${article.authorDate}</td>
               <td>${article.title}</td>
            </tr>
            <tr>
               <td colspan="3">Description</td>
            </tr>
            <tr>
               <td colspan="3">${article.description}</td>
            </tr>
            <tr>
               <td colspan="3">Content</td>
            </tr>
            <tr>
               <td colspan="3">${article.content}</td>
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
