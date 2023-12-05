//显示  lst
var pageIndex = 1;
var total;
function viewdata(data){
    var html = '';
    for(var i = 0; i<data.list.length; i++){
        html +='<tr class="odd" id="'+i+'">';
        html +='<td class="appointId">'+data.list[i].id+'</td>';
        html +='<td class="theme">'+data.list[i].theme+'</td>';
        html +='<td class="roomAdress">'+data.list[i].conferenceRoomName+'</td>';
        html +='<td class="date">'+data.list[i].date+'</td>';
        html +='<td class="time">'+data.list[i].time+'</td>';
        if(data.list[i].examineStatus==1){
            html +='<td classs="examineStatus"><span class="label label-success label-mini">预约成功</span></td>';
        }else if(data.list[i].examineStatus==3){
            html +='<td classs="examineStatus"><span class="label label-danger label-mini">预约失败</span></td>';
        }else{
            html +='<td classs="examineStatus"><span class="label label-warning label-mini">正在审核中</span></td>';
        }
        if(data.list[i].examineReason == null){
            html +='<td class="examineReason">无</td>';
        }else{
            html +='<td classs="examineReason">'+data.list[i].examineReason+'</td>';
        }
        // html +='<td classs="examineStatus"><span class="label label-warning label-mini">'+data[i].examineStatus+'</span></td>';
        html += ' <td ><span class="label label-primary label-mini" style="cursor:pointer;" onclick="del('+data.list[i].id+')">取消预约</span></td>';
     //   html += ' <td ><span class="label label-primary label-mini" onclick="del('+data[i].appointId+')">变更预约</span></td>';
        html +='</tr> ';
    }
    $("#content").html(html);
}
//显示数据的ajax
function getdata(pageIndex){
    var url=Config.host+"/front/myAppointShow";
    var data={"page":pageIndex}
    var func=function(returndata){
        if(returndata.code=="999999"){
            layer.alert('请登录', {
                skin: 'layui-layer-lan' //样式类名
            },function(){
                location.href="frontlogin.html";
            });
        }	else{
            viewdata(returndata.data);
        }
    }
    AJAX.get2(url,data,func);//正常应该是get2
}

function getdata2(){
    var url2=Config.host+"/front/myAppointShow";
	var data2={	"page":pageIndex};
	var func2=function(returndata){
        if(returndata.code=="999999"){
            layer.alert('请登录', {
                skin: 'layui-layer-lan' //样式类名
            },function(){
                location.href="frontlogin.html";
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


function del(id){

    layer.alert('请再次确认，是否要取消预约', {
        skin: 'layui-layer-lan'
    }, function(){
        var url=Config.host+"/front/appointDel";
        var data={"appointId":id};
        var func=function(returndata){
            if(returndata.code=="999999"){
                layer.alert('请登录', {
                    skin: 'layui-layer-lan' //样式类名
                },function(){
                    location.href="frontlogin.html";
                });
            }	else{
                window.location.reload();
            }
            // viewdata(returndata.data);
        }
        AJAX.post2(url,data,func);//正常应该是get2

    });


}