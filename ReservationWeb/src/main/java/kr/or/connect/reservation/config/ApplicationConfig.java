package kr.or.connect.reservation.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = { "kr.or.connect.reservation.dao",  "kr.or.connect.reservation.service"})
@Import({ DBConfig.class }) //패키지 스캔한곳에 import 해라.
public class ApplicationConfig {

}