package kr.or.connect.reservation.service;

import java.util.*;

import kr.or.connect.reservation.dto.*;

public interface MainPageService {
	
	public List<PromotionDto> getPromotions();
	public List<CategoryDto> getCategory(Integer id);
	public List<CategoryDto> getCount();
	public List<ProductDto>	getProduct(Integer id, Integer start, Integer limit);
}
