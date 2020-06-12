package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImple implements ReservationService{
	@Autowired
	ProductDao productDao;
	
	@Autowired
	PromotionDao promotionDao;
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Product> getProduct(Integer categoryId, Integer start) {
		List<Product> list= productDao.selectAll(categoryId, start, LIMIT);
		return list;
	}

	@Override
	public List<Promotion> getPromotion() {
		List<Promotion> list = promotionDao.selectAll();
		return list;
	}

	@Override
	public List<Category> getCategory() {
		List<Category> list = categoryDao.selectAll();
		return list;
	}
	
	
	
}
