package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.sqls.DisplayInfoSqls.SELECT_COMMENTS;

import java.util.*;

import javax.sql.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class CommentsDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<CommentsDto> rowMapper = BeanPropertyRowMapper.newInstance(CommentsDto.class);

    public CommentsDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
        		.withTableName("reservation_user_comment")
                .usingGeneratedKeyColumns("id");
        
    }
    
    public int insertComment(CommentsDto comment) {
   
    	
    	SqlParameterSource params = new BeanPropertySqlParameterSource(comment);
		return insertAction.executeAndReturnKey(params).intValue();
    }
    
    public List<CommentsDto> selectCommentsById(Integer productId){
		
    	try {
    		Map<String, Integer> params = new HashMap<>();
    		
    		params.put("id", productId);
    		
			
			return jdbc.query(SELECT_COMMENTS, params, rowMapper);
			
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}
    	
    }
}
