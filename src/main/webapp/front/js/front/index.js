 //显示  lst
function viewdata(data){
  $("#roomSum").html(data.roomSum);
  $("#appointedSum").html(data.appointedSum);
  $("#freeSum").html(data.sumToday);
  $("#maintainSum").html(data.maintainSum);
}
function viewrule(data){
	$("#title").html(data.title);
    $("#author").html(data.authorName);
    $("#content").html(data.content);
    $("#creatTime").html(data.creatTime);
}
//显示数据的ajax
function getdata(){
	  var url = Config.host+"/front/iconShow";//"http://localhost:8080/ConferenceRoom
	//  var url2 = Config.host+"/front/ruleShow";
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
	  // var func2=function(returndata){
	  //   viewrule(returndata.data);
	  //   console.log(returndata.data);
	  // }
	  AJAX.get(url,data,func);//正常应该是get2
	//  AJAX.get(url2,data,func2);//正常应该是get2
}
getdata();