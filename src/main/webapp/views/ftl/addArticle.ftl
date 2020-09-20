<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Add Article</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>      
   </head>
   <body>
      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
       
      <div>
         <fieldset>
            <legend>Add Article</legend>
            <form name="article" action="" method="POST">
               Author: <@spring.formInput "articleForm.author" "" "text"/>    <br/>
               Date: <@spring.formInput "articleForm.authorDate" "" "text"/>    <br/>
               Title: <@spring.formInput "articleForm.title" "" "text"/>    <br/>
               Description: <@spring.formInput "articleForm.description" "" "text"/>    <br/>
               Content: <@spring.formInput "articleForm.content" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>
