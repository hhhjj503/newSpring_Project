package webprj.newlecture.web.controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import webprj.newlecture.web.notice.entity.NoticeView;
import webprj.newlecture.web.service.NoticeService;
import webprj.newlecture.web.service.JDBC.JDBCNoticeService;

public class ListController implements Controller {

	private NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService =  noticeService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("notice.list");
		List<NoticeView> list = noticeService.getNoticeViewList("TITLE", "", 1);
		mv.addObject("list", list);
		return mv;
	}

}
