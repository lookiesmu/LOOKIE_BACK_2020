package kr.or.connect.reservation.dao;

public class ReservationSqls {
	public static final String SELECT_CATEGORY_COUNT ="SELECT count(*) AS count, id, name FROM category group by id";
	public static final String SELECT_PROMOTION_COUNT = "SELECT promotion.id AS id, product.id AS productId, file_info.save_file_name AS productImageUrl\r\n" + 
														"FROM promotion, product, display_info, display_info_image, file_info\r\n" + 
														"WHERE promotion.product_id=product.id and product.id=display_info.product_id and display_info.id=display_info_image.display_info_id and display_info_image.file_id=file_info.id";
	public static final String SELECT_PRODUCT_ALLINFO = "SELECT display_info.id AS displayInfoId, display_info.place_name AS placeName, product.content AS productContent, product.description AS productDescription, product.id AS productId, file_info.save_file_name AS productImageUrl\r\n" + 
														"FROM category, display_info, product, file_info, product_image, promotion\r\n" + 
														"WHERE :categoryId=product.category_id and display_info.product_id=product.id and promotion.product_id=product.id and product_image.product_id=product.id and product_image.file_id=file_info.id ORDER BY product.id DESC limit :start, :limit";
	
}
