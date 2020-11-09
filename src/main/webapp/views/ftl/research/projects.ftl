<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
     <h3>Projects</h3>
     <a href="/research/addProject">Add Project</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>Project Name</th>
               <th>Subject</th>
               <th>Description</th>
               <th>Edit</th>
            </tr>
            <#if projects??>
            <#list projects as project>
            <tr>
               <td>${project.projectName}</td>
               <td>${project.projectSubject}</td>
               <td>${project.projectDescription}</td>
               <td><a href="/research/editProject/${project.id}">Edit</a></td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

     </main>
   <@nav.footing />
