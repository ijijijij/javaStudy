<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My One Pick</title>
<link rel="stylesheet" href="../common/style.css" type="text/css">
<link rel="stylesheet" href="bbs_css.css" type="text/css">
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<h2>여기에 꾸며주세요</h2>
				
			<!-- 게시판 시작 -->
			<article id="bbs-view">
				<!-- 게시판 헤더 -->
				<section class="bbs-view-header">
				<span>게시판</span>
				<button id="btn-write" class="btn" onclick="글쓰기함수()" style="">글쓰기</button>
				</section>
				
			<!-- 게시물 읽기 시작 -->
				<!-- 게시물 정보 -->
				<article id="bbs-view-info">
					작성자 <strong><span class="info-author">닉네임1</span></strong>
        			조회 <strong><i class="info-hit" aria-hidden="true"></i>12345</strong>
        			<strong class="info-date">작성일 2021-08-03</strong>
				</article>
				<!-- 게시물 제목 -->
				<header>
					<span id="bbs-view-title">타이틀</span>
				</header>
				<!-- 게시물 본문 내용 -->
				<section id="bbs-view-con-holder">
					<div id="bbs-view-con">
						본문내용
					</div>
				</section>
				
				<!-- 게시물 공통 푸터 -->
					<!-- 버튼 -->
				<article id="bbs-view-footer">
					<div id="bbs-view-btn-holder"> <%-- 밖으로 튀어나가면 css에서 margin-left 조절해주세요 ㅠㅠ --%>
						<button class="btn" id="edit" onclick="수정()">수정</button>
						<button class="btn" id="delete" onclick="삭제()">삭제</button>
						<button class="btn" id="List" onclick="목록으로()">목록</button>	
					</div>
					<!-- 댓글 입력 -->
					<div id="comment-w-holder">
						<span>댓글 입력: </span>
						<textarea rows="3" cols="60" class="comment-w" id="comment-w" name="comment-w" placeholder="여기에 댓글 입력.."></textarea>
						<button class="comment-btn" id="submit" onclick="댓글등록()">등록</button>
					</div>	
					<section id="comment-holder">
						<span>닉네임1:</span>
						<div class="comment">
							댓글 내용	
						</div>
					</section>
				</article>	
			<!-- 게시물 읽기 끝 -->	
			</article>
			
			
			
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp" %>
</body>
</html>