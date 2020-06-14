package kr.or.connect.reservation.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@RestController
@RequestMapping(path="/api/promotions")
public class PromotionApiController {
	@Autowired
	MainPageService mainPageService;
	
	@GetMapping
	public List<PromotionDto> list() {
		//client한테 응답이 갈때 json 변환
		List<PromotionDto> list = mainPageService.getPromotions();
		

		return list;
	}
}
