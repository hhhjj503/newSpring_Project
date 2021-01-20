package webprj.newlecture.web.controller.admin.board;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	
	@RequestMapping("list")
	public String list() {
		return "";
	}
	
	@RequestMapping("detail")
	public String detail() {
		return "";
	}
	
	@RequestMapping("reg")
	@ResponseBody
	public String insert(String title, String content, String category) {
		return String.format("title : %s <br>  content : %s <br> category : %s",title,content, category);
	}
	
	@RequestMapping("update")
	public String update() {
		return "";
	}
	
	@RequestMapping("del")
	public String delete() {
		return "";
	}
}
