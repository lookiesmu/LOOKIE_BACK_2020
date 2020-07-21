package kr.or.connect.reservation.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfoImage;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static kr.or.connect.reservation.dao.sqls.DisplayInfoImageSqls.DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID;


@Repository
public class DisplayInfoImageDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);

    public DisplayInfoImageDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public DisplayInfoImage getDisplayInfoImageInfosByDisplayInfoId(int displayInfoId){
        Map<String, Integer> params = new HashMap<>();
        params.put("displayInfoId", displayInfoId);
        return jdbc.queryForObject(DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID, params, rowMapper);
    }
    
}