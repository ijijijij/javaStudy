<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="funding.Community_DAO"
    import="funding.Survey_DAO"
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
<%
String memNum = (String) session.getAttribute("memnum");
Community_DAO cdao = new Community_DAO();
Survey_DAO dao = new Survey_DAO();
String bbsNum = request.getParameter("bbsnum");

String optionNum1 = request.getParameter("srv1opt");
String optionNum2 = request.getParameter("srv2opt");

dao.selectOption(optionNum1);
if(optionNum2!=null){
	dao.selectOption(optionNum2);
}

%>
<body>
</body>
<script type="text/javascript">
location.href="survey_view.jsp?bbsnum=<%=bbsNum%>";
</script>
</html>