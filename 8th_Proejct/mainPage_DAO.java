package funding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class mainPage_DAO {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;

	// 신규 크리에이터
	public ArrayList<Creator> getNewCreator() {
		ArrayList<Creator> newCreatorList = new ArrayList<Creator>();
		try {
			String sql = "SELECT crenum, creprofile, crenm, a.memnum, a.memjoindate\r\n"
					+ "FROM CREATOR c, (SELECT memnum, memjoindate FROM member) a\r\n"
					+ "WHERE c.memnum=a.memnum AND rownum<=7 ORDER BY memjoindate desc";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				newCreatorList.add(new Creator(rs.getString("crenum"), rs.getString("memnum"),
						rs.getString("creprofile"), rs.getString("crenm"), rs.getString("memjoindate")));
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
		return newCreatorList;
	}

	// 신규 펀딩
	public ArrayList<Funding> getNewFunding() {
		ArrayList<Funding> newFundingList = new ArrayList<Funding>();
		try {
			String sql = "SELECT *\r\n"
					+ "FROM (SELECT fdnum, f.crenum, crenm, fdthemenum, fdprojectnm, fdtargetprice, fdimg, \r\n"
					+ "fdcategorynum, fdregidt, fdexpdt, statusnum, fdingprice, trunc(fdexpdt-fdregidt) AS lastday\r\n"
					+ "FROM FUNDING f, creator c\r\n"
					+ "WHERE statusnum=1 AND c.crenum=f.crenum ORDER BY fdregidt desc)\r\n"
					+ "WHERE rownum<=3";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				newFundingList.add(new Funding(rs.getString("fdnum"), rs.getString("crenum"), rs.getInt("fdthemenum"),
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
		return newFundingList;
	}

	// 인기 펀딩
	public ArrayList<Funding> getHotFunding() {
		ArrayList<Funding> hotFundingList = new ArrayList<Funding>();
		try {
			String sql = "SELECT * \r\n"
					+ "FROM (SELECT f.fdnum as fdnum, f.crenum as crenum, crenm, fdthemenum, fdprojectnm, fdtargetprice, fdimg, \r\n"
					+ "fdcategorynum, fdregidt, fdexpdt, statusnum, fdingprice, trunc(fdexpdt-fdregidt) AS lastday\r\n"
					+ "FROM funding f, (SELECT count(sptnum) AS cnt, fdnum FROM support WHERE sptodnum=1 GROUP BY fdnum) s, creator c\r\n"
					+ "WHERE s.fdnum=f.fdnum AND c.crenum=f.crenum ORDER BY s.cnt DESC)\r\n"
					+ "WHERE rownum<=3";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				hotFundingList.add(new Funding(rs.getString("fdnum"), rs.getString("crenum"), rs.getInt("fdthemenum"),
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
		return hotFundingList;
	}
}
