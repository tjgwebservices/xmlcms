<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE HTML>
<html>
   <@nav.heading />
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
   <@nav.conference />
      <#if conferenceName??>
      <h2>${conferenceName}</h2>
      </#if>
      <#if message??>
      <h2>${message}</h2>
      </#if>
      <div>
        <button id="test1" type="button">Test 1</button>
        <button id="test2" type="button">Test 2</button>
        <button id="test3" type="button">Test 3</button>
      </div>
      <div id="conferenceroom">
      </div>

<div class="flexChild" id="camera-container">
  <div class="camera-box">
    <video id="received_video" autoplay></video>
    <video id="local_video" autoplay muted></video>
    <button id="hangup-button" disabled>
      Hang Up
    </button>

<ul class="userlistbox">
    <li></li>
</ul>

  </div>
</div>
<div>

<canvas id="canvas">
</canvas>
    <video id="leftVideo" autoplay></video>
    <video id="rightVideo" autoplay></video>

</div>

   <@nav.footingconferenceroomtest />

