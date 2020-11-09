<#import "/spring.ftl" as spring/>
 <#import "/nav.ftl" as nav/>

<html>
   <head>
      <title>Edit Subscription</title>
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


