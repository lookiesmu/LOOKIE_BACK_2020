package kr.or.connect.reservation.controller;


import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@RestController
@Api(tags="예약 API", description = "예약 API (예약정보조회, 예약하기, 예약취소하기, 한줄평등록)")
@RequestMapping(path="/api/reservations")
public class ReserveApiController {
	@Autowired
	MainPageService mainPageService;
	@Autowired
	ReservationInfoService reservationService;
	
	
	@ApiOperation(value = "예약정보 조회")
	@GetMapping
	public Map<String, Object> getReservations(@RequestParam(name="reservationEmail", required=true) String reservationEmail){
		int displayInfoId;
		int reservationInfoId;
		int size;
		
		List<ReservationsDto> reservationinfo = reservationService.getReservations(reservationEmail);
		size=reservationinfo.size();
		
		for(int i=0; i<size; i++) {
			displayInfoId=reservationinfo.get(i).getDisplayInfoId();
			reservationInfoId=reservationinfo.get(i).getReservationInfoId();
			List<totalPriceDto> totalPriceList=reservationService.getTotalPrice(reservationInfoId);
			List<DisplayInfoDto> displayinfo = mainPageService.getDisplayInfo(displayInfoId);
			reservationinfo.get(i).setDisplayInfo(displayinfo.get(0));
			reservationinfo.get(i).setTotalPrice(totalPriceList.get(0).getTotalPrice());
		}
		
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("reservations", reservationinfo);
		map.put("size", size);
		
		
		return map;
	}
	
	@ApiOperation(value = "예약하기")
	@PostMapping
	public Map<String, Object> doReserve(@RequestBody Map<String, Object> requestParam){
		int reservationId=0;
		int reservationInfoPriceId=0;
		String stringDate;
		Date reservationDate=null;
		
		//ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> prices = (List<Map<String, Object>>)requestParam.get("prices");
		int numsOfprices=prices.size();
		List<PricesDto> pricesList=new ArrayList<PricesDto>();
		
		String reservation_email=(String)requestParam.get("reservationEmail");
//		if(reservationService.getReservationId(reservation_email).size()!=0) {//이미 예약된 email 이라면
//			reservationId=reservationService.getReservationId(reservation_email).get(0).getReservationInfoId();
//		}
		
		
		//reservationId를 제외하고 attribute 등록
		ReservationsDto reservation = new ReservationsDto();
		reservation.setProductId((int)requestParam.get("productId"));
		reservation.setDisplayInfoId((int)requestParam.get("displayInfoId"));
		reservation.setReservationName((String)requestParam.get("reservationName"));
		reservation.setReservationTel((String)requestParam.get("reservationTelephone"));
		reservation.setReservationEmail((String)requestParam.get("reservationEmail"));
		//예약날짜를 parsing해서 등록해야함.
		stringDate=(String) requestParam.get("reservationYearMonthDay");
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			reservationDate = transFormat.parse(stringDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reservation.setReservationDate(reservationDate);
		reservation.setCancelFlag(0);
		reservation.setCreateDate(new Date());
		reservation.setModifyDate(new Date());
		
		reservationId=reservationService.insertReservation(reservation); //reservaionId 부여
		
		
		//prices 등록(구매상품 등록)
		for(int i=0; i<numsOfprices; i++) {
			PricesDto price=new PricesDto();
			price.setReservationInfoId(reservationId);
			price.setCount((int)prices.get(i).get("count"));
			price.setProductPriceId((int)prices.get(i).get("productPriceId"));
			
			
			reservationInfoPriceId=reservationService.insertReservedPrices(price);
			price.setId(reservationInfoPriceId);
			
			pricesList.add(price);
		}
		

		
		Map<String, Object> map = new HashMap<>();
		map.put("reservationInfo",reservation);
		map.put("prices", pricesList);
		
		
		
		return map;
	}
	
	@ApiOperation(value = "예약취소하기")
	@PutMapping(path="/{reservationId}")
	public Map<String,Object> deleteReservation(@PathVariable (name = "reservationId") int reservationId){
		
		//미리 정보 가저오기
		List<ReservationsDto> reservationInfo=reservationService.getReservationById(reservationId);
		List<PricesDto> pricesList=reservationService.getReservationPricesByID(reservationId);
		
		//지우기
		reservationService.cancelRerservation(reservationId);
		reservationService.deleteReservationPrices(reservationId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("reservationInfo", reservationInfo);
		map.put("prices", pricesList);
		
		
		return map;
			
	}
	
}
