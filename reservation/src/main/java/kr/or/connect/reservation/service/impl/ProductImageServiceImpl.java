package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.ProductImageDao;
import kr.or.connect.reservation.dto.FileInfo;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.service.FileInfoService;
import kr.or.connect.reservation.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageDao productImageDao;

    @Autowired
    FileInfoService fileInfoService;

    public List<ProductImage> selectProductImagesByProductId(int productId){
        List<ProductImage> productImageList = new ArrayList<>();

        List<Map<String, Object>> productImageInfos = productImageDao.selectProductImageInfosByProductId(productId);

        for (Map<String, Object> productImageInfo: productImageInfos){
            int productImageId = (int)productImageInfo.get("product_image_id");
            int fileInfoId = (int)productImageInfo.get("file_id");
            String type = (String)productImageInfo.get("type");

            ProductImage productImage = (ProductImage)fileInfoService.selectByFileId((int)productImageInfo.get("file_id"), ProductImage.class);

            productImage.setProductId(productId);
            productImage.setProductImageId(productImageId);
            productImage.setFileInfoId(fileInfoId);
            productImage.setType(type);
            productImageList.add(productImage);
        }

        return productImageList;
    }
}
