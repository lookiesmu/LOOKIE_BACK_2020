package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.sqls.DisplayInfoSqls.SELECT_COMMENT_IMAGES;

import java.util.*;

import javax.sql.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class CommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<CommentImageDto> rowMapper = BeanPropertyRowMapper.newInstance(CommentImageDto.class);

    public CommentImageDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        
    }
    
    public List<CommentImageDto> selectCommentImageById(Integer commentId){
		
    	try {
    		Map<String, Integer> params = new HashMap<>();
    		
    		params.put("id", commentId);
    		
			
			return jdbc.query(SELECT_COMMENT_IMAGES, params, rowMapper);
			
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}
    	
    }
}
