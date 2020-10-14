<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <h3>Employers</h3>
     <a href="/hr/addEmployer">Add Employer</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>Employer Name</th>
               <th>Employer Contact</th>
               <th>POC Title</th>
               <th>Contact Email</th>
            </tr>
            <#list employers as employer>
            <tr>
               <td>${employer.employerName}</td>
               <td>${employer.employerContact}</td>
               <td>${employer.employerContactType}</td>
               <td>${employer.employerContactInfo}</td>
            </tr>
            </#list>
         </table>
      </div>

