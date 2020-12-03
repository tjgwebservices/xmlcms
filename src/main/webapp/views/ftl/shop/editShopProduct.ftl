<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Shop Product</title>
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
            <legend>Edit Shop Product</legend>
            <form name="shopProduct" action="/shop/editShopProduct" method="POST">
               <p>Product Description: <input type="text" id="description" name="description" value="${shopProductEditForm.description}" />    <br/>
               <p>Product Price: <input type="text" id="price" name="price" value="${shopProductEditForm.price}" />    <br/>
               <p><input type="hidden" id="id" name="id" value="${shopProductEditForm.id}" />    <br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       </main>


   <@nav.footing />




