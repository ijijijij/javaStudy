<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="foodreview.Mypage_DAO"
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
	String revNum = request.getParameter("reviewnum");
	Myreview_DAO revdao = new Myreview_DAO();
	revdao.deleteMyReview(memNum, revNum);
%>

</body>
<script type="text/javascript">
	alert("리뷰가 삭제되었습니다.");
	location.href="myReview.jsp?memnum=<%=memNum%>";
</script>
</html>