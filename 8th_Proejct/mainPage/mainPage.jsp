<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="funding.mainPage_DAO"
	import="funding.Pay_DAO"
	import="funding.Creator"
	import="funding.Funding"
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
<link rel="stylesheet" href="../common/style.css" type="text/css">
<link rel="stylesheet" href="mainPage.css" type="text/css">
<style type="text/css">
.profiles{
	padding : 20px;
	display : inline-block;
	text-align : center;
}
.profile{
	float : left;
	padding : 0 10px;
	width : 140px;
}

.thumb img{
	width : 120px;
	height : 120px;
}

.nick {
	text-align : center;
	font-weight : bold;
	font-size : 14pt;
}
.list_title{
	color : gray;
	font-weight : lighter;
}
.prefix{
	color : #FE0D49;
	font-weight : bold;
}
.funding_list{
	display : inline-block;
	margin : 20px auto;
}

#fund_img {
	width: 300px;
	height: 200px;
}
#fund_detail {
	padding : 20px;
	font-family:'Noto Sans KR', sans-serif;
	float : left;
	width: 340px;
	margin : 0 auto;
}
#fund_detail:nth-child(4n){
	clear : both;
}
#percent_num {
	background-color: #138396; 
	height: 10px; 
	width:250px; 
}
#fund_detail span{
	font-weight : bold;
}
.info{
	text-align : left;
}
#last_day{
	margin-top : 10px;
	float : right;
}
#percent {
	background-color: #D5D5D5; 
	height: 10px; 
	width:300px;
	}
#percent_num {
	background-color: #138396;
	height: 10px; 
	width:200px;
	}
</style>
<script type="text/javascript">
	
</script>
</head>
<%
String memNum = (String) session.getAttribute("memnum");
mainPage_DAO dao = new mainPage_DAO();
ArrayList<Creator> newCreatorList = dao.getNewCreator();
ArrayList<Funding> newFundingList = dao.getNewFunding(); 
ArrayList<Funding> hotFundingList = dao.getHotFunding();
Pay_DAO pdao = new Pay_DAO();
%>
<body>
	<%@include file="../common/header.jsp"%>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<!-- 메인 슬라이드 -->
				<div class="main-slider">
					<div class="main-slide fade">
						<img src="image/ad1.jpg">
					</div>
					<div class="main-slide fade">
						<img src="image/ad2.jpg">
					</div>
					<div class="main-slide fade">
						<img src="http://lorempixel.com/580/250/nature/3">
					</div>
					<div class="main-dots">
						<span class="dot"></span> <span class="dot"></span> <span class="dot"></span>
					</div>
				</div>
				<br>
				<!-- 인기 크리에이터-->
				<h3 class="list_title"><span class="prefix">인기</span> 크리에이터</h3>
				<div class="hotCreator profiles">
					<%for(Creator creator : newCreatorList){ %>
					<div class="hotCreator profile">
						<div class="hotCreator thumb"><img src="<%=creator.getCreprofile()%>"></div>
						<div class="hotCreator nick"><span><a href="#?crenum=<%=creator.getCrenum()%>"><%=creator.getCrenm()%></a></span></div>
					</div>
					<%} %>
				</div>
				<!-- 신규 크리에이터-->
				<h3 class="list_title"><span class="prefix">신규</span> 크리에이터</h3>
				<div class="newCreator profiles">
					<%for(Creator creator : newCreatorList){ %>
					<div class="newCreator profile">
						<div class="newCreator thumb"><img src="<%=creator.getCreprofile()%>"></div>
						<div class="newCreator nick"><span><a href="#?crenum=<%=creator.getCrenum()%>"><%=creator.getCrenm()%></a></span></div>
					</div>
					<%} %>
				</div>
				<!-- 인기 펀딩-->
				<h3 class="list_title"><span class="prefix">인기</span> 펀딩</h3>
				<div class="funding_list">
				<%for(Funding hotfunding : hotFundingList){ 
				int[] hotcurrentNum = pdao.getCurrent(hotfunding.getFdNum());%>
					<div id="fund_detail">
						<img src="<%=hotfunding.getFdImg()%>" id="fund_img">
						<h3><a href="../gallery/Funding_detail.jsp?fdnum=<%=hotfunding.getFdNum()%>"><%=hotfunding.getFdProjectNM()%></a></h3>
						<h3>CLOTHES<span style="font-weight:bold; color: #138396; font-size:23px;"> I </span>
						<span style=" color: #138396; font-size:15px;"><%=hotfunding.getCreNM()%></span></h3>
						<div id="percent"><div id="percent_num"></div></div>
						<div class="info">
							<span style="font-weight:bold; color: #138396; font-size:23px" id="current_per"><%=(hotcurrentNum[0]*100/hotfunding.getFdTargetPrice())%>%</span>
							<span style="color: #D5D5D5; font-size:15px;" id="goal_price"><%=hotfunding.getFdTargetPrice()%>원</span>
							<span style=" color: #D5D5D5; font-size:15px;" id="last_day">마감<%=hotfunding.getLastDay()%>일전</span>
						</div>
					</div>
				<%} %>	
				</div>
				<!-- 신규 펀딩-->
				<h3 class="list_title"><span class="prefix">신규</span> 펀딩</h3>
				<div class="funding_list">
				<%for(Funding funding : newFundingList){
					int[] currentNum = pdao.getCurrent(funding.getFdNum());
					%>
					<div id="fund_detail">
						<img src="<%=funding.getFdImg()%>" id="fund_img">
						<h3><a href="../gallery/Funding_detail.jsp?fdnum=<%=funding.getFdNum()%>"><%=funding.getFdProjectNM()%></a></h3>
						<h3>CLOTHES<span style="font-weight:bold; color: #138396; font-size:23px;"> I </span>
						<span style=" color: #138396; font-size:15px;"><%=funding.getCreNM()%></span></h3>
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
	<%@include file="../common/footer.jsp"%>
</body>
<script type="text/javascript">
	//메인 슬라이드
	var slideIdx = 0;
	var slides = document.querySelectorAll(".main-slide");
	var dots = document.querySelectorAll(".main-dots .dot");
	show();
	function show() {
		slideIdx++;
		if (slideIdx > slides.length) {
			slideIdx = 1
		}
		for (var i = 0; i < slides.length; i++) {
			slides[i].style.display = "none";
		}
		for (var i = 0; i < dots.length; i++) {
			dots[i].className = dots[i].className.replace(" active", "");
		}
		slides[slideIdx - 1].style.display = "block";
		dots[slideIdx - 1].className += " active";
		setTimeout(show, 2000);
	}

</script>
</html>