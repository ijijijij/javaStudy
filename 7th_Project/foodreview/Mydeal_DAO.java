package foodreview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mydeal_DAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	//구매한 셰프딜 조회
	public ArrayList<Mydeal> getMydealList(String memnum){
		ArrayList<Mydeal> mydealList = new ArrayList<Mydeal>();
		try {
			String sql = "SELECT m.memnum, r.restnum, c.dnum, d.cdnum, r.resttitle, c.dname, c.dimage, c.spoint, c.todate, c.dpricebf, c.dpercent, c.dpriceaf,\r\n"
					+ "p.methodstatus, o.status, floor(c.todate-sysdate) as duecount \r\n"
					+ "FROM members m, RESTAURANT r, chefdeal c, chefdealod d, PAYMETHOD p, ORDERSTATUS o \r\n"
					+ "WHERE m.memnum=d.memnum and c.dnum=d.dnum and r.restnum=c.restnum and d.payNUM =p.METHODNUM \r\n"
					+ "AND d.odstatus=o.ordstanum AND m.memnum= ? ";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memnum);
			rs = pst.executeQuery();
			while(rs.next()) {
				mydealList.add(new Mydeal(
						rs.getString("memNum"),
						rs.getString("restNum"),
						rs.getString("dNum"),
						rs.getString("cdnum"),
						rs.getString("resttitle"),
						rs.getString("dname"),
						rs.getString("dimage"),
						rs.getInt("spoint"),
						rs.getDate("todate"),
						rs.getInt("dpricebf"),
						rs.getInt("dpercent"),
						rs.getInt("dpriceaf"),
						rs.getString("methodstatus"),
						rs.getString("status"),
						rs.getInt("duecount")
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
		return mydealList;
	}
	
	//사용가능 셰프딜 조회
	public ArrayList<Mydeal> getAvailableDealList(String memnum){
		ArrayList<Mydeal> mydealList = new ArrayList<Mydeal>();
		try {
			String sql = "SELECT m.memnum, r.restnum, c.dnum, d.cdnum, r.resttitle, c.dname, c.dimage, c.spoint, c.todate, c.dpricebf, c.dpercent, c.dpriceaf,\r\n"
					+ "p.methodstatus, o.status, floor(c.todate-sysdate) as duecount \r\n"
					+ "FROM members m, RESTAURANT r, chefdeal c, chefdealod d, PAYMETHOD p, ORDERSTATUS o \r\n"
					+ "WHERE m.memnum=d.memnum and c.dnum=d.dnum and r.restnum=c.restnum and d.payNUM =p.METHODNUM \r\n"
					+ "AND d.odstatus=o.ordstanum AND m.memnum= ? and odstatus = 0 ";
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, memnum);
			rs = pst.executeQuery();
			while(rs.next()) {
				mydealList.add(new Mydeal(
						rs.getString("memNum"),
						rs.getString("restNum"),
						rs.getString("dNum"),
						rs.getString("cdnum"),
						rs.getString("resttitle"),
						rs.getString("dname"),
						rs.getString("dimage"),
						rs.getInt("spoint"),
						rs.getDate("todate"),
						rs.getInt("dpricebf"),
						rs.getInt("dpercent"),
						rs.getInt("dpriceaf"),
						rs.getString("methodstatus"),
						rs.getString("status"),
						rs.getInt("duecount")
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
		return mydealList;
	}
	
	//내 셰프딜 환불(odstatus를 3으로 바꾸기)
	public void refund(String cdNum) {
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "UPDATE chefdealod SET odstatus = 3 WHERE cdnum= ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, cdNum);
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
	
	
}
