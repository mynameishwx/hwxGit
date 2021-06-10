
Spry.Utils.addLoadListener(function() {

$(document).ready(function () {
    var topH=$("#top").height();//获取头部高度
    var navbg=$("#navbg");
    //获取浏览器窗口
    $(window).scroll(function () {
        if($(window).scrollTop()>topH){
            navbg.addClass("scrollNav")//添加样式
        }else{
            navbg.removeClass("scrollNav")
        }
    });
});


});
