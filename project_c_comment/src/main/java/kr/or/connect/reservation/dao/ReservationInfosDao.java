package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ReservationInfoPriceSqls.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationInfos;

@Repository
public class ReservationInfosDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfos> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfos.class);
   
	public ReservationInfosDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
	
	public int insertInfo(Integer productId, Integer displayInfoId, Integer userId, String reservationDate) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("displayInfoId", displayInfoId);
		params.put("userId", userId);
		params.put("reservationDate", reservationDate);
		return jdbc.update(INSERT_RESERVATION_INFO, params);
	}
	
	
	
	public ReservationInfos searchUserId(Integer productId, Integer displayInfoId, Integer userId, String reservationDate){
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("displayInfoId", displayInfoId);
		params.put("userId", userId);
		params.put("reservationDate",reservationDate);
		return jdbc.queryForObject(SELECT_RESERVATION_INFO_ALL, params, rowMapper);
		
	}
	
	public List<ReservationInfos> selectByUserId(Long userId){
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		return jdbc.query(SELECT_RESERVATION_INFO_BY_ID, params, rowMapper);
	}
	
	public int deleteByreservationInfoId(int id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return jdbc.update(UPDATE_CANCEL_FLAG, params);
	}
} 
