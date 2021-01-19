package webprj.newlecture.web.service.JDBC;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webprj.newlecture.web.notice.entity.Notice;
import webprj.newlecture.web.notice.entity.NoticeView;
import webprj.newlecture.web.service.NoticeService;

@Service  // @Component ==  @Controller @Service @Repository
public class JDBCNoticeService implements NoticeService  {
	 
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {this.dataSource = dataSource;}

	public int removeNoticeAll(int[] ids) {
		
		//String url = "jdbc:mysql://localhost:3306/testDB?serverTimezone=Asia/Seoul&useSSL=false";
		//String uid = "root";
		//String upwd ="root"; 
		//String driver = "com.mysql.cj.jdbc.Driver";
		String params = "";
		
		for(int i = 0 ; i < ids.length; i++) {
			if(i < ids.length-1) params += ids[i] + ",";
			else params += ids[i];
		}
			
		String sql = "delete from notice where id in("+ params +")";
		
		// 등차수열 : 1+(page-1)*10
		Connection con = null;
		Statement pst = null;
		int result = 0;
		
		try {
			//Class.forName(driver);
			//con = DriverManager.getConnection(url, uid, upwd);
			con = dataSource.getConnection();
			pst = con.createStatement();
			
			result = pst.executeUpdate(sql);
			
			} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public int pubNoticeAll(int[] oids, int[] cids) {
		
		List<String> oidsList = new ArrayList<>();
		for(int i = 0; i < oids.length ; i++)
			oidsList.add(String.valueOf(oids[i]));
		
		List<String> cidsList = new ArrayList<>();
		for(int i = 0; i < cids.length ; i++)
			cidsList.add(String.valueOf(oids[i]));
		
		
		return pubNoticeAll(oidsList, cidsList);
	}
	
	public int pubNoticeAll(List<String> oids, List<String> cids) {
		
		String oidsCSV = String.join(",", oids);
		String cidsCSV = String.join(",", cids);
		
		return pubNoticeAll(oidsCSV, cidsCSV);
	}
	
	public int pubNoticeAll(String oidsCSV, String cidsCSV) {
				
		String params = "";
		String pubSql = String.format("update notice set pub = true where id in(%s)", oidsCSV);
		String closeSql = String.format("update notice set pub = false where id in(%s)", cidsCSV);
		
		// 등차수열 : 1+(page-1)*10
		Connection con = null;
		Statement openQ = null;
		Statement closeQ = null;
		int open = 0;
		int close = 0;
		
		try {
			//Class.forName(driver);
			//con = DriverManager.getConnection(url, uid, upwd);
			con = dataSource.getConnection();
			openQ = con.createStatement();
			closeQ = con.createStatement();
			
			con.setAutoCommit(false); //트랜잭션 시작
			open = openQ.executeUpdate(pubSql);
			close = closeQ.executeUpdate(closeSql);
			con.commit();
			
			} catch(Exception e) {
			if(con != null)
				try {
					con.rollback();
				} catch (SQLException e1) { e1.printStackTrace(); }
		} finally {
			try {
				con.setAutoCommit(true);
				if(closeQ != null) closeQ.close();
				if(openQ != null) openQ.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.printf(" 공개글 갯수 :  %d\n", open);
		System.out.printf(" 비공개글 갯수 :  %d\n", close);
		return 0;
	}
	
	public int closeNoticeAll(String ids) {
		return 0;
	}

	public int insertNotice(Notice notice) {
		
		String params = "";
		String sql = "insert into notice(title, Writer_ID, content, files, pub) values(?,?,?,?,?)";
		
		// 등차수열 : 1+(page-1)*10
		Connection con = null;
		PreparedStatement pst = null;
		int result = 0;
		
		try {
			//Class.forName(driver);
			//con = DriverManager.getConnection(url, uid, upwd);
			con = dataSource.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, notice.getTitle());
			pst.setString(2, notice.getWriterId());
			pst.setString(3, notice.getContent());
			pst.setString(4, notice.getFiles());
			pst.setBoolean(5, notice.getPub());
			result = pst.executeUpdate();
			System.out.println(result);
			
			} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public int deletenotice(int id) {
		return 0;
	}
	
	public int updateNotice(int id) {
		return 0;
	}
	
	public List<Notice> getNoticeNewestList() {
		return null;
	}
	
	public List<NoticeView> getNoticeViewList() {
		return getNoticeViewList("title","", 1);
	}
	
	public List<NoticeView> getNoticeViewList(int page) {
		return getNoticeViewList("title","", page);
	}
	
	public List<NoticeView> getPubNoticeViewList(String field, String query, int page) {
	
	String sql = "select * from (select (@rownum:=@rownum+1) as num, N.* from "
			+ "(select * from Notice_view order by RegDate desc ) N where (@rownum:=0)=0 ) NN "
			+ "where "+ field +" LIKE ? and pub = 1 limit ? , 10 ";
	
	// 등차수열 : 1+(page-1)*10
	
	List<NoticeView> list = new ArrayList<NoticeView>();
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	try {
		//Class.forName(driver);
		//con = DriverManager.getConnection(url, uid, upwd);
		con = dataSource.getConnection();
		pst = con.prepareStatement(sql);
		
		pst.setString(1, "%"+query+"%");
		//pst.setInt(2, page);
		pst.setInt(2, (page-1)*10);	
		rs = pst.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("Id");
			String title = rs.getString("Title");
			String writer = rs.getString("Writer_ID");
			Date date = rs.getDate("RegDate");
			int hit = rs.getInt("Hit");
			String files =  rs.getString("Files");
			//String content =  rs.getString("Content");
			int cmtcnt = rs.getInt("cmtcnt");
			boolean pub = rs.getBoolean("pub");
			
			NoticeView n = new NoticeView(id, title, writer, date, hit, files, cmtcnt, pub);
			list.add(n);
		}	
		
	if( rs != null)	rs.close();
	if( pst != null) pst.close();
	if( con != null) con.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;

	
	}
	
	public List<NoticeView> getNoticeViewList(String field, String query, int page) {
		
		String sql = "select * from (select (@rownum:=@rownum+1) as num, N.* from "
				+ "(select * from Notice_view order by RegDate desc ) N where (@rownum:=0)=0 ) NN "
				+ "where "+ field +" LIKE ? limit ? , 10";
		
		// 등차수열 : 1+(page-1)*10
		
		List<NoticeView> list = new ArrayList<NoticeView>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			//Class.forName(driver);
			//con = DriverManager.getConnection(url, uid, upwd);
			con = dataSource.getConnection();
			pst = con.prepareStatement(sql);
			
			pst.setString(1, "%"+query+"%");
			//pst.setInt(2, page);
			pst.setInt(2, (page-1)*10);	
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("Id");
				String title = rs.getString("Title");
				String writer = rs.getString("Writer_ID");
				Date date = rs.getDate("RegDate");
				int hit = rs.getInt("Hit");
				String files =  rs.getString("Files");
				//String content =  rs.getString("Content");
				int cmtcnt = rs.getInt("cmtcnt");
				boolean pub = rs.getBoolean("pub");
				
				NoticeView n = new NoticeView(id, title, writer, date, hit, files, cmtcnt, pub);
				list.add(n);
			}	
			
		if( rs != null)	rs.close();
		if( pst != null) pst.close();
		if( con != null) con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int getNoticeViewCount() {
		return getNoticeViewCount("title","");
	}
	
	public int getNoticeViewCount(String field, String query) {
		
		String sql = "select count(*) as count from ( select (@rownum:=@rownum+1) as num, N.* from "
				+ " (select * from notice order by RegDate desc ) N where (@rownum:=0)=0 ) NN "
				+ "where "+field+" LIKE ? and pub = 1";
		int count = 0;
		
		// 등차수열 : 1+(page-1)*10
		Connection con = null;
		
		try {
			//Class.forName(driver);
			//con = DriverManager.getConnection(url, uid, upwd);
			con = dataSource.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			count = rs.getInt("count");
			
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
		
		return count;
	}
	
	public Notice getNotice(String id) {
		int idInt = Integer.valueOf(id);
		return getNotice(idInt);
	}
	
	public Notice getNotice(int id) {
		
		String sql = "select * from notice where id = ? ";
		Notice n = null;

		Connection con = null;
		try {
			//Class.forName(driver);
			//con = DriverManager.getConnection(url, uid, upwd);
			con = dataSource.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				int nid = rs.getInt("Id");
				String title = rs.getString("Title");
				String writer = rs.getString("Writer_ID");
				Date date = rs.getDate("RegDate");
				int hit = rs.getInt("Hit");
				String files =  rs.getString("Files");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");
				
				n = new Notice(nid,title,writer,date,hit,files,content,pub);
			}	
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n;
	}
	
	public NoticeView getNextNoticeView(String id) {
		int idInt = Integer.valueOf(id);
		return getNextNoticeView(idInt);
	}
	public NoticeView getNextNoticeView(int id) {
		
		String sql = "select * from notice_view where pub = true "
				+ "and id > (select id from (select (@rownum:=@rownum+1) as rownum, notice_view.* "
				+ "from notice_view where (@rownum:=0)=0 ) N where id = ? ) order by id limit 0 , 1";
		NoticeView notice = null;
		// 등차수열 : 1+(page-1)*10
		
		
		
		try {
			//Class.forName(driver);
			//con = DriverManager.getConnection(url, uid, upwd);
			Connection con = dataSource.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int nid = rs.getInt("Id");
				String title = rs.getString("Title");
				if(title == null) 
					title = "다음글이 없습니다";
				String writer = rs.getString("Writer_ID");
				Date date = rs.getDate("RegDate");
				int hit = rs.getInt("Hit");
				String files =  rs.getString("Files");
				//String content =  rs.getString("Content");	
				int cmtcnt = rs.getInt("cmtcnt");
				boolean pub = rs.getBoolean("pub");
				
				notice = new NoticeView(nid, title, writer, date, hit, files, cmtcnt, pub);
				
				rs.close();
				st.close();
				con.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
	
	public NoticeView getPrevNoticeView(String id) {
		int idInt = Integer.valueOf(id);
		return getPrevNoticeView(idInt);
	}
	
	public NoticeView getPrevNoticeView(int id) {
		
		String sql = "select * from notice_view where pub = true"
				+ " and id < (select id from (select (@rownum:=@rownum+1) as rownum, notice_view.*"
				+ "	from notice_view where (@rownum:=0)=0 ) N where id = ? ) order by id desc limit 0 ,1";
		NoticeView noticeView = null;
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			//Class.forName(driver);
			//con = DriverManager.getConnection(url, uid, upwd);
			con = dataSource.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			System.out.println(1);
			
			if(rs.next()) {
				int nid = rs.getInt("Id");
				String title = rs.getString("Title");
				String writer = rs.getString("Writer_ID");
				Date date = rs.getDate("RegDate");
				int hit = rs.getInt("Hit");
				String files =  rs.getString("Files");
				int cmtcnt =  rs.getInt("cmtcnt");	
				boolean pub = rs.getBoolean("pub");
				
				noticeView = new NoticeView(nid, title, writer, date, hit, files, cmtcnt, pub);
				System.out.println(2);
			}

			if(rs != null) rs.close();
			if(pst != null) pst.close();
			if(con != null) con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noticeView;
	}

}

