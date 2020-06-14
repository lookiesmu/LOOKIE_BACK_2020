package kr.or.connect.reservation.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"kr.or.connect.reservation.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/img_map/**").addResourceLocations("/img_map/").setCachePeriod(31556926);
        registry.addResourceHandler("/font/**").addResourceLocations("/font/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
 
    // default servlet handler를 사용하게 합니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
   
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    		System.out.println("addViewControllers가 호출됩니다. ");
        registry.addViewController("/").setViewName("mainpage"); //특정 url에 대한 처리. 컨트롤러 클래스 작성하지 않고 매핑할수 있도록 해줌
        //main만 가지고 view를 찾을수 없음. 밑의 bean필요
    }
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/"); //어떤 view 인지 찾아내게해줌
        resolver.setSuffix(".jsp");//확장자 선택
        return resolver;
    }

}
