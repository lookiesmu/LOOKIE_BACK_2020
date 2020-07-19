package kr.or.connect.reservation.dao.sqls;

public class ProductImageSqls {
//	public static final String PRODUCT_IMAGE_INFO_BY_PRODUCTID = "SELECT p_img.product_id AS productId, p_img.id, p_img.type, p_img.file_id, f_info.file_name, f_info.save_file_name, f_info.content_type, f_info.delete_flag, f_info.create_date, modify_date "
//			+ "FROM product_image p_img, file_info f_info "
//			+ "WHERE product_id = :productId AND p_img.file_id=f_info.id";
	
	public static final String PRODUCT_IMAGE_INFO_BY_PRODUCTID = "SELECT p_image.product_id AS productId,  p_image.id AS productImageId, p_image.type, f_info.id AS fileInfoId, f_info.file_name AS fileName, f_info.save_file_name AS saveFileName, f_info.content_type AS contentType, f_info.delete_flag AS deleteFlag, f_info.create_date AS createDate, f_info.modify_date AS modifyDate "
			+ "FROM product_image p_image, file_info f_info "
			+ "WHERE p_image.file_id=f_info.id AND p_image.type=\"ma\" "
			+ "AND p_image.product_id= :productId";
}
