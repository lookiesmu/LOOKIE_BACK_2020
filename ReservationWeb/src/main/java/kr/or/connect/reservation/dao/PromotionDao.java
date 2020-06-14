package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationSqls.SELECT_PROMOTION;

import java.util.*;

import javax.sql.*;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<PromotionDto> rowMapper = BeanPropertyRowMapper.newInstance(PromotionDto.class);

    public PromotionDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("promotion")
                .usingGeneratedKeyColumns("id");
    }
    
    public List<PromotionDto> selectAll() {
    		
        return jdbc.query(SELECT_PROMOTION, Collections.<String, Object>emptyMap(), rowMapper);
    }
}
