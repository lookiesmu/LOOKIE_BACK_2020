package kr.or.connect.reservation.dao.sqls;

public class PromotionSqls {
    public static final String PROMOTION_ALL = "SELECT promotion.id, promotion.product_id, product.category_id, category.name AS category_name, product.description, product_image.file_id " +
            "FROM category category, product product, promotion promotion, product_image product_image " +
            "WHERE category.id = product.category_id AND product.id = promotion.product_id AND product.id = product_image.product_id AND product_image.type = 'th'";

    public static final String COUNT_ALL = "SELECT count(*) FROM promotion";
}
