
<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
        <div>
         <fieldset>
            <legend>Login</legend>
            <form name="loginForm" action="" method="POST">
               Username: <@spring.formInput "loginForm.username" "" "text"/>    <br/>
               Password: <@spring.formInput "loginForm.password" "" "text"/>    <br/>
               <input type="submit" value="Login" />
            </form>
         </fieldset>

        </div>
   </body>
</html>