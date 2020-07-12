package kr.or.connect.reservation.sqls;

public class DisplayInfoSqls {
	public static final String SELECT_DISPLAY_INFO_IMAGE="select display_info_image.id as displayInfoImageId, display_info_image.display_info_id as displayInfoId, file_info.id as fileId, file_info.file_name as fileName, file_info.save_file_name as saveFileName, file_info.content_type as contentType, file_info.delete_flag as deleteFlag, file_info.create_date as createDate, file_info.modify_date as modifyDate "
			+ "from display_info_image,file_info "
			+ "where display_info_image.file_id=file_info.id and display_info_image.display_info_id= :id";
	
	public static final String SELECT_COMMENT_IMAGES="select c_image.id as imageId, c_image.reservation_info_id as reservationInfoId, c_image.reservation_user_comment_id as reservationUserCommentId, file_info.id as fileId, file_info.file_name as fileName, file_info.save_file_name as saveFileName, file_info.content_type as contentType, file_info.delete_flag as deleteFlag, file_info.create_date as createDate, file_info.modify_date as modifyDate "
			+ "from reservation_user_comment_image as c_image, file_info "
			+ "where c_image.file_id=file_info.id "
			+ "and c_image.reservation_user_comment_id= :id";
	
	public static final String SELECT_COMMENTS="select u_comment.id as commentId, u_comment.product_id as productId, u_comment.reservation_info_id as reservationInfoId, u_comment.score, u_comment.comment, r_info.reservation_name as reservationName, r_info.reservation_tel as reservationTelephone, r_info.reservation_email as reservationEmail, r_info.reservation_date as reservationDate, u_comment.create_date as createDate, u_comment.modify_date as modifyDate "
			+ "from reservation_user_comment as u_comment, reservation_info as r_info "
			+ "where u_comment.reservation_info_id=r_info.id "
			+ "and u_comment.product_id= :id";
	
	public static final String SELECT_PRODUCT_PRICES="select * "
			+ "from product_price "
			+ "where product_price.product_id= :id";

}
