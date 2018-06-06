function doAjax() {
		$.ajax({
			type : "GET",
			url : 'rest/film',
			data : ({}),
			contentType : 'application/json',
			dataType : 'json',
			success : function(data) {
				$("body").empty();
				createForm();
				$("body").append($("<table></table>"));


				$.each(data, function(index, element) {
					$("table").append($("<tr></tr>"));
					$("table").append($("<tr></tr>"));
					$.each(element, function(key, element1) {
						if(index === 0 && key != "image"){
							$("tr:first-child").append($("<th>"+key+"</th>"));
						}
						if (key != "image") {
							if(key != "description"){
								$("tr:last-child").append(
										$("<td></td>").text(element1));
							}else{
								$("tr:last-child").append(
										$("<td></td>").text(element1.toString().substr(0, 55)+".."));
							}
							
						}

					});
					var deleteButton = $('<input type="button" onclick="deleteFilm('
							+ element.id + ')" value="Delete"/>');
					var editButton = $('<input type="button" onclick="editById('
							+ element.id + ')" value="Edit"/>');
					$("tr:last-child").append(editButton);
					$("tr:last-child").append(deleteButton);
				});
				$("tr:first-child").append($("<th>#</th>"));
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
	
	function editById(id) {
		$.ajax({
			type : "GET",
			url : 'rest/film/' + id,
			data : ({}),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				$("#id").val(data.id);
				$("#name").val(data.name);
				$("#year").val(data.year);
				$("#genre").val(data.genre);
				$("#director").val(data.director);
				$("#country").val(data.country);
				$("#description").val(data.description);
				$("#movie_length").val(data.movie_length);
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

	function processForm() {
		$("#id").removeAttr("disabled");
		var formData = jQFormSerializeArrToJson($("#myForm").serializeArray());
		if(formData.id>0){
			formUpdate(formData, formData.id);
		}else{
			formSubmit(formData);
		}
	}
	
	function formSubmit(formData) {
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
	
	function formUpdate(formData, id) {
		$.ajax({
			type : "PUT",
			url : 'rest/film/' + id,
			contentType : 'application/json',
  			data : JSON.stringify(formData),
			success : function(data) {
				doAjax();
			}
		});
	}
	
	function clearForm() {
		$("#id").val("");
		$("#name").val("");
		$("#year").val("");
		$("#genre").val("");
		$("#director").val("");
		$("#country").val("");
		$("#description").val("");
		$("#movie_length").val("");
	}
	
	function createForm() {
		$("body").append($('<h2 style="text-align: center;">Here you can input and edit film:</h2>'));
		$("body").append($('<ul class="form-style-1">'));
		$("ul").append($('<form name="myForm" id="myForm" method="POST"></form>'));

		$("form").append('<label for="id" >Id:</label>');
		$("form").append('<input name="id" id="id" style="width:35px;" disabled></input><br>');
		$("form").append('<li></li>');
		$("li:last-child").append('<label for="name">Name:</label>');
		$("li:last-child").append('<input type="text" name="name" id="name" class="field-divided">');
		$("li:last-child").append('<label for="year">Year:</label>');
		$("li:last-child").append('<input type="text" name="year" id="year" class="field-divided">');
		$("form").append('<label for="genre">Genre:</label>');
		$("form").append('<input type="text" name="genre" id="genre" class="field-divided">');
		$("form").append('<label for="director">Director:</label>');
		$("form").append('<input type="text" name="director" id="director" class="field-divided">');
		$("form").append('<label for="country">Country:</label>');
		$("form").append('<input type="text" name="country" id="country" class="field-divided">');
		$("form").append('<label for="movie_length">Movie_length:</label>');
		$("form").append('<input type="text" name="movie_length" id="movie_length" value="00:00:00" class="field-divided">');
		$("form").append('<label for="description">Description:</label>');
		$("form").append('<textarea name="description" id="description" rows="6" cols="45" class="field-divided"></textarea> ');
/* 		$("form").append('<label for="image">Image:</label>');
		$("form").append('<input type="file" name="image" id="image"><br>'); */
		$("form").append('<div class="button-field"></div>');
		$("div").append('<input type="button" onclick="clearForm();" value="Clear">');
		$("div").append('<input type="button" onclick="processForm();" value="Input">');
	}