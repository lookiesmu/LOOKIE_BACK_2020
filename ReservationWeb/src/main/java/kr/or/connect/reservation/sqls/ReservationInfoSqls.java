package kr.or.connect.reservation.sqls;

public class ReservationInfoSqls {
	
	public static final String SELECT_RESERVATIONS_BY_EMAIL="select * "
			+ "from reservation_info "
			+ "where reservation_email= :email";
	public static final String GET_TOTAL_PRICE="select sum(product_price.price*reservation_info_price.count)as totalPrice "
			+ "from reservation_info, reservation_info_price, product_price "
			+ "where reservation_info.id=reservation_info_price.reservation_info_id and reservation_info_price.product_price_id=product_price.id and reservation_info.id= :id";
		
	public static final String GET_RESERVATION_ID_BY_EMAIL="select id "
			+ "from reservation_info "
			+ "where reservation_email= :email";
	
	public static final String GET_RESERVATION_BY_ID="select * "
			+ "from reservation_info "
			+ "where id= :id";
	
	public static final String GET_RESERVATION_PRICE_BY_ID="select * "
			+ "from reservation_info_price "
			+ "where reservation_info_id= :id";
	
	public static final String CANCEL_RESERVATION_BY_ID="update reservation_info "
			+ "set cancel_flag=1 "
			+ "where id= :id";
	
	public static final String DELETE_RESERVATION_PRICE="delete "
			+ "from reservation_info_price "
			+ "where reservation_info_id= :id";
}
