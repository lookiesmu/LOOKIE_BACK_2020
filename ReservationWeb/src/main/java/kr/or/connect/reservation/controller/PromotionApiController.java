package kr.or.connect.reservation.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@RestController
@Api(tags="프로모션 API", description = "프로모션 API (프로모션 목록 구하기)")
@RequestMapping(path="/api/promotions")
public class PromotionApiController {
	@Autowired
	MainPageService mainPageService;
	
	@ApiOperation(value ="프로모션 목록 구하기")
	@GetMapping
	public List<PromotionDto> list() {
		//client한테 응답이 갈때 json 변환
		List<PromotionDto> list = mainPageService.getPromotions();
		

		return list;
	}
}
