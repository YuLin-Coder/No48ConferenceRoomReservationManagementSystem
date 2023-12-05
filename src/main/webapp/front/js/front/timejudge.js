
/**
 * 使用时间段：start<end
 * 判断开始时间小于结束时间的js
 */
var start;
var end;
$("#endtime").on("change",function(){     
  var starttime = $("#starttime").val();
  var endtime =  $("#endtime").val(); 
  if(starttime!='请选择开始时间'){
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
