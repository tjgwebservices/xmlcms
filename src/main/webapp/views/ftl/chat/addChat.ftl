<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Chat</title>
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
            <legend>Add Chat</legend>
            <form name="chat" action="" method="POST">
               <p>From: <@spring.formInput "chatForm.userIdFrom" "" "text"/></p><br/>
               <p>To: <@spring.formInput "chatForm.userIdTo" "" "text"/></p><br/>
               <p>Subject: <@spring.formInput "chatForm.subject" "" "text"/></p><br/>

               <br/>
               <p>Content: <textarea id="message" name="message" value=""></textarea></p>
               <br/>
               <br/>
               <input type="submit" value="Send" />
            </form>
         </fieldset>
      </div>
       </main>

   <@nav.footingform />

