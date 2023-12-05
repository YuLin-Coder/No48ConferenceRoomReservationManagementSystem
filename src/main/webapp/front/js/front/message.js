//显示  lst
var pageIndex = 1;
var total;
function viewdata(data){
	var html = '';
	for(var i = 0; i <data.list.length; i++){
		var time =new Date( data.list[i].creatTime)
		var createTime = time.toLocaleDateString().replace(/\//g, "-") + " " +time.toTimeString().substr(0, 0);//直接用 new Date(时间戳) 格式转化获得当前时间
		//再利用拼接正则等手段转化为yyyy-MM-dd hh:mm:ss 格式
		html +='<li class="list-group-item"> ';
	 	html +='<a class="" href="mail_view.html?noticeId='+data.list[i].id+'"><small class="pull-right text-muted"></small> <strong>'+data.list[i].title+'</strong> '; 
        html +='<span style="margin-left:10px">'+data.list[i].authorName+'</span><span style="float:right;margin-right:10px">'+createTime+'</span>'
        html +='</a> </li>';
	//日期没有显示，后台还没有弄好！！	    
	}
	$("#list").html(html);	
}
//显示数据的ajax
function getdata(pageIndex){
	var url=Config.host+"/front/noticeShow";
	//var url = "http://localhost:8080/ConferenceRoom/back/roomShow";
	data={"page":pageIndex}
	var func=function(returndata){
		viewdata(returndata.data);
	}
	AJAX.get(url,data,func);//正常应该是get2
}
//实现分页
function getdata2(){
    var url2=Config.host+"/front/noticeShow";
	var data2={	"page":pageIndex};
	var func2=function(returndata){
		total = returndata.data.total;
		console.log(total)
		//layui实现分页
		layui.use(['laypage', 'layer'], function(){
			var laypage = layui.laypage,layer = layui.layer;
			laypage.render({
				elem: 'demo1'
				,count: total //数据总数
				,jump: function(){
					pageIndex=$(".layui-laypage-curr .layui-laypage-em+em").html();
					console.log("jump####"+pageIndex);
					getdata(pageIndex);
				}
			});
		});
	}
	AJAX.get(url2,data2,func2);
}
getdata2();
