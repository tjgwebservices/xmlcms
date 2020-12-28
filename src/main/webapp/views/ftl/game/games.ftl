<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
      <div>

     <h3>TJG Web Services Games</h3>
     <p>An assortment of HTML5 games in development.  Click on your character to steer away from the approaching obstacles.  If your character hits the obstacles, points are lost.  If your character avoids obstacles you gain points.  Have fun!!</p>
    <p>TJG Web Services Games Developers</p>
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
      </main>
   </body>
</html>


