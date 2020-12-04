<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
     <h3>Shop Products</h3>
     <a href="/shop/addShopProduct">Add Product</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>Description</th>
               <th>Price</th>
            </tr>
            <#if shopProducts??>
            <#list shopProducts as product>
            <tr>
               <td>${product.description}</td>
               <td>${product.price}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

       </main>


   <@nav.footing />


