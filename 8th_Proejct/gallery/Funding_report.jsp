<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="funding.Pay_DAO"
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
String fdNum = request.getParameter("fdnum");
String creNum = request.getParameter("crenum");
Pay_DAO dao = new Pay_DAO();
String writerNum = dao.getMemNum(creNum);
dao.reportFunding(memNum, writerNum, fdNum);
%>

<body>
	
</body>
<script type="text/javascript">
alert("신고가 접수되었습니다.");
location.href="Funding_detail.jsp?fdnum=<%=fdNum%>";
</script>
</html>