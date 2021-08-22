package foodreview;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Mypage_Controller c = new Mypage_Controller();
		
		//닉네임 변경
		//c.updateNick("mem-0002","오렌지케이크", new Model());
		//이메일 변경
		//c.updateMail(new Members("mem-0002"), "hello123@google.com", new Model());
		
		//리뷰 조회
		//c.getMyReview("mem-0001", "최신순", new Model());
		
		Myreview_DAO dao = new Myreview_DAO();
		ArrayList<Stamp> slist = dao.getStampList("mem-0001", 0);
		System.out.println(slist.size());
		
		int row = dao.deleteStamp("stp-0001");
		System.out.println("테스트 : "+row);
	}

}
