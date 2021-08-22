<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="funding.Community_DAO"
	import="java.util.*"
	import="funding.Comments"
	import="funding.Survey"
	import="funding.Survey_DAO"%>
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
<link rel="stylesheet" href="bbs_css.css?ver4" type="text/css">
<link rel="stylesheet" href="list.css" type="text/css">
 <script src="https://code.jquery.com/jquery-3.6.0.js" type="text/javascript"></script>
<%
String memNum = (String) session.getAttribute("memnum");
System.out.println(memNum);
int pageNum = 1;
if(request.getParameter("pagenum")!=null){
	pageNum = Integer.parseInt(request.getParameter("pagenum"));
}
int grade = -1;
Survey_DAO dao = new Survey_DAO();
if(memNum!=null){
	grade = dao.checkMemGrade(memNum);
}


%>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<div class="tabs">
					<input id="survey" type="radio" name="tab_item" checked> <label
						class="tab_item" for="survey">Survey</label> <input
						id="commission" type="radio" name="tab_item" > <label
						class="tab_item" for="commission">Commission</label>

					<div class="tab_content" id="survey_content">
					<!-- 게시판 시작 -->
						<article id="bbs-view">
							<!-- 게시판 헤더 -->
							<section class="bbs-view-header">
								<span>Survey List</span>
								<%if(memNum!=null && grade==2){ %>
								<button id="btn-write" class="btn" onclick="writeSurvey()" style="visibility:visible">글쓰기</button>
								<%} %>
							</section>

							<!-- 게시판 본문 내용 -->
							<section id="bbs-view-con-holder">
								<table id="ListTab">
									<thead><tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr></thead>
									<tbody>
									<% 
									ArrayList<Survey> surveyList = dao.getSurveyList(10, pageNum);
									%>
									<%for(Survey survey : surveyList){ %>
									<tr>
										<td><%=survey.getSerialNum()%></td>
										<td><a href="survey_view.jsp?bbsnum=<%=survey.getBbsNum()%>"><%=survey.getBbsTitle()%></a></td>
										<td><%=survey.getWriterNick()%></td>
										<td><%=survey.getBbsDate()%></td>
										<td><%=survey.getViewcnt() %></td></tr>
									<%} %>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="5">
										<%if(pageNum !=1){ %>
											<a href="survey_list.jsp?pagenum=<%=pageNum-1%>"><span id="prev">이전</span></a>
										<%} %>
											<a href="survey_list.jsp?pagenum<%=pageNum%>"><%=pageNum%></a>
										<%if(dao.nextPage(10,pageNum+1)) {%>
											<a href="survey_list.jsp?pagenum=<%=pageNum+1%>"><span id="next">다음</span></a>
										<%} %>
											</td>
										</tr>
									</tfoot>
								</table>
							</section>
							<!-- 게시판 공통 푸터 -->
							
							<!-- 게시판 읽기 끝 -->
						</article>
					</div>
					<div class="tab_content" id="commission_content"></div>
				</div>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp"%>
	<script src="commission.js?ver1"></script>
</body>
<script type="text/javascript">

//버튼 함수
function writeSurvey(){
	location.href="survey_write.jsp";
}

</script>
</html>