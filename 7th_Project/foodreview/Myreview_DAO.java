package foodreview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Myreview_DAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	//작성한 리뷰 조회
	public ArrayList<MyReview> getMyReview(String memNum, String searchType){
		ArrayList<MyReview> revList = new ArrayList<MyReview>();
		try {
			String sql = "";
			if(searchType.equals("최신순")) {
				sql = "SELECT re.restnum, r.reviewnum, memnum, resttitle, "
						+ "reviewpoint, reviewpostdate, reviewcontent, "
						+ "nvl(restimage,'../include/image/noimg.jpg') AS reviewimage\r\n"
						+ "FROM review r, restaurant re\r\n"
						+ "WHERE r.restnum=re.restnum AND memnum= ? \r\n"
						+ "ORDER BY reviewpostdate desc";
			} else if(searchType.equals("평점낮은순")) {
				sql = "SELECT re.restnum, r.reviewnum, memnum, resttitle, reviewpoint, reviewpostdate, reviewcontent, "
						+ "nvl(restimage,'../include/image/noimg.jpg') AS reviewimage\r\n"
						+ "FROM review r, restaurant re\r\n"
						+ "WHERE r.restnum=re.restnum AND memnum= ? \r\n"
						+ "ORDER BY reviewpoint";
			} else if(searchType.equals("평점높은순")) {
				sql = "SELECT re.restnum, r.reviewnum, memnum, resttitle, reviewpoint, reviewpostdate, reviewcontent, "
						+ "nvl(restimage,'../include/image/noimg.jpg') AS reviewimage\r\n"
						+ "FROM review r, restaurant re\r\n"
						+ "WHERE r.restnum=re.restnum AND memnum= ? \r\n"
						+ "ORDER BY reviewpoint desc";
			} 

			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			rs = pst.executeQuery();
			while(rs.next()) {
				revList.add(new MyReview(
						rs.getString("reviewnum"),
						rs.getString("restnum"),
						rs.getString("memnum"),
						rs.getString("resttitle"),
						rs.getInt("reviewPoint"),
						rs.getDate("reviewPostDate"),
						rs.getString("reviewContent"),
						rs.getString("reviewimage")
						));
			}
		} catch (SQLException e) {
			System.out.println("sql예외:"+e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:"+e.getMessage());
		}finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(pst!=null) {try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(conn!=null) {try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		}
		return revList;
	}

	
	
	//리뷰 삭제
	public boolean deleteMyReview(String memNum, String revNum) {
		boolean isDeleted = false;
		String confirm = "";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			//선택리뷰 삭제
			String sql = "Delete from review WHERE memnum = ? and reviewnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			pst.setString(2, revNum);
			pst.executeUpdate();
			//삭제 확인
			String sql2 = "SELECT \r\n"
					+ "CASE WHEN NOT EXISTS (SELECT * FROM review WHERE memnum= ? and reviewnum = ?)"
					+ "THEN 'Y'\r\n"
					+ "ELSE 'N'\r\n"
					+ "END AS confirm\r\n"
					+ "FROM dual;";
			pst = conn.prepareStatement(sql2);
			pst.setString(1, memNum);
			pst.setString(2, revNum);
			rs = pst.executeQuery();
			if(rs.next()) {
				confirm = rs.getString("confirm");
			}
			conn.commit();
			
		} catch (SQLException e) {
			System.out.println("sql예외:"+e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:"+e.getMessage());
		}finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(pst!=null) {try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(conn!=null) {try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		}
		if (confirm=="Y") {
			isDeleted = true;
		}
		return isDeleted;
	}
	
	//도장 조회
	public ArrayList<Stamp> getStampList(String memNum, int kind){
		ArrayList<Stamp> stpList = new ArrayList<Stamp>();
		try {
			String sql = "SELECT stampnum, memnum, r.restnum, r.resttitle, r.restimage, s.kind\r\n"
					+ "FROM restaurant r, stamp s\r\n"
					+ "WHERE r.restnum=s.restnum AND memnum=? and kind = ?";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			pst.setInt(2, kind);
			rs = pst.executeQuery();
			while(rs.next()) {
				stpList.add(new Stamp(
						rs.getString("stampnum"),
						rs.getString("restnum"),
						rs.getString("memnum"),
						rs.getString("resttitle"),
						rs.getString("restimage"),
						rs.getInt("kind")
						));
			}
		} catch (SQLException e) {
			System.out.println("sql예외:"+e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:"+e.getMessage());
		}finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(pst!=null) {try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(conn!=null) {try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		}
		return stpList;
	}
	
	//발도장 이동
	public void goFootStamp(String memnum, String stpnum) {
		Stamp stamp = new Stamp();
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "UPDATE stamp SET kind = 0 WHERE memnum = ? and stampnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, memnum);
			pst.setString(2, stpnum);
			pst.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			System.out.println("sql예외:"+e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:"+e.getMessage());
		}finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(pst!=null) {try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(conn!=null) {try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		}
	}
	
	//도장 삭제
	public int deleteStamp(String stpNum) {
		int row = 0;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "DELETE FROM stamp WHERE STAMPNUM = ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, stpNum);
			row = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외:"+e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:"+e.getMessage());
		}finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(pst!=null) {try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(conn!=null) {try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		}
		return row;
	}
	
}
