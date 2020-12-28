<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Blog</title>
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
            <legend>Add Blog</legend>
            <form name="blog" action="" method="POST">
               <p>Author: <@spring.formInput "blogForm.author" "" "text"/></p><br/>
               <p>Date: <@spring.formInput "blogForm.authorDate" "" "text"/></p><br/>
               <p>Title: <@spring.formInput "blogForm.title" "" "text"/></p><br/>
               <p>Description: <@spring.formInput "blogForm.description" "" "text"/></p>
               <br/>
               <p>Content: <textarea id="content" name="content" value=""></textarea></p>
               <br/>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       </main>
   <@nav.footingform />

