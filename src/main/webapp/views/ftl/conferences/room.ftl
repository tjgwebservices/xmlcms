<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE html>
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
      <#if rooms??>
      <main>
        <table>
        <tr>
           <th>Available Conference Rooms</th>
        </tr>
        <#list rooms as room>
        <tr>
           <td>${room.id}</td>
        </tr>
        </#list>
        </table>
      </main>
      </#if>
   <@nav.conference />
      <#if message??>
      <h2>${message}</h2>
      </#if>
      <#if conferenceName??>
      <h2>${conferenceName} Experimental, Not Functional</h2>
      </#if>

   <@nav.footingconference />
