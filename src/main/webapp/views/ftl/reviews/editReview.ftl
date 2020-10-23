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
               <p>Author: <@spring.formInput "reviewForm.author" "${reviewEditForm.author}" "text"/></p><br/>
               <p>Date: <@spring.formInput "reviewForm.authorDate" "${reviewEditForm.authorDate}" "text"/></p><br/>
               <p>Title: <@spring.formInput "reviewForm.title" "${reviewEditForm.title}" "text"/></p><br/>
               <p>Description: <@spring.formInput "reviewForm.description" "${reviewEditForm.description}" "text"/></p>
               <br/>
               <p>Content: <textarea id="content" name="content" value="">${articleEditForm.content}</textarea></p>
               <br/>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   <@nav.footingform />


