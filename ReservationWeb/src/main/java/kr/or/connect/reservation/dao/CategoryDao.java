package kr.or.connect.reservation.dao;
import static kr.or.connect.reservation.sqls.MainPageSqls.COUNT_ALL_CATEGORY;
import static kr.or.connect.reservation.sqls.MainPageSqls.SELECT_CATEGORY;

import java.util.*;

import javax.sql.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<CategoryDto> rowMapper = BeanPropertyRowMapper.newInstance(CategoryDto.class);

    public CategoryDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("category")
                .usingGeneratedKeyColumns("id");
    }
    
    public List<CategoryDto> selectCategory(Integer id) {
    	
    	try {
    		Map<String, Integer> params = new HashMap<>();
    		//Map<String, ?> params = Collections.singletonMap("id", id);
    		params.put("id", id);
			//System.out.print(params);
			return jdbc.query(SELECT_CATEGORY, params, rowMapper);
			//return null;
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}
    }
    public List<CategoryDto> countAll() {
    	
    	return jdbc.query(COUNT_ALL_CATEGORY, Collections.<String, Object>emptyMap(), rowMapper);
    	
	
    	
    }

}
