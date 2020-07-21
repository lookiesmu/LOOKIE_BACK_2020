package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.DisplayInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.dao.sqls.DisplayInfoSqls.*;

@Repository
public class DisplayInfoDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);

    public DisplayInfoDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    @Transactional
    public List<DisplayInfo> getDisplayInfos(int start, int limit, int categoryId){
        Map<String, Integer> params = new HashMap<>();
        List<DisplayInfo> result = null;

        params.put("start", start);
        params.put("limit", limit);

        if(categoryId == 0){
            result = jdbc.query(DISPLAY_ALL, params, rowMapper);
        }else{
            params.put("categoryId", categoryId);
            result = jdbc.query(DISPLAY_BY_CATEGORYID_LIMIT, params, rowMapper);
        }

        return result;
    }

    public DisplayInfo getDisplayInfoById(int displayId){
        Map<String, Integer> params = new HashMap<>();
        params.put("displayId", displayId);
        return jdbc.queryForObject(SELECT_DISPLAY_BY_DISPLAYID, params, rowMapper);
    }

    public int getTotalCount(){
        return jdbc.queryForObject(COUNT_ALL, Collections.<String, Object>emptyMap(), Integer.class);
    }

    public int getCountByCategoryId(int categoryId){
        Map<String, Integer> params = new HashMap<>();
        params.put("categoryId", categoryId);

        return jdbc.queryForObject(COUNT_CATEGORYID, params, Integer.class);
    }

}