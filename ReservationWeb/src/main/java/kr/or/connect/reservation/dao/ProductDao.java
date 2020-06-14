package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationSqls.SELECT_ALL_PRODUCT;
import static kr.or.connect.reservation.dao.ReservationSqls.SELECT_PRODUCT;

import java.util.*;

import javax.sql.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<ProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);

    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        
    }
    
    public List<ProductDto> selectProductByCategory(Integer id, Integer start, Integer limit) {
    	
    	try {
    		Map<String, Integer> params = new HashMap<>();
    		//Map<String, ?> params = Collections.singletonMap("id", id);
    		params.put("id", id);
    		params.put("start", start);
    		params.put("limit", limit);
			//System.out.print(params);
			return jdbc.query(SELECT_PRODUCT, params, rowMapper);
			//return null;
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}
    }
    
public List<ProductDto> selectAllProduct(Integer start, Integer limit) {
		try {
			Map<String, Integer> params = new HashMap<>();
			params.put("start", start);
			params.put("limit", limit);
			
			return jdbc.query(SELECT_ALL_PRODUCT, params, rowMapper);
			
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			
			return null;
		}
    	
    	
	
    	
    }
}
