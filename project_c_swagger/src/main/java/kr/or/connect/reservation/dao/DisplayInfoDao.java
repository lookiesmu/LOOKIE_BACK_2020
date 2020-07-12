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

import kr.or.connect.reservation.dto.Product;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

    public DisplayInfoDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<Product> getdisplayInfos(Integer categoryId, Integer start) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("categoryId", categoryId);
		params.put("start", start);
        return jdbc.query(SELECT_PRODUCT_ALLINFO, params, rowMapper);
    }
    
    public Integer getdisplayTotalCount(Integer categoryId) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("categoryId", categoryId);
    	return jdbc.queryForObject(SELECT_PRODUCT_TOTALCOUNT, params, Integer.class);
    }
    
    public Product getProductUnit(Integer displayInfoId) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("displayInfoId", displayInfoId);
    	return jdbc.queryForObject(SELECT_PRODUCT_UNIT, params, rowMapper);
    }
}
