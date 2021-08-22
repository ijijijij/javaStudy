<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="funding.Community_DAO"
	import="java.util.*"
	import="funding.Commission"
	import="funding.Comments"
	errorPage="errorPage_noPage.jsp"
	%>
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
<link rel="stylesheet" href="commission_write.css">
<link rel="stylesheet" href="bbs_css.css?ver5" type="text/css">
 <script src="https://code.jquery.com/jquery-3.6.0.js" type="text/javascript"></script>
<%
String memNum = (String) session.getAttribute("memnum");
String bbsNum = request.getParameter("bbsnum");
Community_DAO dao = new Community_DAO();
//조회수 증가
dao.addViewCount(bbsNum);
//삭제된 게시물인 경우 에러 발생
Commission commission = dao.getCommission(bbsNum);
if(commission.getStatusNum()==0){
	System.out.println(1/0);
}
//댓글
ArrayList<Comments> commentList = dao.getComments(bbsNum);
%>

</head>
<body>
	<%@include file="../common/header.jsp"%>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<div class="tabs">
					<input id="survey" type="radio" name="tab_item"> <label
						class="tab_item" for="survey">Survey</label> <input
						id="commission" type="radio" name="tab_item" checked> <label
						class="tab_item" for="commission">Commission</label>

					<div class="tab_content" id="survey_content">survey contents</div>
					<div class="tab_content" id="commission_content">
						<!-- 게시판 시작 -->
						<article id="bbs-view">
							<!-- 게시판 헤더 -->
							<section class="bbs-view-header">
								<span>Commission View</span>
								<%if(memNum!=null) {%>
								<button id="btn-write" class="btn" onclick="writeCommission()" style="visibility:visible">글쓰기</button>
								<%} %>
							</section>

							<!-- 게시물 읽기 시작 -->
							<!-- 게시물 정보 -->
							<article id="bbs-view-info">
								작성자 <strong><span class="info-author"><%=commission.getWriterNick()%></span></strong> 조회 <strong><i
									class="info-hit" ><%=commission.getViewcnt()%></i></strong> <strong
									class="info-date">작성일 <%=commission.getBbsDate() %></strong>
							</article>
							<!-- 게시물 제목 -->
							<header>
								<span id="bbs-view-title"><%=commission.getBbsTitle() %></span>
							</header>
							<!-- 게시물 본문 내용 -->
							<section id="bbs-view-con-holder">
								<div id="bbs-view-con"><%=commission.getBbsContent() %></div>
							</section>

							<!-- 게시물 공통 푸터 -->
							<!-- 버튼 -->
							<article id="bbs-view-footer">
								<div id="bbs-view-btn-holder">	
									<div id="bbs-viw-agree-holder"><strong>Agree
								 <span id="bbs-view-agree"><%=commission.getCmsAgree()%></span></strong>
								 </div>
									<%-- 밖으로 튀어나가면 css에서 margin-left 조절해주세요 ㅠㅠ --%>
									<%if(memNum.equals(commission.getWriterNum())) {%>
									<button class="btn" id="edit" onclick="edit()">수정</button>
									<button class="btn" id="delete" onclick="deleteCommission()">삭제</button>
									<%} else{ %>
									<button class="btn" id="agree" onclick="agree()">Agree</button>
									<button class="btn" id="report" onclick="reportbbs()">신고</button>
									<%} %>
									<button class="btn" id="List" onclick="goCmsList()">목록</button>
								</div>
								<!-- 댓글 입력 -->
								<div id="comment-w-holder">
									<form id="comment-form" action="comment_insert.jsp?bbsnum=<%=commission.getBbsNum()%>" method="post">
									<span>댓글 입력: </span>
									<textarea rows="3" cols="60" class="comment-w" id="comment-w"
										name="comment-w" placeholder="여기에 댓글 입력.."></textarea>
									<button class="comment-btn" id="submit">등록</button>	
									</form>
									
								</div>
								<%if(commentList!=null){ 
								for(Comments comment : commentList) {%>
								<section id="comment-holder">
									<span><%=comment.getCmtDate()%></span>
									<span><a href="report.jsp?cmtnum=<%=comment.getCmtNum()%>&bbsnum=<%=bbsNum%>&writernum=<%=comment.getMemNum()%>" id="report">신고</a></span>
									<br>
									<span><%=comment.getMemnick()%> :</span>
									<div class="comment"><%=comment.getCmtContent() %></div>
								</section>
								<%} %>
								<%} %>
							</article>
							<!-- 게시물 읽기 끝 -->
						</article>

					</div>
				</div>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp"%>
	<script src="commission.js?ver1"></script>
	<script type="text/javascript" src="se2/js/service/HuskyEZCreator.js"
		charset="utf-8"></script>
</body>

<script type="text/javascript">
function writeCommission(){
	location.href="commission_write.jsp";
}	

function agree(){
	location.href="commission_agree.jsp?bbsnum=<%=commission.getBbsNum()%>";
}
function reportbbs(){
	if(confirm("신고하시겠습니까?")){
		location.href="report.jsp?bbsnum=<%=bbsNum%>&writernum=<%=commission.getWriterNum()%>";
	}
}

function edit(){
	location.href="commission_edit.jsp?bbsnum=<%=commission.getBbsNum()%>";
}

function deleteCommission(){
	location.href="commission_delete.jsp?bbsnum=<%=commission.getBbsNum()%>";
}

function goCmsList(){
	location.href="commission_list.jsp?pagenum=1";
}

</script>
</html>