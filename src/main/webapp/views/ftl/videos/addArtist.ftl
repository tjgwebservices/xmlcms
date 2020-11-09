<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<html>
   <head>
      <title><#if titleMessage??>${titleMessage}</#if></title>
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
            <legend><#if titleMessage??>${titleMessage}</#if></legend>
            <form name="video" action="" method="POST">
               Music Artist Name: <@spring.formInput "artistForm.artistName" "" "text"/>    <br/>
               <input type="submit" value="Create" />
            </form>
         </fieldset>
      </div>
    </main>
       
   <@nav.footing />






