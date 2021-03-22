
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
                <input type="hidden" value="<#if loginForm.referralPath ??>${loginForm.referralPath}</#if>" />
               <input type="submit" value="Login" />
            </form>
         </fieldset>
         <p id="status"></p>
        </div>
   </body>

<script>

  window.fbAsyncInit = function() {
    FB.init({
      appId      : '',
      cookie     : true,
      xfbml      : true,
      version    : ''
    });
      
    FB.AppEvents.logPageView();   
      
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));


FB.logout(function(response) {
   // Person is now logged out
});

FB.logout(function(response) {
  // user is now logged out
});

</script>
</html>
