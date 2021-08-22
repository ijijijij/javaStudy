/**
 * 
 */

//메뉴 버튼
var menu = document.querySelectorAll(".page_nav_item > a");
for (var i = 0; i < menu.length; i++) {
	menu[i].onclick = function() {
		this.style.color = "orange";
		this.style.borderBottom = "5px solid orange";
	}
}

