package foodreview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String rcNum = "RC001-CJ";
		try {
			String query = "SELECT ESTR, count(a.rsnum)\r\n"
					+ "FROM received a, (SELECT rsnum, education FROM resume) b, education c\r\n"
					+ "WHERE a.rcNum="+"'"+rcNum+"'"+" AND a.rsnum=b.rsnum AND b.education=c.enum\r\n"
					+ "GROUP BY estr";
			
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			System.out.println("학력		인원");
			System.out.println("==================");
			
			while(rs.next()) {
				System.out.print(rs.getString("estr")+"\t");
				System.out.println(rs.getInt("count(a.rsnum)"));
				
			}
		} catch (SQLException se) {
			System.out.println("SELECT문에서 예외 발생");
			se.printStackTrace();
		} finally {
			try {
				if (rs!=null) rs.close();
				if (pstm!=null) pstm.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

	}

}
