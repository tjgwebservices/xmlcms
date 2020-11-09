<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
     <h3>HR Groups</h3>
     <a href="/hr/addHrGroup">Add Group</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>Human Resources Group Name</th>
            </tr>
            <#if hrgroups??>
            <#list hrgroups as hrgroup>
            <tr>
               <td>${hrgroup.groupName}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

       </main>


   <@nav.footing />

