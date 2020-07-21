package kr.or.connect.reservation.dao.sqls;

public class CategorySqls {
    public static final String ALL_CATEGORY = "SELECT c.id, c.name, count(*) as count " +
            "FROM category c, product p, display_info d_info " +
            "WHERE c.id = p.category_id AND p.id = d_info.product_id " +
            "GROUP BY c.id";

    public static final String GET_SIZE = "SELECT count(*) " +
          "FROM category";
}
