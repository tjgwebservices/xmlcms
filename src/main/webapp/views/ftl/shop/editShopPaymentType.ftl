<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Shop Payment Type</title>
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
            <legend>Edit Shop Payment Type</legend>
            <form name="shopPaymentType" action="/shop/editShopPaymentType" method="POST">
               <p>Payment Type: <input type="text" id="shopPaymentTypeDescription" name="shopPaymentTypeDescription" value="${shopPaymentTypeEditForm.paymentTypeDescription}" />    <br/>
               <p><input type="hidden" id="id" name="id" value="${shopPaymentTypeEditForm.id}" />    <br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       </main>


   <@nav.footing />




