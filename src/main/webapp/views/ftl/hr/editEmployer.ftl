<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Edit Employer</title>
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
            <legend>Edit Employer</legend>
            <form name="employer" action="" method="POST">
               <p>Employer Name: <@spring.formInput "employerForm.employerName" "employerEditForm.employerName" "text"/></p><br/>
               <p>Employer POC: <@spring.formInput "employerForm.employerContact" "employerEditForm.employerContact" "text"/></p><br/>
               <p>Employer POC Title: <@spring.formInput "employerForm.employerContactType" "employerEditForm.employerContactType" "text"/></p><br/>
               <p>Employer Email: <@spring.formInput "employerForm.employerContactInfo" "employerEditForm.employerContactInfo" "text"/></p>
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


