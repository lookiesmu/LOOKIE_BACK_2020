package kr.or.connect.reservation.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@RestController
@Api(tags="카테고리 API", description = "카테고리 API (카테고리 목록 구하기)")
@RequestMapping(path="/api/categories")
public class CategoryApiController {
	@Autowired
	MainPageService mainPageService;
	
	@ApiOperation(value="카테고리 목록 구하기")
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
