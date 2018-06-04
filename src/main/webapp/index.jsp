<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/style.css">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script>
	function doAjax() {
		$.ajax({
			type : "GET",
			url : 'rest/film',
			data : ({}),
			dataType : 'json',
			success : function(data) {
				$("body").empty();
				createForm();
				$("body").append($("<table></table>"));

				$.each(data, function(index, element) {
					$("table").append($("<tr></tr>"));
					$.each(element, function(key, element1) {
						if (key != "description") {
							$("tr:last-child").append(
									$("<td></td>").text(element1));
						}
					});
					var r = $('<input type="button" onclick="deleteFilm('
							+ element.id + ')" value="Delete"/>');
					$("tr:last-child").append(r);
				});
			}
		});
	}
	function deleteFilm(id) {
		$.ajax({
			type : "DELETE",
			url : 'rest/film/' + id,
			data : ({}),
			dataType : 'json',
			success : function(data) {
				doAjax();
			}
		});
	}
	
	function jQFormSerializeArrToJson(formSerializeArr){
		 var jsonObj = {};
		 jQuery.map( formSerializeArr, function( n, i ) {
		     jsonObj[n.name] = n.value;
		 });
		 return jsonObj;
	}

	function formSubmit() {
		var formData = jQFormSerializeArrToJson($("#myForm").serializeArray());
		$.ajax({
			type : "POST",
			url : 'rest/film',
			contentType : 'application/json',
  			data : JSON.stringify(formData),
			success : function(data) {
				doAjax();
			}
		});
	}

	function createForm() {
		$("body").append($('<form name="myForm" id="myForm" method="POST"></form>'));
		$("form").append('<label for="name">Name:</label>');
		$("form").append('<input type="text" name="name" id="name"><br>');
		$("form").append('<label for="year">Year:</label>');
		$("form").append('<input type="text" name="year" id="year"><br>');
		$("form").append('<label for="genre">Genre:</label>');
		$("form").append('<input type="text" name="genre" id="genre"><br>');
		$("form").append('<label for="director">Director:</label>');
		$("form").append('<input type="text" name="director" id="director"><br>');
		$("form").append('<label for="country">Country:</label>');
		$("form").append('<input type="text" name="country" id="country"><br>');
		$("form").append('<label for="description">Description:</label>');
		$("form").append('<input type="text" name="description" id="description"><br>');
		$("form").append('<label for="movie_length">Movie_length:</label>');
		$("form").append('<input type="text" name="movie_length" id="movie_length"><br>');
		$("form").append('<label for="image">Image:</label>');
		$("form").append('<input type="file" name="image" id="image"><br>');
		$("form").append('<input type="button" onclick="formSubmit();" value="Ok">');
	}
</script>
</head>
<body id="body" onload="doAjax()">

</body>
</html>