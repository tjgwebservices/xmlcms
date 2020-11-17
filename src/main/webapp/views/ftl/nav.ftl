<#import "/spring.ftl" as spring/>
<#import "/conferencelinks.ftl" as links/>


<#macro heading>
   <head>
   <@links.templateheading />
   </head>
</#macro>
<#macro cssheading>
   <@links.templateheading />
</#macro>
<#macro consultingheading>
   <@links.consultingheading />
</#macro>
<#macro footinghome>
      <p><a href="<@spring.url '/'/>">Home</a></p>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
       <script src="<@spring.url '/js/chat.js'/>"></script>
       <script src="<@spring.url '/js/book.js'/>"></script>

</html>
</#macro>
<#macro footing>
      <p><a href="<@spring.url '/'/>">Home</a></p>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>

</html>
</#macro>
<#macro footingconference>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
       <script src="<@spring.url '/js/stream.js'/>"></script>

</html>
</#macro>
<#macro footingpresentation>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
       <script src="<@spring.url '/js/presentation.js'/>"></script>

</html>
</#macro>
<#macro footingscreenshare>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
      <script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>
       <script src="<@spring.url '/js/eventtable.js'/>"></script>
       <script src="<@spring.url '/js/screenshare.js'/>"></script>

</html>
</#macro>
<#macro footingslides>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
      <script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>
       <script src="<@spring.url '/js/eventtable.js'/>"></script>
       <script src="<@spring.url '/js/slides.js'/>"></script>

</html>
</#macro>

<#macro footingworkshop>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
       <script src="<@spring.url '/js/workshop.js'/>"></script>

</html>
</#macro>

<#macro footingconferencetest>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
       <script src="<@spring.url '/js/videostream.js'/>"></script>

</html>
</#macro>

<#macro footingform>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
       <script src="<@spring.url '/js/calendar.js'/>"></script>

</html>
</#macro>

<#macro navigation>
<h2>TJGXMLCMS - Demonstration Site</h2>
   <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li>
            <span class="dropdown1">
                    <a href="/news/reports">News</a>
                <span class="dropdown1-content">
                    <a href="/shop/shop">Shop</a>                
                    <a href="/career/careers">Careers</a>                
                    <a href="/videos/videoList">Videos</a>                
                    <a href="/videos/addVideo">Add Video</a>
                    <a href="/reviews/reviewList">Reviews</a>
                    <a href="/research/addArtist">Add Artist</a>
                <a href="/reviews/addReview">Add Review</a>
                </span>
            </span>
            </li>
            <li>
            <span class="dropdown1">
                <a href="/game/games">Games</a>
                <span class="dropdown1-content">
                    <a href="/game/blocks">Blocks</a>
                    <a href="/game/discotrucks">Disco Trucks</a>
                    <a href="/game/ghosts">Ghosts</a>
                    <a href="/game/pastelblocks">Pastel Blocks</a>
                    <a href="/game/rhythmblocks">Rhythm Blocks</a>
                    <a href="/game/tankblocks">Tank Blocks</a>
                </span>
            </span>
            </li>
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

            <li>
            <span class="dropdown1">
                <a href="/events/eventList">Events</a>
                <span class="dropdown1-content">
                    <a href="/events/addEvent">Add Event</a>
                    <a href="/events/addEventAdvertisement">Add Event Advertisement</a>
                    <a href="/events/addEventAdministrator">Add Event Administrator</a>
                    <a href="/events/eventAdvertisementList">Advertisement List</a>
                    <a href="/consulting/groups">Consulting</a>
                    <a href="/consulting/about">About</a>
                    <a href="/consulting/contact">Contact</a>
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
                    <a href="/conferences/presentation">Presentation Room</a>
                    <a href="/conferences/project">Projects</a>
                    <a href="/conferences/workshop">Workshops</a>
                    <a href="/conferences/screenshare">Screenshare</a>
                    <a href="/conferences/slides">Slides</a>
                </span>
            </span>
            </li>

            <li>
            <span class="dropdown1">
                <a href="/subscriptions/subscribe">Subscribe</a>
                <span class="dropdown1-content">
                    <a href="/subscriptions/subscribers">Subscribers</a>
                    <a href="/subscriptions/unsubscribe">Unsubscribe</a>
                    <a href="/subscriptions/editSubscription">Edit Subscription</a>
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
<#macro sidelinks2>
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


   <@links.videos />


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
<#macro adminsection>
   <main>
		<h4>Administration Section</h4>
		<p>Discussion topics:</p>
		<br />
   </main>
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



   <@links.videos />

   <@links.frames />


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
        <button id="testcall" type="button">Activate Room</button>
        <button id="startcall" type="button">Start Call</button>
        <button id="testconnection" type="button">Connect</button>
        <button id="testconnection2" type="button">Test 2</button>
        <button id="testconnection3" type="button">Test 3</button>
        <button id="testconnection4" type="button">Test 4</button>
        <button id="testconnection5" type="button">Test 5</button>
        <button id="audioonly" type="button">Audio Only</button>
        <button id="audiofilter" type="button">Audio Filter</button>
        <button id="hangup" type="button">Hang Up</button>
        <button id="startcall" type="button">Initiate Call</button>
      </div>
      <div>
        <table id="eventTable">
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </table>
      </div>
      <div id="conferenceroom">
      </div>
      <article>
      <section>
        <div>
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
        </div>
        </section>
        <section>
            <canvas id="canvas"></canvas>
            <canvas id="srcStream"></canvas>
            <canvas id="peerStream"></canvas>
            <video id="liveStream"></video>
        </section>
        <section>
        <form id="connectform">
        <label for="connect">Websocket connection: </label>
        <button id="connect">Connect</button>
        <button id="disconnect">Disconnect</button>
        </form>
        </section>
        </article>
        <article>
        <section>
            <form id="sendmessage">
            <label for="name">Your name:</label>
            <input type="text" id="name" />
            <button id="send">Send</button>
            </form>
        </section>
        <section>
        <p id="greetings"></p>
        </section>
        <section>
        <p id="conversation"></p>
        <ul id="commands"></ul>
        <table id="commandTable"></table>
        </section>
        </article>

        <article>
        <section>
		<h4>Objectives</h4>
		<p>The objectives of the Conference Series are:</p>
		<ul>
			<li>Share experiences in fields to serve the community</li>
			<li>Provide learning resources</li>
			<li>Share strategies for success</li>
			<li>Provide guidance for mistakes made along the way</li>
		</ul>
        </section>
         <section>
		<br />
        </section>
         <section>
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
        </section>
        </article>
   </main>
