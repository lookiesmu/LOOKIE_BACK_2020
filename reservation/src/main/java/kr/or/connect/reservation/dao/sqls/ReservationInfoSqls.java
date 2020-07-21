package kr.or.connect.reservation.dao.sqls;

public class ReservationInfoSqls {
	//예약 GET
	public static final String GET_RESERVATION_BY_USERID = "SELECT r_info.id, r_info.product_id, r_info.display_info_id, r_info.cancel_flag, p.description AS productDescription, p.content AS prodcutContent, r_info.user_id, r_info.reservation_date, r_info.create_date, r_info.modify_date "
			+ "FROM reservation_info r_info, product p "
			+ "WHERE r_info.product_id=p.id AND r_info.user_id=:userId";
	
	public static final String GET_SIZE = "SELECT count(*) AS count "
			+ "FROM reservation_info "
			+ "WHERE user_id=:userId";
	
	//예약 POST
	public static final String POST_RESERVATION_INFO = "INSERT INTO reservation_info(product_id, display_info_id, user_id, reservation_date, create_date, modify_date) "
			+ "VALUES (:productId, :displayInfoId, :userId, now(), now(), now())";
	
	public static final String POST_RESERVATION_PRICE = "INSERT INTO reservation_info_price(reservation_info_id, product_price_id, count) "
			+ "VALUES (:reservationInfoId, :productPriceId, :count)";
	
	public static final String GET_ID = "SELECT id "
			+ "FROM reservation_info "
			+ "WHERE product_id=:productId AND display_info_id=:displayInfoId AND user_id=:userId";
	
	//예약 취소 POST
	public static final String DELETE_BY_ID = "DELETE FROM reservation_info "
			+ "WHERE reservation_info.id = :id";
	public static final String DELETE_PRICE = "DELETE FROM reservation_info_price "
			+ "WHERE reservation_info_price.reservation_info_id = :id";
	
			
}