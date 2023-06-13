$(document).ready(function() {
	$("#customerForm").validate({
		rules: {
			confirmpassword: {
				equalTo: "#password"
			}
		}
	});

});