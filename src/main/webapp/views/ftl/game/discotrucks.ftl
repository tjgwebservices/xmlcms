<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
<!DOCTYPE html>
<html>
   <head>
      <title>Games Demo - Disco Trucks</title>
       <@nav.cssheading />
<style>
canvas {
    border: 1px solid #899de2;
    background-color: #acbae0;
    width: 90%;
}
</style>
    
   </head>
   <body>
   <@nav.navigation />

</head>
<body>

<main>

</main>
<div>
</div>
<div>
<footer>
<h4>TJG Web Services - Disco Trucks</h4>
<p>Click on the screen to change direction to avoid obstacles.</p>
<p>Add custom built HTML5 games to your website with developers from TJG Web Services.</p>
<p>Advertise on game sites with TJG Web Services Consulting Group.</p>

</footer>

</body>
<script>
var highscore = <#if highscore??>${highscore}</#if>;



</script>
<script src="<@spring.url '/js/gamebase.js'/>"></script>
<script src="<@spring.url '/js/gamediscotrucks.js'/>"></script>

</html>

