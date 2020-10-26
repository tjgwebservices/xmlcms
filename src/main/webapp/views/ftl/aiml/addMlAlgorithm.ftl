<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>

 
<html>
   <head>
      <title>Add ML Algorithm</title>
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
            <legend>Add ML Algorithm</legend>
            <form name="machineLearning" action="" method="POST">
               <p>Title: <@spring.formInput "machineLearning.title" "" "text"/></p><br/>
               <p>Description: <@spring.formInput "machineLearning.description" "" "text"/></p><br/>
               <p>Algorithm Path: <@spring.formInput "machineLearning.algorithmPath" "" "text"/></p><br/>
               <p>Data Source: <@spring.formInput "machineLearning.dataSourcePath" "" "text"/></p>
               <p>Data Target: <@spring.formInput "machineLearning.dataTargetPath" "" "text"/></p>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>






