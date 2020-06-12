package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	ReservationService reservationService;
	
	@GetMapping(path="/products")
	public String producList(@RequestParam(name="start", required=false, defaultValue="0") int start,
		@RequestParam(name="categoryId") int categoryId, ModelMap model){
		
		List<Product> list1 = reservationService.getProduct(categoryId, start);
		List<Promotion> list2 = reservationService.getPromotion();
		List<Category> list3 = reservationService.getCategory();
		int totalCount=0;
		
		for(Product i : list1) {
			totalCount++;
		}
		
		model.addAttribute("productList",list1);
		model.addAttribute("productTotalCount", totalCount);
		model.addAttribute("promotionList",list2);
		model.addAttribute("categoryList",list3);
		
		return "main";
	}
	
}
