package kr.or.connect.reservation.dao.sqls;

public class DisplayInfoSqls {
	public static final String ORDER = " ORDER BY d_info.id ASC";
	public static final String LIMIT = " LIMIT :start, :limit";
	public static final String DISPLAY_ALL = "SELECT d_info.id, p.category_id, d_info_image.display_info_id, c.name, p.description, p.content, p.event, d_info.opening_hours, d_info.place_name, d_info.place_lot, d_info.place_street, d_info.tel, d_info.homepage, d_info.email, d_info.create_date, d_info.modify_date, d_info_image.file_id "
			+"FROM category c, product p, display_info d_info, display_info_image d_info_image "
			+"WHERE c.id=p.category_id AND p.id = d_info.product_id AND d_info.id = d_info_image.display_info_id";
	
	public static final String DISPLAY_LIMIT = DISPLAY_ALL + "ORDER BY d_info.id ASC LIMIT :start, :limit";
	
	public static final String DISPLAY_BY_CATEGORYID_LIMIT = DISPLAY_ALL + " AND c.id = :categoryId" + ORDER + LIMIT;
	
	public static final String SELECT_DISPLAY_BY_DISPLAYID = DISPLAY_ALL + " AND d_info.id = :displayId";
	
	public static final String COUNT_ALL = "SELECT count(*) from display_info";
	
	public static final String COUNT_CATEGORYID = "SELECT count(*) "
			+"FROM category c, product p, display_info d_info "
			+"WHERE c.id=p.category_id AND p.id = d_info.product_id AND c.id = :categoryId";
	
}
