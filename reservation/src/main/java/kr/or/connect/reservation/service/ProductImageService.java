package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ProductImage;

import java.util.List;

public interface ProductImageService {
    public ProductImage getProductImagesByProductId(int productId);
}
