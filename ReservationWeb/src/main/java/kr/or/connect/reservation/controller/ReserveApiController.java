package kr.or.connect.reservation.controller;


import java.io.*;
import java.text.*;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

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
		int reservationInfoId=0;
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
		
		//set한 reservation을 insert
		reservationInfoId=reservationService.insertReservation(reservation); //reservaionId 부여
		
		
		//prices 등록(구매상품 등록)
		for(int i=0; i<numsOfprices; i++) {
			PricesDto price=new PricesDto();
			price.setReservationInfoId(reservationInfoId);
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
		
		//지우기
		reservationService.cancelRerservation(reservationId);
		
		//정보 가저오기
		List<ReservationsDto> reservationInfo=reservationService.getReservationById(reservationId);
		List<PricesDto> pricesList=reservationService.getReservationPricesByID(reservationId);
		
		//지우기
		reservationService.deleteReservationPrices(reservationId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("reservationInfo", reservationInfo);
		map.put("prices", pricesList);
		
		
		return map;
			
	}
	
	@ApiOperation(value ="한줄평 (comment) 등록")
	@PostMapping(path="/{reservationInfoId}/comments")
	public Map<String,Object> insertComment(@PathVariable (name = "reservationInfoId") int reservationInfoId,
			@RequestParam("file") MultipartFile file,
			@RequestParam("comment") String comment,
			@RequestParam("productId") int productId,
			@RequestParam("score") int score){
		
		int commentId;
		//post된 comment insert.
        CommentsDto commentDto=new CommentsDto();
        commentDto.setProductId(productId);
        commentDto.setReservationInfoId(reservationInfoId);
        commentDto.setScore(score);
        commentDto.setComment(comment);
        commentDto.setCreateDate(new Date());
        commentDto.setModifyDate(new Date());
        
        commentId=reservationService.insertComment(commentDto);//DB에 comment insert 하고 id 받기. 
		commentDto.setCommentId(commentId);
        
		//업로드된 commentImage파일 가저오기& insert
        String fileName=null;
        String saveDir="img/";
        String contentType=null;
        FileInfoDto commentImageFileInfo=null;
        CommentImageDto commentImageInfo=null;
        int fileId;
        int userCommentId;
        
        //comment로 이미지가 들어왔을 경우
		if(file!=null) {
			
			fileName=file.getOriginalFilename();
			contentType=file.getContentType();
			System.out.println("파일 이름 : " + file.getOriginalFilename());
			System.out.println("파일 크기 : " + file.getSize());
			
			//commentImage 파일 저장
	        try(
	                // 맥일 경우 
	                //FileOutputStream fos = new FileOutputStream("/tmp/" + file.getOriginalFilename());
	                // 윈도우일 경우
	                FileOutputStream fos = new FileOutputStream(saveDir + file.getOriginalFilename()); //파일이 저장될 경로 설정
	                InputStream is = file.getInputStream();
	        ){
	        	    int readCount = 0;
	        	    byte[] buffer = new byte[1024];
	            while((readCount = is.read(buffer)) != -1){
	                fos.write(buffer,0,readCount);
	            }
	        }catch(Exception ex){
	            throw new RuntimeException("file Save Error");
	        }
	        
	        //DB에 commentImage 등록
	        //file_info Table에 등록
	        commentImageFileInfo=new FileInfoDto();
	        commentImageFileInfo.setFileName(fileName);
	        commentImageFileInfo.setSaveFileName(saveDir+fileName);
	        commentImageFileInfo.setContentType(contentType);
	        commentImageFileInfo.setDeleteFlag(0);
	        commentImageFileInfo.setCreateDate(new Date());
	        commentImageFileInfo.setModifyDate(new Date());
	        
	        fileId=reservationService.insertImageFileInfo(commentImageFileInfo);
	        
	        //reservation_user_comment_image Table에 등록
	        commentImageInfo=new CommentImageDto();
	        commentImageInfo.setReservationInfoId(reservationInfoId);
	        commentImageInfo.setReservationUserCommentId(commentId);
	        commentImageInfo.setFileId(fileId);
	        
	        userCommentId=reservationService.insertCommentImageInfo(commentImageInfo);
	        
		}
		    
    
		//response 객체 만들기
		commentDto.setCommentImages(mainPageService.getCommentImages(commentId));
		
		Map<String, Object> map = new HashMap<>();
		map.put("comment", commentDto);
		
		return map;
	}
	
	@ApiOperation(value ="image 다운로드")
	@GetMapping(path="/{fileId}")
	public Map<String,Object> download(@PathVariable (name = "fileId") int fileId,
			HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<>();
		
		List<FileInfoDto> fileInfoList=mainPageService.getFileInfo(fileId);
		if(fileInfoList.size()==0) {
			System.out.println("file not exist in DataBase");
			map.put("result", "file not exist in DataBase");
			return map;
		}
		
		FileInfoDto fileInfo=fileInfoList.get(0);
		
		String saveDir="D:/tmp/";
		String fileName = fileInfo.getFileName();
		String saveFileName = saveDir+fileInfo.getSaveFileName(); // 맥일 경우 "/tmp/connect.png" 로 수정 (드라이브 이름 미표기)
		String contentType = fileInfo.getContentType();
		File file=new File(saveFileName);
		long fileLength = 0;
		
		//저장소에 해당 파일이 있을 경우
		if(file.exists()) {
			fileLength=file.length();
			
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        response.setHeader("Content-Type", contentType);
	        response.setHeader("Content-Length", "" + fileLength);
	        response.setHeader("Pragma", "no-cache;");
	        response.setHeader("Expires", "-1;");
	        
	        try(
	                FileInputStream fis = new FileInputStream(saveFileName);
	                OutputStream out = response.getOutputStream();
	        		
	        ){
	        	    int readCount = 0;
	        	    byte[] buffer = new byte[1024];
	            while((readCount = fis.read(buffer)) != -1){
	            		out.write(buffer,0,readCount);
	            }
	        }catch(Exception ex){
	            throw new RuntimeException("Server:file transmit Error");
	        }
	        map.put("result", "file download success");
	        
	        return map;
		}
		else {
			System.out.println("file not exist in Disk");
			map.put("result", "file not exist in Disk");
			return map;
		}

	}
	
}
