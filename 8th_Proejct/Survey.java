package funding;


public class Survey {
	int serialNum;
	String bbsNum;
	String bbsTitle;
	String bbsContent;
	String bbsDate;
	int statusNum;
	String writerNum;
	String writerNick;
	String srvNum;
	String svqNum;
	String svoNum;
	String srvThumb;
	String svqTitle;
	String svo;
	int svoCount;
	int viewcnt;
	
	public Survey() {
		super();
	}

	public Survey(int serialNum, String bbsNum, String bbsTitle, String bbsContent, String bbsDate,
			String writerNum, String writerNick, String srvNum, int viewcnt) {
		super();
		this.serialNum = serialNum;
		this.bbsNum = bbsNum;
		this.bbsTitle = bbsTitle;
		this.bbsContent = bbsContent;
		this.bbsDate = bbsDate;
		this.writerNum = writerNum;
		this.writerNick = writerNick;
		this.srvNum = srvNum;
		this.viewcnt = viewcnt;
	}

	public Survey(int serialNum, String bbsNum, String bbsTitle, String bbsContent, String bbsDate, int statusNum,
			String writerNum, String writerNick, String srvNum, String svqNum, String svoNum, String srvThumb,
			String svqTitle, String svo, int svoCount) {
		super();
		this.serialNum = serialNum;
		this.bbsNum = bbsNum;
		this.bbsTitle = bbsTitle;
		this.bbsContent = bbsContent;
		this.bbsDate = bbsDate;
		this.statusNum = statusNum;
		this.writerNum = writerNum;
		this.writerNick = writerNick;
		this.srvNum = srvNum;
		this.svqNum = svqNum;
		this.svoNum = svoNum;
		this.srvThumb = srvThumb;
		this.svqTitle = svqTitle;
		this.svo = svo;
		this.svoCount = svoCount;
	}

	public Survey(String bbsTitle, String bbsContent, String bbsDate, int statusNum, String writerNum,
			String writerNick, String srvNum, String srvThumb) {
		super();
		this.bbsTitle = bbsTitle;
		this.bbsContent = bbsContent;
		this.bbsDate = bbsDate;
		this.statusNum = statusNum;
		this.writerNum = writerNum;
		this.writerNick = writerNick;
		this.srvNum = srvNum;
		this.srvThumb = srvThumb;
	}


	public Survey(String bbsTitle, String bbsContent, String bbsDate, int statusNum, String writerNum,
			String writerNick, String srvNum, String srvThumb, int viewcnt) {
		super();
		this.bbsTitle = bbsTitle;
		this.bbsContent = bbsContent;
		this.bbsDate = bbsDate;
		this.statusNum = statusNum;
		this.writerNum = writerNum;
		this.writerNick = writerNick;
		this.srvNum = srvNum;
		this.srvThumb = srvThumb;
		this.viewcnt = viewcnt;
	}

	public Survey(String srvNum, String svqNum, String svqTitle) {
		super();
		this.srvNum = srvNum;
		this.svqNum = svqNum;
		this.svqTitle = svqTitle;
	}
	public Survey(String svoNum, String svo, int svoCount) {
		super();
		this.svoNum = svoNum;
		this.svo = svo;
		this.svoCount = svoCount;
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
	public String getSrvNum() {
		return srvNum;
	}
	public void setSrvNum(String srvNum) {
		this.srvNum = srvNum;
	}
	public String getSvqNum() {
		return svqNum;
	}
	public void setSvqNum(String svqNum) {
		this.svqNum = svqNum;
	}
	public String getSvoNum() {
		return svoNum;
	}
	public void setSvoNum(String svoNum) {
		this.svoNum = svoNum;
	}
	public String getSrvThumb() {
		return srvThumb;
	}
	public void setSrvThumb(String srvThumb) {
		this.srvThumb = srvThumb;
	}
	public String getSvqTitle() {
		return svqTitle;
	}

	public void setSvqTitle(String svqTitle) {
		this.svqTitle = svqTitle;
	}
	public String getSvo() {
		return svo;
	}

	public void setSvo(String svo) {
		this.svo = svo;
	}

	public int getSvoCount() {
		return svoCount;
	}
	public void setSvoCount(int svoCount) {
		this.svoCount = svoCount;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	
	
}
