//显示  lst
function viewroom(data){
	var html = '';
  	for(var i = 0; i <data.length; i++){
	    html +='<option name="roomId'+ data[i].id +'">'+data[i].name+'  (详细地址:'+data[i].address+ ')</option>';
  	}
  	$("#room").html(html);
}
// 
//判断num是否为数字
var num ;
var numbers;
$("#num").on("input",function(){       
    num  = $("#num").val();
    if( /^\d+$/.test(num) || num == ""){
        numbers = num;
        console.log(numbers); 
    }else{
        layer.msg('请输入数字');
    }
});

/**
 * 使用时间段：start<end
 */
var start;
var end;
$("#endtime").on("change",function(){     
  var starttime = $("#starttime").val();
  var endtime =  $("#endtime").val(); 
  if(starttime!='请选择开始时间' ){
    judge(starttime,endtime);
  }
});
$("#starttime").on("change",function(){     
  var starttime = $("#starttime").val();
  var endtime =  $("#endtime").val(); 
  if(endtime!='请选择结束时间' ){
    judge(starttime,endtime);
  }
});
//判断函数，两个传参是直接获取的val值。$("#starttime").val()
//如果正确，则judg为1
var judg;
function judge(starttime, endtime){
  console.log(starttime)
  console.log(endtime)
  var a = starttime.indexOf(":")
  start =starttime.substring(0,a)
  var b = endtime.indexOf(":")   
  end = endtime.substring(0,b)
  if(parseInt(start) < parseInt(end)){
    judg = 1;
    // console.log("对"+judg)     
  }else{
    judg = 0;
    // console.log("不对"+judg)
    layer.msg('结束时间应大于开始时间，请重新选择');
  }
}

/**
 * 判断日期是否大于当前日期
 * 最晚提前一天预约
**/
// function FormatDate (strTime) {
//   var date = new Date(strTime);
//   return date.getFullYear()+"-"+(date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-'+date.getDate();
// }

//显示数据的ajax
function getdata(){
  var url = Config.host+"/front/roomShow1";//"http://localhost:8080/ConferenceRoom
  var data={};
  var func=function(returndata){
    if(returndata.code =="999999"){
        layer.alert('请登录', {
            skin: 'layui-layer-lan' //样式类名
        },function(){
            location.href="frontlogin.html";
        });
    }
    else if(returndata.code =='111111'){
      layer.alert(returndata.message, {
        skin: 'layui-layer-lan' //样式类名
    },function(){
        location.href="book2.html";
    });
  }
    else{
        viewroom(returndata.data);
    }
  }
  AJAX.get2(url,data,func);//正常应该是get2
  // AJAX.get(url2,data,func2);//正常应该是get2
}
getdata();

$("#ownerAppointment").on("click",function(){
  
  //console.log($("#theme").val().length)
  var theme;
  if($("#theme").val().length == 0){
    theme ="无"
  }else{
    theme = $("#theme").val();
  }
  if($("#num").val().length == 0){
    numbers =1
  }
  var date = $("#test2").val();
  var da = Date.parse(date)
  console.log(da)
  var today = new Date();
  console.log(Date.parse(today));

    var starttime = $("#starttime").val();
    var endtime = $("#endtime").val();
    //numbers;
    var roomId = $("#room option:selected").attr("name");

    if (date.length == 0){
    //添加css样式
    $("#test2").css('borderColor','red');
    $("#null1").css('color','red')
    }else {
        $("#test2").css('borderColor',''); //取消css样式
        $("#null1").css('color','')
    }
    if (starttime == '请选择开始时间'){
        //添加css样式
    $("#starttime").css('borderColor','red');
    $("#null2").css('color','red')
    }else {
      $("#starttime").css('borderColor',''); //取消css样式
      $("#null2").css('color','')
    }
    if (endtime == '请选择结束时间'){
    //添加css样式
    $("#endtime").css('borderColor','red');
    $("#null3").css('color','red')
    }else {
      $("#endtime").css('borderColor',''); //取消css样式
      $("#null3").css('color','')
    }
    if ($("#room").val() == '请选择会议室'){
    //添加css样式
    $("#room").css('borderColor','red');
    $("#null4").css('color','red')
    }else {
      $("#room").css('borderColor',''); //取消css样式
      $("#null4").css('color','')
    }

  if(date.length!=0 && starttime!='请选择开始时间'&&endtime !='请选择结束时间' && roomId!=null){
    console.log("必填")
    judge(starttime,endtime);
    console.log(judg)
    if(judg == 1){
      if(da <= today){
        // console.log("最晚提前一天进行预约")
        layer.msg('请预约正确的日期，注意最晚提前一天进行预约');
      }
      else{
        var json = {
          "theme":theme,
          "date":date,
          "starttime":starttime,
          "endtime":endtime,
          "roomId":roomId,
          "num":numbers,
        }
        insert(json);
      }
      
    }  
  }    
});


//传入申请json
function insert(data){
  var url = Config.host+"/front/ownerAppointment";//"http://localhost:8080/ConferenceRoom
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
          location.href="frontlogin.html";
      });
  }else{
    //已为您提交预约申请
    layer.alert('预约成功，请按照有关规定使用会议室 。祝您使用愉快！', {
      skin: 'layui-layer-molv' //样式类名
      ,closeBtn: 0
    }, function(){
      window.location.reload();
    });
  }  
  }
  AJAX.post2(url,data,func);//正常应该是get2
}

