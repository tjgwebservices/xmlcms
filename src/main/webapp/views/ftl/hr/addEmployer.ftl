<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Employer</title>
   <@nav.cssheading />
    
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
            <legend>Employer</legend>
            <form name="employer" action="" method="POST">
               <p>Employer Name: <@spring.formInput "employerForm.employerName" "" "text"/></p><br/>
               <p>Employer POC: <@spring.formInput "employerForm.employerContact" "" "text"/></p><br/>
               <p>Employer POC Title: <@spring.formInput "employerForm.employerContactType" "" "text"/></p><br/>
               <p>Employer Email: <@spring.formInput "employerForm.employerContactInfo" "" "text"/></p>
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


