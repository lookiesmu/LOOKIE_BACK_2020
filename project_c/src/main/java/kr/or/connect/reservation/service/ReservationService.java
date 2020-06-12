package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.*;

public interface ReservationService {
	public static final Integer LIMIT = 4;
	public List<Product> getProduct(Integer categoryId, Integer start);
	public List<Promotion> getPromotion();
	public List<Category> getCategory();
}
