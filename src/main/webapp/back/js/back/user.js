//显示  lst
var pageIndex = 1;
var total;
function viewdata(data){
	var html = '';
	for(var i = 0; i<data.list.length; i++){
		html +='<tr class="odd" id="'+data.list[i].id+'">';
		html +='<td class="number">'+data.list[i].number+'</td>';
		html +='<td class="password">'+data.list[i].password+'</td>';
		html +='<td class="name">'+data.list[i].name+'</td>';
		html +='<td class="department">'+data.list[i].department+'</td>';
		// if(data[i].phone == null){
		// 	html +='<td class="phone">无</td>';
		// }else{
		// 	html +='<td class="phone">'+data[i].phone+'</td>';
		// }
		html +='<td classs="mailbox">'+data.list[i].mailbox+'</td>';
		html += ' <td ><a  class="edit" onclick="edit('+data.list[i].id+')">修改</a></td>';
		html += ' <td ><a  class="del" onclick="del('+data.list[i].id+')">删除</a></td>';
		html +='</tr> ';
	}
	$(".bo").html(html);  
}
//获取数据
function getdata(pageIndex){
    var url=Config.host+"/back/userShow";
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
    AJAX.get2(url,data,func);//正常应该是get2
}
//实现分页
function getdata2(){
    var url2=Config.host+"/back/userShow";
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

//删除+++++
function del(id){
  layer.alert('确定要删除吗？', {
    skin: 'layui-layer-lan' //样式类名
  }, function(){
    var json={
        "userId":id
      };
    deldata(json);
  }); 
}
function deldata(data){
  var url=Config.host+"/back/userDel";
  var data=data;
  var func=function(returndata){
	if(returndata.code == "111111"){
		layer.alert(returndata.message, {
		  skin: 'layui-layer-molv' //样式类名
		  ,closeBtn: 0
		}, function(){
		  window.location.reload();
		});
	  }else if(returndata.code =="999999"){
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

//编辑.++++++
function edit(id){
	if($("#"+id).children().eq(6).children().text()=='修改'){
	    for(i=0;i<8;i++){
	      $("#"+id).children().eq(i).attr("style","background-color: rgb(154, 215, 225);opacity:0.6;color:#000");
	    }
	    //   $("#"+id).children().eq(0).attr("contenteditable","true");
	      $("#"+id).children().eq(1).attr("contenteditable","true"); 
	      $("#"+id).children().eq(2).attr("contenteditable","true");
	      $("#"+id).children().eq(3).attr("contenteditable","true");
	      $("#"+id).children().eq(3).html("");  
	      // $("#"+id).children().eq(4).attr("contenteditable","true");      
	      $("#"+id).children().eq(5).attr("contenteditable","true");
	      $("#"+id).children().eq(6).children().text("保存");
	      
		var html='';
		html +='<select name="department'+id+'"><option>信息管理工程系</option><option>财政学系</option>';
	    html +='<option>会计系</option><option>工商管理系</option>';
	    html +='<option>旅游管理系</option><option>图书馆学系</option>';
	    html +='<option>档案学系</option></select>';
	    $("#"+id).children().eq(3).append(html);


	  }else if(($("#"+id).children().eq(6).children().text()=='保存')){
	         update(id);
	  }
}
function update(id){
    var department=$("select[name$='department"+id+"'] option:selected").val();  
    var json = {
    		"userId":id,
    	    "number" : $("#"+id).children().eq(0).text(),
    	    "password" : $("#"+id).children().eq(1).text(),
    	    "name" : $("#"+id).children().eq(2).text(),
    	    "department" : department,
    	    // "phone" : $("#"+id).children().eq(4).text(),
    	    "mailbox" :$("#"+id).children().eq(5).text(),
    	    }
    
	var url = Config.host+"/back/userUpdate";//"http://localhost:8080/ConferenceRoom
	var data=json;
	var func=function(returndata){		     
		if(returndata.code =="999999"){
			layer.alert('请登录', {
				skin: 'layui-layer-lan' //样式类名
			},function(){
				location.href="backlogin.html";
			});
		}
		else{
			window.location.reload();
		}
    }  
	AJAX.post2(url,data,func);//正常应该是post2
}


//添加
function add(){
	var html = '';    
	  html +='<tr class="odd">';
	  html +='<td class="number" id="r0" contenteditable="true"></td>';
	  html +='<td class="password" id="r1" contenteditable="true"></td>';
	  html +='<td class="name" id="r2" contenteditable="true"></td>';
	  html +='<td class="department" id="department" name="type" contenteditable="true"><select><option>信息管理工程系</option><option>财政学系</option><option>会计系</option><option>工商管理系</option><option>旅游管理系</option><option>图书馆学系</option><option>档案学系</option></select></td>';
	  html +='<td class="phone" id="r4" contenteditable="true"></td>';
	  // html +='<td class= "mailbox" id="r5" contenteditable="true"></td>';
	  html += ' <td ><a class="edit" onclick="save()" >保存</a></td>';
	  html +='<td></td>';
	  html +='</tr> ';
	  $("#editable-sample_new").attr("disabled", true);//只能点一次，保存之后会刷新
	  $(".bo").append(html);  
}
function save(){
	if( $("#r0").text().length!=0 || $("#r1").text().length!=0||$("#r2").text().length!=0||$("#r4").text().length!=0||$("#r5").text().length!=0){
		var url =Config.host+"/back/userInsert";
		var data = {
			"number" : $("#r0").text(),
			"password" : $("#r1").text(),
			"name" : $("#r2").text(),
			"department" : $("#department>select option:selected").val(),
			// "phone" : $("#r4").text(),
			"mailbox" :$("#r5").text()
		}
		var func=function(returndata){
			//新建完成后，重新展示页面
			if(returndata.code =="999999"){
				layer.alert('请登录', {
					skin: 'layui-layer-lan' //样式类名
				},function(){
					location.href="backlogin.html";
				});
			}
			else{
				window.location.reload();
			}
		}
		if(isnumber()==0){
			if(isEmail() !=false){
				AJAX.post2(url,data,func);
			}	
		}
		
	}
  
    
    	
}

