//显示  lst
var pageIndex = 1;
var total;
function viewdata(data){
	var html = '';
	Date.parse(new Date())
	for(var i = 0; i <data.list.length; i++){
		var time =new Date( data.list[i].creatTime)
		var createTime = time.toLocaleDateString().replace(/\//g, "-") + " " +time.toTimeString().substr(0, 0);//直接用 new Date(时间戳) 格式转化获得当前时间
		//再利用拼接正则等手段转化为yyyy-MM-dd hh:mm:ss 格式
		html +='<li class="list-group-item"> ';
		html+='<span class="pull-left chk"><input type="checkbox"  name="ids" value="'+data.list[i].id+'" /></span>';
	 	html +='<a class="" href="mail_view.html?noticeId='+data.list[i].id+'"><small class="pull-right text-muted"></small> <strong>'+data.list[i].title+'</strong> '; 
        html +='<span style="margin-left:10px">'+data.list[i].authorName+'</span><span style="float:right;margin-right:10px">'+createTime+'</span>'
        html +='</a> </li>';
	}
	$("#list").html(html);	
}

//获取数据
function getdata(pageIndex){
	console.log(23)
    var url=Config.host+"/back/noticeShow";
    var data={"page":pageIndex}
    var func=function(returndata){
		if(returndata.code=="999999"){
			layer.alert('请登录', {
				skin: 'layui-layer-lan' //样式类名
			},function(){
				location.href="backlogin.html";
			});
		}	else{
			viewdata(returndata.data);
		}
        
    }
	AJAX.get2(url,data,func);
}

//实现分页
function getdata2(){
	console.log(23)
    var url2=Config.host+"/back/noticeShow";
	var data2={	"page":pageIndex};
	var func2=function(returndata){
        if(returndata.code=="999999"){
            layer.alert('请登录', {
                skin: 'layui-layer-lan' //样式类名
            },function(){
                location.href="backlogin.html";
            });
        }	else{
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
	}
	AJAX.get2(url2,data2,func2);
}
getdata2();
// //显示数据的ajax
// function getdata(pageIndex){
// 	var url=Config.host+"/back/noticeShow";
// 	//var url = "http://localhost:8080/ConferenceRoom/back/roomShow";
// 	var data={};
// 	var func=function(returndata){
// 		if(returndata.code=="999999"){
// 			layer.alert('请登录', {
// 				skin: 'layui-layer-lan' //样式类名
// 			},function(){
// 				location.href="backlogin.html";
// 			});
// 		}	else{
// 			viewdata(returndata.data);
// 		}
// 	}
// 	AJAX.get2(url,data,func);//正常应该是get2
// }
// //实现分页
// function getdata2(){
//     var url2=Config.host+"/back/noticeShow";
// 	var data2={	"page":pageIndex};
// 	var func2=function(returndata){
//         if(returndata.code=="999999"){
//             layer.alert('请登录', {
//                 skin: 'layui-layer-lan' //样式类名
//             },function(){
//                 location.href="backlogin.html";
//             });
//         }	else{
// 			total = returndata.data.total;
//             console.log(total)
// 			//layui实现分页
// 			layui.use(['laypage', 'layer'], function(){
// 				var laypage = layui.laypage,layer = layui.layer;
// 				laypage.render({
// 					elem: 'demo1'
// 					,count: total //数据总数
// 					,jump: function(){
// 						pageIndex=$(".layui-laypage-curr .layui-laypage-em+em").html();
// 						console.log("jump####"+pageIndex);
// 						getdata(pageIndex);
// 					}
// 				});
// 			});
// 		}
// 	}
// 	AJAX.get2(url2,data2,func2);
// }
// getdata2();


//删除
$("#del").on("click",function(){
	var ids = [];
	$("input[name='ids']:checked").each(function(i){
		ids.push($(this).val())
	})
	
	del(ids.toString())
})
function del(id){
	console.log(id)
	var url=Config.host+"/back/noticeDel";
	var data={"id":id};
	var func=function(returndata){
		if(returndata.code=="999999"){
			layer.alert('请登录', {
				skin: 'layui-layer-lan' //样式类名
			},function(){
				location.href="backlogin.html";
			});
		}	else{
			location.replace(location);
		}
	}
	AJAX.post2(url,data,func);
}
