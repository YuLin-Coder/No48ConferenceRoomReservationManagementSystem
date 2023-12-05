//显示  lst
function viewdata(data){
	var html = '';
	for(var i = 0; i <data.length; i++){
		html +='<tr class="odd" id="'+data[i].id+'">';
		html +='<td class="roomname">'+data[i].name+'</td>';
		html +='<td class="roomtype">'+data[i].type+'</td>';
		html +='<td class="roomaddress">'+data[i].address+'</td>';
		// html +='<td class= "time">'+data[i].availableTime+'</td>';
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
	var url=Config.host+"/back/roomShow";
	var data={};
	var func=function(returndata){
		if(returndata.code =="999999"){
            layer.alert('请登录', {
                skin: 'layui-layer-lan' //样式类名
            },function(){
                location.href="backlogin.html";
            });
        }
        else{
            viewdata(returndata.data);
		}
	}
	AJAX.get2(url,data,func);//正常应该是get2
}
getdata();


//删除
function del(id){
	layer.alert('确定要删除吗？', {
	  skin: 'layui-layer-lan' //样式类名
	}, function(){
		var json={
				"rommId":id
			};
		deldata(json);
	});	
}
//删除的提交数据
function deldata(data){
	var url=Config.host+"/back/roomDel";
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

//编辑.
function edit(id){
  if($("#"+id).children().eq(7).children().text()=='修改'){
	  for(i=0;i<9;i++){
		  $("#"+id).children().eq(i).attr("style","background-color: rgb(154, 215, 225);opacity:0.6;color:#000");
	  }
      $("#"+id).children().eq(0).attr("contenteditable","true");
      $("#"+id).children().eq(1).attr("contenteditable","true");
   //   alert($("#"+id).children().eq(1).html());
      $("#"+id).children().eq(1).html("");   
      $("#"+id).children().eq(2).attr("contenteditable","true");
      $("#"+id).children().eq(3).attr("contenteditable","true");
      // $("#"+id).children().eq(4).attr("contenteditable","true");      
      $("#"+id).children().eq(5).attr("contenteditable","true");
      $("#"+id).children().eq(5).html("");
      $("#"+id).children().eq(6).attr("contenteditable","true");
      $("#"+id).children().eq(7).children().text("保存");

      var html='';
	  html +='<select name="roomtype'+id+'"><option>信息管理工程系</option><option>财政学系</option>';
	  html +='<option>会计系</option><option>工商管理系</option></select>';
	  $("#"+id).children().eq(1).append(html);
	  var html2='';
	  html2 +='<select name="status'+id+'"><option>可使用</option><option>维修中</option></select>';
	  $("#"+id).children().eq(5).append(html2);
  
  }else if(($("#"+id).children().eq(7).children().text()=='保存')){
	  //编辑的保存
         update(id);
  }
}
function update(id){
   
    var roomType=$("select[name$='roomtype"+id+"'] option:selected").val();  
    var status=$("select[name$='status"+id+"'] option:selected").val();
    var sta;
    if(status =="可使用"){
    	sta = 1
    }else if(status =="维修中"){
    	sta = 2
    }  
//    alert($("#"+id).children().eq(3).text());
    var capacity = $("#"+id).children().eq(4).text();
    var cap;
    /**
     * /^\d+$/.test(capacity) : true,表示是数字。js正则表达式
     */
    if( /^\d+$/.test(capacity)){
    	cap = capacity ;
    	var json = {
    	        "rommId":id,
    			"roomName":$("#"+id).children().eq(0).text(),
    			"roomType":roomType ,
    			"address":$("#"+id).children().eq(2).text(),
    			"time":$("#"+id).children().eq(3).text(),
    			"capacity":cap,
    			"status":sta,
    			"equipment":$("#"+id).children().eq(6).text()
    	    }  
    		editdata(json);
    }else{
    	layer.alert('容量：请输入数字', {
    		  skin: 'layui-layer-lan' //样式类名
    		});	
    }	
}
//编辑，提交数据
function editdata(data){
	  var url = Config.host+"/back/roomUpdate";//"http://localhost:8080/ConferenceRoom
	  var data=data;
	  var func=function(returndata){
			//修改过后，重新展示页面
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
	html +='<td class="roomname" id="r0" contenteditable="true"></td>';
	html +='<td class="roomtype"  contenteditable="true"><select id="roomty"  name="type"><option>信息管理工程系</option><option>财政学系</option><option>会计系</option><option>工商管理系</option></select></td>';
	html +='<td class="roomaddress" id="r2" contenteditable="true"></td>';
	// html +='<td class= "time" id="r3" contenteditable="true"></td>';
	html +='<td class="capacity" id="r4" contenteditable="true"></td>';
    html +='<td class="status"  contenteditable="true"><select id="status" name="status"><option>可使用</option><option>维修中</option></select></td>';
	html +='<td class= "equipment" id="r6" contenteditable="true"></td>';
	html += ' <td ><a class="edit" onclick="save()" >保存</a></td>';
	html +='<td></td>';
	html +='</tr> ';
	$("#editable-sample_new").attr("disabled", true);//只能点一次，保存之后会刷新
	$(".bo").append(html);	
}

$("#r4").on("input",function(){       
	isCapacity();
 });
//添加的保存、提交数据
function save(){
	// var roomTypeid = $(this).parent().siblings().eq(1).attr("id");
	// console.log($(this));
	var roomType= $("#roomty>select option:selected").val();
    var status=$("#status>select option:selected").val();
    if(status =="可使用"){
    	sta = 1
    }else if(status =="维修中"){
    	sta = 2
    }   
    // var capacity =  $("#r4").text()
    // var cap;
    /**
     * /^\d+$/.test(capacity) : true,表示是数字。js正则表达式
     */
	if( $("#r0").text().length==0 || $("#r2").text().length==0||$("#r4").text().length==0||$("#r6").text().length==0){
		console.log($("#r0").text());
		console.log($("#r2").text());
		// console.log($("#r3").text());
		console.log($("#r4").text());
		console.log($("#r6").text());
		console.log(1111)
	}else{
		var url =Config.host+"/back/roomInsert";
    	var data = {
    		"roomName" : $("#r0").text(),
    		"roomType" : roomType,
    		"address" : $("#r2").text(),
    		"capacity" : $("#r4").text(),
    		"status" : sta,
    		"equipment" : $("#r6").text()
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
		if(isCapacity() == 0){
			AJAX.post2(url,data,func);
		}
	}	
}


	
