<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
<!DOCTYPE html>
<html>
<head>
<title>TJG Web Services Consulting</title>
<link href="/css/consulting.css?v=0.0.0.1" rel="stylesheet">
</head>
<nav>
<section>
<ul>
<#list consulting.links as link>
<#list link as key,value> 
    <li><a href="${value}">${key}</a></li>
</#list>
</#list>
</ul>
<h2>${consulting.heading}</h2>
</nav>
</section>
<aside>
<h4><a href="//tjgwebservices.com/">TJG Web Services</a></h4>
<ul>
<#list consulting.tabs as tab>
<#list tab as key,value> 
    <li><a href="${value}">${key}</a></li>
</#list>
</#list>
</ul>
</aside>
<main>
<#list consulting.mainarticles as mainarticle>
<article>
<#list mainarticle as article> 

<article><h2><a href=\"articles\\?articleid=${article[1]}">${article}</a></h2>
</p>
<span></span>
<span></span>
<span></span>
<span></span>
</article>

</#list>
</article>
</#list>

<#list consulting.subarticles as subarticle>
<article>
<#list subarticle as article> 

<section><h2><a href=\"articles\\?articleid=${article[1]}">${article}</a></h2>
</p>
<p><img src="" /></p>
</section>

</#list>
</article>
</#list>

<#list consulting.leadarticles as leadarticle>
<article>
<#list leadarticle as article> 

<div><h2><a href=\"articles\\?articleid=${article[1]}">${article}</a></h2>
</p>
</div>

</#list>
</article>
</#list>

<footer id="contact">
<section>


</section>
</footer>
</body>
</html>