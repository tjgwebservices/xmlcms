<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Social</title>
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
            <legend>Edit Social</legend>
            <form name="social" action="/articles/editSocial" method="POST">
               <p>Name: <input type="text" id="postname" name="postname" value="${socialEditForm.postname}" /></p><br/>
               <br/>
               <p>Content: <textarea id="content" name="content" value="">${socialEditForm.content}</textarea></p>
               <br/>
               <p>Reviewed: <input type="text" id="reviewed" name="reviewed" value="${socialEditForm.reviewed}" /></p><br/>
               <p>Date: <input type="text" id="published" name="published" value="${socialEditForm.published}" /></p><br/>
                <p><input type="hidden" id="id" name="id" value="${socialEditForm.id}" /></p><br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       </main>



       
       
   <@nav.footingform />


