<#import "/spring.ftl" as spring/>

<#macro heading>
   <head>
      <meta charset="UTF-8" />
      <title>Welcome</title>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/main.css'/>"/>
   </head>
</#macro>