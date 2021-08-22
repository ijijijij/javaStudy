/**
 * 
 */

//사진미리보기
var uploadImgBtn = document.querySelector("#designImg");
var imgclass= document.querySelector(".img");
uploadImgBtn.onchange=function(obj){
	var img = obj.target.files[0];
	imgclass.style.display="inline-block";
	var reader = new FileReader();
	reader.onload = function(obj){
		document.querySelector("[name=img_preview]").src=obj.target.result;
	}
	reader.readAsDataURL(img);
}
