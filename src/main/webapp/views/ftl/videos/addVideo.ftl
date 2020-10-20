<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title><#if titleMessage??>${titleMessage}</#if></title>
       <@nav.cssheading />
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
            <legend><#if titleMessage??>${titleMessage}</#if></legend>
            <form name="lecture" enctype="multipart/form-data" action="" method="POST">
               Music Video Name: <@spring.formInput "videoForm.videoName" "" "text"/>    <br/>
               Music Video: <@spring.formInput "videoForm.videoContent" "" "file"/>    <br/>
               <input type="submit" value="Upload" />
            </form>
         </fieldset>
      </div>
       
       
   </body>
    
</html>
