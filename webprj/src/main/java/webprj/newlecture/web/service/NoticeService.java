package webprj.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webprj.newlecture.web.notice.entity.Notice;
import webprj.newlecture.web.notice.entity.NoticeView;

public interface NoticeService {

	int removeNoticeAll(int[] ids);
	
	int pubNoticeAll(List<String> oids, List<String> cids);
	
	int pubNoticeAll(String oidsCSV, String cidsCSV);
	
	int closeNoticeAll(String ids);

	int insertNotice(Notice notice);
	
	int deletenotice(int id);
	
	int updateNotice(int id);
	
	List<Notice> getNoticeNewestList();
	
	List<NoticeView> getNoticeViewList();
	
	List<NoticeView> getNoticeViewList(int page);
	
	List<NoticeView> getPubNoticeViewList(String field, String query, int page);
	
	List<NoticeView> getNoticeViewList(String field, String query, int page);
	
	int getNoticeViewCount();
	
	int getNoticeViewCount(String field, String query);
	
	Notice getNotice(String id);
	
	Notice getNotice(int id);
	
	NoticeView getNextNoticeView(String id);
	
	NoticeView getNextNoticeView(int id);
	
	NoticeView getPrevNoticeView(String id);
	
	NoticeView getPrevNoticeView(int id);
}
