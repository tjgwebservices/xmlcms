<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
      <div>
     <h3>Review List</h3>
     <a href="addReview">Add Review</a>
     <br><br>
          
         <table border="1">
            <tr>
               <th>Author</th>
               <th>Date</th>
               <th>Title</th>
            </tr>
            <#list reviews as review>
            <tr>
               <td>${review.author}</td>
               <td>${review.authorDate}</td>
               <td>${review.title}</td>
            </tr>
            <tr>
               <td colspan="3">Description</td>
            </tr>
            <tr>
               <td colspan="3">${review.description}</td>
            </tr>
            <tr>
               <td colspan="3">Content</td>
            </tr>
            <tr>
               <td colspan="3">${review.content}</td>
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

