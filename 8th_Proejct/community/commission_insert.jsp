<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
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

</head>
<body>
<%
String memNum = (String) session.getAttribute("memnum");

String title = request.getParameter("write_title");
String write_to = request.getParameter("write_to");
String content = request.getParameter("ir1");

Community_DAO dao = new Community_DAO();
dao.uploadCommission(title, content, memNum, write_to);
String bbsNum = dao.getThisNum(memNum);
%>
</body>
<script type="text/javascript">
	location.href="commission_view.jsp?bbsnum=<%=bbsNum%>";

</script>
</html>