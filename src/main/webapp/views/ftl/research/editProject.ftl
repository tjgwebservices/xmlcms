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
         <p>Topic Name: <input type="text" id="projectName" name="projectName" value="${projectEditForm.projectName}" /></p><br/>
         <p>Subject: <input type="text" id="projectSubject" name="projectSubject value="${projectEditForm.projectSubject}" /></p><br/>
         <p>Description: <input type="text" id="projectDescription" name="projectDescription" value="${projectEditForm.projectDescription}" /></p><br/>
         <p><input type="hidden" id="id" name="id" value="${projectEditForm.projectDescription}"/></p><br/>
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





