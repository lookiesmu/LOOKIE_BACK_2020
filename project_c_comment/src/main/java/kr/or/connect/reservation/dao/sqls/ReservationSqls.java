package kr.or.connect.reservation.dao.sqls;

public class ReservationSqls {
	public static final String SELECT_CATEGORY_LIST ="SELECT category.id AS id, name AS name, count(display_info.product_id) AS count " + 
													  "FROM category, product, display_info " + 
													  "WHERE product.category_id=category.id and product.id=display_info.product_id " + 
													  "GROUP BY category.id;";
	
	public static final String SELECT_PRODUCT_ALLINFO = "SELECT p.id AS id, c.id AS categoryId, d_fo.id AS displayInfoId, c.name, p.description, p.content, p.event, d_fo.opening_hours, d_fo.place_name, d_fo.place_lot, d_fo.place_street, d_fo.tel, d_fo.homepage, d_fo.email, d_fo.create_date, d_fo.modify_date AS modifyDate, p_im.file_id " + 
														"FROM category c, product p, display_info d_fo, product_image p_im " + 
														"WHERE c.id=:categoryId and c.id=p.category_id and p.id = d_fo.product_id and p.id = p_im.product_id and p_im.type = \"ma\" " + 
														"ORDER BY p.id Limit :start, 4;";
	
	public static final String SELECT_PRODUCT_TOTALCOUNT = "SELECT count(d_fo.product_id) AS count " + 
														   "FROM category c, product p, display_info d_fo " + 
														   "WHERE p.category_id=c.id and p.id=d_fo.product_id and c.id=:categoryId " + 
														   "GROUP BY c.id;";
	
	public static final String SELECT_PROMOTION_ALL = "SELECT pm.id AS id, p.id AS product_id, c.id AS category_id, c.name AS category_name, p.description AS description, f_fo.id AS fileId " + 
													  "FROM category c, product p, promotion pm, product_image p_im, file_info f_fo " + 
													  "WHERE c.id=p.category_id and p.id = pm.product_id and p.id = p_im.product_id and f_fo.id = p_im.file_id and p_im.type=\"th\";";
	
	public static final String SELECT_PRODUCT_IMAGES_LIST = "SELECT p.id AS productId, p_im.id AS productImageId, p_im.type, f_fo.id AS fileInfoId, f_fo.file_name, f_fo.save_file_name, f_fo.content_type, f_fo.delete_flag, f_fo.create_date, f_fo.modify_date " + 
															"FROM category c, product p, product_image p_im, file_info f_fo, display_info d_fo " + 
															"WHERE c.id = p.category_id and d_fo.product_id = p.id and p_im.file_id = f_fo.id and p.id = p_im.product_id and d_fo.id=:displayInfoId and p_im.type = \"ma\";";
	
	public static final String SELECT_DISPLAY_INFO_IMAGES_LIST = "SELECT d_im.id, d_fo.id AS displayInfoId, f_fo.id AS fileId, f_fo.file_name, f_fo.save_file_name, f_fo.content_type, f_fo.delete_flag, f_fo.create_date, f_fo.modify_date " + 
																 "FROM file_info f_fo, display_info d_fo, display_info_image d_im " + 
																 "WHERE d_im.display_info_id = d_fo.id and d_im.file_id = f_fo.id and d_fo.id=:displayInfoId;";
	
	public static final String AVG_COMMENT_SCORE = "SELECT AVG(ruc.score) " + 
												   "FROM product p, display_info d_fo, reservation_user_comment ruc " + 
												   "WHERE p.id = d_fo.product_id and ruc.product_id = p.id and p.id = :productId;";
	
	public static final String SELECT_PRODUCT_PRICE_LIST = "SELECT p_p.id, p.id AS productId, p_p.price_type_name, p_p.price, p_p.discount_rate, p_p.create_date, p_p.modify_date " + 
														   "FROM product p, display_info d_fo, product_price p_p " + 
														   "WHERE p.id = d_fo.product_id and p_p.product_id = p.id and d_fo.id=:displayInfoId;";
	
	public static final String SELECT_PRODUCT_UNIT = "SELECT p.id, c.id AS categoryId, d_fo.id AS displayInfoId, c.name, p.description, p.content, p.event, d_fo.opening_hours, d_fo.place_name, d_fo.place_lot, d_fo.place_street, d_fo.tel, d_fo.homepage, d_fo.email, d_fo.create_date, d_fo.modify_date, p_im.file_id\r\n" + 
													 "FROM category c, product p, display_info d_fo, product_image p_im\r\n" + 
													 "WHERE d_fo.id=:displayInfoId and c.id=p.category_id and p.id = d_fo.product_id and p.id = p_im.product_id and p_im.type = \"ma\";";
	
	public static final String SELECT_USER_COMMENTS = "SELECT ruc.id, ruc.product_id, ruc.reservation_info_id, ruc.score, u.email AS reservationEmail, ruc.comment, ruc.create_date, ruc.modify_date " + 
													  "FROM product p, reservation_user_comment ruc, reservation_info r_fo, user u " + 
													  "WHERE p.id = ruc.product_id and ruc.user_id = u.id and r_fo.id = ruc.reservation_info_id and p.id =:productId " + 
													  "ORDER BY ruc.id DESC Limit :start, 5;";
	
	public static final String COUNT_USER_COMMENTS = "SELECT *" + 
													 "FROM reservation_user_comment;";
	
	public static final String SELECT_PRODUCT_BY_PRODUCT_ID = "SELECT * "+
															  "FROM product "+
															  "WHERE id= :id;";
	
	public static final String INSERT_RESERVATION_USER_COMMENT_BY_ALL_INFO = "INSERT INTO reservation_user_comment(product_id, reservation_info_id, user_id, score, comment,create_date,modify_date) " + 
																			 "VALUES(:productId, :reservationInfoId, :userId, :score, :comment,now(),now());";
	
	public static final String SELECT_USER_COMMENT_ID_BY_ALL_INFO = "SELECT id " + 
																	"FROM reservation_user_comment " + 
																	"WHERE reservation_info_id = :reservationInfoId and user_id = :userId and  score = :score and comment = :comment";
	
	public static final String INSERT_FILE_INFO = "INSERT INTO file_info(file_name, save_file_name, content_type, delete_flag, create_date, modify_date) " + 
												  "VALUES(:fileName, :saveFileName, :contentType, 0, now(), now());";
	public static final String SELECT_FILE_INFO_ID_BY_FILE_NAME = "SELECT id "+
												  				  "FROM file_info "+
												  				  "WHERE file_name=:fileName";
	
	public static final String INSERT_USER_COMMENT_IMAGE_BY_ALL = "INSERT INTO reservation_user_comment_image(reservation_info_id, reservation_user_comment_id, file_id) " + 
																  "VALUES(:reservationInfoId, :reservationUserCommentId, :fileId);";
	
	public static final String SELECT_RESERVATION_USER_COMMENT_IMAGE = "SELECT ruci.id, ruci.reservation_info_id, reservation_user_comment_id, file_id, file_name, save_file_name, content_type, delete_flag, fi.create_date, fi.modify_date " + 
																	   "FROM reservation_user_comment_image ruci, reservation_user_comment ruc, file_info fi " + 
																	   "WHERE ruci.file_id=fi.id and ruci.reservation_user_comment_id=ruc.id;";	
	
	public static final String SELECT_FILE_INFO_BY_FILE_ID = "SELECT * "+
															 "FROM file_info "+
															 "WHERE id = :fileId;";
}
