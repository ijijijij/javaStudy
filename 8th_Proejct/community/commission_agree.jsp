<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="funding.Community_DAO"
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
</head>
<%
String memNum = (String) session.getAttribute("memnum");
session.setAttribute("memnum", memNum);
Community_DAO dao = new Community_DAO();
String bbsNum = request.getParameter("bbsnum");
dao.agree(bbsNum);
%>
<body>
	
</body>
<script type="text/javascript">
location.href="commission_view.jsp?bbsnum=<%=bbsNum%>";
</script>
</html>