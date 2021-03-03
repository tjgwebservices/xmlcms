<#import "/spring.ftl" as spring/>
 <#import "/nav.ftl" as nav/>

<html>
   <head>
      <title>Subscribe</title>
       <@nav.cssheading />
    
   </head>
   <body>
   <@nav.navigation />

      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
       
      <div>
         <fieldset>
            <legend>Subscribe</legend>
            <form name="user" action="" method="POST">
               <p>Username: <@spring.formInput "userForm.username" "" "text"/></p><br/>
               <p>First Name: <@spring.formInput "userForm.firstName" "" "text"/></p><br/>
               <p>Last Name: <@spring.formInput "userForm.lastName" "" "text"/></p><br/>
               <p>Email: <@spring.formInput "userForm.email" "" "text"/></p><br/>
               <p>Phone Number: <@spring.formInput "userForm.phoneNumber" "" "text"/></p><br/>
               <p>Address Line 1: <@spring.formInput "userForm.address1" "" "text"/></p><br/>
               <p>Address Line 2: <@spring.formInput "userForm.address2" "" "text"/></p><br/>
               <p>City: <@spring.formInput "userForm.city" "" "text"/></p><br/>
               <p>State Code: <@spring.formInput "userForm.statecode" "" "text"/></p><br/>
               <p>Zip Code: <@spring.formInput "userForm.zipcode" "" "text"/></p><br/>
               <p>Business Name: <@spring.formInput "userForm.businessName" "" "text"/></p><br/>
               <p>Website Name: <@spring.formInput "userForm.websiteName" "" "text"/></p><br/>
               <p>Profile Image Path: <@spring.formInput "userForm.profileImagePath" "" "text"/></p><br/>
               <br/>
                <p>Provide a brief introduction to your website or consulting practice:</p>
               <p><textarea id="content1" name="content1" value=""></textarea></p>
               <p>In what ways can you benefit from consulting services:</p>
               <p><textarea id="content2" name="content2" value=""></textarea></p>
               <p>What new features would you like to be added to this application:</p>
               <p><textarea id="content3" name="content3" value=""></textarea></p>
              <br/>
               <br/>
               <input type="submit" value="Subscribe" />
            </form>
         </fieldset>
      </div>
       
       
   <@nav.footingform />



