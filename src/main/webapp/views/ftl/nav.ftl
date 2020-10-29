<#import "/spring.ftl" as spring/>

<#macro heading>
   <head>
      <meta charset="UTF-8" />
      <title>TJGXMLCMS - State of the Art Content Management System</title>
        <meta name="description" content="TJGXMLCMS - State of the Art Content Management System" />
        <meta name="keywords" content="TJGXMLCMS Content Management System" />
        <meta name="author" content="TJGXMLCMS">
        <meta name="robots" content="index, follow">
        <meta name="revisit-after" content="3 day">
        <meta name="viewport" content="width=device-width, initial-scale=1" />      
        <link rel="apple-touch-icon" sizes="180x180" href="/images/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="/images/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/images/favicon-16x16.png">
        <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>
        <link rel="stylesheet"
             type="text/css" href="<@spring.url '/css/main.css'/>"/>
        <link rel="stylesheet"
             type="text/css" href="<@spring.url '/css/calendar.css'/>"/>
   </head>
</#macro>
<#macro cssheading>
      <meta charset="UTF-8" />
        <meta name="description" content="TJGXMLCMS - State of the Art Content Management System" />
        <meta name="keywords" content="TJGXMLCMS Content Management System" />
        <meta name="author" content="TJGXMLCMS">
        <meta name="robots" content="index, follow">
        <meta name="revisit-after" content="3 day">
        <meta name="viewport" content="width=device-width, initial-scale=1" />      
        <link rel="apple-touch-icon" sizes="180x180" href="/images/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="/images/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/images/favicon-16x16.png">

      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/style.css'/>"/>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/main.css'/>"/>
      <link rel="stylesheet"
           type="text/css" href="<@spring.url '/css/calendar.css'/>"/>
</#macro>
<#macro footing>
      <p><a href="<@spring.url '/'/>">Home</a></p>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>

</html>
</#macro>
<#macro footingconference>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
       <script src="<@spring.url '/js/videostream.js'/>"></script>
       <script src="<@spring.url '/js/calendar.js'/>"></script>

</html>
</#macro>
<#macro navigation>
   <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li>
            <span class="dropdown1">
                <a href="/research/researchers">Researchers</a>
                <span class="dropdown1-content">
                    <a href="/research/addResearcher">Add Researcher</a>
                    <a href="/research/addProject">Add Project</a>
                    <a href="/research/addTopic">Add Topic</a>
                    <a href="/research/projects">Projects</a>
                    <a href="/research/topics">Topics</a>
                </span>
            </span>
            </li>
            <li><a href="/videos/videoList">Videos</a>
            <span class="dropdown1">
                <a href="/videos/addVideo">Add Video</a>
                <span class="dropdown1-content">
                    <a href="/reviews/reviewList">Reviews</a>
                    <a href="/research/addArtist">Add Artist</a>
                <a href="/reviews/addReview">Add Review</a>
                </span>
            </span>
            </li>
            <li>
            <span class="dropdown1">
                <a href="/events/eventList">Events</a>
                <span class="dropdown1-content">
                    <a href="/events/addEvent">Add Event</a>
                    <a href="/events/addEventAdvertisement">Add Event Advertisement</a>
                    <a href="/events/addEventAdministrator">Add Event Administrator</a>
                    <a href="/events/eventAdvertisementList">Advertisement List</a>
                </span>
            </span>
            </li>
            <li>
            <span class="dropdown1">
                <a href="/articles/articleList">Articles</a>
                <span class="dropdown1-content">
                    <a href="/articles/addArticle">Add Article</a>
                </span>
            </span>
            </li>

            <li>
            <span class="dropdown1">
                <a href="/hr/employers">Employers</a>
                <span class="dropdown1-content">
                    <a href="/hr/addEmployer">Add Employer</a>                
                    <a href="/hr/clients">Clients</a>
                    <a href="/hr/addClient">Clients</a>
                    <a href="/hr/hrgroups">Human Resource Groups</a>
                </span>
            </span>
            </li>
            <li>
            <span class="dropdown1">
                <a href="/conferences/learn">Training</a>
                <span class="dropdown1-content">
                    <a href="/conferences/forum">Forum</a>
                    <a href="/conferences/room">Conference Room</a>
                    <a href="/conferences/project">Projects</a>
                    <a href="/conferences/workshop">Workshops</a>
                </span>
            </span>
            </li>
        </ul>
   </nav>
