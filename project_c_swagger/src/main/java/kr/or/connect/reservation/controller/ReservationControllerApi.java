package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.DisplayInfoImages;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImages;
import kr.or.connect.reservation.dto.ProductPrices;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.ReservationUserComments;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path="/api")
public class ReservationControllerApi {
	@Autowired
	ReservationService reservationService;
	
	
	@GetMapping(path="/categories")
	public Map<String, Object> categoryList(){
		List<Category> list = reservationService.getCategory();
		int size=0;
		
		for(Category i : list) {
			size++;
		}
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("size", size);
		map.put("items", list);
		return map;
	}
	
	
	@GetMapping(path="/displayinfos")
	public Map<String, Object> displayInfo(@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="1") int start){
		if(categoryId!=0) {
		Map<String, Object> map = reservationService.getProduct(categoryId, start);
		return map;
		}else {
			Map<String, Object> map = reservationService.getProductAll(start);
			return map;
			
		}
	}
	@GetMapping(path="/promotions")
	public Map<String, Object> promotions(){
		Map<String, Object> map = reservationService.getPromotionAll();
		return map;
	}
	
	@GetMapping(path="/displayinfos/{displayId}")
	public Map<String, Object> displayinfosUnit(@RequestParam(name="displayInfoId", required=false, defaultValue="1") int displayInfoId){
		Map<String, Object> map = new LinkedHashMap<>();
		Product product = reservationService.getProductUnit(displayInfoId);
		List<ProductImages> productImages = reservationService.getProductImage(displayInfoId);
		List<DisplayInfoImages> displayInfoImages = reservationService.getDisplayInfoImages(displayInfoId);
		List<ProductPrices> productPrices = reservationService.getProductPrice(displayInfoId);
		Integer avgScore = reservationService.getAvgScore(product.getId());
		
		map.put("product", product);
		map.put("productImages", productImages);
		map.put("displayInfoImages", displayInfoImages);
		map.put("avgScore", avgScore);
		map.put("productPrices", productPrices);
		return map;
	}
	
	@GetMapping(path="/comments")
	public Map<String, Object> displayComments(@RequestParam(name="productId", required = false, defaultValue = "1") int productId,
			@RequestParam(name="start", required=false, defaultValue="1") int start){
		Map<String, Object> map = new LinkedHashMap<>();
		List<ReservationUserComments> reservationUserComments = reservationService.getUserComments(productId, start);
		int commentCount= 0;
		List<ReservationUserComments> totalList = reservationService.getUserTotalCount();
		int totalCount = 0;
		for(ReservationUserComments i : totalList) {
			totalCount++;
		}
		
		for(ReservationUserComments i : reservationUserComments) {
			commentCount++;
		}
		
		map.put("totalCount", totalCount);
		map.put("commentCount", commentCount);
		map.put("reservationUserComments", reservationUserComments);
		return map;
	}
	
	

}
