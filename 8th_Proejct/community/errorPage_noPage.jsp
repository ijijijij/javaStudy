<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"   
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
<body>

</body>
<script type="text/javascript">
	if(confirm("삭제되었거나 존재하지 않는 게시물입니다. '확인'을 누르면 메인으로 돌아갑니다.")){
		location.href="../mainpage/mainPage.jsp";
	}
</script>
</html>