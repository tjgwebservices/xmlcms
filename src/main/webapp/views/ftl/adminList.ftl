<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <h3>Admin List</h3>
     <a href="addArticle">Add Article</a>
     <br><br>
      <div>
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
               <td>${administrator.id}</td>
               <td>${administrator.groupName}</td>
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
      <a href="<@spring.url '/addAdministrator'/>">Add Administrator</a>  
      <a href="<@spring.url '/addAdministratorGroup'/>">Add Administrator Group</a>  
      <a href="<@spring.url '/addLecture'/>">Add Lecture</a>  
      <a href="<@spring.url '/addLectureGroup'/>">Add Lecture Group</a>  
      <a href="<@spring.url '/addSchool'/>">Add School</a>  
      <a href="<@spring.url '/addStudent'/>">Add Student</a>  
      <a href="<@spring.url '/display'/>">Display Subscription List</a>  
      <a href="<@spring.url '/publish'/>">Publish to Subscription List</a>  
      </div>
   </body>
</html>

