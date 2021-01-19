package webprj.newlecture.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController /* implements Controller */ {

	/*
	 * @Override public ModelAndView handleRequest(HttpServletRequest request,
	 * HttpServletResponse response) throws Exception {
	 * //System.out.println("오나요!"); ModelAndView mv = new
	 * ModelAndView("root.index"); //mv.setViewName("index"); //webapp 이 root 경로
	 * localhost:8080 return mv; }
	 */
	
	//어노테이션을통해 함수가 컨트롤러로 변경
	@RequestMapping("index") //retrun 이 없으면 url 을 기준으로 viewresolver 를 통해 자동생성
	public String index(HttpServletResponse response) {
		return "root.index";
	}
	
	@RequestMapping("indexResponse")
	public void indexR(HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("Hello index!");
	}
	
	@RequestMapping("indexResponseBody")
	@ResponseBody
	public String indexRB() {
		return "Hello Index!!!";
	}
}
