package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ProductPrice;

import java.util.List;

public interface ProductPriceService {
    public List<ProductPrice> getPriceByProductId(int productId);
}
