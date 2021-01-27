
//select 호출
function ui_select(){
	$('.select').select2({
		minimumResultsForSearch : Infinity,
		dropdownAutoWidth : true,
	});
    $('.select.select-td').on('select2:open', function (e) {
        $('ul.select2-results__options').addClass('td');
    });
}

function tblW(){
	$(".slimscroll").on('scroll', function(){
		var left = $(this).scrollLeft();
		$(this).closest('.table-wrap').find('.thead-wrap .table').css("left", 0 - $(this).scrollLeft());
	});
	$('.col-fix .thead-wrap > .table').each(function(){
		var w = $('.row').width();
		var tw_l = $(this).closest('.col-fix').find('.col-fix-l').width();
		$(this).closest('.col-fix').find('.col-fix-r').css('width', w - tw_l);
		$(this).closest('.col-fix').find('.col-fix-r .table-wrap').css('width', w - tw_l);
	});
}

(function($){
	$(document).ready(function(){
		$(document).on('click', '.selectable > tr', function(){
			$(this).toggleClass('ui-selected');
		});

		// upload
		var fileTarget = $('.filebox .upload-hidden');
		fileTarget.on('change', function() {
			if (window.FileReader) {
				var filename = $(this)[0].files[0].name;
			} else {
				var filename = $(this).val().split('/').pop().split('\\').pop();
			}
			$(this).siblings('.upload-name').val(filename);
		});

		$('[data-toggle="tooltip"]').tooltip();

		if($('.select').length > 0){
            ui_select();
		}

		if($('.slimscroll').length > 0){
			$('.slimscroll').slimscroll({
				height: 'auto'
			});
			tblW();
		}

		$(".modal").on("shown.bs.modal", function () {
			$('.slimscroll').slimscroll({
				height: 280,
				alwaysVisible: false
			});
			tblW();
		});


		/* navbar */
        var ctrlMenu = function() {
            if(true) {
				$('.depthbar').css('height', maxheight).stop(true, true).slideDown(400);
                $('.depth2').css('height', maxheight).stop(true, true).slideDown(400);
			}
        };
        var delay = 200,
					setTimeoutConst;
		var heights = $(".depth2").map(function(){
			return $(this).innerHeight();
			}).get(),
			maxheight = Math.max.apply(null, heights);

        $('.nav-link').on('mouseenter', function () {
            setTimeoutConst = setTimeout(function() {
				ctrlMenu(true);
			}, delay);

        });
        $('.header').on('mouseleave', function(){
            clearTimeout(setTimeoutConst);
			$('.depth2').stop(true, true).slideUp();
			$('.depthbar').css('height', '');
		});

		$('.navbar .btn-more').on('click', function(){
			$(this).toggleClass('btn-close');
			if ($('.navbar-right').is(':visible')) {
				$('.navbar-right').css('display', 'none');
			} else{
				$('.navbar-right').css('display', 'flex');
			}
			$width = $('.users > span').outerWidth();
		});
	});
})(jQuery);
