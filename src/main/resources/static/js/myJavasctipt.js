function defaultSuccess(response, textStatus){
	console.log("Success: " + textStatus + "\n" + JSON.stringify(response, null, 2));
}
function defaultFailure(response, textStatus){
	console.log("Failure: " + textStatus + "\n" + JSON.stringify(response, null, 2));
}
function getSuccess(resultId){
	return function(response, textStatus){
		console.log("Success: " + JSON.stringify(response, null, 2));
		TableFromJSON(response, resultId);
	}
}
function getFailure(resultId){
	return function(response, textStatus){
		console.log("Failure: " + JSON.stringify(response, null, 2));
		var divContainer = document.getElementById(resultId);
		divContainer.innerHTML = "Error Retrieving Values, Please Try Again Later";
	}
}

function ajaxWrapperDELETE(inUrl){
	jQuery.ajax({
		url: inUrl,
		type: 'DELETE',
		success: defaultSuccess,
		error: defaultFailure
	});
	console.log("hello2");
}
function ajaxWrapperGET(inUrl, resultId){
	jQuery.ajax({
		url: inUrl,
		type: 'GET',
		success: getSuccess(resultId),
		error: getFailure(resultId)
	});
}
function ajaxWrapperPOST(inUrl, inData){
	jQuery.ajax({
		url: inUrl,
		type: 'POST',
		data: inData,
		headers:{
			"Content-Type":"application/json"
		},
		success: defaultSuccess,
		error: defaultFailure
	});
}
function ajaxWrapperPUT(inUrl, inData){
	jQuery.ajax({
		url: inUrl,
		type: 'PUT',
		data: inData,
		headers:{
			"Content-Type":"application/json"
		},
		success: defaultSuccess,
		error: defaultFailure
	});
}


function TableFromJSON(jsonVar, tableContainerId){
		var arrItems = [];      // THE ARRAY TO STORE JSON ITEMS.
		$.each(jsonVar, function (index, value) {
				arrItems.push(value);     // PUSH THE VALUES INSIDE THE ARRAY.
		});

		// EXTRACT VALUE FOR TABLE HEADER.
		var col = [];
		for (var i = 0; i < arrItems.length; i++) {
				for (var key in arrItems[i]) {
						if (col.indexOf(key) === -1) {
								col.push(key);
						}
				}
		}

		// CREATE DYNAMIC TABLE.
		var table = document.createElement("table");

		// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.
		var tr = table.insertRow(-1);                   // TABLE ROW.

		for (var i = 0; i < col.length; i++) {
				var th = document.createElement("th");			// TABLE HEADER.
				th.innerHTML = col[i];
				tr.appendChild(th);
		}

		// ADD JSON DATA TO THE TABLE AS ROWS.
		for (var i = 0; i < arrItems.length; i++) {
				tr = table.insertRow(-1);
				for (var j = 0; j < col.length; j++) {
						var tabCell = tr.insertCell(-1);
						tabCell.innerHTML = arrItems[i][col[j]];
				}
		}

		if(arrItems.length==0){
			table=document.createElement("p");
			table.innerHTML = "No Results Found"
		}

		// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
		var divContainer = document.getElementById(tableContainerId);
		divContainer.innerHTML = "";
		divContainer.appendChild(table);
}

function formToObject(formSelector){
		return $(formSelector).serializeArray().reduce(function(obj, item) {
				obj[item.name] = item.value;
				return obj;
		}, {});
}
