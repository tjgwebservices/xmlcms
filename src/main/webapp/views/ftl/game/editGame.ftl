<#import "/spring.ftl" as spring/>
 <#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Review</title>
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
            <legend>Edit Game</legend>
            <form name="game" action="" method="POST">
               <p>Title: <input type="text" id="title" name="title" value="${gameEditForm.title}" /></p><br/>
               <p>High Score: <input type="text" id="highScore" name="highScore" value="${gameEditForm.highScore}" /></p><br/>
               <p>Created: <input type="text" id="created" name="created" value="${gameEditForm.created}" /></p><br/>
               <p>Last Updated: <input type="text" id="lastUpdated" name="lastUpdated" value="${gameEditForm.lastUpdated}" /></p>
               <br/>
               <br/>
               <br/>
                <p><input type="hidden" id="id" name="id" value="${gameEditForm.id}" /></p><br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       
       
   <@nav.footingform />



