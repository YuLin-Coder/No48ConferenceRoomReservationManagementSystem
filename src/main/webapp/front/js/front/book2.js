//显示  lst
function viewroom(data){
	var html = '';
  	for(var i = 0; i <data.length; i++){
	    html +='<option name="roomId'+ data[i].id +'">'+data[i].name+'  (详细地址:'+data[i].address+ ')</option>';
  	}
  	$("#room").html(html);
}

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

//显示数据的ajax
function getdata(){
  var url = Config.host+"/front/roomShow";//"http://localhost:8080/ConferenceRoom
  var data={};
  var func=function(returndata){

      if(returndata.code =="999999"){
        layer.alert('请登录', {
            skin: 'layui-layer-lan' //样式类名
        },function(){
            location.href="frontlogin.html";
        });
    }
    else if(returndata.code =='888888'){
      layer.alert(returndata.message, {
        skin: 'layui-layer-lan' //样式类名
    },function(){
        location.href="index.html";
    });
  }
    else{
        viewroom(returndata.data);
    }
  }
  AJAX.get2(url,data,func);//正常应该是get2
}
getdata();

$("#othersAppointment").on("click",function(){
    var theme;
    var date = $("#test2").val();
    var da = Date.parse(date)
    console.log(da)
    var today = new Date();
    console.log(Date.parse(today));

    var starttime = $("#starttime").val();
    var endtime = $("#endtime").val();
    var roomId = $("#room option:selected").attr("name");
    var specialneeds;
    /**
     * 判空+默认值
     */
    if($("#theme").val().length == 0){
        theme ="无"
    }else{
        theme = $("#theme").val();
    }
    if($("#num").val().length == 0){
        numbers =1
    }
    if($("input[type='radio']:checked").val() != undefined){
        specialneeds =$("input[type='radio']:checked").val()+"多媒体";
    }else{
        specialneeds ="不需要多媒体"
    }
    if($("#specialneeds").val().length == 0){
        specialneeds +="，无附注"
    }else{
        specialneeds = $("#specialneeds").val();
    }
    console.log($("input[type='radio']:checked").val())
    
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

    //必填判空，不提交
      if(date.length!=0 && starttime!='请选择开始时间'){
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
              "specialneeds":specialneeds
          }
          insert(json);
          }
          
    }
        
      }    
});
//传入申请json
function insert(data){
  var url = Config.host+"/front/otherAppointment";
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
    layer.alert('已提交预约申请，请耐心等待审核，并按照有关规定使用会议室 。祝您使用愉快！', {
      skin: 'layui-layer-molv' //样式类名
      ,closeBtn: 0
    }, function(){
      window.location.reload();
    });
  }  
  }
  AJAX.post2(url,data,func);//正常应该是get2
}

