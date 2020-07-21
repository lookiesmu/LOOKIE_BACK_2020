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

import kr.or.connect.reservation.dto.ProductPrices;



@Repository
public class ProductPricesDao {
	private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ProductPrices> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrices.class);
    
    public ProductPricesDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<ProductPrices> getProuctPrices(Integer displayInfoId){
    	Map<String, Integer> params = new HashMap<>();
    	params.put("displayInfoId", displayInfoId);
    	return jdbc.query(SELECT_PRODUCT_PRICE_LIST, params, rowMapper);
    }
    
}
