package webprj.newlecture.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IndexController implements Controller /* extends HttpServletBean */ {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("오나요!");
		ModelAndView mv = new ModelAndView("root.index");
		//mv.setViewName("index");
		//webapp 이 root 경로 localhost:8080
		return mv;
	}
	//POJO
}
