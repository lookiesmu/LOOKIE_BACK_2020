package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Map;

import kr.or.connect.reservation.dto.*;


public interface ReservationService {
	public static final Integer LIMIT = 4;
	public Map<String, Object> getProduct(Integer categoryId, Integer start);
	public List<Category> getCategory();
	public Map<String, Object> getProductAll(Integer start);
	public Map<String, Object> getPromotionAll();
	public List<ProductImages> getProductImage(Integer displayInfoId);
	public List<DisplayInfoImages> getDisplayInfoImages(Integer displayInfoId);
	public Product getProductUnit(Integer displayInfoId);
	public List<ProductPrices> getProductPrice(Integer displayInfoId);
	public Integer getAvgScore(Integer displayInfoId);
	public List<ReservationUserComments> getUserComments(Integer productId, Integer start);
	public List<ReservationUserComments> getUserTotalCount();
}
