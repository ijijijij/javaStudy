/**
 * 
 */


//탭 변경 시 페이지 설정
var commission = document.querySelector("#commission");
var survey = document.querySelector("#survey");
commission.onclick=function(){
	location.href="commission_list.jsp?pagenum=1";
}
survey.onclick=function(){
	location.href="survey_list.jsp?pagenum=1";
}
