$(document).ready(function(){
	$("#uploadForm").on("submit",(function (e){
		e.preventDefault();
		var formData = new FormData();
		formData.append('file',$("#fileInput")[0].files[0]);
		
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8080/gallery',
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			crossDomain: true,
			success: function(data){
				console.log("success");
			},
			error:function (data){
				console.log("error" + data);
			}
		});
	}));
	
});

var date = new Date();
function startSearch() {
	var tmpDate = new Date();
	var result = (tmpDate.getTime() - date.getTime()) > 500 ? true : false;
	date = new Date();
	return result;
}
var timer;
function search(elem) {
	clearTimeout(timer);
	timer = setTimeout(function() {

		// if (startSearch()) {
		if (elem.value == '') {
			var names = $('#hiddenImages > .imageCol');

			names.each(function() {
				$('#imageRow').append($(this));
				$(this).show();
			});
		} else {
			var names = $('.imageName');
			names.each(function() {
				if (!$(this).text().toLowerCase().match(
						elem.value.toLowerCase())) {
					$(this).parent().parent().hide();
					$('#hiddenImages').append($(this).parent().parent());
				} else {
					$('#imageRow').append($(this).parent().parent());
					$(this).parent().parent().show()
				}
			});
		}
		// }
	}, 200);
}

