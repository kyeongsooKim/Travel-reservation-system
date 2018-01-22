jQuery(document).ready(function($) {

	'use strict';

	$("#makeTwoWayReservation").hide();
	$("#makeOneWayReservation").hide();
      $('#form-submit .date').datepicker({
      });

      var owl = $("#owl-suiteroom");

        owl.owlCarousel({

          pagination : true,
          paginationNumbers: false,
          autoPlay: 6000, //Set AutoPlay to 3 seconds
          items : 1, //10 items above 1000px browser width
          itemsDesktop : [1000,1], //5 items between 1000px and 901px
          itemsDesktopSmall : [900,1], // betweem 900px and 601px
          itemsTablet: [600,1], //2 items between 600 and 0
          itemsMobile : false // itemsMobile disabled - inherit from itemsTablet option

      });


      var owl = $("#owl-mostvisited");

        owl.owlCarousel({

          pagination : true,
          paginationNumbers: false,
          autoPlay: 6000, //Set AutoPlay to 3 seconds
          items : 4, //10 items above 1000px browser width
          itemsDesktop : [1000,4], //5 items between 1000px and 901px
          itemsDesktopSmall : [900,2], // betweem 900px and 601px
          itemsTablet: [600,1], //2 items between 600 and 0
          itemsMobile : false // itemsMobile disabled - inherit from itemsTablet option

      });


      var owl = $("#owl-available");

        owl.owlCarousel({

          pagination : true,
          paginationNumbers: false,
          autoPlay: 6000, //Set AutoPlay to 3 seconds
          items : 4, //10 items above 1000px browser width
          itemsDesktop : [1000,4], //5 items between 1000px and 901px
          itemsDesktopSmall : [900,2], // betweem 900px and 601px
          itemsTablet: [600,1], //2 items between 600 and 0
          itemsMobile : false // itemsMobile disabled - inherit from itemsTablet option

      });




        $('.recommendedgroup > div').hide();
        $('.recommendedgroup > div:first-of-type').show();
        $('.tabs a').click(function(e){
          e.preventDefault();
            var $this = $(this),
            tabgroup = '#'+$this.parents('.tabs').data('recommendedgroup'),
            others = $this.closest('li').siblings().children('a'),
            target = $this.attr('href');
        others.removeClass('active');
        $this.addClass('active');
        $(tabgroup).children('div').hide();
        $(target).show();

        })


        $('.weathergroup > div').hide();
        $('.weathergroup > div:first-of-type').show();
        $('.tabs a').click(function(e){
          e.preventDefault();
            var $this = $(this),
            tabgroup = '#'+$this.parents('.tabs').data('weathergroup'),
            others = $this.closest('li').siblings().children('a'),
            target = $this.attr('href');
        others.removeClass('active');
        $this.addClass('active');
        $(tabgroup).children('div').hide();
        $(target).show();

        })


        $('.tabgroup > div').hide();
        $('.tabgroup > div:first-of-type').show();
        $('.tabs a').click(function(e){
          e.preventDefault();
            var $this = $(this),
            tabgroup = '#'+$this.parents('.tabs').data('tabgroup'),
            others = $this.closest('li').siblings().children('a'),
            target = $this.attr('href');
        others.removeClass('active');
        $this.addClass('active');
        $(tabgroup).children('div').hide();
        $(target).show();

        })



        $(".pop-button").click(function () {
            $(".pop").fadeIn(300);

        });

        $(".pop > span").click(function () {
            $(".pop").fadeOut(300);
        });


        $(window).on("scroll", function() {
            if($(window).scrollTop() > 100) {
                $(".header").addClass("active");
            } else {
                //remove the background property so it comes transparent again (defined in your css)
               $(".header").removeClass("active");
            }
        });


	/************** Mixitup (Filter Projects) *********************/
    	$('.projects-holder').mixitup({
            effects: ['fade','grayscale'],
            easing: 'snap',
            transitionSpeed: 400
        });



});

function orderTicketsNowClick(){
	$("#makeTwoWayReservation").hide();
	$("#makeOneWayReservation").hide();
	var url = '/home/customer/search/'
	url += $("#from").val() + '/';
	url += $("#to").val() + '/';
	url += $("#departure").val() + '/';
	url += $("#return").val();
	ajaxWrapperGET(url, "bookFlightResults");
	if($("input[name='trip']:checked").val()=="round"){
		$("#makeTwoWayReservation").show();
	}else{
		$("#makeOneWayReservation").show();
	}
}

function makeRoundTripReservationCustomer(){
	var toSend1 = {};
	toSend1["accountNo"]    = $("#makeRoundTripReservationAccountNumber").val();
	toSend1["airlineId"]      = $("#makeRoundTripReservationFromAirline").val();
	toSend1["flightNo"] = $("#makeRoundTripReservationFromNumber").val();
	toSend1["legNo"]    = $("#makeRoundTripReservationFromLeg").val();
	toSend1["seatClass"]            = $("#makeRoundTripReservationFromClass").val();
	toSend1["seatNo"]       = $("#makeRoundTripReservationFromSeatNumber").val();
	toSend1["meal"]             = $("#makeRoundTripReservationMeal").val();
	var toSend2 = {};
	toSend2["accountNo"]    = $("#makeRoundTripReservationAccountNumber").val();
	toSend2["airlineId"]        = $("#makeRoundTripReservationToAirline").val();
	toSend2["flightNo"]   = $("#makeRoundTripReservationToNumber").val();
	toSend2["legNo"]      = $("#makeRoundTripReservationToLeg").val();
	toSend2["seatClass"]            = $("#makeRoundTripReservationFromClass").val();
	toSend2["seatNo"]       = $("#makeRoundTripReservationFromSeatNumber").val();
	toSend2["meal"]             = $("#makeRoundTripReservationMeal").val();
	ajaxWrapperPOST('/home/rep/roundTrip', JSON.stringify(toSend1));
	ajaxWrapperPOST('/home/rep/roundTrip', JSON.stringify(toSend2));
}

function makeOneWayTripReservationCustomer(){
	var toSend = {};
	toSend["accountNo"]    = $("#makeOneWayTripReservationAccountNumber").val();
	toSend["airlineId"]      = $("#makeOneWayTripReservationAirline").val();
	toSend["flightNo"] = $("#makeOneWayTripReservationNumber").val();
	toSend["legNo"]    = $("#makeOneWayTripReservationLeg").val();
	toSend["seatClass"]            = $("#makeOneWayTripReservationClass").val();
	toSend["seatNo"]       = $("#makeOneWayTripReservationSeatNumber").val();
	toSend["meal"]             = $("#makeOneWayTripReservationMeal").val();
	ajaxWrapperPOST('/home/rep/oneWay', JSON.stringify(toSend));
}
