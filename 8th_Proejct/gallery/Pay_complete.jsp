<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="funding.Pay_DAO"
	import="funding.Funding" import="model2.vo.Address"
	import="model2.vo.Payment"
	errorPage="../community/errorPage_noMemNum.jsp"%>
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
<style type="text/css">
#complete-notice{
	padding-top : 30px;
	color : #10223b;
	font-weight : normal;
}
#supporter-name{
	color : #07CBE5;
	font-weight : bold;
}

#pay-complete-tab{
	margin : 20px auto;
	width : 400px;
	height : 400px;
	border-collapse : collapse;
	text-align : center;
}
#pay-complete-tab tr{
	padding : 10px 10px;
	background-color : white;
	border-bottom : 1px solid #d5d5d5;
}
#pay-complete-tab th{
	background-color : #10223b;
	color : white;
}
.go-btn{
	padding : 20px 0;
	text-align : center;
}
#goGalleryBtn{
	width : 200px;
	height : 50px;
	background-color : #10223b;
	color : white;
	border : none;
	border-radius : 20px;
	font-size : 15pt;
	cursor : pointer;
}
</style>
</head>
<%
String memNum = (String) session.getAttribute("memnum");
String fdNum = request.getParameter("fdnum");
Pay_DAO dao = new Pay_DAO();
Funding funding = dao.getFundingDetail(fdNum);
ArrayList<Address> adrList = dao.getAddress(memNum);
//결제방법
String paymethod = request.getParameter("payMethod");
int paymethodNum= -1;
if(paymethod.equals("카드")){
	paymethodNum = 1;
} else{
	paymethodNum=2;
}
dao.support(memNum, fdNum, paymethodNum);
%>

<body>
	<%@include file="../common/header.jsp"%>
	<section>
		<div class="wrap">
			<article>
				<h3 id="complete-notice" align="center"><span id="supporter-name"><%=request.getParameter("name")%></span>님 후원이 완료되었습니다!</h3>
				<table id="pay-complete-tab">
					<tr>
						<th>펀딩명</th>
						<td><%=funding.getFdProjectNM()%></td>
					</tr>
					<tr>
						<th>후원금액</th>
						<td>20000원</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td><%=request.getParameter("phone")%></td>
					</tr>
					<tr>
						<th>배송지</th>
						<td><%=adrList.get(0).getDefaultAddress()+" "+adrList.get(0).getDetailAddress()%></td>
					</tr>
					<tr>
						<th>결제방법</th>
						<td><%=paymethod%></td>
					</tr>
				</table>
				<div class="go-btn"><input type="button" value="확인" id="goGalleryBtn"
					onclick="goGallery()"></div>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp"%>
</body>
<script type="text/javascript">
	function goGallery() {
		location.href = "Gallery.jsp";
	}
</script>
</html>