<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
	import="foodreview.Mydeal"
	import="foodreview.Mydeal_DAO"
%>
 <%
 	request.setCharacterEncoding("utf-8"); 
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String memNum = request.getParameter("memnum");
String cdNum = request.getParameter("cdnum");
Mydeal_DAO dao = new Mydeal_DAO();
dao.refund(cdNum);
%>
	
</body>
<script type="text/javascript">
	alert("환불이 완료되었습니다.");
	location.href="myDealManage.jsp?memnum=<%=memNum%>";
</script>
</html>