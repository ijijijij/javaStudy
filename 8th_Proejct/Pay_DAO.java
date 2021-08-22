package funding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model2.vo.Address;
import model2.vo.Payment;

public class Pay_DAO {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;

	// 후원 현재 금액, 후원자수 정보
	public int[] getCurrent(String fdNum) {
		int[] current = new int[2];
		try {
			String sql = "SELECT sum(sptprice) AS currentprice, \r\n" + "count(sptnum) AS currentsupporter\r\n"
					+ "FROM support \r\n" + "WHERE sptodnum=1 AND fdnum=? \r\n" + "GROUP BY fdnum";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, fdNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				current[0] = rs.getInt("currentprice");
				current[1] = rs.getInt("currentsupporter");
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
		return current;
	}

	// 갤러리 목록
	public ArrayList<Funding> getFundingList() {
		ArrayList<Funding> fundingList = new ArrayList<Funding>();
		try {
			String sql = "SELECT fdnum, f.crenum, crenm, fdthemenum, fdprojectnm, fdtargetprice, fdimg, \r\n"
					+ "fdcategorynum, fdregidt, fdexpdt, statusnum, fdingprice, trunc(fdexpdt-fdregidt) AS lastday\r\n"
					+ "FROM FUNDING f, creator c\r\n" + "WHERE statusnum=1 AND c.crenum=f.crenum";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				fundingList.add(new Funding(rs.getString("fdnum"), rs.getString("crenum"), rs.getInt("fdthemenum"),
						rs.getString("fdprojectnm"), rs.getInt("fdtargetprice"), rs.getString("fdimg"),
						rs.getInt("fdcategorynum"), rs.getString("fdregidt"), rs.getString("fdexpdt"),
						rs.getInt("statusnum"), rs.getInt("lastday"), rs.getString("crenm")));
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
		return fundingList;
	}

	// 펀딩상세
	public Funding getFundingDetail(String fdNum) {
		Funding funding = null;
		try {
			String sql = "SELECT fdnum, f.crenum, crenm, fdthemenum, fdprojectnm, fdtargetprice, fdimg,\r\n"
					+ "fdcategorynum, fdregidt, fdexpdt, fdtags, fdstoryimg, fdstorysum, fdstory,\r\n"
					+ "fdrefund, fdpolicy, statusnum, trunc(fdexpdt-fdregidt) AS lastday\r\n"
					+ "FROM funding f, creator c\r\n" + "WHERE statusnum=1 AND f.crenum=c.crenum AND fdnum=?";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, fdNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				funding = new Funding(rs.getString("fdnum"), rs.getString("crenum"), rs.getInt("fdthemenum"),
						rs.getString("fdprojectnm"), rs.getInt("fdtargetprice"), rs.getString("fdimg"),
						rs.getInt("fdcategorynum"), rs.getString("fdregidt"), rs.getString("fdexpdt"),
						rs.getString("fdtags"), rs.getString("fdstoryimg"), rs.getString("fdstorysum"),
						rs.getString("fdstory"), rs.getString("fdrefund"), rs.getString("fdpolicy"),
						rs.getInt("statusnum"), rs.getInt("lastday"), rs.getString("crenm"));
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
		return funding;
	}

	// 크리에이터 정보
	public Creator getFundingCreator(String creNum) {
		Creator creator = null;
		try {
			String sql = "SELECT crenum, c.memnum, creprofile, crenm, credetail, crepage \r\n"
					+ "FROM creator c, MEMBER m WHERE crenum=? AND c.memnum=m.memnum";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, creNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				creator = new Creator(rs.getString("crenum"), rs.getString("memnum"), rs.getString("creprofile"),
						rs.getString("crenm"), rs.getString("credetail"), rs.getString("crepage"));
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
		return creator;
	}

	// 후원자 정보
	public String getSupporterMail(String memNum) {
		String memId = null;
		try {
			String sql = "SELECT *\r\n" + "FROM MEMBER\r\n" + "WHERE memnum=?";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				memId = rs.getString("memid");
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
		return memId;
	}

	// 펀딩 크리에이터 멤버번호 확인
	public String getMemNum(String creNum) {
		String memNum = null;
		try {
			String sql = "SELECT memnum from creator where crenum=?";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, creNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				memNum = rs.getString("memnum");
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
		return memNum;
	}

	// 주소 확인
	public ArrayList<Address> getAddress(String memNum) {
		ArrayList<Address> adrList = new ArrayList<Address>();
		try {
			String sql = "SELECT * FROM address WHERE memnum=?";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			rs = pst.executeQuery();
			while(rs.next()) {
				adrList.add(new Address(
						rs.getString("memnum"),
						rs.getString("addressnum"),
						rs.getString("defaultaddress"),
						rs.getString("detailaddress")
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
		return adrList;
	}

	//등록한 카드 확인
	public ArrayList<Payment> getPayment(String memNum) {
		ArrayList<Payment> paymentList = new ArrayList<Payment>();
		try {
			String sql = "SELECT * FROM payment WHERE memnum=?";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memNum);
			rs = pst.executeQuery();
			while(rs.next()) {
				paymentList.add(new Payment(
						rs.getString("paynum"),
						rs.getString("memnum"),
						rs.getString("bankname"),
						rs.getString("cardnum")
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
		return paymentList;
	}

	//후원하기
	public int support(String memNum, String fdNum, int paymethodNum) {
		int cnt = -1;
		try {
			String sql = "INSERT INTO support(sptnum, fdnum, memnum, sptpaynum)\r\n"
					+ "values('spt-'||trim(to_char(spt_seq.nextval,'0000')),?,?,?)";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, fdNum);
			pst.setString(2, memNum);
			pst.setInt(3, paymethodNum);
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

	//펀딩 신고
			public int reportFunding(String memNum, String writerNum, String fdNum) {
				int cnt = -1;
				try {
					String sql = "INSERT INTO report(memnum, memnum2, cttnum) VALUES (?, ?, ?)";
					conn = DBConnection.getConnection();
					conn.setAutoCommit(false);
					pst = conn.prepareStatement(sql);
					pst.setString(1, memNum);
					pst.setString(2, writerNum);
					pst.setString(3, fdNum);
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
