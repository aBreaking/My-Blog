/*
	Halcyonic by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
*/

(function($) {

	var $window = $(window),
		$body = $('body');

	// Breakpoints.
		breakpoints({
			xlarge:  [ '1281px',  '1680px' ],
			large:   [ '981px',   '1280px' ],
			medium:  [ '737px',   '980px'  ],
			small:   [ null,      '736px'  ]
		});

	// Nav.

		// Title Bar.
			$(
				'<div id="titleBar">' +
					'<a href="#navPanel" class="toggle"></a>' +
					'<a href="'+$("#nav .active").attr("href")+'" class="title">'+$("#nav .active").text()+'</a>' +
				'</div>'
			)
				.appendTo($body);

		// Panel.
			$(
				'<div id="navPanel">' +
					'<div id="abreaking">'+
                	$('#abreaking').html()+
					'</div>'+
					'<nav>' +
						$('#nav').navList() +
					'</nav>' +
				'</div>'
			)
				.appendTo($body)
				.panel({
					delay: 500,
					hideOnClick: true,
					hideOnSwipe: true,
					resetScroll: true,
					resetForms: true,
					side: 'left',
					target: $body,
					visibleClass: 'navPanel-visible'
				});

})(jQuery);

/**
 * 滚动到顶部
 * @param speed 速度，1-10
 */
function scroll2Top(speed){
    var d = speed?speed:5;
    var $document = $(document);
    var intervalId = setInterval(function() {
        if ($(document).scrollTop() <= 0){
            clearInterval(intervalId);
        }
        $document.scrollTop($document.scrollTop() + (-20 * d));
    }, 15);
}