<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Edit ML Algorithm</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/main.css'/>"/>
    
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
            <legend>Edit ML algorithm</legend>
            <form name="machineLearning" action="/aiml/editMlAlgorithm" method="POST">
               <p>Title: <input type="text" id="title" name="title" value=${"machineLearningEditForm.title}" /></p><br/>
               <p>Description: <input type="text" id="description" name="description" value="${machineLearningEditForm.description}" /></p><br/>
               <p>Algorithm Path: <input type="text" id="algorithmPath" name="algorithmPath" value="${machineLearningEditForm.algorithmPath}" /></p><br/>
               <p>Data Source Path: <input type="text" id="dataSourcePath" name="dataSourcePath" value="${machineLearningEditForm.dataSourcePath}" /></p>
               <p>Data Target Path: <input type="text" id="dataTargetPath" name="dataTargetPath" value="${machineLearningEditForm.dataTargetPath}" /></p>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
   </body>
    
</html>





