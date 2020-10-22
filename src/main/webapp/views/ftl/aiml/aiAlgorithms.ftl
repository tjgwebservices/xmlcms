<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <h3>Artificial Intelligence Algorithms</h3>
     <a href="/aiml/addAiAlgorithm">Add Algorithm</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>Title</th>
               <th>Description</th>
               <th></th>
            </tr>
            <#if artificialIntelligences??>
            <#list artificialIntelligences as artificialIntelligence>
            <tr>
               <td>${artificialIntelligence.title}</td>
               <td>${artificialIntelligence.description}</td>
               <td><a href="/aiml/editAiAlgorithm/${artificialIntelligence.id}">Edit</a></td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>


