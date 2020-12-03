<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Shop Product</title>
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
            <legend>Add Shop Product</legend>
            <form name="shopProduct" action="" method="POST">
               Product Description: <@spring.formInput "shopProductForm.description" "" "text"/>    <br/>
               Product Price: <@spring.formInput "shopProductForm.price" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       </main>


   <@nav.footing />



