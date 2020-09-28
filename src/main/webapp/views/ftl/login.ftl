
<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
        <div>
         <fieldset>
            <legend>Login</legend>
            <form name="login" action="" method="POST">
               Username: <@spring.formInput "loginForm.username" "" "text"/>    <br/>
               Password: <@spring.formInput "loginForm.password" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>

        </div>
   </body>
</html>