var url = location.search;
if (url.indexOf("?") != -1) {    //判断是否有参数
    var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
    strs = str.split("=");   //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
    //alert(strs[1]);          //直接弹出第一个参数 （如果有多个参数 还要进行循环的）
	
    var noticeId = strs[1];
	console.log(noticeId); 
	var json={
			"noticeId":noticeId
	}
	getdata(json);    
}
//显示  lst
function viewdata(data){
	 $("#author").html("作者：  "+data.authorName);
 	$("#title").html(data.title);
	 $("#content").html(data.content);
	 var time =new Date( data.creatTime)
	 var createTime = time.toLocaleDateString().replace(/\//g, "-") + " " +time.toTimeString().substr(0, 0);//直接用 new Date(时间戳) 格式转化获得当前时间
	 //再利用拼接正则等手段转化为yyyy-MM-dd hh:mm:ss 格式
	  $(".date").html("发布时间：    "+createTime);
}
//显示数据的ajax
function getdata(data){
	var url=Config.host+"/back/noticeShowDetails";
	//var url = "http://localhost:8080/ConferenceRoom/back/roomShow";
	var data=data;
	var func=function(returndata){
		if(returndata.code!="000000"){
			layer.alert('请登录', {
				skin: 'layui-layer-lan' //样式类名
			},function(){
				location.href="http://localhost:8080/hedaluntan/htmls/topic/login.html";
			});
		}	else{
			viewdata(returndata.data);
		}
		
	}
	AJAX.get2(url,data,func);//正常应该是get2
}
