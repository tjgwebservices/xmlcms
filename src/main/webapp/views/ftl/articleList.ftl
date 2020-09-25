<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
     <h3>Article List</h3>
     <a href="addArticle">Add Article</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>Author</th>
               <th>Date</th>
               <th>Title</th>
               <th>Description</th>
               <th>Content</th>
            </tr>
            <#list articles as article>
            <tr>
               <td>${article.author}</td>
               <td>${article.authorDate}</td>
               <td>${article.title}</td>
               <td>${article.description}</td>
               <td>${article.content}</td>
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
