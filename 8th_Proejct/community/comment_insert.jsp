<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="funding.Community_DAO"
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

</head>
<body>
<%
String cmtContent = request.getParameter("comment-w");
String bbsNum = request.getParameter("bbsnum");

String memNum = (String) session.getAttribute("memnum");

Community_DAO dao = new Community_DAO();
Survey_DAO sdao = new Survey_DAO();
dao.regComments(memNum, cmtContent, bbsNum);
int bbscheck = sdao.checkCommentBBS(bbsNum);
%>
</body>
<script type="text/javascript">
	<%if (bbscheck >0){%>
		location.href="commission_view.jsp?bbsnum=<%=bbsNum%>";
	<%} else{%>
		location.href="survey_view.jsp?bbsnum=<%=bbsNum%>";
	<%}%>
	
</script>
</html>