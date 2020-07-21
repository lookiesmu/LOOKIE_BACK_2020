function sendAjax(displayInfoId){
	
//	var Req = new XMLHttpRequest();
//	
//	
//	Req.addEventListener("load",function(){
//		
//		return JSON.parse(Req.responseText);
//		
//	});
//	
//	Req.timeout=0;
//	Req.open("GET", "/ReservationWeb/api/products/"+displayInfoId, true); //ajax 응답값 바로 얻기위해 false 사용해서 동기로 사용
//	Req.send();
	var result;
	$.ajax({
		type: 'GET', 
		url: '/ReservationWeb/api/products/'+displayInfoId, 
		dataType: 'json', 
		async: false, 								//ajax 응답값 바로 얻기위해 false 사용해서 동기로 사용
		success: function(json) { 
			result = json; 
			} 
		});
	
	 
	return result;
	
}

function getDisplayImage(response) {

	var displayImageTemplate = document.querySelector('#displayInfoTemplate').innerText;
	var bindTemplate = Handlebars.compile(displayImageTemplate);
	var insertLocation = document.querySelector('.detail_swipe');
	

			
	var displayInfoItems = response;
			

	var resultHTML=bindTemplate(displayInfoItems);
	insertLocation.innerHTML=resultHTML;


}

function insertProductContent(response){
	
	var insertLocation=document.querySelectorAll('.dsc');
	
	
	var productContent=response.displayInfo.productContent;
	
	insertLocation.forEach(function(e){
		e.innerHTML=productContent;
	})
	
	
}

function insertAverageScore(response){
	var insertLocation=document.querySelector('#averageScore');
	
	var averageScore=response.averageScore.toFixed(1); //소수점 한자리까지 반올림하여 표시 
	
	insertLocation.innerText=averageScore;
}

function insertCommentsCounts(response){
	var insertLocation=document.querySelector('#totalComments');
	
	var totalCounts=response.comments.length;
	
	insertLocation.innerText=totalCounts+"건";
}

function insertComments(response,page){
	var insertLocation=document.querySelector(".list_short_review");
	var reviewTemplate = document.querySelector('#reviewTemplate').innerText;
	var bindTemplate = Handlebars.compile(reviewTemplate);
	
	var commentsItems = response;
	var showComments;
	if(page==="detail" && commentsItems.comments.length >3){	//detail 페이지에는 3개만 표시함
		showComments=commentsItems.comments.slice(0,3);
		commentsItems.comments=showComments;
	}
	

	

	var resultHTML=bindTemplate(commentsItems);
	insertLocation.innerHTML=resultHTML;
}



//url의 parameter값 얻기
function getParam(sname) {

    var params = location.search.substr(location.search.indexOf("?") + 1);
    var sval = "";
    
    params = params.split("&");

    for (var i = 0; i < params.length; i++) {
        temp = params[i].split("=");
        if ([temp[0]] == sname) { sval = temp[1]; }

    }
    return sval;

}