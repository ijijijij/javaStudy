<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="funding.Community_DAO"
	import="java.util.*"
	import="funding.Comments"
	import="funding.Survey"
	import="funding.Survey_DAO"
	errorPage="errorPage_noPage.jsp"
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
<link rel="stylesheet" href="bbs_css.css?ver5" type="text/css">
<link rel="stylesheet" href="survey_view.css" type="text/css">
 <script src="https://code.jquery.com/jquery-3.6.0.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<%
String memNum = (String) session.getAttribute("memnum");
String bbsNum = request.getParameter("bbsnum");
Survey_DAO dao = new Survey_DAO();
int grade = -1;
if(memNum!=null){
	grade = dao.checkMemGrade(memNum);
}
if(bbsNum==null){
	bbsNum = dao.getbbsNum(memNum);
}
//조회수 증가
Community_DAO cdao = new Community_DAO();
cdao.addViewCount(bbsNum);
//삭제된 게시물인 경우 에러발생
Survey survey = dao.getSurvey(bbsNum);
if(survey.getStatusNum()==0){
	System.out.println(1/0);
}
//설문 항목
ArrayList<Survey> sqList = dao.getSurveyQuestion(bbsNum);
String minsvq = sqList.get(0).getSvqNum();
String maxsvq = null;

//설문 선택지
ArrayList<Survey> soFirstList = dao.getSurveyOption(minsvq);
ArrayList<Survey> soSecondList = new ArrayList<Survey>();
if(sqList.size()>1) {
	maxsvq = sqList.get(1).getSvqNum();
	soSecondList = dao.getSurveyOption(maxsvq);
}
//댓글
ArrayList<Comments> commentList = cdao.getComments(bbsNum);
%>
<script type="text/javascript">
//차트 그리기
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
<%if(maxsvq!=null){%>
google.charts.setOnLoadCallback(drawChart2);
<%}%>
//첫번째 차트
function drawChart() {
	var data = google.visualization.arrayToDataTable([
	    ['Option', 'Count',{role:'annotation'}],
	    <%for(Survey svo : soFirstList){%>
	    ['<%=svo.getSvo()%>',  <%=svo.getSvoCount()%>, <%=svo.getSvoCount()%>],
	    <%}%>
	  ]);

  var options = {
	title: '<%=sqList.get(0).getSvqTitle()%>',
    legend : {position : 'none'},
    hAxis : {textPosition : 'none', minValue : 0}, // 가로축 제거
    series :{ 0 : {color : '#07CBE5'}},
    annotations: {
        textStyle: {
          fontSize: 15,
          bold: true,
          italic: true,
          color: '#871b47',
          auraColor: '#d799ae',
          opacity: 0.8
        }
   }
  };
  var chart = new google.visualization.BarChart(document.getElementById('bbs-view-survey-result1'));
  chart.draw(data, options);
}
//두번째 차트
<%if (soSecondList.size()>0 && sqList.size()>1){%>
	function drawChart2() {
		  var data2 = google.visualization.arrayToDataTable([
		    ['Option', 'Count',{role:'annotation'}],
		    <%for(Survey svo2 : soSecondList){%>
		    ['<%=svo2.getSvo()%>',  <%=svo2.getSvoCount()%>, <%=svo2.getSvoCount()%>],
		    <%}%>
		  ]);

		  var options2 = {
		    title: '<%=sqList.get(1).getSvqTitle()%>',
		    legend : {position : 'none'},
		    hAxis: {textPosition : 'none'}, // 가로축 제거
		    series :{ 0 : {color : '#07CBE5'}},
		    annotations: {
		        textStyle: {
		          fontSize: 15,
		          bold: true,
		          italic: true,
		          color: '#871b47',
		          auraColor: '#d799ae',
		          opacity: 0.8
		        }
		   }
		  };
		  var chart2 = new google.visualization.BarChart(document.getElementById('bbs-view-survey-result2'));
		  chart2.draw(data2, options2);
		}
<%} %>
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#save").click(function(){
			invalidItem();
		});
		function invalidItem(){
			if($("input[name=srv1opt]:radio:checked").length<1){
				alert("설문 응답이 완료되지 않았습니다.");
				return false;
			} else{
				$("#survey-join-form").submit();
			}
		}
	});
