package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.ReservationUserComment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentSqls.*;


@Repository
public class ReservationUserCommentDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ReservationUserComment> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserComment.class);
    //private final int limit = 5;

    public ReservationUserCommentDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public int getScore(int productId){
        Map<String, Integer> params = new HashMap<>();
        params.put("productId", productId);
        if(jdbc.queryForObject(SCORE_AVG, params, Integer.class)==null) {
        	return 0;
        }
        return jdbc.queryForObject(SCORE_AVG, params, Integer.class);
    }
    
    public List<ReservationUserComment> getCommentByProductId(int productId, int start, int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        params.put("start", start);
        params.put("limit", limit);

        return jdbc.query(SELECT_BY_PRODUCT_ID, params, rowMapper);
    }

    public List<ReservationUserComment> getComment(int start, int limit){
        Map<String, Object> params = new HashMap<>();

        params.put("start", start);
        params.put("limit", limit);

        return jdbc.query(COMMENT_ALL, params, rowMapper);
    }

    public int getTotalCount(){
        return jdbc.queryForObject(COUNT_COMMENT_ALL, Collections.<String, Object>emptyMap(), Integer.class);
    }
}