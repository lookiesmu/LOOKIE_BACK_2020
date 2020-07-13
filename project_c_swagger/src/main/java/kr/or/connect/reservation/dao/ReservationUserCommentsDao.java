package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationUserComments;


@Repository
public class ReservationUserCommentsDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserComments> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserComments.class);
   
	public ReservationUserCommentsDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
	
	public List<ReservationUserComments> getReservationUserComments(Integer productId, Integer start){
		Map<String, Integer> params = new HashMap<>();
    	params.put("productId", productId);
    	params.put("start", start);
		return jdbc.query(SELECT_USER_COMMENTS, params, rowMapper);
	}
	
	public Integer getAVGScore(Integer productId) {
		Map<String, Integer> params = new HashMap<>();
    	params.put("productId", productId);
    	return jdbc.queryForObject(AVG_COMMENT_SCORE, params, Integer.class);
	}
	
	public List<ReservationUserComments> getUserTotalCount() {
    	return jdbc.query(COUNT_USER_COMMENTS, rowMapper);
	}
}
