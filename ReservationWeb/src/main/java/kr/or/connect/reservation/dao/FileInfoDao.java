package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.sqls.MainPageSqls.SELECT_FILE_BY_ID;

import java.util.*;

import javax.sql.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dto.*;

@Repository
public class FileInfoDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<FileInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(FileInfoDto.class);
    
    public FileInfoDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
        		.withTableName("file_info")
                .usingGeneratedKeyColumns("id");
    }
    
    public int insertImageFileInfo(FileInfoDto fileInfo) {
 	
    	SqlParameterSource params = new BeanPropertySqlParameterSource(fileInfo);
		return insertAction.executeAndReturnKey(params).intValue();
    }
    
    public List<FileInfoDto> selectFileById(Integer fileId) {
    	
    	try {
    		Map<String, Integer> params = new HashMap<>();
    		
    		params.put("id", fileId);
    		
			
			return jdbc.query(SELECT_FILE_BY_ID, params, rowMapper);
			
		}catch(EmptyResultDataAccessException e) {//해당 조건이 없을경우
			return null;
		}
    	
    }
}
