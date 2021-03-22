
<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
<head>
<@nav.cssheading />
</head>

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

         <fieldset>
            <legend>Additional Login Options:</legend>
                <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
                </fb:login-button>
                <div class="fb-login-button" data-width="" data-size="large" data-button-type="continue_with" data-layout="default" data-auto-logout-link="true" data-use-continue-as="true"></div>
                <div id="fb-root"></div>

         </fieldset>

        </div>
   </body>

<script>
  function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
    console.log('statusChangeCallback');
    console.log(response);                   // The current login status of the person.
    if (response.status === 'connected') {   // Logged into your webpage and Facebook.
      testAPI();  
    } else {                                 // Not logged into your webpage or we are unable to tell.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this webpage.';
    }
  }

  function checkLoginState() {               // Called when a person is finished with the Login Button.
    FB.getLoginStatus(function(response) {   // See the onlogin handler
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
    FB.init({
      appId      : '',
      cookie     : true,
      xfbml      : true,
      version    : ''
    });
      
    FB.AppEvents.logPageView();   

    FB.getLoginStatus(function(response) {   // Called after the JS SDK has been initialized.
      statusChangeCallback(response);        // Returns the login status.
    });
      
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));


  function testAPI() {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      console.log('Successful login for: ' + response.name);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + '!';
    });
  }
/*
FB.login(function(response) {
  // handle the response
  if (response.status === 'connected') {

  } else {
    // The person is not logged into your webpage or we are unable to tell. 
  }

}, {scope: 'public_profile,email'});
*/


</script>

<!-- Load the JS SDK asynchronously -->
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v10.0&appId=887463185150668&autoLogAppEvents=1" nonce="ZBog5apk"></script>
</html>