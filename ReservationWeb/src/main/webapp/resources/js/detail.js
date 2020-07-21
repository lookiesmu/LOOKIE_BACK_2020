window.addEventListener("DOMContentLoaded", function() {
	var displayInfoId=getParam("id");
	var jsonResponse=sendAjax(displayInfoId);
	getDisplayImage(jsonResponse);
	insertProductContent(jsonResponse);
	insertAverageScore(jsonResponse);
	insertCommentsCounts(jsonResponse);
	insertComments(jsonResponse,"detail");
	insertLocationInfo(jsonResponse);
	clickTabMenu();

});

function clickTabMenu(){
	var detail_area_wrap=document.querySelector(".detail_area_wrap");
	var detail_location=document.querySelector(".detail_location");
	
	var active_detail=document.querySelector(".info_tab_lst .item .anchor");
	var path=document.querySelector("._path .anchor");
	
	active_detail.addEventListener("click",function(){
		
		detail_location.className=".detail_location hide";
		detail_area_wrap.className=".detail_area_wrap";
		active_detail.className="anchor active";
		path.className="anchor";
		
	});
	
	path.addEventListener("click",function(){
		
		detail_area_wrap.className=".detail_area_wrap hide";
		detail_location.className=".detail_location";
		active_detail.className="anchor";
		path.className="anchor active";
				
	});	
}

function insertLocationInfo(response){
	var insertLocation = document.querySelector(".store_info");
	var locationTemplate= document.querySelector("#locationTemplate").innerHTML;
	
	var locationInfo=response.displayInfo;
	
	var insertLocationInfo="";
	
	
		insertLocationInfo = locationTemplate.replace("{placeLot}", locationInfo.placeLot)
								.replace("{placeStreet}",  locationInfo.placeStreet)
								.replace("{placeName}", locationInfo.placeName)
								.replace("{telephone}", locationInfo.telephone);
		
		insertLocation.innerHTML=insertLocationInfo;
								
		
		
	
}