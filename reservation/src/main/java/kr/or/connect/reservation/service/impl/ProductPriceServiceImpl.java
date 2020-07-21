package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.ProductPriceDao;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
    @Autowired
    ProductPriceDao productPriceDao;

    @Override
    public List<ProductPrice> getPriceByProductId(int productId) {
        return productPriceDao.getPriceByProductId(productId);
    }
}
