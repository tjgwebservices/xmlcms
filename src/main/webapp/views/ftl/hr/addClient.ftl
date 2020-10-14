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

      <#if hrGroups??>       
      <div>
         <fieldset>
            <legend>JobSeeker</legend>
            <form name="article" action="" method="POST">
               <p>First Name: <@spring.formInput "clientForm.clientFirstName" "" "text"/></p><br/>
               <p>Last Name: <@spring.formInput "clientForm.clientLastName" "" "text"/></p><br/>
               <p>Specialty: <@spring.formInput "clientForm.clientSpecialty" "" "text"/></p><br/>
               <p>Email: <@spring.formInput "clientForm.clientContact" "" "text"/></p>
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
      <#else>
            <p>No HR Groups <a href="/hr/addHrGroup">Add HR Group</a></p>
      </#if>       
       
       
   </body>
    
</html>

