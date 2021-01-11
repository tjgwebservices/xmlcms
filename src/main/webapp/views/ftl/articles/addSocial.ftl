<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Social</title>
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
            <legend>Add Social</legend>
            <form name="social" action="" method="POST">
               <p>Name: <@spring.formInput "socialForm.postname" "" "text"/></p><br/>
               <br/>
               <p>Content: <textarea id="content" name="content" value=""></textarea></p>
               <br/>
               <p>Published: <@spring.formInput "socialForm.published" "" "text"/></p><br/>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       </main>
   <@nav.footingform />

