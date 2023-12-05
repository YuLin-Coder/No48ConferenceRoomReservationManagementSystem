//显示  lst
function viewdata(data){
    var html = '';
    for(var i = 0; i <data.length; i++){
        html +='<tr class="odd" id="'+data[i].id+'">';
        html +='<td class="appointId">'+data[i].appointId+'</td>';
        html +='<td class="appointerName">'+data[i].appointerName+'</td>';
        html +='<td class="theme">'+data[i].theme+'</td>';
        html +='<td class="roomAdress">'+data[i].roomAdress+'</td>';
        html +='<td class="date">'+data[i].date+'</td>';
        html +='<td class="time">'+data[i].time+'</td>';
        if(data[i].specialneeds == null){
            html +='<td class="specialneeds">无</td>';
        }else{
            html +='<td classs="specialneeds">'+data[i].specialneeds+'</td>';
        }

        html += ' <td><span class="label label-info label-mini">已结束</span></td>';
        html +='</tr> ';
    }
    $("#content").html(html);
}
//显示数据的ajax
function getdata(){
    var url=Config.host+"/front/myConferenceShow";
    //var url = "http://localhost:8080/ConferenceRoom/back/userShow";
    var data={};
    var func=function(returndata){
        if(returndata.code!="000000"){
            layer.alert('请登录', {
                skin: 'layui-layer-lan' //样式类名
            },function(){
                location.href="frontlogin.html";
            });
        }	else{
            viewdata(returndata.data);
        }
        // viewdata(returndata.data);
    }
    AJAX.get2(url,data,func);//正常应该是get2
}
getdata();