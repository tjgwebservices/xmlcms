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
            <legend>Edit Review</legend>
            <form name="review" action="" method="POST">
               <p>Author: <input type="text" id="author" name="author" value="${reviewEditForm.author}" /></p><br/>
               <p>Date: <input type="text" id="authorDate" name="authorDate" value="${reviewEditForm.authorDate}" /></p><br/>
               <p>Title: <input type="text" id="title" name="title" value="${reviewEditForm.title}" /></p><br/>
               <p>Description: <input type="text" id="description" name="description" value="${reviewEditForm.description}" /></p>
               <br/>
               <p>Content: <textarea id="content" name="content" value="">${reviewEditForm.content}</textarea></p>
               <br/>
               <br/>
                <p><input type="hidden" id="id" name="id" value="${reviewEditForm.id}" /></p><br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       
       
   <@nav.footingform />


