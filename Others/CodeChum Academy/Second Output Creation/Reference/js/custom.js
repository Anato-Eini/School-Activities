$(function() {
	$('#wrapper').fullpage({
		paddingTop: '70px',
		paddingBottom: '70px',
		bigSectionsDestination: 'top',
		responsiveHeight: '414',
		verticalCentered: false,
		onLeave: function(origin, destination, direction){
			if (destination != 1) {
				$('.social-media-icons').hide();
				$('.site-footer').show();
				$('.site-footer button').removeClass('secondary').addClass('primary');

				if (destination == 7) {
					$('.social-media-icons').show();
					$('.site-footer').hide();
				}
			} else {
				$('.social-media-icons').hide();
				$('.site-footer').show();
				$('.site-footer button').removeClass('primary').addClass('secondary');
			}
		},
	});

	$('#join-now').on('click', function() {
    $('#wrapper').fullpage.moveTo(7);
	});

	$('#arrow-down').on('click', function() {
    $.fn.fullpage.moveSectionDown();
	});
});