<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Article</title>
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
       
       
   <@nav.footingform />

