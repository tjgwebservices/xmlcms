<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Article</title>
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
            <legend>Add Article</legend>
            <form name="article" action="" method="POST">
               <p>Author: <@spring.formInput "articleForm.author" "" "text"/></p><br/>
               <p>Date: <@spring.formInput "articleForm.authorDate" "" "text"/></p><br/>
               <p>Title: <@spring.formInput "articleForm.title" "" "text"/></p><br/>
               <p>Description: <@spring.formInput "articleForm.description" "" "text"/></p>
               <br/>
               <p>Content: <textarea id="content" name="content" value=""></textarea></p>
               <br/>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
      <div>
         <fieldset>
            <legend>Publish to Socket List (Test)</legend>
            <form name="publish" action="" method="POST">
               Publish Name: <@spring.formInput "socketDisplay.message" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       </main>
   <@nav.footingform />
