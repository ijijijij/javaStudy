package funding;

public class Commission {
	int serialNum;
	String bbsNum;
	String bbsTitle;
	String bbsContent;
	String bbsDate;
	int statusNum;
	String writerNum;
	String writerNick;
	String cmsNum;
	String cmsTo;
	String cmsToNick;
	int cmsAgree;
	int viewcnt;
	
	public Commission() {
		super();
	}
	
	public Commission(String bbsNum) {
		super();
		this.bbsNum = bbsNum;
	}

	public Commission(int serialNum, String bbsNum, String bbsTitle, String bbsContent, String bbsDate,
			String writerNum, String writerNick, String cmsNum, String cmsToNick, int cmsAgree, int viewcnt) {
		super();
		this.serialNum = serialNum;
		this.bbsNum = bbsNum;
		this.bbsTitle = bbsTitle;
		this.bbsContent = bbsContent;
		this.bbsDate = bbsDate;
		this.writerNum = writerNum;
		this.writerNick = writerNick;
		this.cmsNum = cmsNum;
		this.cmsToNick = cmsToNick;
		this.cmsAgree = cmsAgree;
		this.viewcnt = viewcnt;
	}

	public Commission(String bbsNum, String bbsTitle, String bbsContent, String bbsDate, int statusNum,
			String writerNum, String writerNick, String cmsNum, String cmsTo, String cmsToNick, int cmsAgree) {
		super();
		this.bbsNum = bbsNum;
		this.bbsTitle = bbsTitle;
		this.bbsContent = bbsContent;
		this.bbsDate = bbsDate;
		this.statusNum = statusNum;
		this.writerNum = writerNum;
		this.writerNick = writerNick;
		this.cmsNum = cmsNum;
		this.cmsTo = cmsTo;
		this.cmsToNick = cmsToNick;
		this.cmsAgree = cmsAgree;
	}

	public Commission(String bbsNum, String bbsTitle, String bbsContent, String bbsDate, int statusNum,
			String writerNum, String writerNick, String cmsNum, String cmsTo, String cmsToNick, int cmsAgree,
			int viewcnt) {
		super();
		this.bbsNum = bbsNum;
		this.bbsTitle = bbsTitle;
		this.bbsContent = bbsContent;
		this.bbsDate = bbsDate;
		this.statusNum = statusNum;
		this.writerNum = writerNum;
		this.writerNick = writerNick;
		this.cmsNum = cmsNum;
		this.cmsTo = cmsTo;
		this.cmsToNick = cmsToNick;
		this.cmsAgree = cmsAgree;
		this.viewcnt = viewcnt;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
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

	public int getStatusNum() {
		return statusNum;
	}

	public void setStatusNum(int statusNum) {
		this.statusNum = statusNum;
	}

	public String getWriterNum() {
		return writerNum;
	}

	public void setWriterNum(String writerNum) {
		this.writerNum = writerNum;
	}

	public String getWriterNick() {
		return writerNick;
	}

	public void setWriterNick(String writerNick) {
		this.writerNick = writerNick;
	}

	public String getCmsNum() {
		return cmsNum;
	}

	public void setCmsNum(String cmsNum) {
		this.cmsNum = cmsNum;
	}

	public String getCmsTo() {
		return cmsTo;
	}

	public void setCmsTo(String cmsTo) {
		this.cmsTo = cmsTo;
	}

	public String getCmsToNick() {
		return cmsToNick;
	}

	public void setCmsToNick(String cmsToNick) {
		this.cmsToNick = cmsToNick;
	}

	public int getCmsAgree() {
		return cmsAgree;
	}

	public void setCmsAgree(int cmsAgree) {
		this.cmsAgree = cmsAgree;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	
}
