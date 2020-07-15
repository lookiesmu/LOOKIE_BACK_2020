package kr.or.connect.reservation.service;

import java.util.*;

import kr.or.connect.reservation.dto.*;

public interface MainPageService {
	
	public List<PromotionDto> getPromotions();
	public List<CategoryDto> getCategory(Integer id);
	public List<CategoryDto> getCount();
	public List<ProductDto>	getProduct(Integer id, Integer start, Integer limit);
	
	//상품 전시 정보 구하기 api 에필요한 service
	public List<DisplayInfoDto> getDisplayInfo(Integer displayInfoId);
	public List<ProductImageDto> getProductImage(Integer productId);
	public List<DisplayInfoImageDto> getDisplayInfoImage(Integer displayInfoId);
	public List<CommentsDto> getComments(Integer productId);
	public List<CommentImageDto> getCommentImages(Integer commentId);
	public List<ProductPriceDto> getProductPrice(Integer productId);
}
