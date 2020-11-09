<#import "/spring.ftl" as spring/>
 <#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit User</title>
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
            <legend>Edit User</legend>
            <form name="user" action="" method="POST">
               <p>Username: <@spring.formInput "userForm.username" "${userEditForm.username}" "text"/></p><br/>
               <p>First Name: <@spring.formInput "userForm.firstName" "${userEditForm.firstName}" "text"/></p><br/>
               <p>Last Name: <@spring.formInput "userForm.lastName" "${userEditForm.lastName}" "text"/></p><br/>
               <p>Email: <@spring.formInput "userForm.email" "${userEditForm.email}" "text"/></p><br/>
               <p>Phone Number: <@spring.formInput "userForm.phoneNumber" "${userEditForm.phoneNumber}" "text"/></p><br/>
               <p>Address Line 1: <@spring.formInput "userForm.address1" "${userEditForm.address1}" "text"/></p><br/>
               <p>Address Line 2: <@spring.formInput "userForm.address2" "${userEditForm.title}" "text"/></p><br/>
               <p>City: <@spring.formInput "userForm.city" "${userEditForm.city}" "text"/></p>
               <p>State: <@spring.formInput "userForm.statecode" "${userEditForm.statecode}" "text"/></p>
               <p>Zip: <@spring.formInput "userForm.zipcode" "${userEditForm.zipcode}" "text"/></p>
               <p>Business Name: <@spring.formInput "userForm.businessName" "${userEditForm.businessName}" "text"/></p>
               <p>Web Site: <@spring.formInput "userForm.websiteName" "${userEditForm.websiteName}" "text"/></p>
               <p>Provide a brief introduction to your website or consulting practice:</p>
               <p><textarea id="content1" name="content1" value="">${userEditForm.content1}</textarea></p>
               <p>In what ways can you benefit from consulting services:</p>
               <p><textarea id="content2" name="content2" value="">${userEditForm.content2}</textarea></p>
               <p>What new features would you like to be added to this application:</p>
               <p><textarea id="content3" name="content3" value="">${userEditForm.content3}</textarea></p>
               <br/>
               <br/>
                <p><input type="hidden" id="id" name="id" value="${userEditForm.id}" /></p><br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       </main>

   <@nav.footingform />



