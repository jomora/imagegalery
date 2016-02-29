

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
			var names = $('.imageCol > div > .imageName');
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



function upload(elem) {
	var path = 'http://localhost:8080/gallery/search/' + elem.value;
	console.log(path);
	$('#imageRow').load(path)
}