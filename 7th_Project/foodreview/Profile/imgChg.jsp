<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	request.setCharacterEncoding("utf-8"); //요청값에 대한 한글 encoding 처리
 	String path = request.getContextPath();
 	//기준 경로 설정 \jspexp\src\main\webapp
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="imgChg.css?ver2">
</head>
<body>
	<header>
		<div class="title"><h2>프로필이미지 변경</h2></div>
	</header>
	<section>
	<form action="imgChg_input" method="post" name="imgChgForm" onsubmit="chgImg()">
		<div class="input_wrap">
			<input type="file" accept="img/*" name="pickedimg">
		</div>
		<div class="btn_wrap">
			<div class="btns">
				<input type="submit" value="확인" name="chgbtn">
				<input type="button" value="취소" name="cancelbtn">
			</div>
		</div>
	</form>
	</section>
</body>
<script type="text/javascript">
	var pickedimg = document.querySelector("[name=pickedimg]");
	var chgbtn = document.querySelector("[name=chgbtn]");
	function chgImg(){
		if(pickedimg.value==null){
			window.close();
		}else {
			request.getParameter("profileImg").attr("src",pickedimg.value);
			alert("프로필 이미지 변경 완료");
			window.close();
		}
	}
	
	var cancelbtn = document.querySelector("[name=cancelbtn]");
	cancelbtn.onclick=function(){
		window.close();
	}
</script>
</html>