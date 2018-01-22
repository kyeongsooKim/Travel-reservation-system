//onClick functions

function removeEmployee(){
	var url = '/home/employee/delete/' + $("#removeEmployeeSSN").val();
	ajaxWrapperDELETE(url);
}

function addEmployee(){
	var toSend = {};
	toSend["firstName"] = $("#addEmployeePersonFirstName").val();
	toSend["lastName"] = $("#addEmployeePersonLastName").val();
	toSend["address"] = $("#addEmployeePersonAddress").val();
	toSend["city"] = $("#addEmployeePersonCity").val();
	toSend["state"] = $("#addEmployeePersonState").val();
	toSend["zipCode"] = $("#addEmployeePersonZipCode").val();
	toSend["ssn"] = $("#addEmployeeSSN").val();
	toSend["isManager"] = $("input[name='addEmployeeIsManager']:checked").val()=="yes";
	toSend["startDate"] = $("#addEmployeeStartDate").val();
	toSend["hourlyRate"] = $("#addEmployeeHourlyRate").val();
	ajaxWrapperPOST('/home/employee/insert', JSON.stringify(toSend));
}

function changeEmployee(){
	var toSend = {};
	toSend["ssn"] = $("#changeEmployeeSSN").val();
	toSend["isManager"] = $("input[name='changeEmployeeIsManager']:checked").val()=="yes";
	toSend["startDate"] = $("#changeEmployeeStartDate").val();
	toSend["hourlyRate"] = $("#changeEmployeeHourlyRate").val();
	ajaxWrapperPUT('/home/employee/update', JSON.stringify(toSend));
}

function getAllEmployees(){
	ajaxWrapperGET('/home/employee/allEmployees', "getAllEmployeesResult");
}

function getFlightListing(){
	ajaxWrapperGET('/home/employee/allFlights', "getFlightListingResult");
}

function viewEmployee(){
	ajaxWrapperGET('/home/employee/get/'+$("#viewEmployeeSSN").val(), "viewEmployeeResult");
}

function getReservationsByCustomerName(){
	var url = '/home/employee/reservationsByCustomerName/' + $("#getReservationsByCustomerNameFirst").val() + '/' + $("#getReservationsByCustomerNameLast").val();
	ajaxWrapperGET(url, "getReservationsByCustomerNameResult");
}

function getReservationsByFlightNumber(){
	var url = '/home/employee/reservationsByFlightNumber/' + $("#getReservationsByFlightNumberAirline").val() + '/' + $("#getReservationsByFlightNumberNumber").val();
	ajaxWrapperGET(url, "getReservationsByFlightResult");
}

function getSalesReport(){
	var url = '/home/employee/salesReport/' + $("#getSalesReportMonth").val();
	ajaxWrapperGET(url, "getSalesReportResult");
}

function getRevenueByFlight(){
	var url = '/home/employee/revenueByFlight/' + $("#getRevenueByFlightAirline").val() + '/' + $("#getRevenueByFlightNumber").val();
	ajaxWrapperGET(url, "getRevenueByFlightResult");
}

function getRevenueByDestinationCity(){
	var url = '/home/employee/revenueByDestinationCity/' + $("#getRevenueByDestinationCityCity").val();
	ajaxWrapperGET(url, "getRevenueByDestinationCityResult");
}

function getRevenueByCustomer(){
	var url = '/home/employee/revenueByCustomer/' + $("#getRevenueByCustomerAccountId").val();
	ajaxWrapperGET(url, "getRevenueByCustomerResult");
}

function getCustomerOfMaxRevenue(){
	var url = '/home/employee/customerOfMaxRevenue/';
	ajaxWrapperGET(url, "getCustomerOfMaxRevenueResult");
}

function getMostActiveFlights(){
	var url = '/home/employee/mostActiveFlights/';
	ajaxWrapperGET(url, "getMostActiveFlightsResult");
}

function getCustomersOnFlight(){
	var url = '/home/employee/customersOnFlight/' + $("#getCustomersOnFlightAirline").val() + '/' + $("#getCustomersOnFlightNumber").val() + '/' + $("#getCustomersOnFlightLeg").val();
	ajaxWrapperGET(url, "getCustomersOnFlightResult");
}
