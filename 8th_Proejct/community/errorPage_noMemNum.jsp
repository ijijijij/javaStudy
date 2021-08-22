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
	if(confirm("로그인 한 회원만 사용할 수 있습니다. 로그인해주세요.")){
		location.href="../mainpage/mainPage.jsp";
	}
</script>
</html>