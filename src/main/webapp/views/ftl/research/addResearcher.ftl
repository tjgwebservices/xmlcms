<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title>Add Researcher</title>
   <@nav.cssheading />
    
   </head>
   <body>
   <@nav.navigation />
    <main>

      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
      <div>
         <fieldset>
            <legend>Researcher</legend>
            <form name="researcher" action="" method="POST">
               <p>First Name: <@spring.formInput "researcherForm.researcherFirstName" "" "text"/></p><br/>
               <p>Last Name: <@spring.formInput "researcherForm.researcherLastName" "" "text"/></p><br/>
               <p>Degree: <@spring.formInput "researcherForm.researcherDegree" "" "text"/></p><br/>
               <p>Major: <@spring.formInput "researcherForm.researcherMajor" "" "text"/></p><br/>
               <p>Institution: <@spring.formInput "researcherForm.researcherInstitution" "" "text"/></p><br/>
               <p>Specialty: <@spring.formInput "researcherForm.researcherSpecialty" "" "text"/></p>
               <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       </main>


   <@nav.footing />




