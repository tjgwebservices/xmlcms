<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
<head>
   <title><#if titleMessage??>${titleMessage}</#if></title>
    <@nav.cssheading />
</head>
<body>
<#if errorMessage??>
<div style="color:red;font-style:italic;">
   ${errorMessage}
</div>
</#if>

<div>
   <fieldset>
      <legend><#if titleMessage??>${titleMessage}</#if></legend>
      <form name="project" action="" method="POST">
         <p>Project Name: <@spring.formInput "projectForm.projectName" 
            "" "text"/></p><br/>
         <p>Subject: <@spring.formInput "projectForm.projectSubject" 
            "" "text"/></p><br/>
         <p>Description: <@spring.formInput "projectForm.projectDescription" 
            "" "text"/></p><br/>
          <select id="researcherId" name="researcherId">
              <#list researchers as researcher>
                  <option value="${researcher.id}">${researcher.researcherLastName}</option>
              </#list>
          </select>
         <input type="submit" value="Create" />
      </form>
   </fieldset>
</div>


</body>
    
</html>




