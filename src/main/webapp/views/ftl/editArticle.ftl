<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Add Article</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/main.css'/>"/>
    
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
               <p>Author: <@spring.formInput "articleForm.author" "${articleEditForm.author}" "text"/></p><br/>
               <p>Date: <@spring.formInput "articleForm.authorDate" "${articleEditForm.authorDate}" "text"/></p><br/>
               <p>Title: <@spring.formInput "articleForm.title" "${articleEditForm.title}" "text"/></p><br/>
               <p>Description: <@spring.formInput "articleForm.description" "${articleEditForm.description}" "text"/></p>
               <br/>
               <p>Content: <textarea id="content" name="content" value="">${articleEditForm.content}</textarea></p>
               <br/>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>

