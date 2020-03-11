 AOS.init({
 	duration: 800,
 	easing: 'slide'
 });

(function($) {

    var browserWindow = $(window);

    // :: 1.0 Preloader Active Code
    browserWindow.on('load', function () {
        $('.preloader').fadeOut('slow', function () {
            $(this).remove();
        });
    });

	"use strict";

	var isMobile = {
		Android: function() {
			return navigator.userAgent.match(/Android/i);
		},
			BlackBerry: function() {
			return navigator.userAgent.match(/BlackBerry/i);
		},
			iOS: function() {
			return navigator.userAgent.match(/iPhone|iPad|iPod/i);
		},
			Opera: function() {
			return navigator.userAgent.match(/Opera Mini/i);
		},
			Windows: function() {
			return navigator.userAgent.match(/IEMobile/i);
		},
			any: function() {
			return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
		}
	};


	$(window).stellar({
    responsive: true,
    parallaxBackgrounds: true,
    parallaxElements: true,
    horizontalScrolling: false,
    hideDistantElements: false,
    scrollProperty: 'scroll'
  });


	var fullHeight = function() {

		$('.js-fullheight').css('height', $(window).height());
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height());
		});

	};
	fullHeight();


	// Scrollax
   $.Scrollax();

	var carousel = function() {
		$('.carousel-testimony').owlCarousel({
			center: true,
			loop: true,
			items:1,
			margin: 30,
			stagePadding: 0,
			nav: false,
			navText: ['<span class="ion-ios-arrow-back">', '<span class="ion-ios-arrow-forward">'],
			responsive:{
				0:{
					items: 1
				},
				600:{
					items: 2
				},
				1000:{
					items: 3
				}
			}
		});


	};
	carousel();

	$('nav .dropdown').hover(function(){
		var $this = $(this);
		// 	 timer;
		// clearTimeout(timer);
		$this.addClass('show');
		$this.find('> a').attr('aria-expanded', true);
		// $this.find('.dropdown-menu').addClass('animated-fast fadeInUp show');
		$this.find('.dropdown-menu').addClass('show');
	}, function(){
		var $this = $(this);
			// timer;
		// timer = setTimeout(function(){
			$this.removeClass('show');
			$this.find('> a').attr('aria-expanded', false);
			// $this.find('.dropdown-menu').removeClass('animated-fast fadeInUp show');
			$this.find('.dropdown-menu').removeClass('show');
		// }, 100);
	});


	$('#dropdown04').on('show.bs.dropdown', function () {
	  console.log('show');
	});

	// scroll
	var scrollWindow = function() {
		$(window).scroll(function(){
			var $w = $(this),
					st = $w.scrollTop(),
					navbar = $('.ftco_navbar'),
					sd = $('.js-scroll-wrap');

			if (st > 150) {
				if ( !navbar.hasClass('scrolled') ) {
					navbar.addClass('scrolled');
				}
			}
			if (st < 150) {
				if ( navbar.hasClass('scrolled') ) {
					navbar.removeClass('scrolled sleep');
				}
			}
			if ( st > 350 ) {
				if ( !navbar.hasClass('awake') ) {
					navbar.addClass('awake');	
				}
				
				if(sd.length > 0) {
					sd.addClass('sleep');
				}
			}
			if ( st < 350 ) {
				if ( navbar.hasClass('awake') ) {
					navbar.removeClass('awake');
					navbar.addClass('sleep');
				}
				if(sd.length > 0) {
					sd.removeClass('sleep');
				}
			}
		});
	};
	scrollWindow();

    // var scrollWindow2 = function() {
    //     $(window).scroll(function(){
    //         var $w = $(this),
    //             st = $w.scrollTop(),
    //             backimg = $('.backimg'),
    //             sd = $('.js-scroll-wrap');
	//
    //         if (st < $(window).height()	) {
    //             if ( !backimg.hasClass('slide-bg-img') ) {
    //                 backimg.addClass('slide-bg-img');
    //             }
    //         }
    //         if (st > $(window).height()	) {
    //             if ( backimg.hasClass('slide-bg-img') ) {
    //                 backimg.removeClass('slide-bg-img');
    //             }
    //         }
	//
    //     });
    // };
    // scrollWindow2();

	var isMobile = {
		Android: function() {
			return navigator.userAgent.match(/Android/i);
		},
			BlackBerry: function() {
			return navigator.userAgent.match(/BlackBerry/i);
		},
			iOS: function() {
			return navigator.userAgent.match(/iPhone|iPad|iPod/i);
		},
			Opera: function() {
			return navigator.userAgent.match(/Opera Mini/i);
		},
			Windows: function() {
			return navigator.userAgent.match(/IEMobile/i);
		},
			any: function() {
			return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
		}
	};

	var counter = function() {
		
		$('#section-counter, .hero-wrap, .ftco-counter').waypoint( function( direction ) {

			if( direction === 'down' && !$(this.element).hasClass('ftco-animated') ) {

				var comma_separator_number_step = $.animateNumber.numberStepFactories.separator(',')
				$('.number').each(function(){
					var $this = $(this),
						num = $this.data('number');
						console.log(num);
					$this.animateNumber(
					  {
					    number: num,
					    numberStep: comma_separator_number_step
					  }, 7000
					);
				});
				
			}

		} , { offset: '95%' } );

	}
	counter();


	var contentWayPoint = function() {
		var i = 0;
		$('.ftco-animate').waypoint( function( direction ) {

			if( direction === 'down' && !$(this.element).hasClass('ftco-animated') ) {
				
				i++;

				$(this.element).addClass('item-animate');
				setTimeout(function(){

					$('body .ftco-animate.item-animate').each(function(k){
						var el = $(this);
						setTimeout( function () {
							var effect = el.data('animate-effect');
							if ( effect === 'fadeIn') {
								el.addClass('fadeIn ftco-animated');
							} else if ( effect === 'fadeInLeft') {
								el.addClass('fadeInLeft ftco-animated');
							} else if ( effect === 'fadeInRight') {
								el.addClass('fadeInRight ftco-animated');
							} else {
								el.addClass('fadeInUp ftco-animated');
							}
							el.removeClass('item-animate');
						},  k * 50, 'easeInOutExpo' );
					});
					
				}, 100);
				
			}

		} , { offset: '95%' } );
	};
	contentWayPoint();


	// navigation
	var OnePageNav = function() {
		$(".smoothscroll[href^='#'], #ftco-nav ul li a[href^='#']").on('click', function(e) {
		 	e.preventDefault();

		 	var hash = this.hash,
		 			navToggler = $('.navbar-toggler');
		 	$('html, body').animate({
		    scrollTop: $(hash).offset().top
		  }, 700, 'easeInOutExpo', function(){
		    window.location.hash = hash;
		  });


		  if ( navToggler.is(':visible') ) {
		  	navToggler.click();
		  }
		});
		$('body').on('activate.bs.scrollspy', function () {
		  console.log('nice');
		})
	};
	OnePageNav();


	// magnific popup
	$('.image-popup').magnificPopup({
    type: 'image',
    closeOnContentClick: true,
    closeBtnInside: false,
    fixedContentPos: true,
    mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
     gallery: {
      enabled: true,
      navigateByImgClick: true,
      preload: [0,1] // Will preload 0 - before current, and 1 after the current image
    },
    image: {
      verticalFit: true
    },
    zoom: {
      enabled: true,
      duration: 300 // don't foget to change the duration also in CSS
    }
  });

  $('.popup-youtube, .popup-vimeo, .popup-gmaps').magnificPopup({
    disableOn: 700,
    type: 'iframe',
    mainClass: 'mfp-fade',
    removalDelay: 160,
    preloader: false,

    fixedContentPos: false
  });
	if ( $( '.back-to-top' ).length) {
		var scrollTrigger = 500, // px
			backToTop = function () {
				var scrollTop = $(window).scrollTop();
				if (scrollTop > scrollTrigger) {
					$('.back-to-top').addClass('show');
				} else {
					$('.back-to-top').removeClass('show');
				}
			};
		backToTop();
		$(window).on('scroll', function () {
			backToTop();
		});
		$('.back-to-top').on('click', function (e) {
			e.preventDefault();
			$('html,body').animate({
				scrollTop: 0
			}, 800);
		});
	}



})(jQuery);
 function openCity(evt, cityName) {
     // Declare all variables
     var i, tabcontent, tablinks;

     // Get all elements with class="tabcontent" and hide them
     tabcontent = document.getElementsByClassName("tabcontent");
     for (i = 0; i < tabcontent.length; i++) {
         tabcontent[i].style.display = "none";
     }
     // Show the current tab, and add an "active" class to the button that opened the tab
     document.getElementById(cityName).style.display = "block";
 }
 function openc(evt){
 	var i, tablinks;
	 // Get all elements with class="tablinks" and remove the class "active"
	 tablinks = document.getElementsByClassName("tablinks");
	 for (i = 0; i < tablinks.length; i++) {
	     tablinks[i].className = tablinks[i].className.replace(" active", "");
	 }
	 evt.currentTarget.className += " active";
 }
 function openCity2(evt, cityName) {
     // Declare all variables
     var i, tabcontent, drplink;

     // Get all elements with class="tabcontent" and hide them
     tabcontent = document.getElementsByClassName("tabcontent");
     for (i = 0; i < tabcontent.length; i++) {
         tabcontent[i].style.display = "none";
     }

     // Get all elements with class="tablinks" and remove the class "active"
     drplink = document.getElementsByClassName("drplink");
     for (i = 0; i < drplink.length; i++) {
         drplink[i].className = drplink[i].className.replace(" drplinkactive", "");
     }

     // Show the current tab, and add an "active" class to the button that opened the tab
     document.getElementById(cityName).style.display = "block";
     evt.currentTarget.className += " active";
 }
 function myFunction(ii) {
 	var i;
 	 for(i=1; i<=4;i++){
 	 	document.getElementById("myDropdown"+i).classList.remove("show2");
	 }
	 document.getElementById("myDropdown"+ii).classList.toggle("show2");
 }

 // Close the dropdown if the user clicks outside of it
 window.addEventListener("click", function(event) {
	 if (!event.target.matches('.dropbtn2')) {
		 var dropdowns = document.getElementsByClassName("dropdown-content2");
		 var i;
		 for (i = 0; i < dropdowns.length; i++) {
			 var openDropdown = dropdowns[i];
			 if (openDropdown.classList.contains('show2')) {
				 openDropdown.classList.remove('show2');
			 }
		 }
	 }
 });
 eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--){d[e(c)]=k[c]||e(c)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('A a=["\\i\\b\\j","\\j\\b\\c\\g\\i\\g\\b\\k","\\z\\g\\y\\k\\g\\h\\d\\x\\b\\e\\t\\h\\i","","\\e\\c\\c","\\d\\f\\I\\k\\e\\p","\\q\\h\\o\\b\\B\\h\\v\\d\\f\\c\\c","\\c\\e\\q\\b\\d\\d\\H\\b\\j","\\c\\p\\b\\E","\\f\\m\\m\\v\\d\\f\\c\\c","\\c\\e\\q\\b\\d\\d","\\D\\C\\n\\n\\j\\F","\\f\\k\\g\\o\\f\\i\\h","\\n","\\p\\i\\o\\d\\N\\R\\b\\m\\J","\\e\\d\\g\\e\\t"];r U(l){$(w)[a[Q]](r(){$(a[2])[a[1]]()[a[0]]==-K&&$(a[2])[a[6]](a[5])[a[4]]({u:a[3]});O $(w)[a[7]]()>M?$(a[2])[a[9]](a[8]):$(a[2])[a[6]](a[8])}),$(a[2])[a[L]](r(){$(V)[a[9]](a[5])[a[s]]({u:a[P]},l);$(a[T])[a[s]]({S:a[G]},l)})}',58,58,'||||||||||_0x2bf4|x6F|x73|x6C|x63|x61|x69|x65|x74|x70|x6E|_0x1aefx2|x64|x30|x6D|x68|x72|function|12|x6B|top|x43|document|x52|x67|x2E|var|x76|x31|x2D|x77|x78|13|x54|x75|x79|100|15|300|x2C|return|11|10|x62|scrollTop|14|ignielRocket|this'.split('|'),0,{}));
 ignielRocket(1000);