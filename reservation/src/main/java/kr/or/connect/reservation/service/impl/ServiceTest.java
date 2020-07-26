package kr.or.connect.reservation.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dao.PriceDao;
import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.CustomUserDetailsService;
import kr.or.connect.reservation.service.DisplayInfoImageService;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductImageService;
import kr.or.connect.reservation.service.ProductPriceService;
import kr.or.connect.reservation.service.ReservationInfoService;
import kr.or.connect.reservation.service.ReservationUserCommentImageService;
import kr.or.connect.reservation.service.ReservationUserCommentService;
import kr.or.connect.reservation.service.UserService;

public class ServiceTest {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		CategoryService categoryService = ac.getBean(CategoryService.class);
		
		ReservationUserCommentService rc = ac.getBean(ReservationUserCommentService.class);
		DisplayInfoService d = ac.getBean(DisplayInfoService.class);
		DisplayInfoImageService ds = ac.getBean(DisplayInfoImageService.class);
		ProductPriceService ps = ac.getBean(ProductPriceService.class);
		ProductImageService pi = ac.getBean(ProductImageService.class);
		ReservationInfoService rs = ac.getBean(ReservationInfoService.class);
		UserService us = ac.getBean(UserService.class);
		CustomUserDetailsService cd = ac.getBean(CustomUserDetailsService.class);
		
		ReservationUserCommentImageService ru = ac.getBean(ReservationUserCommentImageService.class);

		System.out.println(ru.getReservationImage(16));
		//System.out.println(us.getUserEntity("carami@connect.co.kr", "1234"));
	}
}
