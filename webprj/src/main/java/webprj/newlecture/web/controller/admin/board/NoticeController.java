package webprj.newlecture.web.controller.admin.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	
	@Autowired
	private ServletContext cxt;
	
	@RequestMapping("list")
	public String list() {
		return "admin.board.notice.list";
	}
	
	@RequestMapping("detail")
	public String detail() {
		return "admin.board.notice.detail";
	}
	
	@GetMapping("reg")
	public String reg() {
		return "admin.board.notice.reg";
	}
	
	@PostMapping("reg")
	public String reg(String title, String content,
			@RequestParam(defaultValue = "") String category,
			@RequestParam(defaultValue = "")String[] foods,
			MultipartFile[] files, HttpServletRequest request ) {
		//특정메서드만 사용하는거라면 해당메서드안에만 선언
		
		for(String food : foods)
			System.out.println(food);
		System.out.println(foods);//배열은 데이터의 출력이 불가함
		
		for(MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			long size = file.getSize();
			System.out.printf("파일 이름 %s , \n파일 크기 %d",fileName,size);
			//ServletContext cxt = request.getServletContext();
			
			String webPath = "/static/upload";
			String realPath = cxt.getRealPath(webPath);
			System.out.println(realPath);
			
			File savePath = new File("realPath");
			if(!savePath.exists()) savePath.mkdirs();
			realPath += File.separator + fileName;
			System.out.println(realPath);
			
			File savedFile = new File(realPath);
			try {
				file.transferTo(savedFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:list";
		//return String.format("title : %s <br>  content : %s <br> category : %s",title,content, category);
	}
	
	@RequestMapping("edit")
	public String edit() {
		return "admin.board.notice.edit";
	}
	
	@RequestMapping("update")
	public String update() {
		return "admin.board.notice.update";
	}
	
	@RequestMapping("del")
	public String delete() {
		return "admin.board.notice.del";
	}
}