</script>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<section>
		<div class="wrap">
			<article>
				<!-- article에 man-writh:1100px; -->
				<div class="tabs">
					<input id="survey" type="radio" name="tab_item" checked> <label
						class="tab_item" for="survey">Survey</label> <input
						id="commission" type="radio" name="tab_item" > <label
						class="tab_item" for="commission">Commission</label>

					<div class="tab_content" id="survey_content">
					<!-- 게시판 시작 -->
						<article id="bbs-view">
							<!-- 게시판 헤더 -->
							<section class="bbs-view-header">
								<span>Survey View</span>
								<%if(memNum!=null && grade==2) {%>
								<input type="button" id="btn-write" class="btn" onclick="writeSurvey()" style="visibility:visible" value="글쓰기">
								<%} %>
							</section>

							<!-- 게시물 읽기 시작 -->
							<!-- 게시물 정보 -->
							<article id="bbs-view-info">
								작성자 <strong><span class="info-author"><%=survey.getWriterNick() %></span></strong> 조회 <strong><i
									class="info-hit" aria-hidden="true"></i><%=survey.getViewcnt() %></strong> <strong
									class="info-date">작성일 <%=survey.getBbsDate() %></strong>
							</article>
							<!-- 게시물 제목 -->
							<header>
								<span id="bbs-view-title"><%=survey.getBbsTitle() %></span>
							</header>
							<!-- 게시물 본문 내용 -->
							<section id="bbs-view-con-holder">
								<div id="bbs-view-con"><%=survey.getBbsContent() %></div>
								<div id="bbs-view-survey-result1"></div>
								<%if(maxsvq!=null){ %>
								<div id="bbs-view-survey-result2"></div>
								<%} %>
								<form id="survey-join-form" method="post" action="survey_join.jsp?bbsnum=<%=bbsNum%>">
								<div id="bbs-view-survey-join">
									<div class="survey-join">
										<h3><%=sqList.get(0).getSvqTitle()%></h3>
										<table>
											<%for(Survey option : soFirstList){ %>
											<tr><td><input type="radio" name="srv1opt" value="<%=option.getSvoNum() %>"/><span><%=option.getSvo() %></span></td></tr>
											<%} %>
										</table>
									</div>
									<%if(maxsvq!=null){ %>
									<div class="survey-join">
										<h3><%=sqList.get(1).getSvqTitle()%></h3>
										<table>
											<%for(Survey option : soSecondList){ %>
											<tr><td><input type="radio" name="srv2opt" value="<%=option.getSvoNum() %>"/><span><%=option.getSvo() %></span></td></tr>
											<%} %>
										</table>
									</div>
									<%} %>
								</div>
								</form>
							</section>
							<!-- 게시물 공통 푸터 -->
							<!-- 버튼 -->
							<article id="bbs-view-footer">
								<div id="bbs-view-btn-holder">	
									<%-- 밖으로 튀어나가면 css에서 margin-left 조절해주세요 ㅠㅠ --%>
									<%if(memNum.equals(survey.getWriterNum())){ %>
									<button class="btn" id="edit" onclick="edit()">수정</button>
									<button class="btn" id="delete" onclick="delsurvey()">삭제</button>
									<%} else{ %>
									<input type="button" class="btn" id="save" value="확인">
									<input type="button" class="btn" id="report" value="신고" onclick="reportbbs()">
									<%} %>
									<button class="btn" id="List" onclick="goSrvList()">목록</button>
								</div>
								<!-- 댓글 입력 -->
								<div id="comment-w-holder">
									<form id="comment-form" action="comment_insert.jsp?bbsnum=<%=bbsNum%>" method="post">
									<span>댓글 입력: </span>
									<textarea rows="3" cols="60" class="comment-w" id="comment-w"
										name="comment-w" placeholder="여기에 댓글 입력.."></textarea>
									<button class="comment-btn" id="submit">등록</button>	
									</form>
									
								</div>
								<%if(commentList!=null){ 
								for(Comments comment : commentList) {%>
								<section id="comment-holder">
									<span><%=comment.getCmtDate()%></span>
									<span><a href="report.jsp?cmtnum=<%=comment.getCmtNum()%>&bbsnum=<%=bbsNum%>" id="report">신고</a></span>
									<br>
									<span><%=comment.getMemnick()%> :</span>
									<div class="comment"><%=comment.getCmtContent()%></div>
								</section>
								<%} %>
								<%} %>
							</article>
							<!-- 게시물 읽기 끝 -->
						</article>
					</div>
					<div class="tab_content" id="commission_content"></div>
				</div>
			</article>
		</div>
	</section>
	<%@include file="../common/footer.jsp"%>
	<script src="commission.js?ver1"></script>
</body>
<script type="text/javascript">

//버튼 함수
function writeSurvey(){
	location.href="survey_write.jsp";
}

function edit(){
	location.href="survey_edit.jsp?bbsnum=<%=bbsNum%>";
}

function delsurvey(){
	location.href="survey_delete.jsp?bbsnum=<%=bbsNum%>";
}

function goSrvList(){
	location.href="survey_list.jsp?pagenum=1";
}

function reportbbs(){
	if(confirm("신고하시겠습니까?")){
		location.href="report.jsp?bbsnum=<%=bbsNum%>&writernum=<%=survey.getWriterNum()%>";
	}
}

</script>
</html>