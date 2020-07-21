package kr.or.connect.reservation.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dao.ReservationInfosPriceDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.DisplayInfoImages;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImages;
import kr.or.connect.reservation.dto.ProductPrices;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.ReservationInfoPrice;
import kr.or.connect.reservation.dto.ReservationInfos;
import kr.or.connect.reservation.dto.ReservationUserComments;
import kr.or.connect.reservation.dto.ReservationInfoFrame;
import kr.or.connect.reservation.service.MemberService;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path="/api")
public class ReservationControllerApi {
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	MemberService memberSerivce;
	
	
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
	
	@PostMapping(path="reservationInfos")
	public Map<String, Object> reservation(@RequestBody Map<String, Object> requestParam, Principal principal){
		if(principal !=null) {
			
			Long loginUserId = memberSerivce.getUserIdNum(principal.getName());
			Map<String, Object> map= new LinkedHashMap<>();
			int productId=(Integer)requestParam.get("productId");
			int displayInfoId = (Integer)requestParam.get("displayInfoId");
			int userId = (Integer)requestParam.get("userId");
			String reservationDate = (String)requestParam.get("reservationYearMonthDay");
			List<Map<String,Object>> prices = (List<Map<String,Object>>)requestParam.get("prices");
			int productPriceId = (Integer)prices.get(0).get("productPriceId");
			int count = (Integer)prices.get(0).get("count");
			
			if(loginUserId == userId) {
				int success = reservationService.InsertInfo(productId, displayInfoId, userId, reservationDate);
				if(success == 0) {
					map.put("오류", "예약에 실패 했습니다.");
					return map;
				}
				
				ReservationInfos RIF = reservationService.searchUserId(productId,displayInfoId,userId,reservationDate);
				int RIFId= RIF.getId();
				
				success = reservationService.insertPrice(RIFId, productPriceId, count);
				if(success == 0) {
					map.put("오류", "예약에 실패 했습니다.");
					return map;
				}
				
				List<ReservationInfoPrice> price = reservationService.getPrice(RIFId);
				
				map.put("id", RIFId);
				map.put("productId", RIF.getProductId());
				map.put("cancelFlag", RIF.getCancelFlag());
				map.put("displayInfoId", RIF.getDisplayInfoId());
				map.put("userId", RIF.getUserId());
				map.put("reservationDate", RIF.getReservationDate());
				map.put("createDate", RIF.getCreateDate());
				map.put("modifyDate", RIF.getModifyDate());
				map.put("prices", price);
				
				return map;
				
			}else {
				Map<String, Object> mape= new LinkedHashMap<>();
				mape.put("오류", "로그인된 사용자와 예약 정보가 다릅니다..");
				return mape;
			}
				
		}else{
			Map<String, Object> mape= new LinkedHashMap<>();
			mape.put("오류", "로그인되어있지 않습니다.");
			return mape;
		}
		
		
	}
	
	@GetMapping(path="/reservationInfos")
	public Map<String,Object> reservationInfo(Principal principal){
		Long loginUserId = memberSerivce.getUserIdNum(principal.getName());
		Map<String, Object> map= new LinkedHashMap<>();
		
		if(loginUserId==0) {
			map.put("오류","로그인 하지 않았습니다.");
			return map;
		}
		List<ReservationInfos> RIFList = reservationService.selectByUserId(loginUserId);
		int size = RIFList.size();
		List<ReservationInfoFrame> IFList =  new ArrayList<>();
		
		for(int i=0; i<size; i++) {
			int RIFId = RIFList.get(i).getId();
			int productId = RIFList.get(i).getProductId();
			int sumPrice = reservationService.getSum(RIFId);
			Product pr = reservationService.selectProductById(productId);
			ReservationInfoFrame IF = new ReservationInfoFrame();
			
			IF.setId(RIFId);
			IF.setProductId(productId);
			IF.setDisplayInfoId(RIFList.get(i).getDisplayInfoId());
			IF.setCancelFlag(RIFList.get(i).getCancelFlag());
			IF.setProductDescription(pr.getDescription());
			IF.setProductCountent(pr.getContent());
			IF.setUserId(RIFList.get(i).getUserId());
			IF.setSumPrice(sumPrice);
			IF.setReservationDate(RIFList.get(i).getReservationDate());
			IF.setCreateDate(RIFList.get(i).getCreateDate());
			IF.setModifyDate(RIFList.get(i).getModifyDate());
			IFList.add(IF);
		}
		map.put("size", size);
		map.put("item", IFList);
		
			
		return map;
	}
	
	@PutMapping(path="reservationInfos")
	public Map<String,Object> reservationCancel(@RequestParam(name="id", required=false)int id){
		Map<String, Object> map= new LinkedHashMap<>();
		int check = reservationService.deleteByreservationInfoId(id);
		if(check!=0) {
			map.put("result", "success");
			return map;
		}
		map.put("result", "fail");
		return map;
		
	}
	
}
