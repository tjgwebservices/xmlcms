<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Cart Status</title>
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
            <legend>Edit Cart Status</legend>
            <form name="cartStatus" action="/shop/editCartStatus" method="POST">
               <p>Cart Status: <input type="text" id="description" name="description" value="${cartStatusEditForm.description}" />    <br/>
               <p><input type="hidden" id="id" name="id" value="${cartStatusEditForm.id}" />    <br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       </main>


   <@nav.footing />



