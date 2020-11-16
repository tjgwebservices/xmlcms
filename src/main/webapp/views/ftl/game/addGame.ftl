<#import "/spring.ftl" as spring/>
 <#import "/nav.ftl" as nav/>

<html>
   <head>
      <title>Add Game</title>
       <@nav.cssheading />
    
   </head>
   <body>
   <@nav.navigation />

      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
       
      <div>
         <fieldset>
            <legend>Add Game</legend>
            <form name="game" action="" method="POST">
               <p>Title: <@spring.formInput "gameForm.title" "" "text"/></p><br/>
               <p>High Score: <@spring.formInput "gameForm.highScore" "" "text"/></p><br/>
               <p>Created: <@spring.formInput "gameForm.created" "" "text"/></p><br/>
               <p>Last Updated: <@spring.formInput "gameForm.lastUpdated" "" "text"/></p>
               <br/>
               <br/>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   <@nav.footingform />


