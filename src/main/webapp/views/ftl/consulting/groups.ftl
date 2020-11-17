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
</nav>
</section>
<aside>
<h2>${consulting.heading}</h2>
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

<article><h2><a href="/index">${mainarticle[0]}</a></h2>
<p>${mainarticle[0]}</p>
<img src="img/${mainarticle[2]}" alt="TJG Web Services Consulting Group" title="TJG Web Services Consulting Group" />

</p>
<span></span>
<span></span>
<span></span>
<span></span>
</article>

</article>
</#list>

<#list consulting.subarticles as subarticle>
<article>

<section><h2><a href="/index">${subarticle[0]}</a></h2>

<p><img src="${subarticle[2]}" />${subarticle[1]}</p>
</section>

</article>
</#list>

<#list consulting.leadarticles as leadarticle>
<article>

<div><h2><a href="/index">${leadarticle[0]}</a></h2>
<p><img src="${leadarticle[2]}" />${leadarticle[1]}</p>
</div>

</article>
</#list>

<footer id="contact">
<section>


</section>
</footer>
</body>
</html>