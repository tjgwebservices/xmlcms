<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Add Review</title>
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
            <legend>Add Review</legend>
            <form name="review" action="" method="POST">
               <p>Author: <@spring.formInput "reviewForm.author" "" "text"/></p><br/>
               <p>Date: <@spring.formInput "reviewForm.authorDate" "" "text"/></p><br/>
               <p>Title: <@spring.formInput "reviewForm.title" "" "text"/></p><br/>
               <p>Description: <@spring.formInput "reviewForm.description" "" "text"/></p>
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
       
       
   </body>
    
</html>

