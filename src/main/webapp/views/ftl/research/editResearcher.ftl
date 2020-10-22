<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Researcher</title>
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
       
      <div>
         <fieldset>
            <legend>Researcher</legend>
            <form name="researcherForm" action="/research/editResearcher" method="POST">
    <p>First Name:<input type="text" id="researcherFirstName" name="researcherFirstName" value="${researcherEditForm.researcherFirstName}" /></p><br/>
    <p>Last Name:<input type="text" id="researcherLastName" name="researcherLastName" value="${researcherEditForm.researcherLastName}" /></p><br/>
    <p>Degree:<input type="text" id="researcherDegree" name="researcherDegree" value="${researcherEditForm.researcherDegree}" /></p><br/>
    <p>Major:<input type="text" id="researcherMajor" name="researcherMajor" value="${researcherEditForm.researcherMajor}" /></p><br/>
    <p>Institution:<input type="text" id="researcherInstitution" name="researcherInstitution" value="${researcherEditForm.researcherInstitution}" /></p><br/>
    <p>Specialty:<input type="text" id="researcherSpecialty" name="researcherSpecialty" value="${researcherEditForm.researcherSpecialty}" /></p><br/>
    <p><input type="hidden" id="id" name="id" value="${researcherEditForm.id}" /></p><br/>
               <input type="submit" value="Edit" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>




