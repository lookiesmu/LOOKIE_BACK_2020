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
		
		System.out.println(MainService.getPromotions());
		System.out.println(categorydao.selectCategory(1));
		System.out.println(MainService.getCategory(2));
		System.out.println(categorydao.countAll());
		
		System.out.println(MainService.getProduct(1,20,4).size());
		
		
		
		
	}

}
