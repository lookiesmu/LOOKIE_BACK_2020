package kr.or.connect.reservation.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@RestController
@RequestMapping(path="/api/products")
public class ProductApiController {
	@Autowired
	MainPageService mainPageService;
	
	
	@GetMapping
	public Map<String,Object> list(@RequestParam(name="categoryId", required=false) int id, @RequestParam(name="start", required=false) int start) {
		//client한테 응답이 갈때 json 변환
		Integer limit=4;
		int totalCount;
		List<ProductDto> productlist=mainPageService.getProduct(id,start,limit);

		if(id==0) {
			
			totalCount=mainPageService.getCount().get(0).getCount();
		}
		else {
			totalCount=mainPageService.getCategory(id).get(0).getCount();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		map.put("items", productlist);
		
		

		return map;
	}

}
