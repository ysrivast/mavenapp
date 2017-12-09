
function validateForm(){
	if(document.forms.userName.value==""){
		alert("Username should not be blacnk")
		document.forms.userName.focus();
		return false;
	}
	
	if(document.forms.password.value==""){
		alert("Password should not be blacnk")
		document.forms.Password.focus();
		return false;
	}
}