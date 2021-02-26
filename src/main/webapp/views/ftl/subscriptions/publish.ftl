<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE html>
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
      <h1>Socket List</h1>
      <#if message??>
      <h2>${message}</h2>
      </#if>
      <#if errorMessage??>
      <h2>${errorMessage}</h2>
      </#if>
      <div>
         <fieldset>
            <legend>Add Message</legend>
            <form name="publishMessage" action="" method="POST">
               Publish Name: <@spring.formInput "socketDisplay.message" "" "text"/>    <br/>
               <input type="submit" value="Send" />
            </form>
         </fieldset>
      </div>
     
          
      <a href="<@spring.url '/index'/>">Home</a>  
       
   </body>
    
</html>

