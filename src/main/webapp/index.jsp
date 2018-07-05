<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/style.css">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="${context}/resources/js/main.js"></script>
<script>

	function processAuth() {
		var data = $(this).serializeObject();
		$.ajax({
		    'type': $(this).action,
		    'url': $(this).target,
		    'data': data
		})
	}

	function auth(){
		$("body").append($('<form name="auth" id="auth" action="j_spring_security_check" method="POST"></form>'));
		$("form").append('<label for="login">Login:</label>');
		$("form").append('<input type="text" name="j_username" id="login">');
		$("form").append('<label for="password">Password:</label>');
		$("form").append('<input type="text" name="j_password" id="password">');
		$("form").append('<input type="button" onclick="doAjax()" value="Login">');
	}
	
	
</script>

</head>
<body id="body" onload="auth()">
</body>
</html>