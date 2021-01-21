package webprj.newlecture.web.controller.admin.board;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String insert(String title, String content, @RequestParam(defaultValue = "") String category, @RequestParam(defaultValue = "")String[] foods) {
		System.out.println(foods);//배열은 데이터의 출력이 불가함
		for(String food : foods)
			System.out.println(food);
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
