$(document).ready(function () {
			$(".js-nav-title li").click(function(){
				$(this).attr("class","on");
				$(this).siblings().attr("class","");
				var index = $(".js-nav-title li").index(this);
				$(".index-news-list").css("display","none");
				$("#index-news-list-"+(index+1)).css("display","block");
			});
		});