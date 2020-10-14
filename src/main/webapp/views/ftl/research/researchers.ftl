<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <h3>Topics</h3>
     <a href="/research/addResearcher">Add Researcher</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>First Name</th>
               <th>Last Name</th>
               <th>Degree</th>
               <th>Major</th>
               <th>Institution</th>
               <th>Specialty</th>
            </tr>
            <#list researchers as reseacher>
            <tr>
               <td>${researcher.researcherFirstName}</td>
               <td>${researcher.researcherLastName}</td>
               <td>${researcher.reseacherDegree}</td>
               <td>${researcher.reseacherMajor}</td>
               <td>${researcher.reseacherInstitution}</td>
               <td>${researcher.reseacherSpecialty}</td>
               <td><a href="/research/editReseacher/${reseacher.id}">Edit</a></td>
            </tr>
            </#list>
         </table>
      </div>


