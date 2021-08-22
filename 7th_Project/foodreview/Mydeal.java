package foodreview;

import java.util.Date;

public class Mydeal {
	String memNum;
	String restNum;
	String dNum;
	String cdNum;
	String resttitle;
	String dname;
	String dimage;
	int spoint;
	Date todate;
	int dpricebf;
	int dpercent;
	int dpriceaf;
	String methodstatus;
	String status;
	int duecount;
	
	public Mydeal() {
		super();
	}
	
	public Mydeal(String memNum, String restNum, String dNum, String cdNum, String resttitle, String dname,
			String dimage, int spoint, Date todate, int dpricebf, int dpercent, int dpriceaf, String methodstatus,
			String status, int duecount) {
		super();
		this.memNum = memNum;
		this.restNum = restNum;
		this.dNum = dNum;
		this.cdNum = cdNum;
		this.resttitle = resttitle;
		this.dname = dname;
		this.dimage = dimage;
		this.spoint = spoint;
		this.todate = todate;
		this.dpricebf = dpricebf;
		this.dpercent = dpercent;
		this.dpriceaf = dpriceaf;
		this.methodstatus = methodstatus;
		this.status = status;
		this.duecount = duecount;
	}

	public String getMemNum() {
		return memNum;
	}

	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}

	public String getRestNum() {
		return restNum;
	}

	public void setRestNum(String restNum) {
		this.restNum = restNum;
	}

	public String getdNum() {
		return dNum;
	}

	public void setdNum(String dNum) {
		this.dNum = dNum;
	}

	public String getCdNum() {
		return cdNum;
	}

	public void setCdNum(String cdNum) {
		this.cdNum = cdNum;
	}

	public String getResttitle() {
		return resttitle;
	}
	public void setResttitle(String resttitle) {
		this.resttitle = resttitle;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDimage() {
		return dimage;
	}
	public void setDimage(String dimage) {
		this.dimage = dimage;
	}
	public int getSpoint() {
		return spoint;
	}
	public void setSpoint(int spoint) {
		this.spoint = spoint;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public int getDpricebf() {
		return dpricebf;
	}
	public void setDpricebf(int dpricebf) {
		this.dpricebf = dpricebf;
	}
	public int getDpercent() {
		return dpercent;
	}
	public void setDpercent(int dpercent) {
		this.dpercent = dpercent;
	}
	public int getDpriceaf() {
		return dpriceaf;
	}
	public void setDpriceaf(int dpriceaf) {
		this.dpriceaf = dpriceaf;
	}
	public String getMethodstatus() {
		return methodstatus;
	}
	public void setMethodstatus(String methodstatus) {
		this.methodstatus = methodstatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getDuecount() {
		return duecount;
	}

	public void setDuecount(int duecount) {
		this.duecount = duecount;
	}
	
}
