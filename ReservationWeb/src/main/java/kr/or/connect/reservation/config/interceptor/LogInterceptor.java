package kr.or.connect.reservation.config.interceptor;

import javax.servlet.http.*;

import org.slf4j.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.*;

public class LogInterceptor extends HandlerInterceptorAdapter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println(handler.toString() + " 가 종료되었습니다.  " + modelAndView.getViewName() + "을 view로 사용합니다.");
		if(modelAndView==null) {
			logger.debug("{} 가종료되었습니다.", handler.toString());
		}else {
			logger.debug("{} 가종료되었습니다. {} 를 view로 사용합니다.", handler.toString(), modelAndView.getViewName());
		}
		
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println(handler.toString() + " 를 호출했습니다.");
		logger.debug("{} 를 호출했습니다.", handler.toString());
		return true;
	}

	
}
