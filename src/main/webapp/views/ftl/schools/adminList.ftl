<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinksadmin />
      <div>
     <h3>Admin List - Sample Class</h3>
     <br><br>
         <h4>Administrators</h4>           
          
         <table border="1">
            <tr>
               <th>Administrator Name</th>
               <th>Group Id</th>
            </tr>
            <#if administrators??>
            <#list administrators as administrator>
            <tr>
               <td>${administrator.administratorName}</td>
               <td>${administrator.administratorGroupId}</td>
            </tr>
            </#list>
            </#if>
         </table>

      </div>
      <div>
         <h4>Administrator Groups</h4>           
          
         <table border="1">
            <tr>
               <th>Administrator Group Id</th>
               <th>Group Name</th>
            </tr>
            <#if administratorGroups??>
            <#list administratorGroups as administratorGroup>
            <tr>
               <td>${administratorGroup.id}</td>
               <td>${administratorGroup.groupName}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>
      <div>
         <h4>Course</h4>           
          
         <table border="1">
            <tr>
               <th>Course Id</th>
               <th>CourseName</th>
            </tr>
            <#if courses??>
            <#list courses as course>
            <tr>
               <td>${course.id}</td>
               <td>${course.courseName}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

      <div>
         <h4>Lectures</h4>           
          
         <table border="1">
            <tr>
               <th>Lecture Id</th>
               <th>Lecture Name</th>
            </tr>
            <#if lectures??>
            <#list lectures as lecture>
            <tr>
               <td>${lecture.id}</td>
               <td>${lecture.lectureName}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

      <div>
         <h4>Lecturers</h4>           
          
         <table border="1">
            <tr>
               <th>Lecturer Name</th>
            </tr>
            <#if lecturers??>
            <#list lecturers as lecturer>
            <tr>
               <td>${lecture.lecturerName}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>


      <div>
         <h4>Lecture Notes</h4>           
          
         <table border="1">
            <tr>
               <th>Lecture Note Instructor</th>
               <th>Lecture Note Lecture</th>
               <th>Lecture Note Text</th>
            </tr>
            <#if lectureNotes??>
            <#list lectureNotes as lectureNote>
            <tr>
               <td>${lectureNote.noteInstructor}</td>
               <td>${lectureNote.noteLecture}</td>
               <td>${lectureNote.noteText}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

      <div>
         <h4>Schools</h4>           
         <table border="1">
            <tr>
               <th>School Name</th>
               <th>School Administrator</th>
            </tr>
            <#if schools??>
            <#list schools as school>
            <tr>
               <td>${school.schoolName}</td>
               <td>${school.schoolLecturer}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

      <div>
         <h4>Students</h4> 
         <table border="1">
            <tr>
               <th>Last Name</th>
               <th>First Name</th>
               <th>Course Id</th>
            </tr>
            <#if students??>
            <#list students as student>
            <tr>
               <td>${student.lastName}</td>
               <td>${student.firstName}</td>
               <td>${student.courseId}</td>
            </tr>
            </#list>
            </#if>
         </table>
      </div>

      <div>
      <a href="<@spring.url '/display'/>">Display Subscription List</a>  
      <a href="<@spring.url '/publish'/>">Publish to Subscription List</a>  
      </div>
   </body>
</html>

