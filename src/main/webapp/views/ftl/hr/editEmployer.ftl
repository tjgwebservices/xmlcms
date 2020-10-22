<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Edit Employer</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/main.css'/>"/>
    
   </head>
   <body>
   <@nav.navigation />

      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>

    <#if hrGroups??>
       
      <div>
         <fieldset>
            <legend>Edit Employer</legend>
            <form name="employer" action="/hr/editEmployer" method="POST">
               <p>Employer Name: <input type="text" id="employerName" name="employerName" value=${"employerEditForm.employerName}" /></p><br/>
               <p>Employer POC: <input type="text" id="employerContact" name="employerContact" value="${employerEditForm.employerContact}" /></p><br/>
               <p>Employer POC Title: <input type="text" id="employerContactType" name="employerContactType" value="${employerEditForm.employerContactType}" /></p><br/>
               <p>Employer Email: <input type="text" id="employerContactInfo" name="employerContactInfo" value="${employerEditForm.employerContactInfo}" /></p>
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


