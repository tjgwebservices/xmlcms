<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
<!DOCTYPE html> 
<html>
    <head>
   <@nav.cssheading />
    <link href="/css/forum.css?v=0.0.0.0.9" rel="stylesheet">
    </head>
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <main>
<h2>${worldNewsTitle}</h2>
<p>${worldNewsDescription}</p>
<p>${worldNewsTopics}</p>


     </main>
   <@nav.footing />
