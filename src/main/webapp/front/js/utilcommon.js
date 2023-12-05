var Config = {
	 // host:"http://localhost:8085/ConferenceRoom"
	host:"http://localhost:8080"

}
/**
 * 封装的ajax
 */
var AJAX ={
		get:function(url,data,func){

			$.ajax({
	 	          url:url,
		          type:"get",
		          data: data,
		          dataType:"json",
	          success:function(returnData){

	       	      func(returnData);

	          },
	          error:function(a,b,c){
	       	   alert("出现未知网络错误");
	          }

	       })
		},

		post:function(url,data,func){
			
			$.ajax({
			      url:url,
		          type:"post",
		          data: data,
		          dataType:"json",
		      success:function(returnData){
		   	     
		   	      func(returnData);
		   	     
		      },
		      error:function(a,b,c){
		   	   alert("出现未知网络错误");
		      }
			   
		   })
       },
       get2:function(url,data,func){

			$.ajax({
				headers:{
					  token:getCookie("token")
				  },
			      url:url,
		          type:"get",
		          data: data,
		          dataType:"json",
		      success:function(returnData){

		   	      func(returnData);

		      },
		      error:function(a,b,c){
		   	   alert("出现未知网络错误");
		      }

		   })
      },
      post2:function(url,data,func){

			$.ajax({
				  headers:{
					  token:getCookie("token")
				  },
			      url:url,
		          type:"post",
		          data: data,
		          dataType:"json",
		      success:function(returnData){

		   	      func(returnData);

		      },
		      error:function(a,b,c){
		   	   alert("出现未知网络错误");
		      }

		   })
     }
       
}

function setCookie(name,value)
{
    //此 cookie 将被保存 30 天
    var Days = 30; 
    var exp = new Date();
 
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/";
    
 
}


function getCookie(name) {
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
 
    if (arr != null) return unescape(arr[2]);
 
    return null;
}

function timetolong(date){
	
	//var date = '2015-03-05 17:59:00.0';
	date = date.substring(0,19);    
	date = date.replace(/-/g,'/'); 
	var timestamp = new Date(date).getTime();
	
	return timestamp;
}
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    };
    return null;
}

function frontQuit(){
	setCookie('token','');
	location.href="frontlogin.html";
}
