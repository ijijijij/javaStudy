<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"
	import="funding.Survey_DAO"%>
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
	<%
	String memNum = (String) session.getAttribute("memnum");
	String title = request.getParameter("write_title");
	String content = request.getParameter("ir2");
	//설문제목
	String poll_title0 = request.getParameter("poll_title0");
	String poll_title1 = request.getParameter("poll_title1");
	//설문 선택지
	String poll_option0 = request.getParameter("poll_option0");
	String poll_option1 = request.getParameter("poll_option1");
	//설문 선택지 배열
	ArrayList<String> poll_option0s = new ArrayList<String>();
	ArrayList<String> poll_option1s = new ArrayList<String>();
	//null값 제외한 선택지 추가
	poll_option0s.add(poll_option0);
	for (int i = 1; i < 5; i++) {
		String option0 = request.getParameter("poll_option0" + i);
		poll_option0s.add(option0);
	}
	poll_option0s.removeAll(Arrays.asList("",null));
	if (poll_title1 != "") {
		poll_option1s.add(poll_option1);
		for (int j = 1; j < 5; j++) {
			String option1 = request.getParameter("poll_option1" + j);
			poll_option1s.add(option1);
		}
		poll_option1s.removeAll(Arrays.asList("",null));
	}
	
	Survey_DAO dao = new Survey_DAO();
	dao.uploadSurvey(memNum, title, content, poll_title0, poll_option0s, poll_title1, poll_option1s);
	%>

</body>
<script type="text/javascript">
	alert("완료!");
	location.href="survey_view.jsp";
</script>
</html>