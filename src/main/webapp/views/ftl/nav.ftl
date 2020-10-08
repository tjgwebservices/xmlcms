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
<#macro footing>
      <p><a href="<@spring.url '/articleList'/>">Article List</a></p>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
</html>
</#macro>

<#macro navigation>
   <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/conference">Conference</a>
            <span class="dropdown1">
                <a href="/report">Report</a>
                <span class="dropdown1-content">
                    <a href="/forum">Forum</a>
                    <a href="/projects">Projects</a>
                    <a href="/report">Report</a>
                    <a href="/learn">Training Sessions</a>
                </span>
            </span>
            </li>
            <li><a href="/workshop">Workshops</a></li>
        </ul>
   </nav>
</#macro>
<#macro sidelinks>
   <aside>
        <ul>
            <li>TJG Web Conferences</li>
            <li>Mangament Information Systems</li>
            <li><a href="/">Home</a></li>
            <li><a href="/conference">Conference</a></li>
            <li><a href="/forum">Forum</a></li>
            <li><a href="/articleList">Article List</a></li>
            <li><a href="/publish">Publish Test</a></li>
        </ul>
	<video width="160" height="120" controls="">
		  <source src="//tjgwebservices.com/images/record_000068.ogg">
		  <source src="//tjgwebservices.com/images/record_000068.ogg" type="video/ogg">
		Your browser does not support the video tag.
	</video>
	<video width="160" height="120" controls="">
		  <source src="//tjgwebservices.com/images/record_000060.ogg">
		  <source src="//tjgwebservices.com/images/record_000060.ogg" type="video/ogg">
		Your browser does not support the video tag.
	</video>
	<video width="160" height="120" controls="">
		  <source src="//tjgwebservices.com/images/record_000078.ogg">
		  <source src="//tjgwebservices.com/images/record_000078.ogg" type="video/ogg">
		Your browser does not support the video tag.
	</video>
	<video width="160" height="120" controls="">
		  <source src="//tjgwebservices.com/images/record_000075.ogg">
		  <source src="//tjgwebservices.com/images/record_000075.ogg" type="video/ogg">
		Your browser does not support the video tag.
	</video>
	<video width="160" height="120" controls="">
		  <source src="//tjgwebservices.com/images/record_000079.ogg">
		  <source src="//tjgwebservices.com/images/record_000079.ogg" type="video/ogg">
		Your browser does not support the video tag.
	</video>
	<ul>
		<li>Conference Activities</li>
		<li>Discussion Points</li>
		<li>Events</li>
		<li>Calendar</li>
		<li>Comments</li>
		<li>Integrations</li>
		<li>Analytics</li>
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
<#macro conference>
   <main>
		<h4>Objectives</h4>
		<p>The objectives of the Conference Series are:</p>
		<ul>
			<li>Share experiences in fields to serve the community</li>
			<li>Provide learning resources</li>
			<li>Share strategies for success</li>
			<li>Provide guidance for mistakes made along the way</li>
		</ul>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
<#macro forum>
   <main>
		<h4>Conference Forum</h4>
		<p>Discussion topics:</p>
		<ul>
			<li>Challenges in information systems project methodologies</li>
			<li>Emerging project management methodologies</li>
			<li>Keys to successful information technology projects</li>
			<li>Measures of success for information technology projects</li>
		</ul>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
<#macro learn>
   <main>
		<h4>Training Sessions</h4>
		<p>Management Information Systems Training Sessions:</p>
		<ul>
			<li>Risk Analysis</li>
			<li>Staffing</li>
			<li>Cost Benefit Analysis</li>
			<li>Return on Investment</li>
		</ul>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
<#macro workshop>
   <main>
		<h4>Workshops</h4>
		<p>Management Information Systems Workshops:</p>
		<ul>
			<li>Planning a project</li>
			<li>Human resources training</li>
			<li>Budgeting a project</li>
		</ul>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
<#macro project>
   <main>
		<h4>Projects</h4>
		<p>Management Information Systems Projects:</p>
		<ul>
			<li>Develop a Design Plan</li>
			<li>Develop an Implementation Plan</li>
			<li>Develop a Migration Plan</li>
		</ul>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
<#macro report>
   <main>
		<h4>Reports</h4>
		<p>Management Information Systems Reports:</p>
		<ul>
			<li>Systems Reports</li>
			<li>Budget Reports</li>
			<li>Compliance Reports</li>
		</ul>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
