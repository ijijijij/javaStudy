<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="funding.Community_DAO"
	import="java.util.*"
	import="funding.Comments"
	import="funding.Commission"
	import="funding.Community_DAO"%>
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
session.setAttribute("memnum","mem-0001");
String memNum=(String) session.getAttribute("memnum");
int pageNum = 1;
if(request.getParameter("pagenum")!=null){
	pageNum = Integer.parseInt(request.getParameter("pagenum"));
}
Community_DAO dao = new Community_DAO();
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
						id="commission" type="radio" name="tab_item"  checked> <label
						class="tab_item" for="commission">Commission</label>

					<div class="tab_content" id="survey_content"></div>
					<div class="tab_content" id="commission_content">
					<!-- 게시판 시작 -->
						<article id="bbs-view">
							<!-- 게시판 헤더 -->
							<section class="bbs-view-header">
								<span>Commission List</span>
								<%if(memNum!=null){ %>
								<button id="btn-write" class="btn" onclick="writeCommission()" style="visibility:visible">글쓰기</button>
								<%} %>
							</section>

							<!-- 게시판 본문 내용 -->
							<section id="bbs-view-con-holder">
								<table id="ListTab">
									<thead><tr><th>번호</th><th>제목</th><th>크리에이터</th><th>작성자</th><th>작성일</th><th>조회수</th></tr></thead>
									<tbody>
									<% 
									ArrayList<Commission> cmsList = dao.getCommissionList(10, pageNum);
									%>
									<%for(Commission commission : cmsList){ %>
									<tr>
										<td><%=commission.getSerialNum()%></td>
										<td><a href="commission_view.jsp?bbsnum=<%=commission.getBbsNum()%>"><%=commission.getBbsTitle()%></a></td>
										<td><%=commission.getCmsToNick()%></td>
										<td><%=commission.getWriterNick()%></td>
										<td><%=commission.getBbsDate()%></td>
										<td><%=commission.getViewcnt() %></td></tr>
									<%} %>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="5">
										<%if(pageNum !=1){ %>
											<a href="commission_list.jsp?pagenum=<%=pageNum-1%>"><span id="prev">이전</span></a>
										<%} %>
											<a href="commission_list.jsp?pagenum=<%=pageNum%>"><%=pageNum%></a>
										<%if(dao.nextPage(10,pageNum+1)) {%>
											<a href="commission_list.jsp?pagenum=<%=pageNum+1%>"><span id="next">다음</span></a>
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
				</div>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp"%>
	<script src="commission.js?ver1"></script>
</body>
<script type="text/javascript">

//버튼 함수
function writeCommission(){
	location.href="commission_write.jsp";
}

</script>
</html>