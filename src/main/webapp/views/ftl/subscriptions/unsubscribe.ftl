<#import "/spring.ftl" as spring/>
 <#import "/nav.ftl" as nav/>

<html>
   <head>
      <title>Subscribe</title>
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
            <legend>Subscribe</legend>
            <form name="user" action="" method="POST">
               <p>Username: <@spring.formInput "userForm.username" "" "text"/></p><br/>
              <br/>
               <br/>
               <input type="submit" value="Subscribe" />
            </form>
         </fieldset>
      </div>
       
       
   <@nav.footingform />

