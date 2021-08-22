package funding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Survey_DAO {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;

	//설문 등록 자격 확인(크리에이터 전용)
	public int checkMemGrade(String memNum) {
		int grade = 1;
		try {
			String sql = "SELECT memgradenum FROM MEMBER WHERE memnum= ? ";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				grade = rs.getInt("memgradenum");
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
		return grade;
	}
	// 설문 등록
	public int uploadSurvey(String memNum, String title, String content, String svq1, ArrayList<String> svoption1,
			String svq2, ArrayList<String> svoption2) {
		int cnt = -1;
		try {
			String sql = "INSERT ALL\r\n" + "INTO bbs (bbsnum, bbstitle, bbscontent, memnum)\r\n"
					+ "values('bbs-'||trim(to_char(bbs_seq.nextval,'0000')), ?, ?,?)\r\n"
					+ "INTO survey (srvnum,bbsnum, srvthumb)\r\n"
					+ "values('srv-'||trim(to_char(srv_seq.nextval,'0000')), "
					+ "'bbs-'||trim(to_char(bbs_seq.currval,'0000')),\r\n"
					+ "'img01')SELECT * FROM dual";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, memNum);
			cnt = pst.executeUpdate();
			if (cnt > 0) {
				String sql2 = "INSERT \r\n"
						+ "INTO surveyquestion values('svq-'||trim(to_char(svq_seq.nextval,'0000')), "
						+ "'srv-'||trim(to_char(srv_seq.currval,'0000')),\r\n"
						+ "?)";
				pst = conn.prepareStatement(sql2);
				pst.setString(1, svq1);
				cnt = pst.executeUpdate();
				if (cnt > 0) {
					String sql3 = "insert\r\n" + "INTO surveyoption(svonum, svqnum, svo)\r\n"
							+ "values('svo-'||trim(to_char(svo_seq.nextval,'0000')),"
							+ "'svq-'||trim(to_char(svq_seq.currval,'0000')),\r\n"
							+ " ? )";
					pst = conn.prepareStatement(sql3);
					for (int i = 0; i < svoption1.size(); i++) {
						pst.setString(1, svoption1.get(i));
						System.out.println(svoption1.get(i));
						cnt = pst.executeUpdate();
					}
				}
				if (!svq2.equals("")) {
					String sql4 = "INSERT\r\n"
							+ "INTO surveyquestion values('svq-'||trim(to_char(svq_seq.nextval,'0000')), "
							+ "'srv-'||trim(to_char(srv_seq.currval,'0000')),\r\n"
							+ "?)";
					pst = conn.prepareStatement(sql4);
					pst.setString(1, svq2);
					cnt = pst.executeUpdate();
					if (cnt == 1) {
						String sql5 = "insert\r\n" + "INTO surveyoption(svonum, svqnum, svo)\r\n"
								+ "values('svo-'||trim(to_char(svo_seq.nextval,'0000')),"
								+ "'svq-'||trim(to_char(svq_seq.currval,'0000')),\r\n"
								+ "?)";
						pst = conn.prepareStatement(sql5);
						for (int i = 0; i < svoption2.size(); i++) {
							pst.setString(1, svoption2.get(i));
							cnt = pst.executeUpdate();
						}
					}
				}
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
		return cnt;
	}
	
	//설문 등록 후 보기
	public String getbbsNum(String memNum) {
		String bbsNum = null;
		try {
			String sql = "SELECT b.bbsnum\r\n"
					+ "FROM bbs b,(\r\n"
					+ "	SELECT memnum, max(bbsdate) AS bbsdate\r\n"
					+ "	FROM bbs\r\n"
					+ "	where memnum= ? GROUP BY memnum) k\r\n"
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
	
	//설문 보기(공통내용)
	public Survey getSurvey(String bbsNum) {
		Survey survey = null;
		try {
			String sql = "SELECT b.bbstitle, b.bbscontent, to_char(b.bbsdate,'YYYY-MM-DD') AS bbsdate, b.memnum, m.memnick, b.statusnum,\r\n"
					+ "sv.srvnum, sv.srvthumb, viewcnt \r\n"
					+ "FROM bbs b, survey sv, MEMBER m \r\n"
					+ "WHERE b.bbsnum=sv.bbsnum AND b.memnum=m.memnum AND b.bbsnum=?";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, bbsNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				survey = new Survey(
						rs.getString("bbsTitle"),
						rs.getString("bbsContent"),
						rs.getString("bbsDate"),
						rs.getInt("statusNum"),
						rs.getString("memNum"),
						rs.getString("memNick"),
						rs.getString("srvNum"),
						rs.getString("srvThumb"),
						rs.getInt("viewcnt")
						);
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
		return survey;
	}
	
	//설문 보기(설문항목)
	public ArrayList<Survey> getSurveyQuestion(String bbsNum) {
		ArrayList<Survey> surveyQuestionList = new ArrayList<Survey>();
		try {
			String sql = "SELECT sq.svqnum, sq.srvnum, sq.svqtitle FROM survey sv, "
					+ "SURVEYQUESTION sq, bbs b\r\n"
					+ "WHERE sv.srvnum=sq.srvnum AND b.bbsnum=sv.bbsnum "
					+ "AND b.bbsnum= ? ";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, bbsNum);
			rs = pst.executeQuery();
			while (rs.next()) {
				surveyQuestionList.add(new Survey(
						rs.getString("srvNum"),
						rs.getString("svqNum"),
						rs.getString("svqTitle")
						));
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
		return surveyQuestionList;
	}

	//설문 보기(설문선택지)
	public ArrayList<Survey> getSurveyOption(String svqNum) {
		ArrayList<Survey> surveyOptionList = new ArrayList<Survey>();
		try {
			String sql = "SELECT so.svonum, so.svo, so.svocount \r\n"
					+ "FROM surveyquestion sq, surveyoption so\r\n"
					+ "WHERE so.svqnum=sq.svqnum AND sq.svqnum= ? ";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, svqNum);
			rs = pst.executeQuery();
			while (rs.next()) {
				surveyOptionList.add(new Survey(
						rs.getString("svoNum"),
						rs.getString("svo"),
						rs.getInt("svocount")
						));
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
		return surveyOptionList;
	}

	
	//설문 선택값 넘기기
	public int selectOption(String svoNum) {
		int cnt = -1;
		try {
			String sql = "UPDATE surveyoption SET svocount=svocount+1 WHERE svonum= ? ";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, svoNum);
			cnt = pst.executeUpdate();
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
	
	//댓글 게시판 번호 확인
	public int checkCommentBBS(String bbsNum) {
		int cnt = 0;
		try {
			String sql = "SELECT count(bbsnum) AS count FROM commission WHERE bbsnum= ? ";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, bbsNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt("count");
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
		return cnt;
	}

	//설문 수정
	public int editSurvey(String title, String content, String bbsNum) {
		int cnt = -1;
		try {
			String sql = "UPDATE bbs SET bbstitle=?, bbscontent=? WHERE bbsnum=?";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, bbsNum);
			cnt = pst.executeUpdate();
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

	//설문 삭제
	public int delSurvey(String bbsNum) {
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
	
	//설문 개수
	public int getSurveyCount() {
		int cnt = 0;
		try {
			String sql = "SELECT count(srvnum) as count FROM survey s, bbs b "
					+ "WHERE s.bbsnum=b.bbsnum AND b.statusnum=1";
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
	public ArrayList<Survey> getSurveyList(int numByPage, int pageNum){
		int count = getSurveyCount();
		ArrayList<Survey> surveyList = new ArrayList<Survey>();
		try {
			String sql = "SELECT * FROM (SELECT row_number() OVER (ORDER BY srvnum) AS serialnum, \r\n"
					+ "b.bbsnum, srvnum, bbstitle, bbscontent,m.memnum, m.memnick, to_char(bbsdate,'YYYY-MM-DD') AS BBSDATE, viewcnt FROM MEMBER m, bbs b, survey s\r\n"
					+ "WHERE m.memnum=b.memnum AND b.bbsnum=s.bbsnum AND b.statusnum=1)\r\n"
					+ "WHERE serialnum BETWEEN ? AND ?"
					+ "ORDER BY srvnum desc";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			//pst.setInt(1, numByPage*pageNum-(numByPage-1));
			//pst.setInt(2, pageNum*numByPage);
			pst.setInt(1, count-(pageNum*numByPage)+1);
			pst.setInt(2, count-(pageNum-1)*numByPage);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				surveyList.add(new Survey(
						rs.getInt("serialnum"),
						rs.getString("bbsNum"),
						rs.getString("bbstitle"),
						rs.getString("bbscontent"),
						rs.getString("bbsdate"),
						rs.getString("memnum"),
						rs.getString("memnick"),
						rs.getString("srvnum"),
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
		return surveyList;
	}
	
	public boolean nextPage(int numByPage, int pageNum) {
		int count = getSurveyCount();
		try {
			String sql = "SELECT * FROM (SELECT row_number() OVER (ORDER BY srvnum) AS serialnum, \r\n"
					+ "b.bbsnum, srvnum, bbstitle, bbscontent,m.memnum, m.memnick, to_char(bbsdate,'YYYY-MM-DD') AS BBSDATE, viewcnt FROM MEMBER m, bbs b, survey s\r\n"
					+ "WHERE m.memnum=b.memnum AND b.bbsnum=s.bbsnum AND b.statusnum=1)\r\n"
					+ "WHERE serialnum <= ?"
					+ "ORDER BY srvnum desc";
			/*
			String sql = "SELECT * FROM (SELECT row_number() OVER (ORDER BY srvnum) AS serialnum, \r\n"
					+ "b.bbsnum, srvnum, bbstitle, bbscontent,m.memnum, m.memnick, bbsdate, viewcnt\r\n"
					+ "				FROM MEMBER m, bbs b, survey s\r\n"
					+ "				WHERE m.memnum=b.memnum AND b.bbsnum=s.bbsnum AND b.statusnum=1)\r\n"
					+ "WHERE serialnum < ?\r\n"
					+ "ORDER BY srvnum desc";
			*/		
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
	
}
