package foodreview;

import java.util.Date;

public class MyReview {
	String reviewNum;
	String restNum;
	String memNum;
	String resttitle;
	int revPoint;
	Date  revPostDate;
	String revContent;
	String revImg;
	
	public MyReview() {
		super();
	}
	
	
	public MyReview(String reviewNum, String restNum, String memNum, String resttitle, int revPoint, Date revPostDate,
			String revContent, String revImg) {
		super();
		this.reviewNum = reviewNum;
		this.restNum = restNum;
		this.memNum = memNum;
		this.resttitle = resttitle;
		this.revPoint = revPoint;
		this.revPostDate = revPostDate;
		this.revContent = revContent;
		this.revImg = revImg;
	}


	public String getReviewNum() {
		return reviewNum;
	}


	public void setReviewNum(String reviewNum) {
		this.reviewNum = reviewNum;
	}


	public String getRestNum() {
		return restNum;
	}


	public void setRestNum(String restNum) {
		this.restNum = restNum;
	}


	public String getMemNum() {
		return memNum;
	}


	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}


	public String getResttitle() {
		return resttitle;
	}

	public void setResttitle(String resttitle) {
		this.resttitle = resttitle;
	}


	public int getRevPoint() {
		return revPoint;
	}
	public void setRevPoint(int revPoint) {
		this.revPoint = revPoint;
	}
	public Date getRevPostDate() {
		return revPostDate;
	}
	public void setRevPostDate(Date revPostDate) {
		this.revPostDate = revPostDate;
	}
	public String getRevContent() {
		return revContent;
	}
	public void setRevContent(String revContent) {
		this.revContent = revContent;
	}
	public String getRevImg() {
		return revImg;
	}
	public void setRevImg(String revImg) {
		this.revImg = revImg;
	}
	
	
}
