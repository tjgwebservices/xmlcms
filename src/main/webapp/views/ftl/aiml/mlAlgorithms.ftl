<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
     <h3>Machine Learning Algorithms</h3>
     <a href="/aiml/addMlAlgorithm">Add Algorithm</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>Title</th>
               <th>Description</th>
               <th></th>
            </tr>
            <#if machinelearnings??>
            <#list machinelearnings as machinelearning>
            <tr>
               <td>${machinelearning.title}</td>
               <td>${machinelearning.description}</td>
               <td><a href="/aiml/editMlAlgorithm/${machinelearning.id}">Edit</a></td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

       </main>


   <@nav.footing />



