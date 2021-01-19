package webprj.newlecture.web.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import webprj.newlecture.web.notice.entity.NoticeView;
import webprj.newlecture.web.service.NoticeService;

@Controller
@RequestMapping("/customer/notice/")
public class NoticeController { //view 단의 customer 폴더안의 notice 폴더를 의미하는 클래스명
	
	@Autowired
	private NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService =  noticeService;
	}
	
	@RequestMapping("list")
	public String list() {
		List<NoticeView> list = noticeService.getNoticeViewList();
		
		return "notice.list";
	}
	
	@RequestMapping("detail")
	public String detail() {
		
		return "notice.detail"; // return 이 resolver 를 사용해서 문서를 출력한다는 의미!
	}
}
