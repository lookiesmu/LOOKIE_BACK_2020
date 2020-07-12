package kr.or.connect.reservation.main;

import org.springframework.context.*;
import org.springframework.context.annotation.*;

import kr.or.connect.reservation.config.*;
import kr.or.connect.reservation.dao.*;
import kr.or.connect.reservation.service.*;

public class promotiontest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		MainPageService MainService = ac.getBean(MainPageService.class);
		CategoryDao categorydao = ac.getBean(CategoryDao.class);
		DisplayInfoDao displaydao= ac.getBean(DisplayInfoDao.class);
		ProductImageDao productImageDao=ac.getBean(ProductImageDao.class);
		DisplayInfoImageDao displayimagedao=ac.getBean(DisplayInfoImageDao.class);		
		CommentsDao commentsDao=ac.getBean(CommentsDao.class);
		
//		System.out.println(MainService.getPromotions());
//		System.out.println(categorydao.selectCategory(1));
//		System.out.println(MainService.getCategory(2));
//		System.out.println(categorydao.countAll());
//	
		
//		System.out.println(MainService.getProduct(1,0,4).size());
//		System.out.println(MainService.getProductImage(29));
//		System.out.println(MainService.getDisplayInfoImage(30));
//		System.out.println(MainService.getCommentImages(1));
		System.out.println(MainService.getComments(1));
		
		
		
		
	}

}
