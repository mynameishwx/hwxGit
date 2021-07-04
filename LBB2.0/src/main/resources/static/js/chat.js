function SendMsg()
{
    var text = document.getElementById("text");
    if (text.value == "" || text.value == null)
    {
        alert("发送信息为空，请输入！");
    }
    else
    {
		var http=new XMLHttpRequest();  //创建对象
	
	    http.onreadystatechange=function(){  //绑定事件
		if(http.readyState==4 && http.status==200){
			 AddMsg('default', SendMsgDispose(text.value));
             text.value = "";
		}else 
			alert("发送失败"+"->:"+text.value+":<-"+"状态码:"+http.status);
	     }
	}
	var content="content="+text.value;
	http.open("post","/news/set?"+content,true);
	
	http.send();
   }
// 发送的信息处理
function SendMsgDispose(detail)
{
    detail = detail.replace("\n", "<br>").replace(" ", "&nbsp;")
    return detail;
}

// 增加信息
function AddMsg(user,content)
{
	 var str = CreadMsg(user, content);
    var msgs = document.getElementById("msgs");
    msgs.innerHTML = msgs.innerHTML + str;

}

// 生成内容
function CreadMsg(user, content)
{
    var str = "";
    if(user == 'default')
    {
        str = "<div class=\"msg guest\"><div class=\"msg-right\"><div class=\"msg-host headDefault\"></div><div class=\"msg-ball\" title=\"今天 17:52:06\">" + content +"</div></div></div>"
    }
    else
    {
        str = "<div class=\"msg robot\"><div class=\"msg-left\" worker=\"" + user + "\"><div class=\"msg-host photo\" style=\"background-image: url(../Images/head.png)\"></div><div class=\"msg-ball\" title=\"今天 17:52:06\">" + content + "</div></div></div>";
    }
    return str;
}
function altercontent(){
      document.getElementById("shoname").innerHTML="纱萨";
      document.getElementById("content").innerHTML="撒坚持就是老大宝贝回复";
}
