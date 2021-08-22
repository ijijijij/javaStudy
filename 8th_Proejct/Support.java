package funding;

public class Support {
	String sptNum;
	String fdNum;
	String memNum;
	String sptOdDT;
	int sptPrice;
	int sptOdNum;
	int sptPayNum;
	public Support() {
		super();
	}
	public Support(String sptNum, String fdNum, String memNum, String sptOdDT, int sptPrice, int sptOdNum,
			int sptPayNum) {
		super();
		this.sptNum = sptNum;
		this.fdNum = fdNum;
		this.memNum = memNum;
		this.sptOdDT = sptOdDT;
		this.sptPrice = sptPrice;
		this.sptOdNum = sptOdNum;
		this.sptPayNum = sptPayNum;
	}
	public String getSptNum() {
		return sptNum;
	}
	public void setSptNum(String sptNum) {
		this.sptNum = sptNum;
	}
	public String getFdNum() {
		return fdNum;
	}
	public void setFdNum(String fdNum) {
		this.fdNum = fdNum;
	}
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public String getSptOdDT() {
		return sptOdDT;
	}
	public void setSptOdDT(String sptOdDT) {
		this.sptOdDT = sptOdDT;
	}
	public int getSptPrice() {
		return sptPrice;
	}
	public void setSptPrice(int sptPrice) {
		this.sptPrice = sptPrice;
	}
	public int getSptOdNum() {
		return sptOdNum;
	}
	public void setSptOdNum(int sptOdNum) {
		this.sptOdNum = sptOdNum;
	}
	public int getSptPayNum() {
		return sptPayNum;
	}
	public void setSptPayNum(int sptPayNum) {
		this.sptPayNum = sptPayNum;
	}
	
	
}
