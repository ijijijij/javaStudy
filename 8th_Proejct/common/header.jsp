<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>  

<%
Object sessionChk = session.getAttribute("memnum");
%>   
<header>
		<div class="wrap">
			<div class="header_nav">
				<h3 class="logo">
					<a href="../mainPage/mainpage.jsp"><img src="../common/picklogo.png"></a>
				</h3>
				<ul>
					<li><a href="#">Gallery</a></li>
					<li><a href="#">Community</a></li>
					<li><a href="#">Contractor</a></li>
					<li><a href="#">Creator #</a></li>
					<%if (sessionChk == null) {	%>
						<li><a href="#">Be Creator</a></li>
					<%} else {%>
						<li><a href="#">New Artwork</a></li>
					<%} %>
				</ul>
				<div class="utils">
					<div class="account">
						<a href="#">mypage</a>
					</div>
					<div class="login_join">
						<%if (sessionChk == null) {	%>
							<a href="#">Log in</a> <span>|</span> <a href="#">Join</a>
						<%} else {%>
							<a href="#">Log out</a>
						<%}	%>
					</div>
					<div class="search_bar">
						<input type="text" class="search_text" placeholder="검색어를 입력해주세요.">
						<button>Search</button>
					</div>
				</div>
			</div>
		</div>
	</header>    
