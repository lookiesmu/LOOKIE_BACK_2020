package kr.or.connect.reservation.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import kr.or.connect.reservation.dao.*;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@Service
public class MainPageServiceImpl implements MainPageService{
	@Autowired
	PromotionDao promotionDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	DisplayInfoDao displayinfoDao;
	@Autowired
	ProductImageDao productImageDao;
	@Autowired
	DisplayInfoImageDao displayinfoimageDao;
	@Autowired
	CommentImageDao commentimageDao;
	@Autowired
	CommentsDao commentsDao;
	@Autowired
	ProductPriceDao	productpriceDao;
	@Autowired
	FileInfoDao fileInfoDao;
	
	
	@Override
	@Transactional
	public List<PromotionDto> getPromotions() {
		// TODO Auto-generated method stub
		
		List<PromotionDto> list = promotionDao.selectAll(); //한페이지 몇개씩 보여줄지 로 지정.
		
		return list;
	}


	@Override
	@Transactional //readonly이다.
	public List<CategoryDto> getCategory(Integer id) {
		// TODO Auto-generated method stub
		List<CategoryDto> category = categoryDao.selectCategory(id);
		
		return category;
	}


	@Override
	@Transactional
	public List<CategoryDto> getCount() {
		// TODO Auto-generated method stub
		List<CategoryDto> category = categoryDao.countAll();
		
		return category;
	}


	@Override
	@Transactional
	public List<ProductDto> getProduct(Integer id, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		
		List<ProductDto> productlist;
		
		if(id==0) {
			productlist=productDao.selectAllProduct(start, limit);
		}
		else {
			productlist=productDao.selectProductByCategory(id, start, limit);
		}
		
		return productlist;
	}


	@Override
	@Transactional
	public List<DisplayInfoDto> getDisplayInfo(Integer displayInfoId) {
		// TODO Auto-generated method stub
		List<DisplayInfoDto> displayinfo=displayinfoDao.selectDisplayInfoById(displayInfoId);
		
		return displayinfo;
	}


	@Override
	@Transactional
	public List<ProductImageDto> getProductImage(Integer productId) {
		// TODO Auto-generated method stub
		List<ProductImageDto> productImage=productImageDao.selectProductImageById(productId);
		
		return productImage;
	}


	@Override
	@Transactional
	public List<DisplayInfoImageDto> getDisplayInfoImage(Integer displayInfoId) {
		// TODO Auto-generated method stub
		List<DisplayInfoImageDto> displayinfoimage=displayinfoimageDao.selectDisplayInfoImageById(displayInfoId);
		
		return displayinfoimage;
	}


	@Override
	@Transactional
	public List<CommentImageDto> getCommentImages(Integer commentId) {
		// TODO Auto-generated method stub
		List<CommentImageDto> commentimages=commentimageDao.selectCommentImageById(commentId);
		
		return commentimages;
	}


	@Override
	@Transactional
	public List<CommentsDto> getComments(Integer productId) {
		// TODO Auto-generated method stub
		List<CommentsDto> comments=commentsDao.selectCommentsById(productId);
		
		return comments;
	}


	@Override
	@Transactional
	public List<ProductPriceDto> getProductPrice(Integer productId) {
		// TODO Auto-generated method stub
		List<ProductPriceDto> productprices=productpriceDao.selectProductPriceById(productId);
				
		return productprices;
	}
	
	@Override
	public List<FileInfoDto> getFileInfo(int fileId) {
		// TODO Auto-generated method stub
		return fileInfoDao.selectFileById(fileId);
	}
	

}
