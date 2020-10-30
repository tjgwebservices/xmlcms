<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <h3>Chats</h3>
     <a href="/chat/addChat">Add Chat</a>
     <br><br>
      <div>
          
         <table border="1">
            <tr>
               <th>From</th>
               <th>To</th>
               <th>Time</th>
               <th>Subject</th>
               <th>Message</th>
               <th></th>
            </tr>
            <#if chats??>
            <#list chats as chat>
            <tr>
               <td>${chat.userIdFrom}</td>
               <td>${chat.userIdTo}</td>
               <td>${chat.dateTime}</td>
               <td>${chat.subject}</td>
               <td>${chat.message}</td>
               <td><a href="/chat/editchat/${chat.id}">Edit</a></td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>


