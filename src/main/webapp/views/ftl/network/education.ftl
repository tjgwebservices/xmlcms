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
<h2>${internationalNewsTitle}</h2>
<p>${internationalNewsDescription}</p>
<p>${internationalNewsTopics}</p>


     </main>
   <@nav.footing />
