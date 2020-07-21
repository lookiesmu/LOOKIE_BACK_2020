package kr.or.connect.reservation.dao.sqls;

public class ReservationUserCommentSqls {
	public static final String COMMENT_ALL = "SELECT rc.id, rc.product_id, rc.reservation_info_id, rc.score, u.email, rc.comment, rc.create_date, rc.modify_date "
			+"FROM reservation_user_comment rc, user u " 
			+"WHERE u.id=rc.user_id "
			+"ORDER BY id DESC LIMIT :start, :limit";
	
    public static final String SELECT_BY_PRODUCT_ID = "SELECT id, product_id, reservation_info_id, score, user.email, comment, create_date, modify_date "
            +"FROM reservation_user_comment, user "
            +"WHERE user.id=reservation_user_comment.user_id AND product_id = :productId ORDER BY id DESC LIMIT :start, :limit";
    
    public static final String SCORE_AVG = "SELECT avg(score) FROM reservation_user_comment WHERE product_id = :productId";
    
    public static final String COUNT_COMMENT_ALL = "SELECT count(*) "
            +"FROM reservation_user_comment";
}
