package foodreview;

public class Mypage_Controller {
	Mypage_Service service = new Mypage_Service();
	
	//닉네임 변경
	public String updateNick(String memNum, String nick, Model d) {
		d.addAttribute("닉네임 변경", service.updateNick(memNum, nick));
		return "화면 호출";
	}
	
	//이메일 변경
	public String updateMail(String memNum, String mail, Model d) {
		d.addAttribute("이메일 변경", service.updateMail(memNum, mail));
		return "화면 호출";
	}
	
	//리뷰 조회
	public String getMyReview(String memNum, String searchType, Model d) {
		d.addAttribute("리뷰 조회", service.getMyReview(memNum, searchType));
		return "화면 호출";
	}
}
