<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="foodreview.MyProfile"
	import="foodreview.Mypage_DAO"%>
 <%
 	request.setCharacterEncoding("utf-8"); 
 	String path = request.getContextPath();
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="pwChg.css?ver2">
</head>
<body>
	<%
	Mypage_DAO dao = new Mypage_DAO();
	String memNum = request.getParameter("memnum");
	MyProfile profile = dao.getMemInfo(memNum); 
	%>
	<header>
		<div class="title"><h2>비밀번호 변경</h2></div>
	</header>
	<section>
	<form action="pwChg_input.jsp?memnum=<%=memNum%>" method="post" id="pwform">	
		<div class="input_wrap">
			<div class="pwInput"><span>변경전 비밀번호</span><input type="password" name="before"></div>
			<div class="pwInput"><span>새로운 비밀번호</span><input type="password" name="after"></div>
			<div class="pwInput"><span>비밀번호 확인</span><input type="password" name="afterChk"></div>
		</div>
		<div class="btn_wrap">
			<div class="btns">
				<input type="button" value="확인" name="chgbtn" onclick="pwChg()">
				<input type="button" value="취소" name="cancelbtn">
			</div>
		</div>
	</form>
	</section>
</body>
<script type="text/javascript">
	var pwbefore = document.querySelector("[name=before]");
	var pwafter = document.querySelector("[name=after]");
	var pwafterChk = document.querySelector("[name=afterChk]");
	var chgbtn = document.querySelector("[name=chgbtn]");
	var cancelbtn = document.querySelector("[name=cancelbtn]");
	function pwChg(){
		var pwreg = /^[a-zA-Z0-9]{8,12}$/;
		var pwb = pwbefore.value;
		var pwa = pwafter.value;
		var pwc = pwafterChk.value;
		if(pwb.trim()=='' || pwa.trim()==''){
			alert("비밀번호를 입력하세요.");
			return;
		}
		if(!pwreg.test(pwa)){
			alert("비밀번호는 8-12자리로 입력하세요.");
			return;
		}
		if(pwc!=pwa){
			alert("새로운 비밀번호와 입력한 비밀번호가 일치하지 않습니다.");
			return;
		} 
		if(pwb!="<%=profile.getMemPW()%>"){
			alert("비밀번호가 정확하지 않습니다.");
			return;
		}
		document.querySelector("#pwform").submit();
		return true;
	}
	
	cancelbtn.onclick=function(){
		window.close();
	}
	

</script>
</html>