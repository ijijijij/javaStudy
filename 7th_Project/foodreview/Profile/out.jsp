<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	request.setCharacterEncoding("utf-8"); //요청값에 대한 한글 encoding 처리
 	String path = request.getContextPath();
 	//기준 경로 설정 \jspexp\src\main\webapp
 %>    
<!DOCTYPE html>
<html>
    <head lang="ko">
        <meta charset="utf-8">
        <title>All You Can Eat</title>
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="out.css"/>
    </head>
    <body>
        <header>
            <div class="wrap">
                <div class="header_nav">
                    <h1 class="logo">
                        <a href="index.html"><img src="image/FOODlogo.png"></a>
                    </h1>
                    <ul>
                        <li><a href="#">로그인/프로필</a></li>
                        <li><a href="#">맛집커뮤니티</a></li>
                        <li><a href="#">맛집추천</a></li>
                        <li><a href="#">쉐프딜</a></li>
                    </ul>
                </div>
            </div>
        </header>
        <section>
            <div class="wrap">
                <article> <!-- article에 man-writh:1100px; -->
                    <div class="title">
						<span>회원탈퇴</span>
					</div>
					<div class="container">
						<span>회원탈퇴를 원하시면 비밀번호를 입력해주세요</span>
						<div class="pw_input"><span>비밀번호 입력</span><input type="password" name="outPW"></div>
						<div class="btn_wrap">
							<input type="submit" name="outBtn" value="확인">
							<input type="button" name="cancelbtn" value="취소">
						</div>
					</div>
                </article>
            </div>
        </section>
        <footer>
            <div class="wrap">
                <h5>회사정보</h5>
            </div>
        </footer>
    </body>
<script type="text/javascript">
var outbtn = document.querySelector("[name=outBtn]");
var cancelbtn = document.querySelector("[name=cancelbtn]");
cancelbtn.onclick=function(){
	location.href="profile.html";
}
</script>
</html>
