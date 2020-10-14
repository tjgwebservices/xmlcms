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
            <form name="clientForm" action="" method="POST">
               <p>First Name: <input type="text" id="clientFirstName" name="clientFirstName" value="${clientEditForm.clientFirstName}" /></p><br/>
               <p>Last Name: <input type="text" id="clientLastName" name="clientLastName" value="${clientEditForm.clientLastName}" /></p><br/>
               <p>Specialty: <input type="text" id="clientSpecialty" name="clientSpecialty" value="${clientEditForm.clientSpecialty}" /></p><br/>
               <p>Email: <input type="text" id="clientContact" name="clientContact" value="${clientEditForm.clientContact}" "text"/></p>
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


