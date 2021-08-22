package foodreview;

public class Stamp {
	String stpNum;
	String memNum;
	String restNum;
	String resttitle;
	String restimage;
	int kind;
	public Stamp() {
		super();
	}
	


	public Stamp(String stpNum, String memNum, String restNum, String resttitle, String restimage, int kind) {
		super();
		this.stpNum = stpNum;
		this.memNum = memNum;
		this.restNum = restNum;
		this.resttitle = resttitle;
		this.restimage = restimage;
		this.kind = kind;
	}



	public String getStpNum() {
		return stpNum;
	}



	public void setStpNum(String stpNum) {
		this.stpNum = stpNum;
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



	public String getResttitle() {
		return resttitle;
	}

	public void setResttitle(String resttitle) {
		this.resttitle = resttitle;
	}

	public String getRestimage() {
		return restimage;
	}

	public void setRestimage(String restimage) {
		this.restimage = restimage;
	}

	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
}