</#macro>
<#macro sidelinks>
   <aside>
        <ul>
            <li>TJGXMLCMS</li>
            <li>Demo</li>
            <li><a href="/">Home</a></li>
            <li><a href="/research/researchers">Researchers</a></li>
            <li><a href="/research/projects">Projects</a></li>
            <li><a href="/research/topics">Topics</a></li>
            <li><a href="/research/addResearcher">Add Researcher</a></li>
            <li><a href="/research/addTopic">Add Topic</a></li>
            <li><a href="/research/addProject">Add Project</a></li>
        </ul>
	<ul>
            <li><a href="/aiml/aiAlgorithms">AI Algorithms</a></li>
            <li><a href="/aiml/mlAlgorithms">ML Algorithms</a></li>
            <li><a href="/aiml/addAiAlgorithm">Add Artificial Intelligence</a></li>
            <li><a href="/aiml/addMlAlgorithm">Add Machine Learning</a></li>
		<li>TJGXMLCMS Training</li>
		<li>TJGXMLCMS Community Edition</li>
		<li>TJGXMLCMS Enterprise Edition</li>
		<li>Calendar</li>
		<li>Comments</li>
		<li>Integrations</li>
		<li>Analytics</li>
	</ul>
   </aside>
</#macro>
<#macro sidelinksadmin>
   <aside>
        <ul>
            <li>Demo Application</li>
            <li>Course Management</li>
            <li><a href="/">Home</a></li>
            <li><a href="<@spring.url '/schools/addAdministrator'/>">Add Administrator</a></li>
            <li><a href="<@spring.url '/schools/addAdministratorGroup'/>">Add Administrator Group</a></li>
            <li><a href="<@spring.url '/schools/addLecture'/>">Add Lecture</a></li>
            <li><a href="<@spring.url '/schools/addLecturer'/>">Add Lecturer</a></li>
            <li><a href="<@spring.url '/schools/addLectureNote'/>">Add Lecture Note</a></li>
            <li><a href="<@spring.url '/schools/addCourse'/>">Add Course</a></li>
            <li><a href="<@spring.url '/schools/addSchool'/>">Add School</a></li>
            <li><a href="<@spring.url '/schools/addStudent'/>">Add Student</a></li>
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

        <iframe src="//tjgwebservices.com/management/" ></iframe>
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

      <div>
        <button id="startcall" type="button">Start Call</button>
        <button id="testcall" type="button">Test Call</button>
        <button id="testconnection" type="button">Test Connection</button>
        <button id="audioonly" type="button">Audio Only</button>
        <button id="audiofilter" type="button">Audio Filter</button>
        <button id="hangup" type="button">Hang Up</button>
      </div>
      <div>
        <button id="startcall" type="button">Start Call</button>
      </div>
      <div id="conferenceroom">
      </div>

        <form id="rtcform">
        <label for="rtcconnectname">Connect to video name: </label>
        <input type="text" id="rtcconnectname" name="rtcconnectname" />
        <label for="rtcconnectbutton">Connection:</label>
        <button id="rtcconnectbutton">Connect</button>
        <label for="rtcsend">Send Message:</label>
        <button id="rtcsend">Send</button>
        <video id="rtcremote">
        </video>
        <video id="rtcself">
        </video>
        </form>
        <canvas id="canvas"></canvas>
        <canvas id="srcStream"></canvas>
        <canvas id="peerStream"></canvas>
        <video id="liveStream"></video>
        <form id="connectform">
        <label for="connect">Websocket connection: </label>
        <button id="connect">Connect</button>
        <button id="disconnect">Disconnect</button>
        </form>
        <form id="sendmessage">
        <label for="name">Your name:</label>
        <input type="text" id="name" />
        <button id="send">Send</button>
        </form>

        <p id="greetings"></p>

        <p id="conversation"></p>

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
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
<#macro learn>
   <main>
		<h4>Training Sessions</h4>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
<#macro workshop>
   <main>
		<h4>Workshops</h4>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
<#macro project>
   <main>
		<h4>Projects</h4>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
<#macro report>
   <main>
		<h4>Reports</h4>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>
</#macro>
