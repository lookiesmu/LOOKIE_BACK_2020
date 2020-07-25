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
	public int InsertInfo(Integer productId, Integer displayInfoId,Integer userId, String reservationDate);
	public ReservationInfos searchUserId(Integer productId, Integer displayInfoId,Integer userId, String reservationDate);
	public int insertPrice(Integer reservationInfoId, Integer productPriceId, Integer count);
	public List<ReservationInfoPrice> getPrice(int id);
	public Integer getSum(int id);
	public List<ReservationInfos> selectByUserId(Long userId);
	public Product selectProductById(int id);
	public int deleteByreservationInfoId(int id);
	public int insertComments(int productId, int reservationInfoId, Long userId, int score, String comment);
	public int getreservationUserCommentId(int reservationInfoId, Long userId, int score, String comment);
	public int insertFileInfo(String fileName);
	public int getFileId(String fileName);
	public int insertUserCommentImage(int reservationInfoId, int reservationUserCommentId, int fileId);
	public List<ReservationUserCommentImages> getReservationUserCommentImages();
	public ReservationUserCommentImages getReservationUserCommentImagesFile(int fileId);
}
