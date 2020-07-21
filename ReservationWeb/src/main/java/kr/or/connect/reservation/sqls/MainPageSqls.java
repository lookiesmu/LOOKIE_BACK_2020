package kr.or.connect.reservation.sqls;

public class MainPageSqls {
	public static final String SELECT_PROMOTION = "select promotion.id, save_file_name as ProductImageUrl "
			+ "from product_image, promotion,file_info "
			+ "where product_image.product_id=promotion.product_id and type=\"th\" and product_image.file_id=file_info.id";
	
	public static final String COUNT_ALL_CATEGORY="select count(category.id) as count "
			+ "from category,product "
			+ "where product.category_id=category.id";
	
	public static final String SELECT_CATEGORY = "select count(category.id) as count, category.id, category.name "
			+ "from category,product "
			+ "where product.category_id=category.id "
			+ "and category.id= :id "
			+ "group by category.id";
	
	public static final String SELECT_PRODUCT = "select display_info.id as displayInfoId, product.id as productId, description as productDescription, place_name, content as productContent, save_file_name as productImageUrl "
			+ "from product, display_info, product_image, file_info "
			+ "where product.id=display_info.product_id and product_image.product_id=product.id and type=\"th\" and product_image.file_id=file_info.id and category_id= :id "
			+ "limit :start, :limit";
	
	public static final String SELECT_ALL_PRODUCT ="select product.id as displayInfoId, product.id as productId, description as productDescription, place_name, content as productContent, save_file_name as productImageUrl "
			+ "from product, display_info, product_image, file_info "
			+ "where product.id=display_info.id and product_image.product_id=product.id and type=\"th\" and product_image.file_id=file_info.id "
			+ "limit :start, :limit";
	
	
}
