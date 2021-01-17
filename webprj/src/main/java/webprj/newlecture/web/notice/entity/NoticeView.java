package webprj.newlecture.web.notice.entity;

import java.util.Date;

public class NoticeView extends Notice {

	private int cmtcnt;
	private boolean pub;
	
	public NoticeView() {
	}
	
	public NoticeView(int id, String title, String writerId, Date date, int hit, String files, int cmtcnt, boolean pub) {
		super(id, title, writerId, date, hit, files, null, pub);
		this.cmtcnt = cmtcnt;
	}

	public int getCmtcnt() {
		return cmtcnt;
	}

	public void setCmtcnt(int cmtcnt) {
		this.cmtcnt = cmtcnt;
	}
	
}
