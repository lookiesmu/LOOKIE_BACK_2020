package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ReservationSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<Product> selectAll(Integer categoryId, Integer start, Integer limit) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
        return jdbc.query(SELECT_PRODUCT_ALLINFO, params, rowMapper);
    }
    public Product selectProductById(int id) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("id", id);
    	return jdbc.queryForObject(SELECT_PRODUCT_BY_PRODUCT_ID, params, rowMapper);
    }
    
}
