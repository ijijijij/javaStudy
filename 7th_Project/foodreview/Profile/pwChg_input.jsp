<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="foodreview.MyProfile"
	import="foodreview.Mypage_DAO"%>
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
	Mypage_DAO dao = new Mypage_DAO();
	//비밀번호 변경
	String memNum = request.getParameter("memnum");
	dao.updatePW(memNum, request.getParameter("after")); 
	%>
</body>
<script type="text/javascript">
	alert("비밀번호가 변경되었습니다.");
	window.close();
</script>
</html>