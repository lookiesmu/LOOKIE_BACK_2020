package kr.or.connect.reservation.dao.sqls;

public class ReservationInfoPriceSqls {
	public static final String SELECT_BY_PRICE_ID = "SELECT * "+
			 										"FROM reservation_info_price"+
			 										"WHERE id = :reservationInfoPriceId";
	
	public static final String INSERT_RESERVATION_INFO = "INSERT INTO reservation_info (product_id, display_info_id, user_id, reservation_date, create_date, modify_date) " + 
														 "VALUES(:productId, :displayInfoId, :userId, :reservationDate , now(), now());";	
	
	public static final String SELECT_RESERVATION_INFO_ALL = "SELECT * "+
			 											 	 "FROM reservation_info "+
			 											 	 "WHERE product_id = :productId and display_info_id = :displayInfoId and user_id = :userId and reservation_date = :reservationDate";
	
	public static final String INSERT_RESERVATION_INFO_PRICE = "INSERT INTO reservation_info_price (reservation_info_id, product_price_id, count )" + 
																"VALUES(:reservationInfoId, :productPriceId, :count);";
	
	public static final String SELECT_PRICE = "SELECT * " + 
											  "FROM reservation_info_price " + 
											  "WHERE id = :id;";
	
	public static final String GET_SUM_PRICE = "SELECT SUM(count * price) " + 
												"FROM reservation_info_price r " + 
												"JOIN product_price p " + 
												"ON r.product_price_id = p.id " + 
												"WHERE reservation_info_id = :id;";
	
	public static final String SELECT_RESERVATION_INFO_BY_ID = "SELECT * "  + 
															   "FROM reservation_info " + 
															   "WHERE user_id = :userId;";
	
	public static final String UPDATE_CANCEL_FLAG = "UPDATE reservation_info " + 
													"SET cancel_flag = 1 " + 
													"WHERE id = :id;";
}
