/**
 * 
 */




//리뷰 삭제
function deleteReview(reviewnum){
	if(confirm("삭제하시겠습니까?")){
		location.href="myReview_delete.jsp?reviewnum="+reviewnum;
	}
}

//메뉴 색 변경
var menuwrap = document.querySelectorAll(".inner_nav_item > a");

menuwrap.forEach(function(ele, idx){
	ele.onclick=function(){
		console.log(idx);
	}
})