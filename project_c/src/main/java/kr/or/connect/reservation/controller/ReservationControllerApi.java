package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path="/api")
public class ReservationControllerApi {
	@Autowired
	ReservationService reservationService;
	
	@GetMapping(path="/products")
	public Map<String, Object> productList(@RequestParam(name="start", required=false, defaultValue="0") int start,
			@RequestParam(name="categoryId") int categoryId){
		
		List<Product> list = reservationService.getProduct(categoryId, start);
		int totalCount=0;
		
		for(Product i : list) {
			totalCount++;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("item", list);
		map.put("totalCount", totalCount);
		return map;
	}
	
	@GetMapping(path="/promotions")
	public List<Promotion> promotionList(){
		List<Promotion> list = reservationService.getPromotion();
		return list;
	}
	
	@GetMapping(path="/categorys")
	public List<Category> categoryList(){
		List<Category> list =reservationService.getCategory();
		return list;
	}
}
