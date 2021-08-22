<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="funding.Community_DAO"
	import="java.util.*"
	import="funding.Commission"%>
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
<link rel="stylesheet" href="commission_write.css">

<%
String memNum = (String) session.getAttribute("memnum");
String bbsNum = request.getParameter("bbsnum");
Community_DAO dao = new Community_DAO();
Commission commission = dao.getCommission(bbsNum);
%>

</head>
<body>
	<%@include file="../common/header.jsp"%>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<div class="tabs">
					<input id="survey" type="radio" name="tab_item"> 
					<label class="tab_item" for="survey">Survey</label> 
					<input id="commission" type="radio" name="tab_item" checked> 
					<label class="tab_item" for="commission">Commission</label>

					<div class="tab_content" id="survey_content">survey contents</div>
					<div class="tab_content" id="commission_content">
						<h2>New Commission</h2>
						<div class="write_wrap">
							<form id="commission_form" action="commission_edit_update.jsp?bbsnum=<%=bbsNum%>" method="post">
								<div class="to">
									<span>To.</span><label for="write_to"></label>
									<%=commission.getCmsToNick()%>
								</div>
								<input type="text" name="write_title" value="<%=commission.getBbsTitle()%>">
								<hr>
								<textarea name="ir1" id="ir1" rows="10" cols="100"><%=commission.getBbsContent()%></textarea>
								<div class="save">
									<input type="button" id="saveBtn" value="SAVE" onclick="submitContents()">
									<input type="button" id="cancelBtn" value="CANCEL" onclick="cancel()">
								</div>
							</form>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp"%>
	<script src="commission.js?ver1"></script>
	<script type="text/javascript" src="se2/js/service/HuskyEZCreator.js"
	charset="utf-8"></script>
</body>

<script type="text/javascript">

//스마트에디터 생성 코드
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 oAppRef: oEditors,
 elPlaceHolder: "ir1",
 sSkinURI: "se2/SmartEditor2Skin.html",
 fCreator: "createSEditor2"
});

//submit
function submitContents() {
	// 에디터의 내용이 textarea에 적용된다.
	oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
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
	var contentVal = document.getElementById("ir1").value;
	if(contentVal==""){
		alert("내용을 입력하세요");
		return false;
	}
	try {
		document.getElementById("ir1").form.submit();
	} catch(e) {}
}

function cancel(){
	location.href="commission_view.jsp?bbsnum=<%=bbsNum%>";
}

</script>
</html>