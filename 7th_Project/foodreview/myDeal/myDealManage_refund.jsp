<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="foodreview.Mydeal"
	import="foodreview.Mydeal_DAO"%>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head lang="ko">
<meta charset="utf-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<title>All You Can Eat</title>
<link rel="stylesheet" href="../include/style.css?ver1" />
<link rel="stylesheet" href="../include/mypage.css">
<link rel="stylesheet" href="../mydeal/myDeal.css?ver1">
<link rel="stylesheet" href="../mydeal/myDealManage_refund.css?ver1">
<style type="text/css">
.state>a {
	color: orange;
}
</style>
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<%
	String memNum = request.getParameter("memnum");
	String cdNum = request.getParameter("cdnum");
	Mydeal_DAO dao = new Mydeal_DAO();
	ArrayList<Mydeal> availableList = dao.getAvailableDealList(memNum);
	%>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<h2>내 셰프딜</h2>
				<div class="mypage_nav">
					<div class="page_nav">
						<ul>
							<li class="page_nav_item"><a
								href="../profile/profile.jsp?memnum=<%=memNum%>">프로필</a></li>
							<li class="page_nav_item"><a
								href="../myReview/myReview.jsp?memnum=<%=memNum%>">리뷰</a></li>
							<li class="page_nav_item"><a
								href="../mydeal/myDeal.jsp?memnum=<%=memNum%>">셰프딜</a></li>
							<li class="page_nav_item"><a href="#">리스트</a></li>
							<li class="page_nav_item"><a href="#">북마크</a></li>
						</ul>
					</div>
				</div>
				<div class="mydeal_nav">
					<div class="inner_nav">
						<ul>
							<li class="inner_nav_item"><a
								href="../mydeal/myDeal.jsp?memnum=<%=memNum%>">구매내역</a></li>
							<li class="inner_nav_item"><a
								href="../mydeal/myDealManage.jsp?memnum=<%=memNum%>">구매관리</a></li>
						</ul>
					</div>
				</div>
				<div class="refund_container">
					<div class="mydeal_history">
						<div class="history_title">
							<span>환불정보 입력</span>
						</div>
						<div class="refund_input">
							<table id="refund_tab">
								<tr>
									<th>이름</th>
									<td><input type="text" name="refund_name"></td>
								</tr>
								<tr>
									<th>은행선택</th>
									<td><select>
											<option>국민kb</option>
											<option>신한</option>
											<option>하나</option>
											<option>농협</option>
											<option>우리</option>
									</select></td>
								</tr>
								<tr>
									<th>계좌번호</th>
									<td><input type="text" name="refund_account"></td>
								</tr>
							</table>
							<div class="refund_btn">
								<input type="button" id="refund_btn" value="확인">
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
	<%@ include file="../include/footer.jsp"%>

	<script type="text/javascript" src="../include/mypageNav.js?ver1"></script>
</body>
<script type="text/javascript">
var rname= document.querySelector("[name=refund_name]");
var account = document.querySelector("[name=refund_account]");
var refund_btn = document.querySelector("#refund_btn");
refund_btn.onclick=function(){
	if(rname.value==""){
		alert("이름을 입력하세요");
		rname.focus();
	} else if(account.value==""){
		alert("계좌번호를 입력하세요");
		account.focus();
	}else{
		location.href="refund.jsp?memnum=<%=memNum%>&cdnum=<%=cdNum%>";
	}
}

</script>
</html>