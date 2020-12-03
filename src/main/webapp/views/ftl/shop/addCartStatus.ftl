<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Shopping Cart Status</title>
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
            <legend>Add Shopping Cart Status</legend>
            <form name="cartStatus" action="" method="POST">
               Description: <@spring.formInput "cartStatusForm.description" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       </main>


   <@nav.footing />


