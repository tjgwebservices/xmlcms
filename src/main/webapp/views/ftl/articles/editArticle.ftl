<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Article</title>
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
            <legend>Edit Article</legend>
            <form name="article" action="/articles/editArticle" method="POST">
               <p>Author: <input type="text" id="author" name="author" value="${articleEditForm.author}" /></p><br/>
               <p>Date: <input type="text" id="authorDate" name="authorDate" value="${articleEditForm.authorDate}" /></p><br/>
               <p>Title: <input type="text" id="title" name="title" value="${articleEditForm.title}" /></p><br/>
               <p>Description: <input type="text" id="description" name="description" value="${articleEditForm.description}" /></p>
               <br/>
               <p>Content: <textarea id="content" name="content" value="">${articleEditForm.content}</textarea></p>
               <br/>
               <br/>
                <p><input type="hidden" id="id" name="id" value="${articleEditForm.id}" /></p><br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       
       
   <@nav.footingform />

