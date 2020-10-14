<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
    <h3>Course Page</h3>
     <a href="addArticle">Add Article</a>
     <br><br>
      <div>
         <h4>Course Page</h4>   
      </div>

      <div>
         <h4>Lectures</h4>           
          
         <table border="1">
            <tr>
               <th>Lecture Id</th>
               <th>Lecture Name</th>
            </tr>
            <#if lectures??>
            <#list lectures as lecture>
            <tr>
               <td>${lecture.id}</td>
               <td>${lecture.lectureName}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>


      <div>
         <h4>Students</h4> 
         <table border="1">
            <tr>
               <th>Last Name</th>
               <th>First Name</th>
               <th>Course Id</th>
            </tr>
            <#if students??>
            <#list students as student>
            <tr>
               <td>${student.lastName}</td>
               <td>${student.firstName}</td>
               <td>${student.courseId}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>
        
      <div>
      <a href="<@spring.url '/display'/>">Display Subscription List</a>  
      <a href="<@spring.url '/publish'/>">Publish to Subscription List</a>  
      </div>
   </body>
</html>

 
