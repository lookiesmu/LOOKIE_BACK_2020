package kr.or.connect.config;

import kr.or.connect.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
//  Spring MVC와 Swagger2의 기본 설정이 자동으로 설정 된다.
@EnableWebMvc   // Spring MVC설정
@EnableSwagger2 // Swagger2설정
@ComponentScan(basePackages = {"kr.or.connect.controller"})

/* 공통 */
// 사용되지 않는 메소드, 변수는 삭제
// try catch의 catch문에는 stacktrace 호출 (EmptyResultDataAccessException가 발생하면 Dao에서 null을 반환하도록 했으므로, 제외)
// 필요없는 import 제거
// count, avg와 같은 숫자값 하나 조회하는 것을 제외하고 조회는 select를 사용.

/* dao, Service */
// TODO: queryForObject, queryForInt 등은 Dao에서 try catch하고, controller에서는 null만 체크하도록.
// createDate, modifyDate는 dao에서 삽입

/* dto */
// TODO : @JsonFormat 어노테이션 사용하지 않아도 일괄적으로 Date 객체의 형식을 지정할 수 있는 방법 찾기
// date 타입 필드는 @JsonFormat 어노테이션 사용

/* dao */
// sql문의 param이 한개인 경우는 Collections.singletonMap 사용
// sql문 상수 사용할 때 클래스 명시하기.
// insertAction, rowMapper 이름 구체적으로
// rowMapper, jdbc, insertAction에 final 붙이기

/* Service */
// 구현체에 @Override 빠져있는지 확인
// 트랜잭션 단위로 잘 나누어져 있는지 확인
// @Transactional은 service에서 사용, 메소드 모두 올바르게 적용되었는지 확인.
// @Transcational readOnly 제대로 적용되었는지 확인
// 서비스에서는 Dao가 아닌 다른 서비스 객체를 이용하도록. (대신 생성자가 아닌 Bean으로 Service 객체를 생성하는 경우, Service끼리 양방향 참조하는지 유의, 빈 생성시 오류가 발생 할 수 있으며, 설계상으로도 좋지 않은 구조라고 한다.)

/* Controller */
// TODO: 라우팅 할 수 있는 방법 찾기

public class WebMvcContextConfig implements WebMvcConfigurer {
    /*
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            registry.jsp("/WEB-INF/view/", ".jsp");
        }
    */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1048 * 1048 * 10);   // 최대 10MB

        return multipartResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");    // "/"로 요청이 들어오면 "/swagger-ui.html"로 리다이렉트
    }

    // DefaultServlet에 대한 설정을 합니다.
    // DispatcherServlet이 처리하지 못하는 URL은 DefaultServlet이 처리하게 됩니다.
    // 해당 설정이 없으면 자동 생성된 Swaager 페이지를 볼 수 없다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
    }

    /*
        Swagger2를 사용하려면 Docket객체를 Bean으로 설정해야 한다.
        Docker객체에는 어떤 경로의 Web API들을 자동으로 문서화 할 것인지에 대한 설정과 문서 설명에 대한 내용이 포함된다.
        Swagger 사용 시에는 Docket Bean을 품고있는 설정 클래스 1개가 기본으로 필요하다.
        Spring Boot에서는 이 기본적인 설정파일 1개로 Swagger 와 Swagger UI 를 함께 사용 가능하지만,
        Spring MVC 의 경우 Swagger UI 를 위한 별도의 설정이 필요하다.
        이는, Swagger UI 를 ResourceHandler에 수동으로 등록해야 하는 작업인데,
        Spring Boot 에서는 이를 자동으로 설정해주지만 Spring MVC 에서는 그렇지 않기 때문이다.
    */
    @Bean
    public Docket api() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder()
                .code(200)
                .message("OK")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(404)
                .message("The requested information could not be found")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(500)
                .message("Internal Server Error")
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
                .paths(PathSelectors.ant("/api/**"))// PathSelectors.any() 를 할경우 모든 경로가 다 사용된다. RestController가 아닌 것 까지 사용된다.
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages);    // API 응답 메시지
    }

     // API Info
    private ApiInfo apiInfo() {
        Contact contact = new Contact("길민호", "https://www.edwith.org", "rlfalsgh95@naver.com");
        return new ApiInfo("Reservation API", "You can get reservation information.", "Sample Doc 0.1v", "", contact, "", "/");
    }

    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        final int cachePeriod = 31556926;
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:META-INF/resourses/webjars").setCachePeriod(cachePeriod);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(cachePeriod);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(cachePeriod);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(cachePeriod);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
    }
    */
}