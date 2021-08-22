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
String writerNum = request.getParameter("writernum");
String cmtNum = request.getParameter("cmtnum");
String bbsNum = request.getParameter("bbsnum");
Community_DAO dao = new Community_DAO();
Survey_DAO sdao = new Survey_DAO();
//bbs종류 확인
int chkbbs = sdao.checkCommentBBS(bbsNum);
if(cmtNum==null){
	int bbsReportResult = dao.reportbbs(memNum, writerNum, bbsNum);
} else{
	int cmtReportResult = dao.reportcomment(memNum, writerNum, cmtNum);
}
%>
<body>
	
</body>
<script type="text/javascript">
alert("신고가 접수되었습니다.");
	<%if(cmtNum==null && chkbbs==0){%>
		location.href="survey_list.jsp?pagenum=1";
	<%} else if(cmtNum==null && chkbbs>0){ %>
		location.href="commission_list.jsp?pagenum=1";
	<%} else if(cmtNum!=null && chkbbs==0){%>
		location.href="survey_view.jsp?bbsnum=<%=bbsNum%>";
	<%} else{%>
		location.href="commission_view.jsp?bbsnum=<%=bbsNum%>";
	<%}%>
	
</script>
</html>