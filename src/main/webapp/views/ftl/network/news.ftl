<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
<!DOCTYPE html> 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <main>
<h2>${worldNewsTitle}</h2>
<p>${worldNewsDescription}</p>
<p>${worldNewsTopics}</p>


     </main>
   <@nav.footing />
