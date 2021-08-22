<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="foodreview.Stamp"
	import="foodreview.Myreview_DAO"
    %>
 <%
 	request.setCharacterEncoding("utf-8"); //요청값에 대한 한글 encoding 처리
 	String path = request.getContextPath();
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%String memNum = request.getParameter("memnum"); 
	String stpNum = request.getParameter("stampnum");
	Myreview_DAO dao = new Myreview_DAO();
	dao.goFootStamp(memNum, stpNum);
	%>
	
</body>
<script type="text/javascript">
location.href="../myReview/eyestamp.jsp?memnum=<%=memNum%>";

</script>
</html>