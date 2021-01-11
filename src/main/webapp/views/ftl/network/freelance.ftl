<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
<!DOCTYPE html> 
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <main>
<h2>${internationalNewsTitle}</h2>
<p>${internationalNewsDescription}</p>
<p>${internationalNewsTopics}</p>


     </main>
   <@nav.footing />
