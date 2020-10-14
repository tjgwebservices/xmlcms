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
      <form name="topic" action="" method="POST">
         <p>Topic Name: <@spring.formInput "topicForm.topicName" 
            "topicEditForm.topicName" "text"/></p><br/>
         <p>Subject: <@spring.formInput "topicForm.topicSubject" 
            "topicEditForm.topicSubject" "text"/></p><br/>
         <p>Description: <@spring.formInput "topicForm.topicDescription" 
            "topicEditForm.topicDescription" "text"/></p><br/>
         <p><@spring.formInput "topicForm.id" 
            "topicEditForm.id" "hidden"/></p><br/>
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





