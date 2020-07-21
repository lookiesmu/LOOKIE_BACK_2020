package kr.or.connect.reservation.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.ProductImage;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.dao.sqls.DisplayInfoSqls.SELECT_DISPLAY_BY_DISPLAYID;
import static kr.or.connect.reservation.dao.sqls.ProductImageSqls.PRODUCT_IMAGE_INFO_BY_PRODUCTID;

@Repository
public class ProductImageDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);

    public ProductImageDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public ProductImage getProductImageInfosByProductId(int productId){
        Map<String, Integer> params = new HashMap<>();
        params.put("productId", productId);
        return jdbc.queryForObject(PRODUCT_IMAGE_INFO_BY_PRODUCTID, params, rowMapper);
    }
}

