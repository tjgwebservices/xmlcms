<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
      <div>
     <h3>Games</h3>
     <br><br>
          
         <table border="1">
            <tr>
               <th>Title</th>
               <th>High Score</th>
            </tr>
            <#list games as game>
            <tr>
               <td>${game.title}</td>
               <td>${game.highScore}</td>
            </tr>
            </#list>
         </table>
      </div>
      <div>
      </div>
   </body>
</html>


