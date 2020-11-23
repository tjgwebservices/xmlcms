<#macro indexpage>

        <h4>II Data School Requirements Gathering</h4>
        <article>
            <section>
                <h4>II Data School</h4>
                <p>Planning Committee</p>
            </section>
            <section>
                <h4>II Data School</h4>
                <p>Needs Analysis</p>
            </section>
            <section>
                <h4>II Data School</h4>
                <p>Requirements Gathering</p>
            </section>
        </article>
        <article>
            <section>
                <h4>II Data School</h4>
                <p>Preliminary Data</p>
            </section>
            <section>
                <h4>II Data School</h4>
                <p>Big Data</p>
            </section>
            <section>
                <h4>II Data School</h4>
                <p>Data Analysis</p>
            </section>
        </article>

        <article>
                <img src="/images/iidataschool2.png" alt="II Data School" title="II Data School" />
        </article>
        

        <p>II Data School Requirements Gathering</p>
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


</#macro>

<#macro conferencepage>


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

</#macro>

<#macro presentationpage>

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


</#macro>

<#macro workshoppage>

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

</#macro>


<#macro screensharepage>
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

</#macro>

<#macro slidespage>
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
</#macro>