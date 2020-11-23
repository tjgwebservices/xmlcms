<#import "/spring.ftl" as spring/>
<#import "/nav.ftl" as nav/>
<!DOCTYPE html>  
<html>
    <head>
   <@nav.cssheading />
    <link href="/css/forum.css?v=0.0.0.0.9" rel="stylesheet">
    </head>
   <body>
   <@nav.navigation />
   <@nav.sidelinks />
     <main>


<style>

iframe {
    height: 600px;
    border: none;
}

main article section {
    height:auto;
    min-height: auto;
}

article > article {
	font-family: Arial;
}

article > article > section {
	background-color: #0C394B;
	color: #FFF;
	text-align: center;
}

article > article:nth-of-type(2) > section {
	background-color: #FEE254;
	color: #0C394B;
}

body div > article > article > section > img,
div > article > section > img,
main > article > article > section > img,
main > article > article > section > video {
	width: 100%;
        display: block;
        float: left;
}

#countdown{
	text-align: center;
	font-weight: 700;
	font-size: 28px;
}
</style>
<h4><a href="/index" target='_parent'>TJG Web Services</a></h4>
<h3>TJG Web Conference Series Planning Sessions</h3>
<p id="countdown"></p>
<script>

var countDownDate = new Date("Nov 25, 2020 08:00:00").getTime();
var x = setInterval(function() {
  var now = new Date().getTime();
  var distance = countDownDate - now;
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
  document.getElementById("countdown").innerHTML = days + "d " + hours + "h "
  + minutes + "m " + seconds + "s ";
  if (distance < 0) {
    clearInterval(x);
    document.getElementById("countdown").innerHTML = "";
  }
}, 1000);
</script>
<article>
	<article>
		<section>
			<p>TJG Web Conference Series</p>
		</section>
		<section>
			<p>Next conference: TJG Web Conference Planning Sessions</p>
		</section>
		<section>
			<p>TJG Web Conferences</p>
		</section>
	</article>
	<article>
		<section>
			<img src="//tjgwebservices.com/networks/newsnetwork/images/tjg_news001.png" alt="TJG Conference Series" title="TJG Conference Series" />
		</section>
		<section>
			<img src="//tjgwebservices.com/networks/newsnetwork/images/tjg_news002.png" alt="TJG Conference Series" title="TJG Conference Series" />
		</section>
		<section>
			<img src="//tjgwebservices.com/networks/newsnetwork/images/tjg_news003.png" alt="TJG Conference Series" title="TJG Conference Series" />
		</section>
	</article>

</article>


<article>
	<article>
		<section>

<video width="320" height="240" controls="">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0001.ogg">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0001.ogg" type="video/ogg">
Your browser does not support the video tag.
</video>
		
		
		</section>
		<section>
<video width="320" height="240" controls="">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0002.ogg">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0002.ogg" type="video/ogg">
Your browser does not support the video tag.
</video>

		</section>
		<section>
<video width="320" height="240" controls="">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0003.ogg">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0003.ogg" type="video/ogg">
Your browser does not support the video tag.
</video>
		</section>
	</article>
	<article>
		<section>
		<video width="320" height="240" controls="">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0004.ogg">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0004.ogg" type="video/ogg">
Your browser does not support the video tag.
</video>

		</section>
		<section>
<video width="320" height="240" controls="">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0005.ogg">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0005.ogg" type="video/ogg">
Your browser does not support the video tag.
</video>

		</section>
		<section>
<video width="320" height="240" controls="">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0006.ogg">
<source src="//forum.tjgwebservices.com/platforms/AIMLBD0006.ogg" type="video/ogg">
Your browser does not support the video tag.
</video>

		</section>
	</article>

	<article>
<section>
</section>

<section>
<p>Upload a video for review:</p>

<iframe src="//forum.tjgwebservices.com/musicvideos/"></iframe>


</section>

<section>
</section>

</article>
     </main>
   <@nav.footing />