package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import static kr.or.connect.reservation.dao.sqls.ReservationSqls.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class FileInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public FileInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int insertFileInfo(String fileName) {
		Map<String, Object> params = new HashMap<>();
		String saveFileName = "c:/tmp/"+fileName;
		String contentType = "image/png";
		
		params.put("fileName", fileName);
		params.put("saveFileName", saveFileName);
		params.put("contentType", contentType);
		return jdbc.update(INSERT_FILE_INFO, params);
	}
	
	public int getFileId(String fileName) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("fileName", fileName);
		return jdbc.queryForObject(SELECT_FILE_INFO_ID_BY_FILE_NAME, params, Integer.class);
	}
}
