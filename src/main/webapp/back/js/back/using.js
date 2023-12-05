//显示  lst
//弃用
function viewdata(data){
	var html = '';
	for(var i = 0; i <data.length; i++){
		html +='<tr class="odd" id="'+data[i].id+'">';
		html +='<td class="roomname">'+data[i].name+'</td>';
		html +='<td class="roomtype">'+data[i].type+'</td>';
		html +='<td class="roomaddress">'+data[i].address+'</td>';
		html +='<td class= "time">'+data[i].availableTime+'</td>';
		html +='<td class="capacity">'+data[i].capacity+'</td>';
		if(data[i].status == 2){
			html +='<td class= "status">维修中</td>';
		}else{
			html +='<td class= "status">可使用</td>';
		}
		html +='<td class= "equipment">'+data[i].equipment+'</td>';
		html += ' <td ><a  class="edit" onclick="edit('+data[i].id+')">修改</a></td>';
		html += ' <td ><a  class="del" onclick="del('+data[i].id+')">删除</a></td>';
		html +='</tr> ';
	}
	$(".bo").html(html);	
}
//显示数据的ajax
function getdata(){
	var url=Config.host+"/back/nopass";
	var data={};
	var func=function(returndata){
		/*if(returndata.code!="000000"){
			layer.alert('请登录', {
				skin: 'layui-layer-lan' //样式类名
			},function(){
				location.href="http://localhost:8080/hedaluntan/htmls/topic/login.html";
			});
		}	else{
			viewdata(returndata.data);
		}*/
		viewdata(returndata.data);
	}
    AJAX.get(url,data,func);//正常应该是get2
    
    var url1=Config.host+"/back/nopass";
	var data1={};
	var func1=function(returndata){
		/*if(returndata.code!="000000"){
			layer.alert('请登录', {
				skin: 'layui-layer-lan' //样式类名
			},function(){
				location.href="http://localhost:8080/hedaluntan/htmls/topic/login.html";
			});
		}	else{
			viewdata(returndata.data);
		}*/
		viewdata(returndata.data);
	}
    AJAX.get(url1,data1,func1);//正常应该是get2

    var url2=Config.host+"/back/nopass";
	var data2={};
	var func2=function(returndata){
		/*if(returndata.code!="000000"){
			layer.alert('请登录', {
				skin: 'layui-layer-lan' //样式类名
			},function(){
				location.href="http://localhost:8080/hedaluntan/htmls/topic/login.html";
			});
		}	else{
			viewdata(returndata.data);
		}*/
		viewdata(returndata.data);
	}
    AJAX.get(url2,data2,func2);//正常应该是get2
}
getdata();
