package kr.or.connect.reservation.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.controller.ReservationApiController;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.ReservationUserCommentService;

public class ServiceTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		CategoryService categoryService = ac.getBean(CategoryService.class);
		
		ReservationUserCommentService rc = ac.getBean(ReservationUserCommentService.class);
		CategoryService cs = ac.getBean(CategoryService.class);
		System.out.println(cs.getCategory())	;
		System.out.println(rc.selectAll(1));
	}
}
