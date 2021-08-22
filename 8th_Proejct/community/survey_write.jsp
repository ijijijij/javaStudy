<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*"
	errorPage="errorPage_noMemNum"%>
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
<link rel="stylesheet" href="survey_write.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js" type="text/javascript"></script>

<%
String memNum = (String) session.getAttribute("memnum");
//비로그인 회원 접근 금지
if(memNum==null){
	System.out.println(1/0);
}

%>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<div class="tabs">
					<input id="survey" type="radio" name="tab_item" checked> 
					<label class="tab_item" for="survey">Survey</label> 
					<input id="commission" type="radio" name="tab_item"> 
					<label class="tab_item" for="commission">Commission</label>

					<div class="tab_content" id="survey_content">
						<h2>New Survey</h2>
							<div class="write_wrap">
								<form id="survey_form" action="survey_insert.jsp" method="post">
									<input type="text" name="write_title" placeholder="제목">
									<hr>
									<div class="img-upload">
										<label for="designImg" id="upload-img">Design Upload</label>
										<input type="file" id="designImg" name="designImg">
										<div class="img" style="display:none"><img src="#" name="img_preview"></div>
									</div>
									<textarea name="ir2" id="ir2" rows="10" cols="100"></textarea>
									<!-- 설문 내용 -->
									<div class="poll_plus"><input type="button" id="plus_poll" value="Add Poll"></div>
									<div class="poll_wrap">
										<div class="poll">
											<input type="text" id="poll_title" name="poll_title" placeholder="투표 제목을 입력하세요"><hr>
											<div class="poll_option_group">
												<input type="text" class="poll_option" name="poll_option" placeholder="선택지를 입력하세요">
											</div>
											<input type="button" id="plus_option" value="Add Option" onclick="addOption(this)">
										</div>
									</div>
									<!-- 버튼 -->
									<div class="save">
										<input type="button" id="saveBtn" value="SAVE" onclick="submitContents()">
										<input type="button" id="cancelBtn" value="CANCEL" onclick="cancel()">
									</div>
								</form>
							</div>
						</div>
					<div class="tab_content" id="commission_content"></div>
				</div>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp"%>
	<script src="commission.js?ver1"></script>
	<script src="survey_write.js"></script>
	<script type="text/javascript" src="se2/js/service/HuskyEZCreator.js"
	charset="utf-8"></script>
</body>

<script type="text/javascript">
//선택지 개수
var option = document.querySelectorAll(".poll_option");
var optionCnt = option.length;
console.log(optionCnt);

//선택지 추가
var j =1;
function addOption(e){
	var id = e.getAttribute('id');
	var idNum = id.charAt(id.length-1);
	var pollOptionSec = document.querySelector(".poll"+idNum+" .poll_option_group");
	if(j<5){
		var option = "<input type=\"text\" class=\"poll_option\" name=\"poll_option"+idNum+(j++)+"\" placeholder=\"선택지를 입력하세요\">";
		pollOptionSec.innerHTML += option;
	} else if(Number(idNum)==0 && j==5){
		alert("선택지는 5개까지만 입력가능합니다.");
	} else 	if(Number(idNum)==1 &&pollOptionSec.childElementCount!=5){
		j = 1;
	} else if(j==5){
		alert("선택지는 5개까지만 입력가능합니다.");
	}
}

//항목추가
	var cnt = 1;
	$("#plus_poll").on("click",function(){
		if(cnt>2) {
			alert("투표는 2개까지만 등록할 수 있습니다.");
			return false;
		}
		$("div.poll").clone().appendTo(".poll_wrap");
		$(".poll_wrap").find("div.poll").each(function(i){
			$(this).attr("class","poll"+i);
		});
		$(".poll_wrap").find("input#poll_title").each(function(i){
			$(this).attr("id","poll_title"+i);
		});
		$(".poll_wrap").find("input[name=poll_title]").each(function(i){
			$(this).attr("name","poll_title"+i);
		});
		
		$(".poll_wrap").find("input[name=poll_option]").each(function(i){
			$(this).attr("name","poll_option"+i);
		});
		$(".poll_wrap").find("input#plus_option").each(function(i){
			$(this).attr("id","plus_option"+i);
		});	
		if(cnt==1){
			$("div.poll0").css("display","block");			
		} else{
			$("div.poll1").css("display","block");
		}
		cnt++;
	});
	//설문 유효성검사
	function pollChk(){
		var pollTitle0 = document.querySelector("#poll_title0");
		var pollTitle1 = document.querySelector("#poll_title1");
		
		if(cnt==2){
			if(pollTitle0.value==""){
				alert("설문제목을 입력하세요");
				return false;
			}
			var optArry = document.querySelectorAll(".poll0 .poll_option");
			for(var opt of optArry) {
				if(opt.value==""){
					alert("선택지를 입력하세요"); 
					return false;
				}
			}
		} else if(cnt>=3){
			if(pollTitle0.value=="" || pollTitle1.value==""){
				alert("설문제목을 입력하세요");
				return false;
			}
			var optArry = document.querySelectorAll(".poll_option");
			for(var opt of optArry){
				if(opt.value=="") {
					alert("선택지를 입력하세요"); 
					return false;
				}
			}
		}
	}
//스마트에디터 생성 코드
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 oAppRef: oEditors,
 elPlaceHolder: "ir2",
 sSkinURI: "se2/SmartEditor2Skin.html",
 fCreator: "createSEditor2"
});

//submit
function submitContents() {
	// 에디터의 내용이 textarea에 적용된다.
	oEditors.getById["ir2"].exec("UPDATE_CONTENTS_FIELD", []);
	//유효성 검사
	var titleVal = document.querySelector("[name=write_title]");
	if(titleVal.value==""){
		alert("제목을 입력하세요");
		titleVal.focus();
		return false;
	}
	var maxlength=20;
	if (titleVal.value.length > maxlength) {
		alert("제목은 20자까지 입력 가능합니다.");
		titleVal.focus();
		return false;
	} 
	if(cnt==1){
		alert("설문을 추가하세요");
		return false;
	}
	if(pollChk()==false){
		return false;
	}
	var contentVal = document.getElementById("ir2").value;
	if(contentVal=='<p></p>' || contentVal==""){
		alert("내용을 입력하세요");
		return false;
	}
	try {
		document.getElementById("ir2").form.submit();
	} catch(e) {}
}

function cancel(){
	location.href="survey_list.jsp?pagenum=1";
}

</script>
</html>