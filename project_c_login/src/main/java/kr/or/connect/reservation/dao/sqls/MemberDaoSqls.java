package kr.or.connect.reservation.dao.sqls;

public class MemberDaoSqls {
	public static final String SELECT_ALL_BY_EMAIL = "SELECT id, name, password, email, create_date, modify_date FROM user WHERE email = :email";
}
