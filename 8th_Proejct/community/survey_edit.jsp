<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*"
	import="funding.Survey"
	import="funding.Survey_DAO"%>
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
String bbsNum = request.getParameter("bbsnum");
Survey_DAO dao = new Survey_DAO();
Survey survey = dao.getSurvey(bbsNum);
//설문 항목
ArrayList<Survey> sqList = dao.getSurveyQuestion(bbsNum);
String minsvq = sqList.get(0).getSvqNum();
String maxsvq = null;
//설문 선택지
ArrayList<Survey> soFirstList = dao.getSurveyOption(minsvq);
ArrayList<Survey> soSecondList = null;
if(sqList.size()>1) {
	maxsvq = sqList.get(1).getSvqNum();
	soSecondList = dao.getSurveyOption(maxsvq);
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
								<form id="survey_form" action="survey_edit_update.jsp?bbsnum=<%=bbsNum%>" method="post">
									<input type="text" name="write_title" value="<%=survey.getBbsTitle()%>">
									<hr>
									<div class="img-upload">
										<label for="designImg" id="upload-img">Design Upload</label>
										<input type="file" id="designImg" name="designImg">
										<div class="img" style="display:none"><img src="#" name="img_preview"></div>
									</div>
									<textarea name="ir2" id="ir2" rows="10" cols="100"><%=survey.getBbsContent()%></textarea>
									<!-- 설문 내용 -->
									<div class="poll_wrap">
										<div class="poll0" style="display:block">
											<input disabled type="text" id="poll_title0" name="poll_title0" value="<%=sqList.get(0).getSvqTitle()%>"><hr>
											<div class="poll_option_group">
												<input disabled type="text" class="poll_option" name="poll_option0" value="<%=soFirstList.get(0).getSvo()%>">
												<%for(int i=1; i<soFirstList.size();i++){ %>
												<input disabled type="text" class="poll_option" name="poll_option0<%=i%>" value="<%=soFirstList.get(i).getSvo()%>">
												<%} %>
											</div>
										</div>
										<%if(maxsvq!=null){ %>
										<div class="poll1" style="display:block">
											<input disabled type="text" id="poll_title1" name="poll_title1" value="<%=sqList.get(1).getSvqTitle()%>"><hr>
											<div class="poll_option_group">
												<input disabled type="text" class="poll_option" name="poll_option1" value="<%=soSecondList.get(0).getSvo()%>">
												<%for(int i=1; i<soSecondList.size();i++){ %>
												<input disabled type="text" class="poll_option" name="poll_option1<%=i%>" value="<%=soSecondList.get(i).getSvo()%>">
												<%} %>
											</div>
										</div>
										<%} %>
									</div>
									<!-- 버튼 -->
									<div class="save">
										<input type="button" id="saveBtn" value="SAVE" onclick="save()">
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

//버튼
function cancel(){
	location.href="survey_view.jsp?bbsnum=<%=bbsNum%>";
}

function save(){
	var frm = document.querySelector("#survey_form");
	frm.submit();
}

</script>
</html>