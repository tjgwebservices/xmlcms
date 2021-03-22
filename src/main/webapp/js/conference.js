(function () {

var messages=["Email for info","Simulation and Modeling Tools","Visualization and Charting","Geographic Information Systems",
			"Email for pricing"];

var showModal = function(selection) {
	document.getElementById('modalwindow').style.display='block';
	document.getElementById("message").innerHTML=messages[selection];
}

var signupButtons = document.querySelectorAll('button.signup4');
for (var i=0;i<signupButtons.length;i++){
    signupButtons[i].addEventListener('click',function(e){
        e.preventDefault();
        showModal(4);
    });
}


var countDownDate = new Date("Mar 22, 2021 08:00:00").getTime();
var x = setInterval(function() {
var now = new Date().getTime();
var distance = countDownDate - now;
var days = Math.floor(distance / (1000 * 60 * 60 * 24));
var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
var seconds = Math.floor((distance % (1000 * 60)) / 1000);
document.getElementById("countdown").innerHTML = "Investigative Peace Journalism starts in: " + days + "d " + hours + "h "
+ minutes + "m " + seconds + "s ";
if (distance < 0) {
	clearInterval(x);
	document.getElementById("countdown").innerHTML = "Welcome to Investigative Peace Journalism";
}
}, 1000);

})();


var Menu = (function() {
			console.log("menu");
	
	var container = document.querySelector( 'article > article' ),						
		cover = document.querySelector( 'article > article > article > section:nth-child(1)' ),
		middle = document.querySelector( 'article > article > article > section:nth-child(2)' ),
		right = document.querySelector( 'article > article > article > section:nth-child(3)' ),
		open = document.querySelector('a.button-open'),
		close = document.querySelector('span.button-close'),
		//$details = $container.find( 'a.rm-viewdetails' ),

		init = function() {
			console.log("menu init");

			initEvents();

		},
		initEvents = function() {

			open.addEventListener( 'click', function( event ) {
				event.preventDefault();
				openMenu();
				return false;

			} );

			close.addEventListener( 'click', function( event ) {
				event.preventDefault();
				closeMenu();
				return false;

			} );
			/*
			details.addEventListener( 'click', function( event ) {

				container.removeClass( 'rm-in' ).children( 'div.rm-modal' ).remove();
				viewDetails( ( this ) );
				return false;

			} ); */
			
		},
		openMenu = function() {

			container.classList.add( 'rm-open' );

		},
		closeMenu = function() {

			container.classList.remove( 'rm-open' );
			container.classList.remove( 'rm-nodelay' );
			container.classList.remove( 'rm-in' );

		}/*,
		viewDetails = function( recipe ) {

			var title = recipe.text(),
				img = recipe.data( 'thumb' ),
				description = recipe.parent().next().text(),
				url = recipe.attr( 'href' );

			var $modal = $( '<div class="rm-modal"><div class="rm-thumb" style="background-image: url(' + img + ')"></div><h5>' + title + '</h5><p>' + description + '</p><a href="' + url + '">See the recipe</a><span class="rm-close-modal">x</span></div>' );

			$modal.appendTo( $container );

			var h = $modal.outerHeight( true );
			$modal.css( 'margin-top', -h / 2 );

			setTimeout( function() {

				$container.addClass( 'rm-in rm-nodelay' );

				$modal.find( 'span.rm-close-modal' ).on( 'click', function() {

					$container.removeClass( 'rm-in' );

				} );
			
			}, 0 );

		}*/;

	return { init : init };
	

})();

Menu.init();
