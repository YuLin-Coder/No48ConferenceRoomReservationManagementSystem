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
			html +='<td >'+data[i].number+'</td>';
			html +='<td >'+data[i].password+'</td>';
            html +='<td >'+data[i].name+'</td>';
            html +='<td >'+data[i].department+'</td>';
			html +='<td >'+data[i].mailbox +'</td>';
	
			html +='<td><button  onclick="pass('+data[i].id+')" class="btn btn-post pull-center">通过</button></td>';
			html +='<td><input id="'+data[i].id+'" type="text"></td>';
			html +='<td><button  onclick="nopass('+data[i].id+')" class="btn btn-post pull-center">不通过</button></td>';
			html +='</tr> ';
		}
	}
	$("#content").html(html);	
}
//显示数据的ajax
function getdata(){
	var url=Config.host+"/back/newuser";
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

function pass(id){
	/* console.log($(this).parent().next().children(input).val()); */
	var index = layer.load(1, {
		shade: [0.1,'#fff'] //0.1透明度的白色背景
	  });
	var url=Config.host+"/back/userpass";
	var data={"userId":id};
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
function nopass(id){
	var index = layer.load(1, {
		shade: [0.1,'#fff'] //0.1透明度的白色背景
	  });
	var reason = $("#"+id).val();
	if(reason == ""){
		reason = "无"
	}
	var url=Config.host+"/back/usernopass";
	var data={"userId":id,"reason":reason};
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
