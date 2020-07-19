package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.Promotion;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

import static kr.or.connect.reservation.dao.sqls.PromotionSqls.PROMOTION_ALL;
import static kr.or.connect.reservation.dao.sqls.PromotionSqls.COUNT_ALL;

@Repository
public class PromotionDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Promotion> rowMapper = new BeanPropertyRowMapper<>(Promotion.class);

    public PromotionDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Promotion> getPromotion(){
        return jdbc.query(PROMOTION_ALL, rowMapper);
    }

    public int getCount(){
        return jdbc.queryForObject(COUNT_ALL, Collections.<String, Object>emptyMap(), Integer.class);
    }
}