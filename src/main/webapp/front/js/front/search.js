$("#bt").on("click",function(){
	var json = {
		  "date":$("#test2").val(),
		  // "starttime":$("#starttime").val(),
			// "endtime": $("#endtime").val()
		    }
	getdata(json);

	$("#show1").css('display','')
});
//显示  lst
function viewdata(data){
	console.log(data);
	var html1 = '';
	if(data == null){
		html1 +='<tr class="odd">';
		html1 +='<td>暂无会议</td>';
		html1 +='</tr> ';
	}else{
		for(var i = 0; i <data.length; i++){		
			/**
			 * 按日期搜索出的会议信息
			 */
			html1 +='<tr class="odd">';
			html1 +='<td >'+data[i].confenenceId+'</td>';
			html1 +='<td >'+data[i].conRoomName+'</td>';
			html1 +='<td >'+data[i].conRoomType+'</td>';
			html1 +='<td >'+data[i].time+'</td>';
			html1 +='</tr> ';
		}	
	}
	$("#searchshow1").html(html1);
}
function viewdata2(data){
	var html='';
	if(data == null){
		html +='<tr class="odd">';
		html +='<td>暂无可使用的会议室</td>';
		html +='</tr> ';
	}else{
		for(var i = 0; i <data.length; i++){		
			html +='<tr class="odd">';
			html +='<td >'+data[i].roomName+'</td>';
			html +='<td >'+data[i].address+'</td>';
			html +='<td >'+data[i].capacity+'</td>';
			html +='<td >'+data[i].type+'</td>';
			html +=' <td><a href="book.html"><span class="label label-success label-mini">本系预约</a></span></td>';			
			html +=' <td><a href="book2.html"><span class="label label-warning label-mini">跨系预约</a></span></td>';
		// 		html +=' <td><a href="book.html"><span class="label label-warning label-mini">我要预约</a></span></td>';
			if(data[i].status == 2){
				html +='<td>维修中,'+data[i].equipment+'</td>';
			}else{
				html +='<td>可使用,'+data[i].equipment+'</td>';
			}
			html +='</tr> ';
		}
	}
	$("#searchshow").html(html);
}

//显示数据的ajax
function getdata(data){
	var url=Config.host+"/front/roomSearch2";
	var func=function(returndata){
		viewdata(returndata.data);
	}
	AJAX.post(url,data,func);

	var url2=Config.host+"/front/roomSearch";
	//var url = "http://localhost:8080/ConferenceRoom/front/roomSearch";
	var func2=function(returndata){
		viewdata2(returndata.data);
	}
	AJAX.post(url2,data,func2);
}
