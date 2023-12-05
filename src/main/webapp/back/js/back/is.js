var num ;
var numbers;
function isnumber(){
  num  =$("#r0").text()
    if( /^\d+$/.test(num) || num == ""){
        numbers = num;
       // console.log(numbers); 
       return 0;
    }else{
        layer.msg('教职工号：请输入数字');
    }
}


function isEmail(){ 
    var email = $("#r5").text()
    if($("#r5").text().length != 0) {
       var reg =   /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
       //调用正则验证test()函数
       isok= reg.test(email);
         if(!isok) {
            alert("邮箱格式不正确，请重新输入！");
            $("#r5").focus();
            return false;
        }
    }
}


var cap;
function isCapacity(){
    var capacity =  $("#r4").text();
    if( /^\d+$/.test(capacity)){
        cap = capacity ;
        return 0;
    }else{
        layer.msg('容量：请输入数字');
    }
}
