package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

import static kr.or.connect.reservation.dao.sqls.CategorySqls.ALL_CATEGORY;
import static kr.or.connect.reservation.dao.sqls.CategorySqls.GET_SIZE;

@Service
public class CategoryDao {
    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

    public CategoryDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Category> getCategory(){
        return jdbc.query(ALL_CATEGORY, rowMapper);
    }

    public int getCount(){
        return jdbc.queryForObject(GET_SIZE, Collections.<String, Object>emptyMap(), Integer.class);
    }
}