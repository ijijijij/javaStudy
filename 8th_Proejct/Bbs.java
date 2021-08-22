package funding;

public class Bbs {
	int serialNum;
	String bbsNum;
	String bbsTitle;
	String bbsContent;
	String bbsDate;
	String memNum;
	String memNick;
	int statusnum;
	public Bbs() {
		super();
	}
	public Bbs(String bbsNum, String bbsTitle, String bbsContent, String bbsDate, String memNum, int statusnum) {
		super();
		this.bbsNum = bbsNum;
		this.bbsTitle = bbsTitle;
		this.bbsContent = bbsContent;
		this.bbsDate = bbsDate;
		this.memNum = memNum;
		this.statusnum = statusnum;
	}
	public Bbs(int serialNum, String bbsNum, String bbsTitle, String bbsContent, String bbsDate, String memNum,
			String memNick, int statusnum) {
		super();
		this.serialNum = serialNum;
		this.bbsNum = bbsNum;
		this.bbsTitle = bbsTitle;
		this.bbsContent = bbsContent;
		this.bbsDate = bbsDate;
		this.memNum = memNum;
		this.memNick = memNick;
		this.statusnum = statusnum;
	}
	public String getBbsNum() {
		return bbsNum;
	}
	public void setBbsNum(String bbsNum) {
		this.bbsNum = bbsNum;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public String getBbsDate() {
		return bbsDate;
	}
	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public int getStatusnum() {
		return statusnum;
	}
	public void setStatusnum(int statusnum) {
		this.statusnum = statusnum;
	}
	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	
	
}
