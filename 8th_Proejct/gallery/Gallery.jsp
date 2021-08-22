<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="funding.Pay_DAO" import="funding.Funding"
	import="java.util.*"%>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My One Pick</title>
<link rel="stylesheet" href="Gallery.css?ver1" type="text/css">
<link rel="stylesheet" href="../common/style.css" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
</head>
<%
session.setAttribute("memnum","mem-0001");
//String memNum = (String) session.getAttribute("memnum");
Pay_DAO pdao = new Pay_DAO();
ArrayList<Funding> fundingList = pdao.getFundingList();
%>
<body>
	<%@include file="../common/header.jsp"%>
	
		<div id="menubar">
			<ul>
				<li>ALL</li>
				<li>CLOTHES</li>
				<li>ACCESSORIES</li>
				<li>OFFICE SUPPLIES</li>
				<li>　　</li>
				<li>　　</li>
				<li>　　</li>
				<li>　　</li>
			</ul>
		</div>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<div class="filters">
					<input type="button" id="fielter" value="최신순">
					<input type="button" id="fielter" value="인기순">				
				</div>
				<br>
				<h3 style="position:relative"> 상품 (<%=fundingList.size()%>) </h3>
				<div class="funding_list">
				<%for(Funding funding : fundingList){ 
					int[] currentNum = pdao.getCurrent(funding.getFdNum());
				%>
					<div id="fund_detail">
						<img src="<%=funding.getFdImg()%>" id="fund_img">
						<h3><a href="Funding_detail.jsp?fdnum=<%=funding.getFdNum()%>"><%=funding.getFdProjectNM()%></a></h3>
						<h3>CLOTHES<span style="font-weight:bold; color: #138396; font-size:23px;"> I </span>
						<span><a href="#"  style=" color: #138396; font-size:15px;"><%=funding.getCreNM()%></a></span></h3>
						<div id="percent"><div id="percent_num"></div></div>
						<div class="info">
							<span style="font-weight:bold; color: #138396; font-size:23px" id="current_per"><%=(currentNum[0]*100/funding.getFdTargetPrice())%>%</span>
							<span style="color: #D5D5D5; font-size:15px;" id="goal_price"><%=funding.getFdTargetPrice()%>원</span>
							<span style=" color: #D5D5D5; font-size:15px;" id="last_day">마감<%=funding.getLastDay()%>일전</span>
						</div>
					</div>
				<%} %>	
				</div>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp" %>
</body>
</html>