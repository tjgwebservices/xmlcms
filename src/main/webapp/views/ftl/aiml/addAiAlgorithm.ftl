<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add AI Algorithm</title>
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
            <legend>Add AI Algorithm</legend>
            <form name="artificialIntelligence" action="" method="POST">
               <p>Title: <@spring.formInput "artificialIntelligence.title" "" "text"/></p><br/>
               <p>Description: <@spring.formInput "artificialIntelligence.description" "" "text"/></p><br/>
               <p>Algorithm Path: <@spring.formInput "artificialIntelligence.algorithmPath" "" "text"/></p><br/>
               <p>Data Source: <@spring.formInput "artificialIntelligence.dataSourcePath" "" "text"/></p>
               <p>Data Target: <@spring.formInput "artificialIntelligence.dataTargetPath" "" "text"/></p>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>





