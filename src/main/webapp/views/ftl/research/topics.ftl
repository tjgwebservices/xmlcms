<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
     <h3>Topics</h3>
     <a href="/research/addTopic">Add Topic</a>
     <br><br>


      <div>
          
         <table border="1">
            <tr>
               <th>Topic Name</th>
               <th>Subject</th>
               <th>Description</th>
               <th>Edit</th>
            </tr>
            <#if topics??>
            <#list topics as topic>
            <tr>
               <td>${topic.topicName}</td>
               <td>${topic.topicSubject}</td>
               <td>${topic.topicDescription}</td>
               <td><a href="/research/editTopic/${topic.id}">Edit</a></td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

       </main>


   <@nav.footing />
