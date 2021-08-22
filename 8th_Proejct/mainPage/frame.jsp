<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My One Pick</title>
<link rel="stylesheet" href="../common/style.css" type="text/css">
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<h2>여기에 꾸며주세요</h2>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp" %>
</body>
</html>