package kr.or.connect.reservation.dao.sqls;

public class DisplayInfoDaoSqls {
    public static final String SELECT_ALL = "SELECT p.id, c.id as category_id, d_info_image.display_info_id, c.name, p.description, p.content, p.event, d_info.opening_hours, d_info.place_name, d_info.place_lot, d_info.place_street, d_info.tel, d_info.homepage, d_info.email, d_info.create_date, d_info.modify_date, p_image.file_id " +
                                            "FROM category c, product p, display_info d_info, product_image p_image, display_info_image d_info_image " +
                                            "WHERE c.id = p.category_id AND p.id = d_info.product_id AND p.id = p_image.product_id AND d_info.id = d_info_image.display_info_id AND p_image.type = 'ma'";

    public static final String SELECT_ALL_LIMIT = SELECT_ALL + "ORDER BY d_info.id ASC LIMIT :start, :limit";
    public static final String SELECT_BY_CATEGORY_ID_LIMIT = SELECT_ALL + "AND c.id = :categoryId ORDER BY d_info.id ASC LIMIT :start, :limit";

    public static final String SELECT_BY_DISPLAY_ID = SELECT_ALL + "AND d_info.id = :displayId";

    public static final String GET_TOTAL_COUNT = "SELECT count(*) from display_info";
    public static final String GET_TOTAL_COUNT_BY_CATEGORY_ID_LIMIT = "SELECT count(*) " +
                                                                      "FROM category c, product p, display_info d_info " +
                                                                      "WHERE c.id = p.category_id AND p.id = d_info.product_id AND c.id = :categoryId";

}

