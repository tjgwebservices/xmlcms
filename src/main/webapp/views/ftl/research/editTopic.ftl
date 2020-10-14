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
      <form name="topic" action="/research/editTopic" method="POST">
         <p>Topic Name: <input type="text" id="topicName" name="topicName" value="${topicEditForm.topicName}" /></p><br/>
         <p>Subject: <input type="text" id="topicSubject" name="topicSubject" value="${topicEditForm.topicSubject}" /></p><br/>
         <p>Description: <input type="text" id="topicDescription" name="topicDescription" value="${topicEditForm.topicDescription}" /></p><br/>
         <p><input type="hidden" id="id" name="id" value="${topicEditForm.id}" /></p><br/>
          <select id="researcherId" name="researcherId">
              <#list researchers as researcher>
                  <option value="${researcher.id}">${researcher.researcherLastName}</option>
              </#list>
          </select>
         <input type="submit" value="Edit" />
      </form>
   </fieldset>
</div>


</body>
    
</html>






