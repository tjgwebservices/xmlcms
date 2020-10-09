<#import "/spring.ftl" as spring/>
 
<html>
   <head>
      <title>Add Lecture Note</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>      
   </head>
   <body>
      <#if errorMessage??>
      <div style="color:red;font-style:italic;">
         ${errorMessage}
      </div>
      </#if>
       
      <div>
         <fieldset>
            <legend>Add Lecture Note</legend>
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



