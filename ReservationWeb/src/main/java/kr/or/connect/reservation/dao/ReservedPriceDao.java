package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.sqls.ReservationInfoSqls.DELETE_RESERVATION_PRICE;
import static kr.or.connect.reservation.sqls.ReservationInfoSqls.GET_RESERVATION_PRICE_BY_ID;

import java.util.*;

import javax.sql.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class ReservedPriceDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<PricesDto> rowMapper = BeanPropertyRowMapper.newInstance(PricesDto.class);

    public ReservedPriceDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
        		.withTableName("reservation_info_price")
                .usingGeneratedKeyColumns("id");
             
    }
    
    public int deleteReservationById(int reservationInfoId) {
		Map<String, ?> params = Collections.singletonMap("id", reservationInfoId);
		return jdbc.update(DELETE_RESERVATION_PRICE, params);
	}
    
    public int insertReservedPrices(PricesDto prices) {
    	
    	SqlParameterSource params = new BeanPropertySqlParameterSource(prices);
		return insertAction.executeAndReturnKey(params).intValue();
    	
    }
    
    public List<PricesDto> getReservationPrices(int reservationInfoId){
    	try {
    		Map<String, Integer> params = new HashMap<>();
    		//Map<String, ?> params = Collections.singletonMap("id", id);
    		params.put("id", reservationInfoId);
    		
			//System.out.print(params);
			return jdbc.query(GET_RESERVATION_PRICE_BY_ID,params,rowMapper);
			//return null;
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}

    }
}
