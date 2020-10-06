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
        <h4>TJG XML CMS</h4>
        <p>TJG XML Content Management Systems</p>
        <p> - The XML Content Management System is a content management system that separates content from presentation with XML</p>
        <p> The content management system, CMS, is a tool designed to provide an easier management of content on the web.</p>
        <p> To find out more about the TJGXMLCMS, or support the project, contact <a href="mailto:info@tjgwebservices.com">info@tjgwebservices.com</a></p>
        <p>Design Strategies for the CMS</p>
        <ul>
            <li>model based software engineering</li>
            <li>test driven development</li>
            <li>component based software engineering</li>
        </ul>
        <p>Current component development</p>
        <ul>
            <li>Interface for accessing application programming interfaces</li>
            <li>Security authentication and authorization</li>
            <li>Content editing tools</li>
            <li>Data management</li>
            <li>Reporting tools</li>
        </ul>
   </main>
</#macro>
<#macro videosection>
   <main>
        <article>
            <video width="320" height="240" controls>
              <source src="movie.mp4" type="video/mp4">
              Your browser does not support the video tag.
            </video>
        </article>
        <article>
            <video width="320" height="240" controls>
              <source src="movie.mp4" type="video/mp4">
              Your browser does not support the video tag.
            </video>
        </article>
   </main>
</#macro>