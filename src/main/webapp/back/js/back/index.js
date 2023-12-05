//显示  lst
function viewdata(data){
	var html = '';
	for(var i = 0; i <data.length; i++){
		var department;
		if(data[i].department ==null ){
			department ="未知"
		}else{
			//前端预约的教职工号必须是库里存在的，否则页面不显示
			html +='<tr class="odd" >';
			html +='<td >'+data[i].username+data[i].number+'</td>';
			html +='<td name="sdg" >'+data[i].department+'</td>';
			html +='<td >'+data[i].roomName+'</td>';
			html +='<td >'+data[i].roomType+'</td>';
			html +='<td> <p>'+data[i].date+'</p> <p>'+data[i].time+'</p></td>';//'+data[i].date + data[i].time+'
			html +='<td >'+data[i].theme +'</td>';
			html +='<td >'+data[i].maxNum+'</td>';
			if( data[i].specialneeds == null){
				html +='<td >无</td>';
			}else{
				html +='<td >'+data[i].specialneeds+'</td>';
			}
			//id="pass'+data[i].appointmentId+'"
			html +='<td><button  onclick="pass('+data[i].appointmentId+')" class="btn btn-post pull-center">通过</button></td>';
			html +='<td><input id="'+data[i].appointmentId+'" type="text"></td>';
			html +='<td><button  onclick="nopass('+data[i].appointmentId+')" class="btn btn-post pull-center">不通过</button></td>';
			html +='</tr> ';
		}
	}
	$("#content").html(html);	
}
//显示数据的ajax
function getdata(){
	var url=Config.host+"/back/waitPassShow";
	var data={};
	var func=function(returndata){
		if(returndata.code =="999999"){
            layer.alert('请登录', {
                skin: 'layui-layer-lan' //样式类名
            },function(){
                location.href="backlogin.html";
            });
        }
        else if(returndata.code =='111111'){
        //   layer.alert(returndata.message, {
        //     skin: 'layui-layer-lan' //样式类名
        // },function(){
        //     location.href="book2.html";
        // });
      }
        else{
            viewdata(returndata.data);
        }
	}
	AJAX.get2(url,data,func);//正常应该是get2
}
getdata();

function pass(appointmentId){
	/* console.log($(this).parent().next().children(input).val()); */
	var index = layer.load(1, {
		shade: [0.1,'#fff'] //0.1透明度的白色背景
	  });
	var url=Config.host+"/back/waitPass1";
	var data={"appointmentId":appointmentId};
	var func=function(returndata){
		if(returndata.code =="999999"){
            layer.alert('请登录', {
                skin: 'layui-layer-lan' //样式类名
            },function(){
                location.href="backlogin.html";
            });
        }else{
            window.location.reload();
        }	
	}
	AJAX.post2(url,data,func);
}
function nopass(appointmentId){
	var index = layer.load(1, {
		shade: [0.1,'#fff'] //0.1透明度的白色背景
	  });
	var reason = $("#"+appointmentId).val();
	if(reason == ""){
		reason = "无"
	}
	var url=Config.host+"/back/waitNoPass1";
	var data={"appointmentId":appointmentId,"reason":reason};
	var func=function(returndata){
		if(returndata.code =="999999"){
            layer.alert('请登录', {
                skin: 'layui-layer-lan' //样式类名
            },function(){
                location.href="backlogin.html";
            });
        }else{
            window.location.reload();
        }	
	}
	AJAX.post2(url,data,func);
}
