package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.ProductImageDao;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageDao productImageDao;

    public ProductImage getProductImagesByProductId(int productId){
        return productImageDao.getProductImageInfosByProductId(productId);
    }
}
