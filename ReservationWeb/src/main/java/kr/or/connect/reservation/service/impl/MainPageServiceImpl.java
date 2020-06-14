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
	
	

}
