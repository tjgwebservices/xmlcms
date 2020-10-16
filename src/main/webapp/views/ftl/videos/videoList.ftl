<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
     <h3>Video List - Videos</h3>
     <br><br>
      <div>
         <h4>Videos</h4>           
          
         <table border="1">
            <tr>
               <th>Video Name</th>
            </tr>
            <#if videos??>
            <#list videos as video>
            <tr>
               <td>${video.videoName}</td>
            </tr>
            </#list>
            </#if>
         </table>

         <table border="1">
            <tr>
               <th>Artists</th>
            </tr>
            <#if artists??>
            <#list artists as artist>
            <tr>
               <td>${artist.artistName}</td>
            </tr>
            </#list>
            </#if>
         </table>
