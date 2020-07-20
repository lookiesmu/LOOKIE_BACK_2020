package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.sqls.ReservationInfoSqls.CANCEL_RESERVATION_BY_ID;
import static kr.or.connect.reservation.sqls.ReservationInfoSqls.GET_RESERVATION_BY_ID;
import static kr.or.connect.reservation.sqls.ReservationInfoSqls.GET_TOTAL_PRICE;
import static kr.or.connect.reservation.sqls.ReservationInfoSqls.SELECT_RESERVATIONS_BY_EMAIL;

import java.util.*;

import javax.sql.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<ReservationsDto> rowMapper = BeanPropertyRowMapper.newInstance(ReservationsDto.class);
    private RowMapper<totalPriceDto> priceMapper=BeanPropertyRowMapper.newInstance(totalPriceDto.class);


    public ReservationInfoDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
        		.withTableName("reservation_info")
                .usingGeneratedKeyColumns("id");
             
    }
    
//    public int insertReservedPrices(PricesDto prices) {
//    	this.insertAction = insertAction
//                .withTableName("reservation_info_price")
//                .usingGeneratedKeyColumns("id");
//    	SqlParameterSource params = new BeanPropertySqlParameterSource(prices);
//		return insertAction.executeAndReturnKey(params).intValue();
//    	
//    }
    
    public int cancelReservationById(int id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(CANCEL_RESERVATION_BY_ID, params);
	}
    
    public int insertReservation(ReservationsDto reservation) {
   
    	
    	SqlParameterSource params = new BeanPropertySqlParameterSource(reservation);
		return insertAction.executeAndReturnKey(params).intValue();
    }
    
    public List<ReservationsDto> selectReservationInfoByEmail(String email){
		
    	try {
    		Map<String, String> params = new HashMap<>();
    		//Map<String, ?> params = Collections.singletonMap("id", id);
    		params.put("email", email);
    		
			//System.out.print(params);
			return jdbc.query(SELECT_RESERVATIONS_BY_EMAIL, params, rowMapper);
			//return null;
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}
    	
    }
    
    public List<totalPriceDto> getTotalPriceById(int reservationInfoId) {
    	try {
    		Map<String, Integer> params = new HashMap<>();
    		//Map<String, ?> params = Collections.singletonMap("id", id);
    		params.put("id", reservationInfoId);
    		
			//System.out.print(params);
    		
			return jdbc.query(GET_TOTAL_PRICE, params, priceMapper);
			//return null;
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}
    	
    }
    
    public List<ReservationsDto> getReservationId(String email) {
    	try {
    		Map<String, String> params = new HashMap<>();
    		//Map<String, ?> params = Collections.singletonMap("id", id);
    		params.put("email", email);
    		
			//System.out.print(params);
			return jdbc.query(SELECT_RESERVATIONS_BY_EMAIL,params,rowMapper);
			//return null;
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}
    }
    
    public List<ReservationsDto> getReservationById(int reservationInfoId){
    	try {
    		Map<String, Integer> params = new HashMap<>();
    		//Map<String, ?> params = Collections.singletonMap("id", id);
    		params.put("id", reservationInfoId);
    		
			//System.out.print(params);
    		
			return jdbc.query(GET_RESERVATION_BY_ID, params, rowMapper);
			//return null;
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}

    	
    	
    }
}
