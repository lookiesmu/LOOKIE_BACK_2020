package kr.or.connect.reservation.sqls;

public class ReservationSqls {
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
	
	public static final String SELECT_PRODUCT = "select product.id as displayInfoId, product.id as productId, description as productDescription, place_name, content as productContent, save_file_name as productImageUrl "
			+ "from product, display_info, product_image, file_info "
			+ "where product.id=display_info.id and product_image.product_id=product.id and type=\"th\" and product_image.file_id=file_info.id and category_id= :id "
			+ "limit :start, :limit";
	
	public static final String SELECT_ALL_PRODUCT ="select product.id as displayInfoId, product.id as productId, description as productDescription, place_name, content as productContent, save_file_name as productImageUrl "
			+ "from product, display_info, product_image, file_info "
			+ "where product.id=display_info.id and product_image.product_id=product.id and type=\"th\" and product_image.file_id=file_info.id "
			+ "limit :start, :limit";
	
	public static final String SELECT_DISPLAY_INFO_BY_ID="select display_info.product_id as productId, category.id as categoryId, display_info.id as displayInfoId, category.name as categoryName, product.description as productDescription, product.content as productContent, product.event as productEvent, display_info.opening_hours as openingHours, display_info.place_name as placeName, display_info.place_lot as placeLot, display_info.place_street as placeStreet, display_info.tel as telephone, display_info.homepage, display_info.email, display_info.create_date as createDate, display_info.modify_date as modifyDate " 
			+ "from display_info,product,category " 
			+ "where product.category_id=category.id and display_info.product_id=product.id and display_info.id= :id";
	
	public static final String SELECT_PRODUCT_IMAGE_BY_ID="select product_image.product_id as productId,  product_image.id as productImageId, product_image.type, file_info.id as fileInfoId, file_info.file_name as fileName, file_info.save_file_name as saveFileName, file_info.content_type as contentType, file_info.delete_flag as deleteFlag, file_info.create_date as createDate, file_info.modify_date as modifyDate "
			+ "from product_image,file_info "
			+ "where product_image.file_id=file_info.id and product_image.type=\"ma\" "
			+ "and product_image.product_id= :id";
	
}
