package foodreview;

import java.util.ArrayList;

public class Mypage_Service {
	Mypage_DAO dao = new Mypage_DAO();
	Myreview_DAO rdao = new Myreview_DAO();
	
	//닉네임 변경
	public String updateNick(String memNum, String nick) {
		String changedNick = dao.updateNick(memNum, nick);
		System.out.println("======# 닉네임 변경 후 #======");
		System.out.println("[ 닉네임을 '"+changedNick+"'로 변경했습니다 ]");
		return changedNick;
	}
	
	//이메일 변경
	public String updateMail(String memNum, String mail) {
		String changedMail = dao.updateMail(memNum, mail);
		System.out.println("======# 이메일 변경 후 #======");
		System.out.println("[ 이메일을 '"+changedMail+"'로 변경했습니다 ]");
		return changedMail;
	}
	
	//리뷰 조회
	public ArrayList<MyReview> getMyReview(String memNum, String searchType){
		ArrayList<MyReview> reviewList = rdao.getMyReview(memNum, searchType);
		System.out.println(reviewList.size());
		return reviewList;
	}
}
