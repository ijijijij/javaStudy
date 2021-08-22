<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
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
String bbsNum = request.getParameter("bbsnum");
String memNum = (String) session.getAttribute("memnum");

Survey_DAO dao = new Survey_DAO();
dao.delSurvey(bbsNum);
%>
</body>
<script type="text/javascript">
alert("삭제되었습니다.");
	location.href="survey_list.jsp?pagenum=1";
</script>
</html>