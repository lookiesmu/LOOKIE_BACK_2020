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

import kr.or.connect.reservation.dto.ReservationInfoPrice;


@Repository
public class ReservationInfosPriceDao {
	private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ReservationInfoPrice> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfoPrice.class);
    
    public ReservationInfosPriceDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public int insertPrice(int reservationInfoId, int productPriceId, int count) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("reservationInfoId", reservationInfoId);
    	params.put("productPriceId", productPriceId);
    	params.put("count", count);
    	return jdbc.update(INSERT_RESERVATION_INFO_PRICE, params);
	}

    public List<ReservationInfoPrice> getPrice(int id){
    	Map<String, Integer> params = new HashMap<>();
    	params.put("id", id);
    	return jdbc.query(SELECT_PRICE, params, rowMapper);
}

    public Integer getSum(int id) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("id", id);
    	return jdbc.queryForObject(GET_SUM_PRICE, params, Integer.class);
    	}
}
