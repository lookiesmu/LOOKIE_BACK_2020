window.addEventListener("DOMContentLoaded", function() {
	var displayInfoId=getParam("id");
	var jsonResponse=sendAjax(displayInfoId);
	insertAverageScore(jsonResponse);
	insertCommentsCounts(jsonResponse);
	insertComments(jsonResponse,"review");

});

