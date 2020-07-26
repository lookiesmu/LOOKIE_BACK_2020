package kr.or.connect.reservation.dao.sqls;

public class ReservationUserCommentImageSqls {
	public static final String GET_PRODUCT_ID = "SELECT product_id "
			+ "FROM reservation_info "
			+ "WHERE id=:reservationInfoId";
	
	public static final String GET_RESERVATION_COMMENT_ID = "SELECT id "
			+ "FROM reservation_user_comment "
			+ "WHERE product_id=:productId, reservation_info_id=:reservationInfoId, user_id=:userId";
	
	public static final String GET_FILE_ID = "SELECT id "
			+ "FROM file_info "
			+ "WHERE file_name=:fileName";
	
	public static final String GET_FILE_NAME = "SELECT file_name "
			+ "FROM file_info "
			+ "WHERE id=:fileId";
	
	public static final String GET_FILE_TYPE = "SELECT content_type "
			+ "FROM file_info "
			+ "WHERE id=:fileId";
	
	public static final String POST_RESERVATION_IMAGE = "INSERT INTO reservation_user_comment(product_id, reservation_info_id, user_id, score, comment, create_date, modify_date) "
			+ "VALUES (:productId, :reservationInfoId, :userId, :score, :comment, now(), now())";
	
	public static final String POST_FILE = "INSERT INTO file_info(file_name, save_file_name, content_type, delete_flag, create_date, modify_date) "
			+ "VALUES (:fileName, :saveFileName, :contentType, :deleteFlag, now(), now())";
	
	public static final String POST_RESERVATION_COMMENT_IMAGE = "INSERT INTO reservation_user_comment_image(reservation_info_id,  reservation_user_comment_id, file_id) "
			+ "VALUES (:reservationInfoId, :reservationUserCommentId, :fileId)";
	
	public static final String GET_RESERVATION_IMAGE = "SELECT r.id, r.reservation_info_id AS reservationInfoId, r.reservation_user_comment_id AS reservationUserCommentId, f.id AS fileId, f.file_name AS fileName, f.save_file_name AS svaeFileName, f.content_type AS contentType, f.delete_flag AS deleteFlag, f.create_date AS createDate, f.modify_date AS modifyDate "
			+ "FROM reservation_user_comment_image r, file_info f "
			+ "WHERE r.file_id = f.id AND r.reservation_info_id = :reservationInfoId";
}