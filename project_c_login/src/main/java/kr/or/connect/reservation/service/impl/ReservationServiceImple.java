package kr.or.connect.reservation.service.impl;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.DisplayInfoImagesDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dao.ProductImagesDao;
import kr.or.connect.reservation.dao.ProductPricesDao;
import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dao.ReservationInfosDao;
import kr.or.connect.reservation.dao.ReservationInfosPriceDao;
import kr.or.connect.reservation.dao.ReservationUserCommentsDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.DisplayInfoImages;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImages;
import kr.or.connect.reservation.dto.ProductPrices;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.ReservationInfoPrice;
import kr.or.connect.reservation.dto.ReservationInfos;
import kr.or.connect.reservation.dto.ReservationUserComments;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImple implements ReservationService{
	@Autowired
	ProductDao productDao;
	
	@Autowired
	DisplayInfoDao displayInfoDao;
	
	@Autowired
	PromotionDao promotionDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductImagesDao productImagesDao;
	
	@Autowired
	DisplayInfoImagesDao displayInfoImagesDao;
	
	@Autowired
	ProductPricesDao productPricesDao;
	
	@Autowired
	ReservationUserCommentsDao reservationUserCommentsDao;
	
	@Autowired
	ReservationInfosDao reservationInfosDao;
	
	@Autowired
	ReservationInfosPriceDao reservationInfosPriceDao;

	
	@Override
	public Map<String, Object> getProduct(Integer categoryId, Integer start) {
		Map<String, Object> map = new LinkedHashMap<>();
		List<Product> products= displayInfoDao.getdisplayInfos(categoryId, start);
		int totalCount = displayInfoDao.getdisplayTotalCount(categoryId);
		int productCount = 0;
		for(Product i : products) {
			productCount++;
		}
		map.put("totalCount", totalCount);
		map.put("productCount", productCount);
		map.put("products", products);
		return map;
	}
	@Override
	public Map<String, Object> getProductAll(Integer start){
		Map<String, Object> map = new LinkedHashMap<>();
		List<Product> products1= displayInfoDao.getdisplayInfos(1, start);
		List<Product> products2= displayInfoDao.getdisplayInfos(2, start);
		List<Product> products3= displayInfoDao.getdisplayInfos(3, start);
		List<Product> products4= displayInfoDao.getdisplayInfos(4, start);
		List<Product> products5= displayInfoDao.getdisplayInfos(5, start);
		
		map.put("product_category1", products1);
		map.put("product_category2", products2);
		map.put("product_category3", products3);
		map.put("product_category4", products4);
		map.put("product_category5", products5);
		return map;
		
	}


	@Override
	public List<Category> getCategory() {
		List<Category> list = categoryDao.getCategoryList();
		return list;
	}
	
	@Override
	public Map<String, Object> getPromotionAll(){
		Map<String, Object> map = new LinkedHashMap<>();
		List<Promotion> promotion = promotionDao.getPromotionAll();
		int size = 0;
		
		for(Promotion i : promotion) {
			size++;
		}
		map.put("size", size);
		map.put("items", promotion);
		return map;
	}
	@Override
	public List<ProductImages> getProductImage(Integer displayInfoId){
		List<ProductImages> productImages = productImagesDao.getProductImage(displayInfoId);
		return productImages;
	}
	
	@Override
	public List<DisplayInfoImages> getDisplayInfoImages(Integer displayInfoId){
		List<DisplayInfoImages> displayInfoImaes = displayInfoImagesDao.getDisplayInfoImages(displayInfoId);
		return displayInfoImaes;
	}
	
	@Override
	public Product getProductUnit(Integer displayInfoId) {
		Product product = displayInfoDao.getProductUnit(displayInfoId);
		return product;
	}
	
	@Override
	public List<ProductPrices> getProductPrice(Integer displayInfoId){
		List<ProductPrices> productprices = productPricesDao.getProuctPrices(displayInfoId);
		return productprices;
	}
	
	@Override
	public Integer getAvgScore(Integer productId) {
		Integer score = reservationUserCommentsDao.getAVGScore(productId);
		return score;
	}
	
	@Override
	public List<ReservationUserComments> getUserComments(Integer productId, Integer start){
		List<ReservationUserComments> userComments = reservationUserCommentsDao.getReservationUserComments(productId,start);
		return userComments;
	}
	
	@Override
	public List<ReservationUserComments> getUserTotalCount() {
		return reservationUserCommentsDao.getUserTotalCount();
	}
	
	@Override
	public int InsertInfo(Integer productId, Integer displayInfoId, Integer userId, String reservationDate) {
		return reservationInfosDao.insertInfo(productId, displayInfoId, userId, reservationDate);
	}
	@Override
	public ReservationInfos searchUserId(Integer productId, Integer displayInfoId, Integer userId, String reservationDate){
		return reservationInfosDao.searchUserId(productId, displayInfoId, userId, reservationDate);
	}
	@Override
	public int insertPrice(Integer reservationInfoId, Integer productPriceId, Integer count) {
		return reservationInfosPriceDao.insertPrice(reservationInfoId, productPriceId, count);
	}
	@Override
	public List<ReservationInfoPrice> getPrice(int id){
		return reservationInfosPriceDao.getPrice(id);
	}
	 public Integer getSum(int id) {
		 return reservationInfosPriceDao.getSum(id);
	 }
	 public List<ReservationInfos> selectByUserId(Long userId){
		 return reservationInfosDao.selectByUserId(userId);
	 }
	 public Product selectProductById(int id) {
		 return productDao.selectProductById(id);
	 }
	 public int deleteByreservationInfoId(int id) {
		 return reservationInfosDao.deleteByreservationInfoId(id);
	 }
}
