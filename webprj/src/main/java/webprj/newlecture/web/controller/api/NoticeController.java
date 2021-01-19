package webprj.newlecture.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webprj.newlecture.web.notice.entity.NoticeView;
import webprj.newlecture.web.service.NoticeService;

@RestController("apiNoticeController") //IoC 에 생성되는 객체의 이름을 직접 지정
@RequestMapping("/api/")
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@RequestMapping("list")
	public List<NoticeView> list() {
		List<NoticeView> list = noticeService.getNoticeViewList();
		return list; //문서반환이 아니라 responsebody (특정문서를 찾는게 아니라 return 그대로를 담아서 반환) 같은 효과르 가짐
	}
}
