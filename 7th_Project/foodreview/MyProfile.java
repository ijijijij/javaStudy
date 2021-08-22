package foodreview;

public class MyProfile {
	private String memNum;
	private String memID;
	private String memPW;
	private String memnick;
	private String memMail;
	private String memimg;
	private int revNum;
	private int memPoint;
	public MyProfile() {
		super();
	}
	
	public MyProfile(String memNum) {
		super();
		this.memNum = memNum;
	}


	public MyProfile(String memNum, String memID, String memPW, String memnick, String memMail, String memimg, int revNum,
			int memPoint) {
		super();
		this.memNum = memNum;
		this.memID = memID;
		this.memPW = memPW;
		this.memnick = memnick;
		this.memMail = memMail;
		this.memimg = memimg;
		this.revNum = revNum;
		this.memPoint = memPoint;
	}
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	public String getMemPW() {
		return memPW;
	}
	public void setMemPW(String memPW) {
		this.memPW = memPW;
	}
	public String getMemnick() {
		return memnick;
	}
	public void setMemnick(String memnick) {
		this.memnick = memnick;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMemimg() {
		return memimg;
	}
	public void setMemimg(String memimg) {
		this.memimg = memimg;
	}
	public int getRevNum() {
		return revNum;
	}
	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
}
