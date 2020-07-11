package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class ReservationApiController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    DisplayInfoService displayInfoService;
    @Autowired
    PromotionService promotionService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ReservationUserCommentService reservationUserCommentService;
    @Autowired
    ProductPriceService productPriceService;
    @Autowired
    DisplayInfoImageService displayInfoImageService;
    
    
    @GetMapping(path = "/categories")
    public Map<String, Object> getCategories(){
        Map<String, Object> map = new LinkedHashMap<>();

        List<Category> items = categoryService.getCategory();
        int size = categoryService.getCount();

        map.put("size", size);
        map.put("items", items);

        return map;
    }
    
    @GetMapping(path = "/displayinfos")
    public Map<String, Object> getDisplayInfos(@RequestParam(name ="categoryId", required = false, defaultValue = "0") int categoryId, @RequestParam (name = "start", required = false, defaultValue = "1") int start){
        Map<String, Object> map = new LinkedHashMap<>();
        List<DisplayInfo> displayInfos = null;
        int displayCount = 0;
        int totalCount = 0;

        try{
             displayInfos = displayInfoService.selectDisplayInfos(start - 1, categoryId);
             displayCount = displayInfos.size();

             if(categoryId == 0)
                 totalCount = displayInfoService.getTotalCount();
             else
                 totalCount = displayInfoService.getCountByCategoryId(categoryId);

            map.put("totalCount", totalCount);
            map.put("productCount", displayCount);
            map.put("products", displayInfos);
        }catch(Exception e){
            e.printStackTrace();
        }

        return map;
    }
    
    @GetMapping(path = "/displayinfos/{displayId}")
    public Map<String, Object> getDisplayinfosById(@PathVariable (name = "displayId") int displayId){
        Map<String, Object> map = new LinkedHashMap<>();

        try{
            DisplayInfo displayInfo = displayInfoService.selectDisplayInfoById(displayId);

            int productId = displayInfo.getId();
            List<ProductImage> productImageList = productImageService.selectProductImagesByProductId(productId);
            int avgScore = reservationUserCommentService.getScore(productId);

            List<ProductPrice> productPriceList = productPriceService.selectByProductId(productId);
            List<DisplayInfoImage> displayInfoImageList = displayInfoImageService.selectDisplayInfoImagesByDisplayInfoId(displayId);

            map.put("product", displayInfo);
            map.put("productImages", productImageList);
            map.put("displayInfoImages", displayInfoImageList);
            map.put("avgScore", avgScore);
            map.put("productPrices", productPriceList);
        }catch(Exception e){
            e.printStackTrace();
        }

        return map;
    }
    
    @GetMapping(path = "/promotions")
    public Map<String, Object> getPromotions(){
        Map<String, Object> map = new LinkedHashMap<>();

        int size = promotionService.getCount();
        List<Promotion> promotions = promotionService.selectAll();

        map.put("size", size);
        map.put("items", promotions);

        return map;
    }
    
    @GetMapping(path = "/comments")
    public Map<String, Object> getComments(@RequestParam(name ="productId", required = false, defaultValue = "-1") int productId, @RequestParam (name = "start", required = false, defaultValue = "1") int start){
        Map<String, Object> map = new LinkedHashMap<>();
        List<ReservationUserComment> commentList = null;

        try{
            int totalCount = reservationUserCommentService.getTotalCount();

            if(productId == -1) {
                commentList = reservationUserCommentService.selectAll(start - 1);
            }else{
                commentList = reservationUserCommentService.selectByProductId(productId, start - 1);
            }
            int commentCount = commentList.size();

            map.put("totalCount", totalCount);
            map.put("commentCount", commentCount);
            map.put("reservationUserComments", commentList);
        }catch(Exception e){
            e.printStackTrace();
        }

        return map;
    }
}

