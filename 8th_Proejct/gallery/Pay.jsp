<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"
	import="funding.Pay_DAO" import="funding.Funding"
	import="model2.vo.Address" import="model2.vo.Payment"
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
<link rel="stylesheet" href="Pay.css?ver3" type="text/css">
<link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
  />
<script src="https://code.jquery.com/jquery-3.6.0.js"
	type="text/javascript"></script>
</head>
<%
String memNum = (String) session.getAttribute("memnum");
String fdNum = request.getParameter("fdnum");
Pay_DAO dao = new Pay_DAO();
Funding funding = dao.getFundingDetail(fdNum);
int[] current = dao.getCurrent(fdNum);
String memId = dao.getSupporterMail(memNum);
ArrayList<Address> adrList = dao.getAddress(memNum);
ArrayList<Payment> paymentList = dao.getPayment(memNum);
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
						<h3>모인금액</h3><span><%=current[0]%>원</span> 
						<h3>목표금액</h3><span><%=funding.getFdTargetPrice()%>원</span>
						<div id="percent"><div id="percent_num"></div></div>
						<span id="percent-num"><%=(current[0]*100/funding.getFdTargetPrice())%>%</span>
					</div>
				</div>		
				<hr>
				<form id="support-form" method="post" action="Pay_complete.jsp?fdnum=<%=fdNum%>">
				<div class="support-detail-tab">
					<div class="supporter-tab">
						<div class="supporter-info tab">
							<h3>후원자 정보</h3>
							<table id="tab01">
								<tr><th>연락처</th><td><input type="text" name="phone" placeholder="핸드폰번호 입력"></td></tr>
								<tr><th>이메일</th><td><%=memId%></td></tr>
							</table>
						</div>
						<div class="supporter-info address tab">
							<h3>배송지</h3>
							<table id="tab02">
								<tr><th>이름</th><td><input type="text" name="name" placeholder="이름 입력"></td></tr>
								<tr><th>주소</th><td><%=adrList.get(0).getDefaultAddress()+" "+adrList.get(0).getDetailAddress()%></td></tr>
							</table>
						</div>
						<div class="supporter-info paymentmethod tab">
							<h3>결제수단</h3>
							<input type="radio" name="payMethod" value="카드" checked onclick="showCardInfo(this)">카드
							<input type="radio" name="payMethod" value="카카오 페이" onclick="showCardInfo(this)">카카오 페이
							<div class="card-pay" style="visibility:visible">
								<i class="far fa-credit-card" style="font-size:90px"></i>
								<div class="card-info">
									<h4><%=paymentList.get(0).getBankName()%>카드</h4>
									<span><%=paymentList.get(0).getCardNum() %></span>
								</div>
							</div>
						</div>
					</div>
					<div class="payment-tab">
						<h3>결제</h3>
						<div class="payment-info">
							<h4>최종 결제금액 : 20000원</h4>
							<p>프로젝트 성공 시 결제는 <span><%=funding.getFdExpDT()%></span>에 진행됩니다. 프로젝트가 무산되거나 취소된 경우 예약된 결제는 자동취소됩니다.</p>
						</div>
						<div class="pay-btn"><input type="button" id="payBtn" value="결제하기" onclick="pay()"></div>
					</div>
				</div>
				</form>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp"%>
</body>
<script type="text/javascript">
	//결제(후원)진행
	function pay(){
		var phone = document.querySelector("[name=phone]");
		if(phone.value==""){
			alert("핸드폰 번호를 입력하세요");
			return false;
		}
		var payMethod = document.querySelectorAll("[name=payMethod]");
		if(!payMethod[0].checked && !payMethod[1].checked){
			alert("결제 방법을 선택하세요");
			return false;
		}
		var form = document.querySelector("#support-form");
		if(confirm("후원하시겠습니까?")){
			form.submit();
		}
	}
	
	var cardInfo = document.querySelector(".card-pay");
	function showCardInfo(e){
		if(e.value=="카드"){
			cardInfo.style.visibility="visible";
		}else{
			cardInfo.style.visibility="hidden";
		}
	}
	
</script>
</html>