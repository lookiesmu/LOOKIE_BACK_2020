package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.ProductPrice;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.dao.sqls.ProductPriceSqls.PRICE_BY_PRODUCT_ID;

@Repository
public class ProductPriceDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ProductPrice> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

    public ProductPriceDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<ProductPrice> getPriceByProductId(int productId){
        Map<String, Integer> params = new HashMap<>();
        params.put("productId", productId);
        return jdbc.query(PRICE_BY_PRODUCT_ID, params, rowMapper);
    }
}