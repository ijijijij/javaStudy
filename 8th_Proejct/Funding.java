package funding;

public class Funding {
	String fdNum;
	String creNum;
	int fdThemeNum;
	String fdProjectNM;
	int fdTargetPrice;
	String fdImg;
	int fdCategoryNum;
	String fdRegiDT;
	String fdExpDT;
	String fdTags;
	String fdStoryImg;
	String fdStorySum;
	String fdStory;
	String fdRefund;
	String fdPolicy;
	int statusnum;
	int lastDay;
	String creNM;
	public Funding() {
		super();
	}
	
	public Funding(String fdNum, String creNum, int fdThemeNum, String fdProjectNM, int fdTargetPrice, String fdImg,
			int fdCategoryNum, String fdRegiDT, String fdExpDT, int statusnum, int lastDay, String creNM) {
		super();
		this.fdNum = fdNum;
		this.creNum = creNum;
		this.fdThemeNum = fdThemeNum;
		this.fdProjectNM = fdProjectNM;
		this.fdTargetPrice = fdTargetPrice;
		this.fdImg = fdImg;
		this.fdCategoryNum = fdCategoryNum;
		this.fdRegiDT = fdRegiDT;
		this.fdExpDT = fdExpDT;
		this.statusnum = statusnum;
		this.lastDay = lastDay;
		this.creNM = creNM;
	}

	public Funding(String fdNum, String creNum, int fdThemeNum, String fdProjectNM, int fdTargetPrice, String fdImg,
			int fdCategoryNum, String fdRegiDT, String fdExpDT, String fdTags, String fdStoryImg, String fdStorySum,
			String fdStory, String fdRefund, String fdPolicy, int statusnum, int lastDay, String creNM) {
		super();
		this.fdNum = fdNum;
		this.creNum = creNum;
		this.fdThemeNum = fdThemeNum;
		this.fdProjectNM = fdProjectNM;
		this.fdTargetPrice = fdTargetPrice;
		this.fdImg = fdImg;
		this.fdCategoryNum = fdCategoryNum;
		this.fdRegiDT = fdRegiDT;
		this.fdExpDT = fdExpDT;
		this.fdTags = fdTags;
		this.fdStoryImg = fdStoryImg;
		this.fdStorySum = fdStorySum;
		this.fdStory = fdStory;
		this.fdRefund = fdRefund;
		this.fdPolicy = fdPolicy;
		this.statusnum = statusnum;
		this.lastDay = lastDay;
		this.creNM = creNM;
	}

	public String getFdNum() {
		return fdNum;
	}
	public void setFdNum(String fdNum) {
		this.fdNum = fdNum;
	}
	public String getCreNum() {
		return creNum;
	}
	public void setCreNum(String creNum) {
		this.creNum = creNum;
	}
	public int getFdThemeNum() {
		return fdThemeNum;
	}
	public void setFdThemeNum(int fdThemeNum) {
		this.fdThemeNum = fdThemeNum;
	}
	public String getFdProjectNM() {
		return fdProjectNM;
	}
	public void setFdProjectNM(String fdProjectNM) {
		this.fdProjectNM = fdProjectNM;
	}
	public int getFdTargetPrice() {
		return fdTargetPrice;
	}
	public void setFdTargetPrice(int fdTargetPrice) {
		this.fdTargetPrice = fdTargetPrice;
	}
	public String getFdImg() {
		return fdImg;
	}
	public void setFdImg(String fdImg) {
		this.fdImg = fdImg;
	}
	public int getFdCategoryNum() {
		return fdCategoryNum;
	}
	public void setFdCategoryNum(int fdCategoryNum) {
		this.fdCategoryNum = fdCategoryNum;
	}
	public String getFdRegiDT() {
		return fdRegiDT;
	}
	public void setFdRegiDT(String fdRegiDT) {
		this.fdRegiDT = fdRegiDT;
	}
	public String getFdExpDT() {
		return fdExpDT;
	}
	public void setFdExpDT(String fdExpDT) {
		this.fdExpDT = fdExpDT;
	}
	public String getFdTags() {
		return fdTags;
	}
	public void setFdTags(String fdTags) {
		this.fdTags = fdTags;
	}
	public String getFdStoryImg() {
		return fdStoryImg;
	}
	public void setFdStoryImg(String fdStoryImg) {
		this.fdStoryImg = fdStoryImg;
	}
	public String getFdStorySum() {
		return fdStorySum;
	}
	public void setFdStorySum(String fdStorySum) {
		this.fdStorySum = fdStorySum;
	}
	public String getFdStory() {
		return fdStory;
	}
	public void setFdStory(String fdStory) {
		this.fdStory = fdStory;
	}
	public String getFdRefund() {
		return fdRefund;
	}
	public void setFdRefund(String fdRefund) {
		this.fdRefund = fdRefund;
	}
	public String getFdPolicy() {
		return fdPolicy;
	}
	public void setFdPolicy(String fdPolicy) {
		this.fdPolicy = fdPolicy;
	}
	public int getStatusnum() {
		return statusnum;
	}
	public void setStatusnum(int statusnum) {
		this.statusnum = statusnum;
	}

	public int getLastDay() {
		return lastDay;
	}

	public void setLastDay(int lastDay) {
		this.lastDay = lastDay;
	}

	public String getCreNM() {
		return creNM;
	}

	public void setCreNM(String creNM) {
		this.creNM = creNM;
	}
	
	
}
