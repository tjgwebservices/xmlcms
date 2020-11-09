<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
    <main>
      <div>
     <h3>User List</h3>
     <a href="/authenticated/addUser">Add User</a>
     <br><br>
          
         <table border="1">
            <tr>
               <th>Username</th>
               <th>Name</th>
               <th>Email</th>
            </tr>
            <#list users as user>
            <tr>
               <td>${user.username}</td>
               <td>${user.firstName} ${user.lastName}</td>
               <td>${user.email}</td>
            </tr>
            <tr>
               <td colspan="3">Address</td>
            </tr>
            <tr>
               <td colspan="3">
                <p>${user.address1}</p>
                <p>${user.address2}</p>
                <p>${user.city}, ${user.statecode} ${user.zipcode}</p>
                </td>
            </tr>
            <tr>
               <td colspan="3">Business or Website</td>
            </tr>
            <tr>
               <td colspan="3">
                <p>${user.businessName}</p>
                <p>${user.websiteName}</p>

                </td>
            </tr>
            </#list>
         </table>
      </div>
       </main>


   <@nav.footing />



