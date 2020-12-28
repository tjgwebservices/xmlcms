<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Blog</title>
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
            <legend>Edit Blog</legend>
            <form name="blog" action="/articles/editBlog" method="POST">
               <p>Author: <input type="text" id="author" name="author" value="${blogEditForm.author}" /></p><br/>
               <p>Date: <input type="text" id="authorDate" name="authorDate" value="${blogEditForm.authorDate}" /></p><br/>
               <p>Title: <input type="text" id="title" name="title" value="${blogEditForm.title}" /></p><br/>
               <p>Description: <input type="text" id="description" name="description" value="${blogEditForm.description}" /></p>
               <br/>
               <p>Content: <textarea id="content" name="content" value="">${blogEditForm.content}</textarea></p>
               <br/>
               <br/>
                <p><input type="hidden" id="id" name="id" value="${blogEditForm.id}" /></p><br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       </main>



       
       
   <@nav.footingform />


