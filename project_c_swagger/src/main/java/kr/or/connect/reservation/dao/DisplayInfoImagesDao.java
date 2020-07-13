package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfoImages;


@Repository
public class DisplayInfoImagesDao {
	private NamedParameterJdbcTemplate jdbc;
    private RowMapper<DisplayInfoImages> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImages.class);
    
    public DisplayInfoImagesDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<DisplayInfoImages> getDisplayInfoImages(Integer displayInfoId) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("displayInfoId", displayInfoId);
    	return jdbc.query(SELECT_DISPLAY_INFO_IMAGES_LIST, params, rowMapper);
    }
    
    
}
