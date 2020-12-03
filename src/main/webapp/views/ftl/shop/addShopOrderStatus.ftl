<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Shop Order Status</title>
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
            <legend>Add Shop Order Status</legend>
            <form name="shopOrderStatus" action="" method="POST">
               Order Status: <@spring.formInput "shopOrderStatusForm.description" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       </main>


   <@nav.footing />



