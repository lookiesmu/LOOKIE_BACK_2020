package kr.or.connect.reservation.main;

import java.util.*;

import org.springframework.context.*;
import org.springframework.context.annotation.*;

import kr.or.connect.reservation.config.*;
import kr.or.connect.reservation.dao.*;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

public class promotiontest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		MainPageService MainService = ac.getBean(MainPageService.class);
		ReservationInfoService ReservationService=ac.getBean(ReservationInfoService.class);
		
		CategoryDao categorydao = ac.getBean(CategoryDao.class);
		DisplayInfoDao displaydao= ac.getBean(DisplayInfoDao.class);
		ProductImageDao productImageDao=ac.getBean(ProductImageDao.class);
		DisplayInfoImageDao displayimagedao=ac.getBean(DisplayInfoImageDao.class);		
		CommentsDao commentsDao=ac.getBean(CommentsDao.class);
		ReservationInfoDao reservationDao=ac.getBean(ReservationInfoDao.class);
		ReservationsDto reservation=new ReservationsDto();
		
//		System.out.println(MainService.getPromotions());
//		System.out.println(categorydao.selectCategory(1));
//		System.out.println(MainService.getCategory(2));
//		System.out.println(categorydao.countAll());
//	
		
//		System.out.println(MainService.getProduct(1,0,4).size());
//		System.out.println(MainService.getProductImage(29));
//		System.out.println(MainService.getDisplayInfoImage(30));
//		System.out.println(MainService.getCommentImages(1));
//		System.out.println(MainService.getComments(1));
		System.out.println(ReservationService.getReservations("kimjinsu@connect.co.kr"));
		System.out.println(ReservationService.getTotalPrice(2));
		if(ReservationService.getReservationId("kim@connect.co.kr").size()!=0) {
			
			System.out.println("1");
		}
		else {
			System.out.println("null");
			
			
		}
		reservation.setProductId(1);
		reservation.setDisplayInfoId(1);
		reservation.setReservationName("테스트");
		reservation.setReservationTel("010-1234-1234");
		reservation.setReservationEmail("test@test.com");
		reservation.setReservationDate(new Date());
		reservation.setCancelFlag(0);
		reservation.setCreateDate(new Date());
		reservation.setModifyDate(new Date());
		int key=reservationDao.insertReservation(reservation);
		System.out.println(key);
		
		
		
		
	}

}
