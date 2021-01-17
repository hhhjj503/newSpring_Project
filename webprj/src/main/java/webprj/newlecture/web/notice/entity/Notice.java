package webprj.newlecture.web.notice.entity;

import java.util.Date;

public class Notice {
	
	private int id;
	private String title;
	private String writerId;
	private Date date;
	private int hit;
	private String files;
	private String content;
	private boolean pub;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	public Notice(int id, String title, String writerId, Date date, int hit, String files, String content, boolean pub) {
		super();
		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.date = date;
		this.hit = hit;
		this.files = files;
		this.content = content;
		this.pub = pub;
	}

	public boolean getPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writerId=" + writerId + ", date=" + date + ", hit=" + hit
				+ ", files=" + files + ", content=" + content + ", pub=" + pub + "]";
	}

	
	
}
