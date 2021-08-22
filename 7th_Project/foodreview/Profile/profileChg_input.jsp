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
	String memNum = request.getParameter("memnum");
	dao.updateNick(memNum, request.getParameter("nick"));
	dao.updateMail(memNum, request.getParameter("mail"));
	//dao.updateImg(new Members("mem-0002"), request.getParameter("profileImg")); 
	%>
</body>
<script type="text/javascript">
	alert("저장되었습니다.");
	location.href="profile.jsp?memnum=<%=memNum%>";
</script>
</html>