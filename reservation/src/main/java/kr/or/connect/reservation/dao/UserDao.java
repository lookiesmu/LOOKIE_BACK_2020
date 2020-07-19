package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.dto.UserEntity;

import static kr.or.connect.reservation.dao.sqls.UserSqls.USER_ALL_BY_EMAIL;


@Repository
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
    private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
    
    public UserDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public User getUser(String email) {
    	try {
    		Map<String, Object> params = new HashMap<>();
        	params.put("email", email);
        	return jdbc.queryForObject(USER_ALL_BY_EMAIL, params, rowMapper);
    	}
    	catch(EmptyResultDataAccessException e){
    		return null;
    	}
    }
}
