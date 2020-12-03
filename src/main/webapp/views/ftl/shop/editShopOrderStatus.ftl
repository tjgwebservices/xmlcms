<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Shop Order Status</title>
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
            <legend>Edit Shop Order Status</legend>
            <form name="shopOrderStatus" action="/shop/editShopOrderStatus" method="POST">
               <p>Order Status: <input type="text" id="description" name="description" value="${shopOrderStatusEditForm.description}" />    <br/>
               <p><input type="hidden" id="id" name="id" value="${shopOrderStatusEditForm.id}" />    <br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       </main>


   <@nav.footing />




