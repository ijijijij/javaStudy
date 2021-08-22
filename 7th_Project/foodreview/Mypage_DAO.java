package foodreview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Mypage_DAO {
	//DB연결 설정
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	//회원 정보 확인
	public MyProfile getMemInfo(String memNum) {
		MyProfile profile = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT A.MEMNUM, A.MEMID, A.MEMPW, A.MEMNICK, "
					+ "A.MEMMAIL, A.MEMIMG, A.MEMPOINT,\r\n"
					+ "NVL(B.REVCOUNT,0) AS REVCOUNT\r\n"
					+ "FROM MEMBERS A, (SELECT MEMNUM, COUNT(REVIEWNUM) "
					+ "AS REVCOUNT FROM REVIEW GROUP BY MEMNUM) B\r\n"
					+ "WHERE A.MEMNUM=B.MEMNUM(+) AND A.MEMNUM= ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			rs = pst.executeQuery();
			if(rs.next()) {
				profile = new MyProfile(
					rs.getString("memnum"),
					rs.getString("memID"),
					rs.getString("memPW"),
					rs.getString("memNick"),
					rs.getString("memMail"),
					rs.getString("memImg"),
					rs.getInt("revcount"),
					rs.getInt("memPoint")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profile;
	}

	//내 프로필 보기
	/*
	public Members showProfile(Members member) {
		Members myprofile = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from members where memnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, member.getMemNum());
			rs = pst.executeQuery();
			if(rs.next()) {
				myprofile = new Members(
					rs.getString("memID"),
					rs.getString("memNick"),
					rs.getString("memMail"),
					rs.getString("memImg"),
					rs.getInt("memPoint")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myprofile;
	}*/
	
	//비밀번호 변경
	public String updatePW(String memNum, String pw) {
		String changedPW = "";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			//비밀번호 변경
			String sql = "UPDATE members SET mempw = ? WHERE memnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, pw);
			pst.setString(2, memNum);
			pst.executeUpdate();
			//변경 비밀번호 확인
			String sql2 = "select mempw from members where memnum = ?";
			pst = conn.prepareStatement(sql2);
			pst.setString(1,memNum);
			rs = pst.executeQuery();
			if(rs.next()) {
				changedPW = rs.getString("mempw");
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
		return changedPW;
	}
	
	//닉네임 변경
	public String updateNick(String memNum, String nick) {
		String changedNick = "";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			//닉네임 변경
			String sql = "UPDATE members SET memnick = ? WHERE memnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, nick);
			pst.setString(2, memNum);
			pst.executeUpdate();
			//변경 닉네임 조회
			String sql2 = "select memnick from members where memnum = ?";
			pst = conn.prepareStatement(sql2);
			pst.setString(1,memNum);
			rs = pst.executeQuery();
			if(rs.next()) {
				changedNick = rs.getString("memNick");
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
		return changedNick;
	}
	
	//이메일 변경
	public String updateMail(String memNum, String mail) {
		String changedMail = "";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			//이메일 변경
			String sql = "UPDATE members SET memmail = ? WHERE memnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, mail);
			pst.setString(2, memNum);
			pst.executeUpdate();
			//변경 이메일 조회
			String sql2 = "select memmail from members where memnum=?";
			pst = conn.prepareStatement(sql2);
			pst.setString(1, memNum);
			rs = pst.executeQuery();
			if(rs.next()) {
				changedMail = rs.getString("memMail");
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
		return changedMail;
	}

	//프로필 이미지 변경
	public String updateImg(String memNum, String img) {
		String changedImg = "";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			//이미지 변경
			String sql = "UPDATE members SET memimg = ? WHERE memnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, img);
			pst.setString(2, memNum);
			pst.executeUpdate();
			//변경 이미지 조회
			String sql2 = "select memimg from members where memnum=?";
			pst = conn.prepareStatement(sql2);
			pst.setString(1, memNum);
			rs = pst.executeQuery();
			if(rs.next()) {
				changedImg = rs.getString("memImg");
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
		return changedImg;
	}
	
	//회원 탈퇴
	public boolean deleteAccount(String memNum) {
		boolean isDeleted = false;
		String confirm = "";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			//회원정보 삭제
			String sql = "Delete from members WHERE memnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			pst.executeUpdate();
			//삭제 확인
			String sql2 = "SELECT \r\n"
					+ "CASE WHEN NOT EXISTS (SELECT * FROM members WHERE memnum= ? ) "
					+ "THEN 'Y'\r\n"
					+ "ELSE 'N'\r\n"
					+ "END AS confirm\r\n"
					+ "FROM dual;";
			pst = conn.prepareStatement(sql2);
			pst.setString(1, memNum);
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
	
	
}
