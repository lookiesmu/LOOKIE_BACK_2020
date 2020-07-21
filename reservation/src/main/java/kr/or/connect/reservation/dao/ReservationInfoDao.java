package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ReservationInfoSqls.GET_RESERVATION_BY_USERID;
import static kr.or.connect.reservation.dao.sqls.ReservationInfoSqls.GET_ID;
import static kr.or.connect.reservation.dao.sqls.ReservationInfoSqls.GET_SIZE;
import static kr.or.connect.reservation.dao.sqls.ReservationInfoSqls.POST_RESERVATION_INFO;
import static kr.or.connect.reservation.dao.sqls.ReservationInfoSqls.DELETE_BY_ID;
import static kr.or.connect.reservation.dao.sqls.ReservationInfoSqls.DELETE_PRICE;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationInfo;

@Repository
public class ReservationInfoDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ReservationInfo> rowMapper = new BeanPropertyRowMapper<>(ReservationInfo.class);

    public ReservationInfoDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<ReservationInfo> getReservationInfo(int userId){
    	Map<String, Integer> params = new HashMap<>();
    	params.put("userId", userId);
        return jdbc.query(GET_RESERVATION_BY_USERID, params, rowMapper);
    }
    
    public int getSize(int userId){
        Map<String, Integer> params = new HashMap<>();
        params.put("userId", userId);
        if(jdbc.queryForObject(GET_SIZE, params, Integer.class)==null) {
        	return 0;
        }
        return jdbc.queryForObject(GET_SIZE, params, Integer.class);
    }
    
    public int postReservation(int productId, int displayInfoId, int userId){
    	Map<String, Integer> params = new HashMap<>();
    	params.put("productId", productId);
    	params.put("displayInfoId", displayInfoId);
    	params.put("userId", userId);
    	
    	return jdbc.update(POST_RESERVATION_INFO, params);
    }
    
    public int getId(int productId, int displayInfoId, int userId){
    	Map<String, Integer> params = new HashMap<>();
    	params.put("productId", productId);
    	params.put("displayInfoId", displayInfoId);
    	params.put("userId", userId);
    	
    	return jdbc.queryForObject(GET_ID, params, Integer.class);
    }
    
    public int deletePrice(int id) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("id",id);
    	return jdbc.update(DELETE_PRICE, params);
    }
    
    public int deleteId(int id) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("id",id);
    	return jdbc.update(DELETE_BY_ID, params);
    }
}
