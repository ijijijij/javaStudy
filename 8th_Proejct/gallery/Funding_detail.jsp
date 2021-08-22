<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"
	import="funding.Pay_DAO" import="funding.Funding"
	import="funding.Creator"
	errorPage="../community/errorPage_noPage.jsp"%>
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
<link rel="stylesheet" href="Funding_detail.css?ver1" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"
	type="text/javascript"></script>
</head>
<%
String memNum = (String) session.getAttribute("memnum");
String fdNum = request.getParameter("fdnum");
Pay_DAO dao = new Pay_DAO();
Funding funding = dao.getFundingDetail(fdNum);
Creator creator = dao.getFundingCreator(funding.getCreNum());
int[] currentNum = dao.getCurrent(fdNum);
%>
<body>
	<%@include file="../common/header.jsp"%>
	<section>
		<div class="wrap">
			<article>
				<div class="funding-introduction">
					<div class="funding-detail-img">
						<img src="<%=funding.getFdImg()%>" id="detail-img">
					</div>
					<div class="funding-detail-desc">
						<h2><%=funding.getFdProjectNM()%></h2>
						<div class="funding-current">
							<h4>후원자수</h4>
							<span><%=currentNum[1]%>명</span>
							<h4>모인 금액</h4>
							<span><%=currentNum[0]%>원</span>
							<h4>목표 금액</h4>
							<span><%=funding.getFdTargetPrice()%>원</span>
						</div>
						<div id="percent">
							<div id="percent_num"></div>
						</div>
						<div class="detail-info">
							<span style="font-weight: bold; color: #138396; font-size: 23px"
								id="current_per"><%=(currentNum[0]*100/funding.getFdTargetPrice())%>%</span> 
							<span style="color: #D5D5D5; font-size: 15px;" id="last_day">마감<%=funding.getLastDay()%>일전</span>
						</div>
						<div class="funding_btns">
							<input type="button" value="후원하기" id="support_btn" onclick="support()">
							<input type="button" value="신고" id="report_btn" onclick="report()">
						</div>
					</div>
				</div>
				<div class="funding-detail-tab">
					<div class="funding-tab">
						<div class="funding-story tab">
							<h3>펀딩 스토리</h3>
							<img src="<%=funding.getFdStoryImg()%>">
							<br>
							<p><%=funding.getFdStorySum()%></p>
							<br>
							<p><%=funding.getFdStory() %></p>
						</div>
						<div class="funding-support-info tab">
							<div class="funding-refund tab">
								<h3>반환정책</h3>
								<p><%=funding.getFdRefund()%></p>
							</div>
							<div class="fundig-policy tab">
								<h3>A/S정책</h3>
								<p><%=funding.getFdPolicy()%></p>
							</div>
						</div>
					</div>
					<div class="creator-tab">
						<h3>크리에이터 소개</h3>
						<div class="creator-summary">
							<div class="creatorimg">
								<img src="<%=creator.getCreprofile()%>" id="creator-img">
							</div>
							<div class="creator-info">
								<h4><%=funding.getCreNM()%></h4>
								<p><%=creator.getCreDetail()%></p>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp"%>
</body>

<script type="text/javascript">
function support(){
	<%if(memNum==null){%>
		alert("로그인 해주세요.");
	<%} else{%>
		location.href="Pay.jsp?fdnum=<%=fdNum%>";
	<%}%>
}
function report(){
	<%if(memNum==null){%>
		alert("로그인 후 이용해주세요");
	<%} else{%>
		if(confirm("신고하시겠습니까?")){
			location.href="Funding_report.jsp?fdnum=<%=fdNum%>&crenum=<%=funding.getCreNum()%>";
		}
	<%}%>
	
}

</script>
</html>