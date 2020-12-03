<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Shop Payment Type</title>
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
            <legend>Add Shop Payment Type</legend>
            <form name="shopPaymentType" action="" method="POST">
               Payment Type: <@spring.formInput "shopPaymentTypeForm.paymentTypeDescription" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       </main>


   <@nav.footing />



