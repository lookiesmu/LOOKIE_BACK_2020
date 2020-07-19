package kr.or.connect.reservation.dao.sqls;

public class UserSqls {
	public static final String USER_ALL_BY_EMAIL = "SELECT id, name, password, email, phone, create_date, modify_date "
			+ "FROM user "
			+ "WHERE email = :email";
	
	public static final String USER_ROLE_ALL_BY_EMAIL = "SELECT ur.id, ur.user_id, ur.role_name "
			+ "FROM user_role ur JOIN user u ON ur.user_id = u.id "
			+ "WHERE u.email = :email";
	
	public static final String GET_USER_ENTITY = "SELECT email AS loginUserId, password "
			+ "FROM user "
			+ "WHERE email = :email";
}
