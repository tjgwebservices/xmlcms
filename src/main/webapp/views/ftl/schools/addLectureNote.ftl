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
            <form name="lectureNote" action="" method="POST">
               Lecture Note Instructor: <@spring.formInput "lectureNoteForm.noteInstructor" "" "text"/>    <br/>
               Lecture: <@spring.formInput "lectureNoteForm.noteLecture" "" "text"/>    <br/>
               Text: <@spring.formInput "lectureNoteForm.noteText" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>



