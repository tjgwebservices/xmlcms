<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>

 
<html>
   <head>
      <title>Add ML Algorithm</title>
       <@nav.cssheading />
    
   </head>
   <body>
   <@nav.navigation />
    <main>

      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>

      <div>
         <fieldset>
            <legend>Add ML Algorithm</legend>
            <form name="machineLearning" action="" method="POST">
               <p>Title: <@spring.formInput "machineLearningForm.title" "" "text"/></p><br/>
               <p>Description: <@spring.formInput "machineLearningForm.description" "" "text"/></p><br/>
               <p>Algorithm Path: <@spring.formInput "machineLearningForm.algorithmPath" "" "text"/></p><br/>
               <p>Data Source: <@spring.formInput "machineLearningForm.dataSourcePath" "" "text"/></p>
               <p>Data Target: <@spring.formInput "machineLearningForm.dataTargetPath" "" "text"/></p>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       </main>


   <@nav.footing />







