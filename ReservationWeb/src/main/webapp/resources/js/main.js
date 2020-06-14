
window.addEventListener("DOMContentLoaded", function() {
	getSlideImage();
	runImageSlider();
	getProductAjax(0,0); //초기화면 전체리스트 보여주기위해 0 넣어줌.(id=0,start=0) 
	clickTabMenu(); //카테고리 클릭시 event 등록
	clickMoreButton(); //더보기버튼 event 등록
});

function getSlideImage() {
	var Req = new XMLHttpRequest();
	var promotionTemplate = document.querySelector('#promotionItem').innerHTML;
	var insertItem = "";
	var promotionSlider = document.querySelector('#slide');
	

	Req.addEventListener("load",function(){
			
			var insertItemList = JSON.parse(Req.responseText);
//			for ( var key in insertItemList) {
//				insertItem = promotionTemplate.replace("{ProductImageId}",
//						insertItemList[key].productImageUrl);
//				promotionSlider.innerHTML += insertItem;
//			}
			insertItemList.forEach(function(e){
				insertItem = promotionTemplate.replace("{ProductImageId}",
						e.productImageUrl);
				promotionSlider.innerHTML += insertItem;
			})
	});


	Req.open("GET", "/ReservationWeb/api/promotions", true);
	Req.send();
}

function clickTabMenu(){
	var menu=document.querySelector(".section_event_tab");
	var itembox=document.querySelectorAll(".lst_event_box");
	var morebt=document.querySelector(".more .btn"); //더보기 버튼에 현재 어떤 카테고리인지 알려줘야 한다.
	var clickedid;
	
	
	menu.addEventListener("click",function(evt) {
		

	   if(evt.target.tagName==="SPAN" || evt.target.tagName==="A"){
		   viewMoreButton(); //더보기버튼 초기화
		   document.querySelector(".section_event_tab .active").className="anchor";
		   evt.target.closest("a").className="anchor active";
		   var temp=evt.target.closest("li"); //가장가까운 li tag
		   clickedid=temp.dataset.category;
		   itembox[0].innerHTML=""; //카테고리Tab 의 메뉴 클릭시 product display 공간 초기화
		   itembox[1].innerHTML="";
		   
		   morebt.dataset.category=clickedid;//더보기버튼에 현재카테고리 알려주기
		   
		   getCategoryAjax(clickedid);
		    
		   getProductAjax(clickedid,0);
		   
	   }
	});
	
}

function getCategoryAjax(id){
	var Req = new XMLHttpRequest();
	var getid=id;
	//const categorymap={"전체리스트":"0", "전시":"1", "뮤지컬":"2", "콘서트":"3", "클래식":"4", "연극":"5"};
	//var id=categorymap[clickedname];
	var countlocation=document.querySelector(".event_lst_txt .pink");
	
	Req.addEventListener("load",function(){
		var items=JSON.parse(Req.responseText);
		var count=items.items[0].count;
		
		countlocation.innerText=count+"개"		
		
	});
	
	Req.open("GET", "/ReservationWeb/api/categories?id="+getid,true);
	Req.send();
	
}

function getProductAjax(id,start){ //카테고리 클릭할때마다 실행됨
	var Req = new XMLHttpRequest();
	var categoryid=id;
	var start=start;
	var itemTemplate=document.querySelector("#itemList").innerHTML;
	var itembox=document.querySelectorAll(".lst_event_box");
	var insertItem="";
	
	Req.addEventListener("load",function(){
		var itemslist=JSON.parse(Req.responseText);
		var totalCount=itemslist.totalCount;
		var item=itemslist.items;
		
//		for ( var key in item){
//			insertItem = itemTemplate.replace("{description}", item[key].productDescription)
//									.replace("{ProductImageId}", item[key].productImageUrl)
//									.replace("{description}", item[key].productDescription)
//									.replace("{placeName}",item[key].placeName)
//									.replace("{content}",item[key].productContent);
//			if(key%4 ==0 || key%4==1){
//				itembox[0].innerHTML +=insertItem;
//			}
//			else{
//				itembox[1].innerHTML +=insertItem;
//			}
//		};
		item.forEach(function(e,i){//item 의 element ,index 한개한개씩 접근
			insertItem = itemTemplate.replace("{description}", e.productDescription)
									.replace("{ProductImageId}", e.productImageUrl)
									.replace("{description}", e.productDescription)
									.replace("{placeName}",e.placeName)
									.replace("{content}",e.productContent);
			if(i%2 == 0){
				itembox[0].innerHTML +=insertItem;
			}
			else{
				itembox[1].innerHTML +=insertItem;
			}
		});
		
		if((start+item.length) >= totalCount){
			deleteMoreButton();
		}
		
	});
	
	
	Req.open("GET", "/ReservationWeb/api/products?categoryId="+categoryid+"&start="+start,true);
	Req.send();
}


function runImageSlider() {
	var index = 0;
	var width = 414;
	var slide = document.querySelector("#slide");
	var length = document.querySelectorAll(".item").length;

	setInterval(function() {
		index = index + 1;
		index = index % length;
		slide.style.transform = "translate(-" + (width * index) + "px,0)";
	}, 2000)
}

function clickMoreButton(){
	//함수 호출시에만 정의/선언
	var morebt=document.querySelector(".more .btn");  //이벤트 리스너 전 정의/선언 부분은 이벤트시마다 재 정의/선언 되는것이 아님. 
	morebt.dataset.category=0;//초기화면이 전체리스트(id=0)이므로 초기화
	
	morebt.addEventListener("click",function(){
		var categoryid=morebt.dataset.category;
		var start=document.querySelectorAll(".wrap_event_box .lst_event_box .item").length;
		getProductAjax(categoryid,start);
	})
		
	
}

function deleteMoreButton(){
	var morebt=document.querySelector(".more .btn");
	morebt.style.display="none";
	
}

function viewMoreButton(){
	var morebt=document.querySelector(".more .btn");
	morebt.style.display="block";
}