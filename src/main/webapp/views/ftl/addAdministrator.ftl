<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Add Administrator</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>      
   </head>
   <body>
      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
       
      <div>
         <fieldset>
            <legend>Add Administrator</legend>
            <form name="administrator" action="" method="POST">
               Administrator Name: <@spring.formInput "administratorForm.administratorName" "" "text"/>    <br/>
               Administrator Group Id: <@spring.formInput "administratorForm.administratorGroupId" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>



