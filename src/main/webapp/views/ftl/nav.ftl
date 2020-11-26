<#import "/spring.ftl" as spring/>
<#import "/conferencelinks.ftl" as links/>
<#import "/newssection.ftl" as news/>

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
<#macro footingcalendardisplay>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
       <script src="<@spring.url '/js/calendarDisplay.js'/>"></script>

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
<#macro footingmessages>
    <footer>
        <h6>Powered by TJGXMLCMS</h6>
    </footer>
    </body>
       <script src="<@spring.url '/js/script.js'/>"></script>
       <script src="<@spring.url '/js/message.js'/>"></script>

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
<h2><img src="/images/iidataschool_banner001.png" alt="II Data School" title="II Data School" /></h2>
<h2>II Data School - Requirements Gathering Committee</h2>
   <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li>
            <span class="dropdown1">
                    <a href="/news/news">News</a>
                <span class="dropdown1-content">
                    <a href="/international/international">International</a>
                    <a href="/international/news">News</a>
                    <a href="/international/politics">Politics</a>
                    <a href="/international/finance">Finance</a>
                    <a href="/international/business">Business</a>
                    <a href="/international/local">Local News</a>
                    <a href="/consulting/groups">Consulting</a>
                    <a href="/news/reports">Reports</a>                
                    <a href="/news/forum">Forum</a>                
                    <a href="/news/news">News</a>
                    <a href="/shop/shop">Shop</a>                
                    <a href="/videos/videoList">Videos</a>                
                    <a href="/videos/addVideo">Add Video</a>
                    <a href="/reviews/reviewList">Reviews</a>
                    <a href="/research/addArtist">Add Artist</a>
                    <a href="/reviews/addReview">Add Review</a>
                    <a href="/articles/articleList">Articles</a>
                    <a href="/articles/addArticle">Add Article</a>
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
                    <a href="/subscriptions/subscribe">Subscribe</a>
                    <a href="/subscriptions/unsubscribe">Unsubscribe</a>
                </span>
            </span>
            </li>
            <li>
            <span class="dropdown1">
                <a href="/consulting/groups">Networks</a>
                <span class="dropdown1-content">
                    <a href="/network/education">Education Network</a>
                    <a href="/network/freelance">Freelance Network</a>
                    <a href="/network/news">News Network</a>
                    <a href="/medical/technologies">Medical</a>
                    <a href="/medical/technologies">Technologies</a>
                    <a href="/medical/research">Research</a>
                    <a href="/medical/consulting">Consulting</a>
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
                    <a href="/consulting/about">About</a>
                    <a href="/consulting/contact">Contact</a>
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
                <span class="dropdown1-content">
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

   <@news.indexpage />



   <@links.videos />



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

   <@news.conferencepage />

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

   <@links.frames />
		<br />
		<p>More info: <a href="//conferences.tjgwebservices.com">Conferences</a></p>
   </main>


</#macro>
<#macro presentation>
   <main>

   <@news.presentationpage />

   </main>
</#macro>

<#macro workshop>
   <main>

   <@news.workshoppage />

   </main>
</#macro>
<#macro screenshare>
   <main>

   <@news.screensharepage />

   </main>
</#macro>
<#macro slides>
   <main>

   <@news.slidespage />

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
