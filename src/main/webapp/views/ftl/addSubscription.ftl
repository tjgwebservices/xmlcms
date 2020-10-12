<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Add Subscription</title>
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
            <legend>Add Subscription</legend>
            <form name="subscription" action="" method="POST">
               Publication: <@spring.formInput "subscriptionForm.publisher" "" "text"/>    <br/>
               Subscription: <@spring.formInput "subscriptionForm.subscriptionPlan" "" "text"/>    <br/>
               Topic: <@spring.formInput "subscriptionForm.topic" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>

