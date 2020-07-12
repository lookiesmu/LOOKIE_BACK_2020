package kr.or.connect.reservation.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@RestController
@Api(tags="상품 API", description = "상품 API (상품 목록 구하기, 상품 전시 정보 구하기)")
@RequestMapping(path="/api/products")
public class ProductApiController {
	@Autowired
	MainPageService mainPageService;
	
	@ApiOperation(value = "상품 목록 구하기")
	@GetMapping		//상품 목록 구하기 api
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
	
	@ApiOperation(value = "상품 전시 정보 구하기")
	@GetMapping(path="/{displayInfoId}") //상품 전시정보 구하기 api
	public Map<String,Object> getDisplayInfoById(@PathVariable (name = "displayInfoId") int displayInfoId){
		int productId;
		int commentId;
		int totalScore=0;
		double averageScore=0;
		
		List<DisplayInfoDto> displayinfo = mainPageService.getDisplayInfo(displayInfoId);
		
		productId=displayinfo.get(0).getProductId(); //displayinfo 에 맞는 productid를 구한다.
		List<ProductImageDto> productImage=mainPageService.getProductImage(productId); //productid를 가지고 해당 productimage를 구한다.
		
		List<DisplayInfoImageDto> displayinfoimage= mainPageService.getDisplayInfoImage(displayInfoId);//displayinfoid 에 해당하는 displayimage를 구한다.
		List<CommentsDto> comments=mainPageService.getComments(productId);//productid에 해당하는 comments를 구한다.
		List<ProductPriceDto> productprices=mainPageService.getProductPrice(productId);
		
		//comment에 commentImage 추가하기
		for(int i=0; i<comments.size();i++) {
			commentId=comments.get(i).getCommentId();
			List<CommentImageDto> commentImage=mainPageService.getCommentImages(commentId);
			comments.get(i).setCommentImages(commentImage);
			totalScore+=comments.get(i).getScore();
		}
		
		//averageScore 구하기
		averageScore=(double)totalScore/comments.size();
		
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("displayInfo", displayinfo.get(0));
		map.put("productImages", productImage);
		map.put("displayInfoImage", displayinfoimage.get(0));
		map.put("comments", comments);
		map.put("averageScore", averageScore);
		map.put("productPrices", productprices);
		
		return map;
		
	}

}
