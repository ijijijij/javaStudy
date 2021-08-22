<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*"
	import="foodreview.MyReview"
	import="foodreview.Myreview_DAO"
	import="foodreview.Stamp"
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
<link rel="stylesheet" href="myReview.css?ver6">
<link rel="stylesheet" href="stamp.css?ver1">
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<%
	String memNum = request.getParameter("memnum");
	//도장리스트 조회
	int kind = 0;
	Myreview_DAO rdao = new Myreview_DAO();
	ArrayList<Stamp> stpList = rdao.getStampList(memNum, kind);
	%>

	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<h2>내 리뷰</h2>
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
				<div class="myreview_nav">
					<div class="inner_nav">
						<ul>
							<li class="inner_nav_item"><a href="../myReview/myReview.jsp?memnum=<%=memNum%>">작성리뷰</a></li>
							<li class="inner_nav_item"><a href="../myReview/footstamp.jsp?memnum=<%=memNum%>">발도장</a></li>
							<li class="inner_nav_item"><a href="../myReview/eyestamp.jsp?memnum=<%=memNum%>">눈도장</a></li>
						</ul>
					</div>
				</div>
				<div class="stamp_container">
					<div class="stamp_sort">
						<div class="count"><span>발도장 수(<%=stpList.size()%>)</span></div>
					</div>
					<%if(stpList.size()==0){ %>
						<div class="no_list">저장한 발도장이 없습니다.</div>
						<%} else{ %>
					<div class="stamp_list">
						<%for(Stamp s : stpList){ %>
						<div class="stamp_box">
							<div class="stamp_container">
								<div class="thumbnail">
									<img id="thumb" src="<%=s.getRestimage()%>">
								</div>
								<div class="summary">
									<div class="title"><a href="#"><%=s.getResttitle()%></a></div>
									<div class="update">
										<div class="write_review"><a href="#">리뷰쓰기</a></div>
										<div class="delete">
											<a href="stamp_delete.jsp?memnum=<%=memNum%>&stampnum=<%=s.getStpNum()%>">삭제</a>
										</div>			
									</div>
								</div>
							</div>
						</div>
						<%} %>
					</div>
					<%} %>
				</div>			
			</article>
		</div>
	</section>
	<%@ include file="../include/footer.jsp"%>

	<script type="text/javascript" src="../include/mypageNav.js?ver1"></script>
	<script type="text/javascript" src="../myReview/myReview.js?ver1"></script>
</body>
</html>