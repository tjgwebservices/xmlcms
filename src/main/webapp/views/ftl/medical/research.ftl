<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 


<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
<nav>
<section>
<ul>
<#list market.links as link>
    <li><a href="${link}">${link}</a></li>
</#list>
</ul>
</nav>
<aside>
<h2>${market.heading}</h2>
<h4><a href="//tjgwebservices.com/">TJG Web Services</a></h4>
<ul>
<#list market.links as link>
    <li><a href="${link}">${link}</a></li>
</#list>
</ul>
</aside>
<main>
<#list market.articles as article>
<#list article as key,value>

<article>

<h2>${key}</h2>

<p>${value}</p>


</article>
</#list>
</#list>

<#list market.topics as topic>
<#list topic as key,value>
<article>

<h2>${key}</h2>

<p>${value}</p>


</article>
</#list>
</#list>

<#list market.services as service>
<#list service as key,value>
<article>

<h2>${key}</h2>

<p>${value}</p>


</article>
</#list>
</#list>

     </main>
   <@nav.footing />

