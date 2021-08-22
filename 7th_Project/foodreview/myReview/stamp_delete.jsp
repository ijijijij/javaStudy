<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="foodreview.Myreview_DAO"%>
 <%
 	request.setCharacterEncoding("utf-8"); //요청값에 대한 한글 encoding 처리
 	String path = request.getContextPath();
 	//기준 경로 설정 \jspexp\src\main\webapp
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
//삭제
	String memNum = request.getParameter("memnum");
	String stpNum = request.getParameter("stampnum");
	Myreview_DAO dao = new Myreview_DAO();
	dao.deleteStamp(stpNum);
%>

</body>
<script type="text/javascript">
	alert("도장이 삭제되었습니다.");
	location.href="eyestamp.jsp?memnum=<%=memNum%>";
</script>
</html>