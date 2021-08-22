package funding;

public class Comments {
	String cmtNum;
	String cmtContent;
	String memNum;
	String memnick;
	String cmtDate;
	String bbsNum;
	int statusNum;
	public Comments() {
		super();
	}
	
	public Comments(String cmtNum, String cmtContent, String memNum, String memnick, String cmtDate, String bbsNum,
			int statusNum) {
		super();
		this.cmtNum = cmtNum;
		this.cmtContent = cmtContent;
		this.memNum = memNum;
		this.memnick = memnick;
		this.cmtDate = cmtDate;
		this.bbsNum = bbsNum;
		this.statusNum = statusNum;
	}

	public String getCmtNum() {
		return cmtNum;
	}
	public void setCmtNum(String cmtNum) {
		this.cmtNum = cmtNum;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public String getBbsNum() {
		return bbsNum;
	}
	public void setBbsNum(String bbsNum) {
		this.bbsNum = bbsNum;
	}
	public int getStatusNum() {
		return statusNum;
	}
	public void setStatusNum(int statusNum) {
		this.statusNum = statusNum;
	}
	public String getMemnick() {
		return memnick;
	}
	public void setMemnick(String memnick) {
		this.memnick = memnick;
	}

	public String getCmtDate() {
		return cmtDate;
	}

	public void setCmtDate(String cmtDate) {
		this.cmtDate = cmtDate;
	}
}
	
