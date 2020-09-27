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
<#macro navigation>
   <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/articleList">Article List</a></li>
            <li><a href="/publish">Publish Test</a></li>
        </ul>
   </nav>
</#macro>
<#macro sidelinks>
   <aside>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/articleList">Article List</a></li>
            <li><a href="/publish">Publish Test</a></li>
        </ul>
   </aside>
</#macro>
<#macro mainsection>
   <main>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/articleList">Article List</a></li>
            <li><a href="/publish">Publish Test</a></li>
        </ul>
   </main>
</#macro>