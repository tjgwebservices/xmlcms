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
            <form name="administrator" action="" method="POST">
               Administrator Name: <@spring.formInput "administratorForm.administratorName" "" "text"/>    <br/>
                <select id="administratorGroupId" name="administratorGroupId">
                    <#list administratorGroups as administratorGroup>
                        <option value="${administratorGroup.id}">${administratorGroup.groupName}</option>
                    </#list>
                </select>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>



