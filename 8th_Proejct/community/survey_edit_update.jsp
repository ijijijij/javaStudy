<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="funding.Survey_DAO"
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

</head>
<body>
<%
String title = request.getParameter("write_title");
String content = request.getParameter("ir2");
String bbsNum = request.getParameter("bbsnum");
String memNum = (String) session.getAttribute("memnum");

Survey_DAO dao = new Survey_DAO();
dao.editSurvey(title, content, bbsNum);
%>
</body>
<script type="text/javascript">
	location.href="survey_view.jsp?bbsnum=<%=bbsNum%>";
</script>
</html>