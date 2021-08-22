package funding;

public class Creator {
	String crenum;
	String memnum;
	String creprofile;
	String crenm;
	String memjoindate;
	String creDetail;
	String crePage;
	public Creator() {
		super();
	}
	
	public Creator(String crenum, String memnum, String creprofile, String crenm, String memjoindate) {
		super();
		this.crenum = crenum;
		this.memnum = memnum;
		this.creprofile = creprofile;
		this.crenm = crenm;
		this.memjoindate = memjoindate;
	}
	
	public Creator(String crenum, String memnum, String creprofile, String crenm, String creDetail, String crePage) {
		super();
		this.crenum = crenum;
		this.memnum = memnum;
		this.creprofile = creprofile;
		this.crenm = crenm;
		this.creDetail = creDetail;
		this.crePage = crePage;
	}

	public String getCrenum() {
		return crenum;
	}
	public void setCrenum(String crenum) {
		this.crenum = crenum;
	}
	public String getMemnum() {
		return memnum;
	}
	public void setMemnum(String memnum) {
		this.memnum = memnum;
	}
	public String getCreprofile() {
		return creprofile;
	}
	public void setCreprofile(String creprofile) {
		this.creprofile = creprofile;
	}
	public String getCrenm() {
		return crenm;
	}
	public void setCrenm(String crenm) {
		this.crenm = crenm;
	}
	public String getMemjoindate() {
		return memjoindate;
	}
	public void setMemjoindate(String memjoindate) {
		this.memjoindate = memjoindate;
	}

	public String getCreDetail() {
		return creDetail;
	}

	public void setCreDetail(String creDetail) {
		this.creDetail = creDetail;
	}

	public String getCrePage() {
		return crePage;
	}

	public void setCrePage(String crePage) {
		this.crePage = crePage;
	}
	
	
}
