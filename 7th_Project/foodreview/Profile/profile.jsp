<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="foodreview.MyProfile"
	import="foodreview.Mypage_DAO"
	import="foodreview.MyReview"
	import="foodreview.Myreview_DAO"
	import="java.util.*"
%>
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
<link
	href="https://fonts.googleapis.com/css2?family=Italianno&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="profile.css?ver.5">
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<%
	// 회원번호 샘플
		Mypage_DAO dao = new Mypage_DAO();
		String memNum = "mem-0001";
		MyProfile profile = dao.getMemInfo(memNum); 
	%>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<h2>마이페이지</h2>
				<div class="mypage_nav">
					<div class="page_nav">
						<ul>
							<li class="page_nav_item"><a href="../profile/profile.jsp?memnum=<%=memNum%>">프로필</a></li>
							<li class="page_nav_item"><a href="../myReview/myReview.jsp?memnum=<%=memNum%>">리뷰</a></li>
							<li class="page_nav_item"><a href="../mydeal/myDeal.jsp?memnum=<%=memNum%>">셰프딜</a></li>
							<li class="page_nav_item"><a href="#">리스트</a></li>
							<li class="page_nav_item"><a href="#">북마크</a></li>
						</ul>
					</div>
				</div>
				<form action="profileChg_input.jsp?memnum=<%=memNum%>" method="post" onsubmit="return validate()" name="profileform">
					<div class="profile_wrap">
						<div class="profile">
							<div class="profile_img">
								<img src="<%=profile.getMemimg()%>" name="profileImg"><br> 
								<div class="img">
									<label for="profileImg_input" id="imgChgBtn">프로필사진변경</label>
									<input type="file" id="profileImg_input"><br>
									<div class="etc">
										<span class="cnt">리뷰</span><span><%=profile.getRevNum()%></span><br> <span
											class="cnt">포인트</span><span><%=profile.getMemPoint()%></span>
									</div>
								</div>
							</div>
							<div class="profile_info">
								<div class="profile_title">
									<span class="title">My Profile</span>
									<input type="hidden" value="<%=memNum%>" name="num">
								</div>
								<div class="profile_id">
									<span class="head">아이디</span><span><%=profile.getMemID()%></span>
								</div>
								<div class="profile_pw">
									<span class="head">비밀번호</span><span><input type="button"
										value="비밀번호 변경"></span> 
								</div>
								<div class="profile_mail">
									<span class="head">이메일</span><span><input type="text"
										value="<%=profile.getMemMail()%>" name="mail"></span> 
								</div>
								<div class="profile_nickname">
									<span class="head">닉네임</span><span><input type="text"
										value="<%=profile.getMemnick()%>" name="nick"></span> 
								</div>
								<div class="profile_out">
									<input type="button" value="회원탈퇴" name="outbtn">
								</div>
							</div>
						</div>
					</div>
				<div class="btn_wrap">
					<input type="submit" value="저장">
					<input type="button" value="취소" name="cancelbtn">
				</div>
				</form>
			</article>
		</div>
	</section>
	<%@ include file="../include/footer.jsp"%>

	<script type="text/javascript" src="profile.js?ver7"></script>
	<script type="text/javascript" src="../include/mypageNav.js?ver1"></script>
</body>
<script type="text/javascript">
//비밀번호 변경
var pwChgBtn = document.querySelector(".profile_pw input[type=button]");
pwChgBtn.onclick = function() {
	window.open("pwChg.jsp?memnum="+"<%=memNum%>", "pwChg", "width=500 height=320");
	
}
</script>
</html>