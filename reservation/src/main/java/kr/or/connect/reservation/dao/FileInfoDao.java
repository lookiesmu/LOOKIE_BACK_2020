package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.FileInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.HashAttributeSet;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.dao.sqls.FileInfoDaoSqls.SELECT_BY_ID;

@Repository
public class FileInfoDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<FileInfo> rowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);

    public FileInfoDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    @Transactional
    public Object selectByFileId(int fileId, Class<?> requiredType){
        Map<String, Integer> params = new HashMap<>();
        params.put("fileId", fileId);

        RowMapper requiredRowMapper = BeanPropertyRowMapper.newInstance(requiredType);
        return jdbc.queryForObject(SELECT_BY_ID, params, requiredRowMapper);
    }
}
