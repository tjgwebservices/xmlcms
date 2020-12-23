<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
 
<!DOCTYPE HTML>
<html>
    <head>
        <@nav.cssheading />
          <link rel="stylesheet"
               type="text/css" href="<@spring.url '/css/music.css'/>"/>
    </head>
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
   <@nav.project />
    <main>

            <div>
            </div>
            <svg height='100' id='level' width='100'>
              <path d='M 15.39 75 A 49 49 0, 0, 1, 50 2' fill='none' stroke-width='4' stroke='blue'></path>
              <circle cx='50' cy='50' fill='none' r='40' stroke-width='4' stroke='grey'></circle>
              <circle cx='50' cy='17' fill='grey' r='3' stroke-width='0' stroke='none'></circle>
            <rect fill='none' height='100' id='knob' width='100'>

            </rect>

            </svg>

            <div id="selector2">

            </div>
            <div id="knobs">

            </div>

            <button>Test</button>

            <div id="keyboard">
            </div>
            <div id='log'></div>
            <div id="keyboard1">
            </div>
            <div id="controls">
            <button onclick="stopSequences();">Stop</button>
            </div>
            <div id="records">
            </div>
    </main>
      <#if conferenceName??>
      <h2>${conferenceName}</h2>
      </#if>
      <h2>Projects</h2>
      <#if message??>
      <h2>${message}</h2>
      </#if>

   <@nav.footingmusic />

