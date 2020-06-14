package kr.or.connect.reservation.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@RestController
@RequestMapping(path="/api/categories")
public class CategoryApiController {
	@Autowired
	MainPageService mainPageService;
	
	@GetMapping
	public Map<String,Object> list(@RequestParam(name="id", required=false) int id) {
		//client한테 응답이 갈때 json 변환
		List<CategoryDto> category;
		if(id==0) {
			category = mainPageService.getCount();
		}
		else {
			category = mainPageService.getCategory(id);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("items", category);
		
		

		return map;
	}
}
