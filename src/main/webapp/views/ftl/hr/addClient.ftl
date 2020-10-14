<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Add Client</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/main.css'/>"/>
    
   </head>
   <body>
      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
       
      <div>
         <fieldset>
            <legend>JobSeeker</legend>
            <form name="article" action="" method="POST">
               <p>Author: <@spring.formInput "clientForm.clientFirstName" "" "text"/></p><br/>
               <p>Date: <@spring.formInput "clientForm.clientLastName" "" "text"/></p><br/>
               <p>Title: <@spring.formInput "clientForm.clientSpecialty" "" "text"/></p><br/>
               <p>Description: <@spring.formInput "clientForm.clientContact" "" "text"/></p>
                <select id="hrGroupId" name="hrGroupId">
                    <#list hrGroups as hrGroup>
                        <option value="${hrGroup.id}">${hrGroup.groupName}</option>
                    </#list>
                </select>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>