</#macro>
<#macro book>
<div class="datasciencebook">
        <div class="click swing panel">
            <div class="first">
                    <p>II Data School</p>
                    <p>Welcome to II Data School</p>
            </div>
            <div class="front" id="text1">
                    <h2>Training</h2>
            </div>
            <div class="last">
                    <p>Training from II Data School</p>
            </div>
            <div class="back" id="text2">
                <div class="pad">
                        <h2>Training from II Data School</h2>
                </div>
            </div>
        </div>
    </div>
    <#if pages??>
            <#list pages as page>
                <article class="articles">
                <h2>${page.title}</h2>
                <p>${page.content}</p>
                </article>
            </#list>
    
    </#if>
</#macro>

<#macro chat>
<div id="chatBox">
</div>
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
<#macro presentation>
   <main>
		<h4>Presentation</h4>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
        <button id="startcall" type="button">Start Presentation</button>
      <div>
        <table id="eventTable">
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </table>
      </div>
      <div id="conferenceroom">
      </div>

   </main>
</#macro>

<#macro workshop>
   <main>
		<h4>Workshops</h4>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
        <button id="startcall" type="button">Start Workshop</button>
      <div>
        <table id="eventTable">
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </table>
      </div>
      <div id="conferenceroom">
      </div>

   </main>
</#macro>
<#macro screenshare>
   <main>
		<h4>Screenshare</h4>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
        <button id="startcall" type="button">Start Workshop</button>
        <h1>Realtime communication with WebRTC</h1>

        <video id="localVideo" autoplay playsinline></video>
        <video id="remoteVideo" autoplay playsinline></video>

        <div>
          <button id="startButton">Start</button>
          <button id="callButton">Call</button>
          <button id="hangupButton">Hang Up</button>
        </div>
      <div>
        <table id="eventTable">
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </table>
            <canvas id="photo"></canvas>
        <textarea id="dataChannelSend"></textarea>
        <textarea id="dataChannelReceive"></textarea>
        <div>
          <button id="snap">Snap</button>
          <button id="send">Send</button>
          <button id="snapAndSend">Snap and Send</button>
        </div>
        <div id="trail">
        </div>
      </div>
      <div id="conferenceroom">
      </div>

   </main>
</#macro>
<#macro slides>
   <main>
		<h4>Slides</h4>
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
        <button id="startcall" type="button">Start Workshop</button>
        <h1>Realtime communication with WebRTC</h1>

        <video id="localVideo" autoplay playsinline></video>
        <video id="remoteVideo" autoplay playsinline></video>

        <div>
          <button id="startButton">Start</button>
          <button id="callButton">Call</button>
          <button id="hangupButton">Hang Up</button>
        </div>
      <div>
        <table id="eventTable">
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </table>
      </div>
            <canvas id="photo"></canvas>
        <textarea id="dataChannelSend"></textarea>
        <textarea id="dataChannelReceive"></textarea>
        <div>
          <button id="snap">Snap</button>
          <button id="send">Send</button>
          <button id="snapAndSend">Snap and Send</button>
        </div>
        <div id="trail">
        </div>

      <div id="conferenceroom">
      </div>

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
