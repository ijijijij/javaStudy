package funding;

public class Follow {
	String memNum;
	String followMemNum;
	public Follow() {
		super();
	}
	public Follow(String memNum, String followMemNum) {
		super();
		this.memNum = memNum;
		this.followMemNum = followMemNum;
	}
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public String getFollowMemNum() {
		return followMemNum;
	}
	public void setFollowMemNum(String followMemNum) {
		this.followMemNum = followMemNum;
	}
}
