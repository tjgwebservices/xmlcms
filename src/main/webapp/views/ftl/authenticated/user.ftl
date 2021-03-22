<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE html>
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinksadmin />
   <@nav.adminsection />
      <h2>Administration Page</h2>
      <#if message??>
      <h2>${message}</h2>
      </#if>
   <@nav.footing />


    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>

<script>
    FB.getLoginStatus(function(response) {
      if (response.status === 'connected') {
        // The user is logged in and has authenticated your
        // app, and response.authResponse supplies
        // the user's ID, a valid access token, a signed
        // request, and the time the access token 
        // and signed request each expire.
        var uid = response.authResponse.userID;
        var accessToken = response.authResponse.accessToken;
      } else if (response.status === 'not_authorized') {
        // The user hasn't authorized your application.  They
        // must click the Login button, or you must call FB.login
        // in response to a user gesture, to launch a login dialog.
      } else {
        // The user isn't logged in to Facebook. You can launch a
        // login dialog with a user gesture, but the user may have
        // to log in to Facebook before authorizing your application.
      }
     });
</script>

</html>


