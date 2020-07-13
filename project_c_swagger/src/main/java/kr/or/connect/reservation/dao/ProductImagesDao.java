package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ProductImages;


@Repository
public class ProductImagesDao {
	private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ProductImages> rowMapper = BeanPropertyRowMapper.newInstance(ProductImages.class);

    public ProductImagesDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<ProductImages> getProductImage(Integer displayInfoId){
    	Map<String, Integer> params = new HashMap<>();
    	params.put("displayInfoId",displayInfoId);
    	return jdbc.query(SELECT_PRODUCT_IMAGES_LIST, params, rowMapper);
    }
}
