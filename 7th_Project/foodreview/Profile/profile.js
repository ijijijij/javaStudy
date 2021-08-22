/**
 * 
 */

//프로필 사진 변경 : DB연결 안함
var imgChgBtn = document.querySelector("#profileImg_input");
imgChgBtn.onchange=function(obj){
	var img = obj.target.files[0];
	
	var reader = new FileReader();
	reader.onload = function(obj){
		document.querySelector("[name=profileImg]").src=obj.target.result;
	}
	reader.readAsDataURL(img);
	//alert("변경!");
}


//저장
function validate() {
	//이메일 체크
	var mailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if (!mailReg.test(profileform.mail.value)) {
		alert("이메일 형식에 맞춰 입력하세요.");
		profileform.mail.focus();
		return false;
	} 
	//닉네임 체크
	var nickReg = /^[가-힣a-zA-Z0-9]{1,8}$/;
	if (!nickReg.test(profileform.nick.value)) {
		alert("닉네임은 최소 1자리, 최대 8자리로 입력하세요.");
		profileform.nick.value="";		
		profileform.nick.focus();
		return false;
	} 
	return true;
}

//취소
var cancelbtn = document.querySelector("[name=cancelbtn]");
cancelbtn.onclick = function() {
	location.href = "profile.jsp";
}

//회원 탈퇴 : db연결 안함
var out = document.querySelector("[name=outbtn]");
out.onclick=function(){
	if(confirm("회원탈퇴를 하시겠습니까?")){
		location.href="index.html";
	}
}

