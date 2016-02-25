//$(document).ready(function() {
//	$('#uploadFileInput').on("change", uploadFile());
//});

function uploadFile(elem) {
	var data = new FormData();
	$.each($('#uploadFileInput')[0].files, function(i, file) {
		data.append('file-' + i, file);
	});
	// var url = "/gallery/upload"
	// alert(data);
	$("#imageRow").load("/gallery/upload",data,alert());
//	$.ajax({
//		url : "/gallery/upload",
//		type : "POST",
//		data : data,
//		enctype : 'multipart/form-data',
//		processData : false,
//		contentType : false,
//		cache : false,
//		success : function() {
//			// Handle upload success
//			// ...
//			alert("upload successful");
//		},
//		error : function() {
//			// Handle upload error
//			alert("upload didnt work");
//			// ...
//		}
//	});
}