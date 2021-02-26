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
           <th>Workshop Attendees</th>
        </tr>
        <#list rooms as room>
        <tr>
           <td>${room.id}</td>
        </tr>
        </#list>
        </table>
      </main>
      </#if>
   <@nav.workshop />

      <#if conferenceName??>
      <h2>${conferenceName}</h2>
      </#if>
      <h2>Workshops</h2>
      <#if message??>
      <h2>${message}</h2>
      </#if>
   <@nav.footingworkshop />
