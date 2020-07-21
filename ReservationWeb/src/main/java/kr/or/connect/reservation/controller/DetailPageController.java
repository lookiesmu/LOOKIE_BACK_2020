package kr.or.connect.reservation.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/detail")
public class DetailPageController {
	
	
	@GetMapping
	public String detail(@RequestParam(name="id", required=true) int displayInfoId, HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws ServletException, IOException {
		
		String test_email="xxx@test.com";
		
		session.setAttribute("loginInfo", test_email);
		session.setAttribute("displayInfoId", displayInfoId);
//		request.setAttribute("displayInfoId", displayInfoId);
//		RequestDispatcher rd = request.getRequestDispatcher("/detailpage.jsp");
//		rd.forward(request, response);
		
					
		return "detailpage";
	}
	
	@GetMapping(path="/review")
	public String review(@RequestParam(name="id", required=true) int displayInfoId) {
		
		
		
		return "review";
		
	}
	
	@GetMapping(path="/reserve")
	public String reserve(@RequestParam(name="id", required=true) int displayInfoId) {
		
		return "reservepage";
	}

}
