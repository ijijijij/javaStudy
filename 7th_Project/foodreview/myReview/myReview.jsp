<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="foodreview.MyProfile"
	import="foodreview.Mypage_DAO" import="foodreview.MyReview"
	import="foodreview.Myreview_DAO"%>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head lang="ko">
<meta charset="utf-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<title>All You Can Eat</title>
<link rel="stylesheet" href="../include/style.css" />
<link rel="stylesheet" href="../include/mypage.css">
<link rel="stylesheet" href="myReview.css?ver9">
<style type="text/css">
.inner_nav_item>a:first-child:visited {
	background-color: orange;
}
</style>
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<%
	String searchType = "최신순";
	if (request.getParameter("searchType") != null) {
		searchType = request.getParameter("searchType");
	}
	String memNum = request.getParameter("memnum");
	// 회원
	Mypage_DAO dao = new Mypage_DAO();
	MyProfile profile = dao.getMemInfo(memNum);
	//리뷰
	Myreview_DAO revdao = new Myreview_DAO();
	ArrayList<MyReview> reviewList = revdao.getMyReview(memNum, searchType);
	%>

	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<h2>내 리뷰</h2>
				<div class="mypage_nav">
					<div class="page_nav">
						<ul>
							<li class="page_nav_item"><a
								href="../profile/profile.jsp?memnum=<%=memNum%>">프로필</a></li>
							<li class="page_nav_item"><a
								href="../myReview/myReview.jsp?memnum=<%=memNum%>">리뷰</a></li>
							<li class="page_nav_item"><a
								href="../mydeal/myDeal.jsp?memnum=<%=memNum%>">셰프딜</a></li>
							<li class="page_nav_item"><a href="#">리스트</a></li>
							<li class="page_nav_item"><a href="#">북마크</a></li>
						</ul>
					</div>
				</div>
				<div class="myreview_nav">
					<div class="inner_nav">
						<ul>
							<li class="inner_nav_item"><a
								href="../myReview/myReview.jsp?memnum=<%=memNum%>">작성리뷰</a></li>
							<li class="inner_nav_item"><a
								href="../myReview/footstamp.jsp?memnum=<%=memNum%>">발도장</a></li>
							<li class="inner_nav_item"><a
								href="../myReview/eyestamp.jsp?memnum=<%=memNum%>">눈도장</a></li>
						</ul>
					</div>
				</div>
				<div class="review_container">
					<div class="review_sort">
						<div class="count">
							<span>총 리뷰(<%=reviewList.size()%>)
							</span>
						</div>
						<div class="sort">
							<select name="searchType" onchange="listType(this)">
								<option value="최신순">최신순</option>
								<option value="평점낮은순"
									<%if (searchType.equals("평점낮은순"))
	out.println("selected");%>>평점낮은순</option>
								<option value="평점높은순"
									<%if (searchType.equals("평점높은순"))
	out.println("selected");%>>평점높은순</option>
							</select>
						</div>
					</div>
					<div class="review_list">
						<%
						if (reviewList.size() == 0) {
						%>
						<div class="no_list">작성한 리뷰가 없습니다.</div>
						<%
						} else {
						%>
						<%
						for (MyReview r : reviewList) {
						%>
						<div class="review_box">
							<div class="box_container">
								<div class="restThumb">
									<img src="<%=r.getRevImg()%>" id="thumbnail">
									<div class="grade">
										<div class="point">
											<span id="star"><%=r.getRevPoint()%>/5</span>
										</div>
										<div class="star">
											<%
											for (int i = 0; i < r.getRevPoint(); i++) {
											%>
											<span>★</span>
											<%
											}
											%>
										</div>
									</div>
								</div>
								<div class="revCon">
									<div class="restName">
										<a href="#"><%=r.getResttitle()%></a>
										<div class="update">
											<div class="edit">
												<a href="#">수정</a>
											</div>
											<div class="delete">
												<button onclick=deleteReview()>삭제</button>
											</div>
										</div>
									</div>
									<div class="postDate">
										<span><%=r.getRevPostDate()%></span>
									</div>
									<div class="text">
										<p><%=r.getRevContent()%></p>
									</div>
								</div>
							</div>
						</div>
						<%
						}
						%>
						<%
						}
						%>
					</div>
				</div>
				<div class="buy_history"></div>
			</article>
		</div>
	</section>
	<%@ include file="../include/footer.jsp"%>
	<script type="text/javascript" src="../include/mypageNav.js?ver1"></script>
	<script type="text/javascript" src="../myReview/myReview.js?ver4"></script>
</body>
<script type="text/javascript">
//정렬
function listType(obj){
	var type = obj.value;
	location.href="../myReview/myReview.jsp?memnum=<%=memNum%>"+"&searchType="+type;
}
</script>
</html>