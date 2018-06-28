var employeeServiceLink = "http://localhost:8088/employees";

function addDynamic() {
	$(function() {
		//mode buttons
		$('#displayMode').click(function(){turnOnDisplayMode()});
		$('#addMode').click(function(){turnOnAddMode()});
		$('#deleteMode').click(function(){turnOnDeleteMode()});
		
		//display mode
		$('#expandAll').click(function(){$('#employeeTree').treeview('expandAll', { silent: true });});
		$('#collapseAll').click(function(){$('#employeeTree').treeview('collapseAll', { silent: true });});
		
		//add mode
		$('#addModeContols #searchButton').click(function(){handleSearchAddMode()});
		$('#addModeContols #addButton').click(function(){handleAddEmployee()});
		
		//delete mode
		$('#deleteModeControls #searchButton').click(function(){handleSearchDeleteMode()});
		$('#deleteModeControls #deleteButton').click(function(){handleDeleteEmployee()});	
	});
}


function getDataToAddEmployee() {
	var selected = $('#employeeTree').treeview('getSelected');
	if (selected.length == 0) {
		return;
	}
	var firstName = $('#fisrtName').val();
	var secondName = $('#secondName').val();
	return "managerId=" + selected[0].employeeId + "&firstName=" + firstName + "&secondName=" + secondName;
}

function getDataToDeleteEmployee() {
	var selected = $('#employeeTree').treeview('getSelected');
	if (selected.length == 0) {
		return;
	}
	
	return "employeeId=" + selected[0].employeeId;
}

// add employee
function handleAddEmployee() {
	if (isValidAddEmployee()) {
		madeAddEmployeeRequest();
	}
	
}

function handleDeleteEmployee() {
	if (isValidDeleteEmployee()) {	
		madeDeleteEmployeeRequest();
	}
	
}

function isValidAddEmployee() {
	var errorText = ""; 
	var selected = $('#employeeTree').treeview('getSelected');
	if (selected.length == 0) {
		errorText = "Please select employee";
		
	}
	var firstName = $('#fisrtName').val();
	if (firstName.length < 3 || firstName.length > 30) {
		if (errorText == "") {
			errorText = "The length of first name has to be more than 3 and less then 30."
		} else {
			errorText =  errorText + "<br>The length of first name has to be more than 3 and less then 30"
		}
	}		
		
	var secondName = $('#secondName').val();
	if (secondName.length < 3 || secondName.length > 30) {
		if (errorText == "") {
			errorText = "The length of second name has to be more than 3 and less then 30."
		} else {
			errorText =  errorText + "<br>The length of second name has to be more than 3 and less then 30"
		}
	}
	
	if (errorText == "") {
		return true;
	} else {
		$('#errorMessage').html(errorText).show();
		return false;
	}
	
}

function isValidDeleteEmployee() {	
	var selected = $('#employeeTree').treeview('getSelected');
	if (selected.length == 0) {
		$('#errorMessage').html("Please select employee.").show();
		return false;
	}
	return true;
}

function madeAddEmployeeRequest() {
	clearSuccessErrorMessages();
	$.ajax({
		  method: "POST",
		  url: employeeServiceLink,
		  data: getDataToAddEmployee()
		  }) .done(function( msg ) {
			  initTree();
			  displaySuccessMessage();
		  }) .fail(function(msg) {
			  displayErrorMessage(msg);
		  });
}

// delete employee
function madeDeleteEmployeeRequest() {
	clearSuccessErrorMessages();
	$.ajax({
		  method: "DELETE",
		  url: employeeServiceLink + "?" + getDataToDeleteEmployee()
		  }) .done(function( msg ) {
			  initTree();
			  displaySuccessMessage();
		  }) .fail(function(msg) {
			  displayErrorMessage(msg);	
		  });
}


///////////////////// draw
function initTree() {	
	$.ajax({
		method: "GET",
		url: employeeServiceLink
		}) .done(function( msg ) {
			drawTree(msg);
		})
}

function drawTree(data) {
	var treeData = [];
	treeData[0] = data.data;
	$('#employeeTree').treeview({
		data: treeData,
		levels: 1,
		color: "#428bca",});
}


// dynamic
function turnOnDisplayMode() {
	hideAllModes();
	cleanInputSearchFields();
	$('#displayModeControls').show();
}

function turnOnAddMode() {
	hideAllModes();
	cleanInputSearchFields();
	$('#addModeContols').show();
}

function turnOnDeleteMode() {
	hideAllModes();
	cleanInputSearchFields();
	$('#deleteModeControls').show();
}

function cleanInputSearchFields() {
	$("#addModeContols #searchValue").val("");
	$("#addModeContols #fisrtName, #addModeContols #secondName").val("");
	$("#deleteModeControls #searchValue").val("");
}

function hideAllModes() {
	//$('#displayModeControls').hide();
	$('#addModeContols').hide();
	$('#deleteModeControls').hide();
}

function clearSearch() {
	$('#employeeTree').treeview('clearSearch');
}

function displaySuccessMessage() {
	$('#successMessage').html("Operation has been successfuly finished").show();
}

function displayErrorMessage(response) {
	if (response.responseJSON.meta.errors && response.responseJSON.meta.errors.length != 0) {
		$('#errorMessage').html(response.responseJSON.meta.errors[0].errorDescription).show();
	} 
}

function clearSuccessErrorMessages() {
	$('#successMessage, #errorMessage').hide().html("");
	
}

function handleSearchAddMode() {
	var searchQuery = $('#addModeContols #searchValue').val();
	$('#employeeTree').treeview('search', [ searchQuery, {
		  ignoreCase: true,     // case insensitive
		  exactMatch: false,    // like or equals
		  revealResults: true,  // reveal matching nodes
		}]);
}

function handleSearchDeleteMode() {
	var searchQuery = $('#deleteModeControls #searchValue').val();
	$('#employeeTree').treeview('search', [ searchQuery, {
		  ignoreCase: true,     // case insensitive
		  exactMatch: false,    // like or equals
		  revealResults: true,  // reveal matching nodes
		}]);
}
			