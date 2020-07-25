package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.dao.sqls.ReservationSqls.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationUserCommentImages;



@Repository
public class ReservationUserCommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserCommentImages> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserCommentImages.class);
	
	public ReservationUserCommentImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ReservationUserCommentImages> getReservationUserCommentImages(){
		return jdbc.query(SELECT_RESERVATION_USER_COMMENT_IMAGE, rowMapper);
	}
	
	public ReservationUserCommentImages getReservationUserCommentImagesFile(int fileId) {
		Map<String, Object> params = new HashMap<>();
		params.put("fileId", fileId);
		return jdbc.queryForObject(SELECT_FILE_INFO_BY_FILE_ID, params, rowMapper);
	}
	
}
