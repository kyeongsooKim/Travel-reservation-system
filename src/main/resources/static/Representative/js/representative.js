function makeRoundTripReservation(){
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
	ajaxWrapperPOST('/home/rep/roundTrip', JSON.stringify([toSend1, toSend2]));
}

function makeOneWayTripReservation(){
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

function addCustomer(){
	var toSend = {};
	toSend["firstName"] = $("#addCustomerPersonFirstName").val();
	toSend["lastName"] = $("#addCustomerPersonLastName").val();
	toSend["address"] = $("#addCustomerPersonAddress").val();
	toSend["city"] = $("#addCustomerPersonCity").val();
	toSend["state"] = $("#addCustomerPersonState").val();
	toSend["zipCode"] = $("#addCustomerPersonZipCode").val();
	toSend["phoneNo"] = $("#addCustomerPhoneNo").val();
	toSend["creditCardNo"] = $("#addCustomerCreditCard").val();
	toSend["creationDate"] = $("#addCustomerDate").val();
	toSend["email"] = $("#addCustomerEmail").val();
	ajaxWrapperPOST('/home/rep/addCustomer', JSON.stringify(toSend));
}

function changeCustomer(){
	var toSend = {};
	//toSend["accountNo"] = $("#changeCustomerAccountNo").val();
	toSend["phoneNo"] = $("#changeCustomerPhoneNo").val();
	toSend["creditCardNo"] = $("#changeCustomerCreditCard").val();
	toSend["creationDate"] = $("#changeCustomerDate").val();
	toSend["email"] = $("#changeCustomerEmail").val();
	var url = '/home/rep/updateCustomer/' + $("#changeCustomerAccountNo").val()
	ajaxWrapperPUT(url, JSON.stringify(toSend));
}

function deleteCustomer(){
	var url = '/home/rep/deleteCustomer/' + $("#deleteCustomerAccountNo").val();
	ajaxWrapperDELETE(url);
}

function getRecommendedFlightsByCustomer(){
	var url = '/home/rep/recommendedFlights/' + $("#getRecommendedFlightsByCustomerAccountNo").val();
	ajaxWrapperGET(url, "getRecommendedFlightsByCustomerResult");
}

function getMailingList(){
	var url = '/home/rep/getMailingList';
	ajaxWrapperGET(url, "getMailingListResult");
}
