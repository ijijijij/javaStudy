package funding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import funding.DBConnection;

public class Community_DAO {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;

	// 커미션 등록
	public int uploadCommission(String title, String contents, String memNum, String cmsTo) {
		int cnt = -1;
		try {
			String sql = "INSERT all\r\n" + "INTO bbs(bbsnum, bbstitle, bbscontent, memnum)\r\n"
					+ "values('bbs-'||trim(to_char(bbs_seq.nextval,'0000')),? ,?, ?)\r\n"
					+ "INTO commission(cmsnum, bbsnum, cmsto)\r\n"
					+ "values('cms-'||trim(to_char(cms_seq.nextval, '0000')), "
					+ "'bbs-'||trim(to_char(bbs_seq.currval,'0000')),\r\n"
					+ "(SELECT memnum  AS cmsto FROM member WHERE memnick=?))\r\n" + "SELECT * FROM dual";
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			pst.setString(2, contents);
			pst.setString(3, memNum);
			pst.setString(4, cmsTo);
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	// 아이디에 해당하는 회원번호 존재여부 확인
	public String getMember(String id) {
		String creatorMemNum = null;
		try {
			String sql = "SELECT memnum FROM member WHERE memid= ?";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				creatorMemNum = rs.getString("memnum");
			}
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return creatorMemNum;
	}

	// 크리에이터 닉네임 목록 가져오기(팔로우 제외)
	public ArrayList<String> getCreatorIdList(String memnum) {
		ArrayList<String> creatorIdList = new ArrayList<String>();
		try {
			String sql = "SELECT memnick FROM member WHERE memgradenum='2' \r\n" + "MINUS \r\n"
					+ "SELECT a.memnick FROM member a, follow b WHERE b.memnum= ? \r\n" + "AND flwnum=a.memnum";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memnum);
			rs = pst.executeQuery();
			while (rs.next()) {
				creatorIdList.add(rs.getString("memnick"));
			}
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return creatorIdList;
	}

	// 크리에이터 닉네임 목록 가져오기(전체)
	public ArrayList<String> getCreatorIdList() {
		ArrayList<String> creatorIdList = new ArrayList<String>();
		try {
			String sql = "SELECT memnick FROM member WHERE memgradenum='2' or memgradenum='3'";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				creatorIdList.add(rs.getString("memnick"));
			}
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return creatorIdList;
	}

	// 팔로우 닉네임 목록 가져오기
	public ArrayList<String> getFollowList(String memNum) {
		ArrayList<String> followList = new ArrayList<String>();
		try {
			String sql = "SELECT a.memnick as memnick FROM member a, follow b WHERE b.memnum= ? AND flwnum=a.memnum";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			rs = pst.executeQuery();
			while (rs.next()) {
				followList.add(rs.getString("memnick"));
			}
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return followList;
	}

	// 커미션 글 등록 후 bbsnum받기
	public String getThisNum(String memNum) {
		String bbsNum = null;
		try {
			String sql = "SELECT b.bbsnum\r\n" + "FROM bbs b,(\r\n" + "	SELECT memnum, max(bbsdate) AS bbsdate\r\n"
					+ "	FROM bbs\r\n" + "	where memnum= ? GROUP BY memnum) k\r\n"
					+ "WHERE b.memnum=k.memnum AND b.bbsdate=k.bbsdate";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				bbsNum = rs.getString("bbsnum");
			}
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bbsNum;
	}

	// 커미션 글 선택해서 보기
	public Commission getCommission(String bbsNum) {
		Commission commission = null;
		try {
			String sql = "SELECT b.bbsnum, b.bbstitle, b.bbscontent, to_char(b.bbsdate,'YYYY-MM-DD') as bbsdate, b.statusnum,b.memnum AS writernum, m.memnick AS writernick, c.cmsnum, c.cmsto, c.cmstonick, c.cmsagree, viewcnt\r\n"
					+ "FROM bbs b, MEMBER m, \r\n"
					+ "	(SELECT ba.bbsnum, cmsnum, cmsto, ma.memnick AS cmstonick, cmsagree\r\n"
					+ "	FROM bbs ba, commission ca, member ma \r\n"
					+ "	WHERE ba.bbsnum=ca.bbsnum AND ca.cmsto=ma.memnum) c\r\n"
					+ "WHERE b.bbsnum= ? AND b.bbsnum=c.bbsnum AND b.memnum=m.memnum";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, bbsNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				commission = new Commission(rs.getString("bbsnum"), rs.getString("bbstitle"),
						rs.getString("bbscontent"), rs.getString("bbsdate"), rs.getInt("statusnum"),
						rs.getString("writernum"), rs.getString("writernick"), rs.getString("cmsnum"),
						rs.getString("cmsto"), rs.getString("cmstonick"), rs.getInt("cmsagree"),rs.getInt("viewcnt"));
			}
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return commission;
	}

	// 커미션 agree
	public int agree(String bbsNum) {
		int cnt = -1;
		try {
			String sql = "UPDATE commission SET cmsagree = cmsagree + 1 WHERE bbsnum= ? ";
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, bbsNum);
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	// 커미션 글 수정
	public int updateCommission(String title, String contents, String bbsNum) {
		int cnt = -1;
		try {
			String sql = "UPDATE bbs SET bbstitle= ? , bbscontent= ? WHERE bbsnum= ? ";
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			pst.setString(2, contents);
			pst.setString(3, bbsNum);
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	// 커미션 글 삭제
	public int deleteCommission(String bbsNum) {
		int cnt = -1;
		try {
			String sql = "UPDATE bbs SET statusnum=0 WHERE bbsnum=?";
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, bbsNum);
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	// 댓글 등록
	public int regComments(String memNum, String cmtContent, String bbsNum) {
		int cnt = -1;
		try {
			String sql = "INSERT INTO comments(cmtnum, memnum, cmtcontent, bbsnum)\r\n"
					+ "values('cmt-'||trim(to_char(cmt_seq.nextval,'0000')),?,?, ?)";
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			pst.setString(2, cmtContent);
			pst.setString(3, bbsNum);
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	// 댓글목록보기
	public ArrayList<Comments> getComments(String bbsNum) {
		ArrayList<Comments> commentList = new ArrayList<Comments>();
		try {
			String sql = "select cmtnum, c.memnum, cmtcontent,cmtdate, statusnum,bbsnum,memnick FROM comments c, MEMBER m\r\n"
					+ "WHERE bbsnum= ? AND c.memnum=m.memnum AND statusnum=1 ORDER BY cmtnum desc";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, bbsNum);
			rs = pst.executeQuery();
			while (rs.next()) {
				commentList.add(new Comments(rs.getString("cmtnum"), rs.getString("cmtcontent"), rs.getString("memnum"),
						rs.getString("memnick"), rs.getString("cmtdate"), rs.getString("bbsnum"),
						rs.getInt("statusnum")));
			}
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return commentList;
	}

	
	//개수
		public int getCommissionCount() {
			int cnt = 0;
			try {
				String sql = "SELECT count(cmsnum) as count FROM commission c, bbs b "
						+ "WHERE c.bbsnum=b.bbsnum AND b.statusnum=1";
				conn = DBConnection.getConnection();
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				if(rs.next()) {cnt = rs.getInt("count");}
			} catch (SQLException e) {
				System.out.println("sql예외:" + e.getMessage());
			} catch (Exception e) {
				System.out.println("일반예외:" + e.getMessage());
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return cnt;
		}
	//리스트
	public ArrayList<Commission> getCommissionList(int numByPage, int pageNum){
		int count = getCommissionCount();
		ArrayList<Commission> commissionList = new ArrayList<Commission>();
		try {
			String sql = "SELECT * FROM (SELECT row_number() OVER (ORDER BY cmsnum) AS serialnum,\r\n"
					+ "b.bbsnum, b.bbstitle, b.bbscontent, to_char(b.bbsdate,'YYYY-MM-DD') AS bbsdate, b.statusnum,b.memnum AS writer, m.memnick AS writernick, c.cmsnum, c.cmstonick, c.cmsagree, viewcnt\r\n"
					+ "FROM bbs b, MEMBER m, \r\n"
					+ "	(SELECT ba.bbsnum, cmsnum, cmsto, ma.memnick AS cmstonick, cmsagree\r\n"
					+ "	FROM bbs ba, commission ca, member ma \r\n"
					+ "	WHERE ba.bbsnum=ca.bbsnum AND ca.cmsto=ma.memnum) c\r\n"
					+ "WHERE b.bbsnum=c.bbsnum AND b.memnum=m.memnum AND b.statusnum=1\r\n"
					+ ")\r\n"
					+ "WHERE serialnum BETWEEN ? AND ?\r\n"
					+ "ORDER BY cmsnum desc";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, count-(pageNum*numByPage)+1);
			pst.setInt(2, count-(pageNum-1)*numByPage);
			//pst.setInt(1, numByPage*pageNum-(numByPage-1));
			//pst.setInt(2, pageNum*numByPage);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				commissionList.add(new Commission(
						rs.getInt("serialnum"),
						rs.getString("bbsnum"),
						rs.getString("bbstitle"),
						rs.getString("bbscontent"),
						rs.getString("bbsdate"),
						rs.getString("writer"),
						rs.getString("writernick"),
						rs.getString("cmsnum"),
						rs.getString("cmstonick"),
						rs.getInt("cmsagree"),
						rs.getInt("viewcnt")
						));
			}
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return commissionList;
	}
	
	public boolean nextPage(int numByPage, int pageNum) {
		int count = getCommissionCount();
		try {
			String sql = "SELECT * FROM (SELECT row_number() OVER (ORDER BY cmsnum) AS serialnum,\r\n"
					+ "b.bbsnum, b.bbstitle, b.bbscontent, to_char(b.bbsdate,'YYYY-MM-DD') AS bbsdate, b.statusnum,b.memnum AS writer, m.memnick AS writernick, c.cmsnum, c.cmstonick, c.cmsagree, viewcnt\r\n"
					+ "FROM bbs b, MEMBER m, \r\n"
					+ "	(SELECT ba.bbsnum, cmsnum, ma.memnick AS cmstonick, cmsagree\r\n"
					+ "	FROM bbs ba, commission ca, member ma \r\n"
					+ "	WHERE ba.bbsnum=ca.bbsnum AND ca.cmsto=ma.memnum) c\r\n"
					+ "WHERE b.bbsnum=c.bbsnum AND b.memnum=m.memnum AND b.statusnum=1\r\n"
					+ ")\r\n"
					+ "WHERE serialnum <= ?\r\n"
					+ "ORDER BY cmsnum desc";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			//pst.setInt(1, getNext()-(pageNum-1)*numByPage);
			//pst.setInt(1, getNext()-(pageNum-1)*numByPage);
			pst.setInt(1, count-(pageNum-1)*numByPage);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	//조회수
	public int addViewCount(String bbsNum) {
		int cnt = -1;
		try {
			String sql = "UPDATE bbs SET viewcnt = viewcnt+1 WHERE bbsnum=?";
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, bbsNum);
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}
	
	//게시물 신고
	public int reportbbs(String memNum, String writerNum, String bbsNum) {
		int cnt = -1;
		try {
			String sql = "INSERT INTO report(memnum, memnum2, cttnum) VALUES (?, ?, ?)";
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			pst.setString(2, writerNum);
			pst.setString(3, bbsNum);
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	//댓글 신고
	public int reportcomment(String memNum, String writerNum, String cmtNum) {
		int cnt = -1;
		try {
			String sql = "INSERT INTO report(memnum, memnum2, cttnum) VALUES (?, ?, ?)";
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			pst.setString(2, writerNum);
			pst.setString(3, cmtNum);
			cnt = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("sql예외:" + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("일반예외:" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}
	
	
	
}
