<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="funding.Community_DAO"
	import="java.util.*"
	errorPage="errorPage_noMemNum"
	%>
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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<%
String memNum = (String) session.getAttribute("memnum");
//비로그인 회원 접근 금지
if(memNum==null){
	System.out.println(1/0);
}
Community_DAO dao = new Community_DAO();
//ArrayList<String> followList = dao.getFollowList(memNum);
//int size = followList.size();
ArrayList<String> creatorIdList = dao.getCreatorIdList();
%>

<script type="text/javascript">
var autoSearchList = [];
//자동완성 follow목록 가져오기


//자동완성 creatorid목록 가져오기
<%for(int i=0; i<creatorIdList.size();i++){%>
	var creatorId = {};
	creatorId.label = "<%=creatorIdList.get(i)%>";
	creatorId.category="All";
	autoSearchList.push(creatorId);
<%}%>
//자동완성 jquery
$( function() {
    $.widget( "custom.catcomplete", $.ui.autocomplete, {
      _create: function() {
        this._super();
        this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
      },
      _renderMenu: function( ul, items ) {
        var that = this,
          currentCategory = "";
        $.each( items, function( index, item ) {
          var li;
          if ( item.category != currentCategory ) {
            ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
            currentCategory = item.category;
          }
          li = that._renderItemData( ul, item );
          if ( item.category ) {
            li.attr( "aria-label", item.category + " : " + item.label );
          }
        });
      }
    });
    var data = autoSearchList;
 
    $( "#write_to" ).catcomplete({
      delay: 0,
      source: data
    });
  } );
</script>
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
							<form id="commission_form" action="commission_insert.jsp" method="post">
								<div class="to">
									<span>To.</span><label for="write_to"></label>
									<input type="text" name="write_to"
										id="write_to" placeholder="의뢰하고 싶은 creator를 입력하세요">
								</div>
								<input type="text" name="write_title" placeholder="제목">
								<hr>
								<textarea name="ir1" id="ir1" rows="10" cols="100"></textarea>
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
	var toVal = document.querySelector("[name=write_to]");
	if(toVal.value==""){
		alert("대상 Creator를 입력하세요");
		toVal.focus();
		return false;
	}
	var creatorReg = /^[가-힣a-zA-Z0-9]{1,12}$/;
	if (!creatorReg.test(toVal.value)) {
		alert("크리에이터 아이디는 최소 1자리, 최대 12자리입니다.");
		toVal.value="";		
		toVal.focus();
		return false;
	} 
	var creatorID = document.querySelector("#write_to");
	var creatorIdVal = creatorID.value;
	if(autoSearchList.some(item => item.label === creatorIdVal)==false){
		alert("존재하지 않는 ID입니다.");
		return false;
	}
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
	location.href="commission_list.jsp?pagenum=1";
}

</script>
</html>