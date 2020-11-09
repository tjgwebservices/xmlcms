<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <main>
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
            <#if researchers??>
            <#list researchers as researcher>
            <tr>
               <td>${researcher.researcherFirstName}</td>
               <td>${researcher.researcherLastName}</td>
               <td>${researcher.researcherDegree}</td>
               <td>${researcher.researcherMajor}</td>
               <td>${researcher.researcherInstitution}</td>
               <td>${researcher.researcherSpecialty}</td>
               <td><a href="/research/editResearcher/${researcher.id}">Edit</a></td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>
     </main>
   <@nav.footing />


