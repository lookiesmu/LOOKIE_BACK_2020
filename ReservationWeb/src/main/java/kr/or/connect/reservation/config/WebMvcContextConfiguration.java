package kr.or.connect.reservation.config;

import java.util.*;

import org.springframework.context.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.*;

import kr.or.connect.reservation.config.interceptor.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

@Configuration
@EnableWebMvc //Model View Controller (MVC 설정)
@EnableSwagger2 //Swagger 설정 추가
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
    
    @Override//인터셉터 등록
	public void addInterceptors(InterceptorRegistry registry) {
    		registry.addInterceptor(new LogInterceptor());
//    		모든 request가 아닌 특정한 req에만 실행할 interceptor는 
//    		registry.addInterceptor(new GuestBookInterceptor()).addPathPatterns("/auth/**");
    		
	}
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/"); //어떤 view 인지 찾아내게해줌
        resolver.setSuffix(".jsp");//확장자 선택
        return resolver;
    }
    
    
    //Swagger config
    @Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any()) // // RequestMapping으로 할당된 모든 URL 리스트를 검색하여 인식
				.paths(PathSelectors.ant("/api/**"))// PathSelectors.any() 를 할경우 Web api가 아닌 경로도 api path로 인식한다. .ant() 로 api 로사용할 경로를 넣어준다.
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(true);
				
	}
    
    //file upload resolver
    @Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
        return multipartResolver;
    }
    
    
    
    private ApiInfo apiInfo() {
    	//현재 swagger version : 2.9.2
        Contact contact = new Contact("이규홍", "https://github.com/eQueue", "latancy486@naver.com");
        List<VendorExtension> vendorExtensions = new ArrayList<>();
        ApiInfo apiInfo = new ApiInfo("ReservationWeb APi", "Show API", "v1.0", "localhost:8080/ReservationWeb/swagger-ui.html", contact, "", "", vendorExtensions);
        return apiInfo;
    }

}
