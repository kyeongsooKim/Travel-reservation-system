jQuery(document).ready(function($) {
	'use strict';
	$("#accordianRestOfThePage").hide();
});

function loggedIn(){
	$("#accordianRestOfThePage").show();
/*
	var inUrl = '/home/customer/login/'+$("#accountNo").val()+'/'+$("#password").val();
	resultId = "loginResult";
	jQuery.ajax({
		url: inUrl,
		type: 'GET',
		success: function(response, textStatus){
			console.log("Success: " + JSON.stringify(response, null, 2));
			var arrItems = [];      // THE ARRAY TO STORE JSON ITEMS.
			$.each(response, function (index, value) {
					arrItems.push(value);     // PUSH THE VALUES INSIDE THE ARRAY.
			});
			if(arrItems.length>0){
				divContainer.innerHTML = "Logged in as account number\"" + $("#accountNo").val() + "\"";
				$("#accordianRestOfThePage").show();
			}else{
				divContainer.innerHTML = "Incorecct account number or password, try again";
			}
		},
		error: function(response, textStatus){
			console.log("Failure: " + JSON.stringify(response, null, 2));
			var divContainer = document.getElementById(resultId);
			divContainer.innerHTML = "Error Retrieving Values, Please Try Again Later";
		}
	});
}*/

function getCurrentReservations(){
	ajaxWrapperGET('/home/customer/getCurrentReservations/'+$("#accountNo").val(), "getCurrentReservationsResult");
}

function getAllReservations(){
	ajaxWrapperGET('/home/customer/getAllReservations/'+$("#accountNo").val(), "getAllReservationsResult");
}

function getRecommendedFlights(){
	var url = '/home/rep/recommendedFlights/' + $("#accountNo").val();
	ajaxWrapperGET(url, "getRecommendedFlightsResult");
}
