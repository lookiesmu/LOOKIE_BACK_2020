package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.sqls.DisplayInfoSqls.SELECT_DISPLAY_INFO_IMAGE;

import java.util.*;

import javax.sql.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class DisplayInfoImageDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<DisplayInfoImageDto> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImageDto.class);

    public DisplayInfoImageDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        
    }
    
    public List<DisplayInfoImageDto> selectDisplayInfoImageById(Integer displayInfoId){
		
    	try {
    		Map<String, Integer> params = new HashMap<>();
    		//Map<String, ?> params = Collections.singletonMap("id", id);
    		params.put("id", displayInfoId);
    		
			//System.out.print(params);
			return jdbc.query(SELECT_DISPLAY_INFO_IMAGE, params, rowMapper);
			//return null;
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}
    	
    }
}
