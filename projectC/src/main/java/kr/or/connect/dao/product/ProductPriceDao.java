package kr.or.connect.dao.product;

import kr.or.connect.dto.product.ProductPrice;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.dao.product.sqls.ProductPriceDaoSqls.SELECT_BY_PRODUCT_ID;

@Repository
public class ProductPriceDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<ProductPrice> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

    public ProductPriceDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<ProductPrice> selectByProductId(int productId){
        Map<String, Integer> params = new HashMap<>();
        params.put("productId", productId);
        return jdbc.query(SELECT_BY_PRODUCT_ID, params, rowMapper);
    }
}
