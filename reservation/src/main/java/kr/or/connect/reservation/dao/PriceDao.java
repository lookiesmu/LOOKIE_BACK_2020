package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ReservationInfoSqls.POST_RESERVATION_PRICE;

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
public class PriceDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Price> rowMapper = new BeanPropertyRowMapper<>(Price.class);

    public PriceDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    

    public int postPrice(int reservationInfoId, int productPriceId, int count){
    	Map<String, Integer> params = new HashMap<>();
    	    	
    	params.put("reservationInfoId", reservationInfoId);
    	params.put("productPriceId", productPriceId);
    	params.put("count", count);
    	
    	return jdbc.update(POST_RESERVATION_PRICE, params);
    	//return jdbc.queryForObject(POST_RESERVATION_PRICE, params, rowMapper);
    }
}

//public Long insert(Guestbook guestbook) {
//	SqlParameterSource params = new BeanPropertySqlParameterSource(guestbook);
//	return insertAction.executeAndReturnKey(params).longValue();
//}