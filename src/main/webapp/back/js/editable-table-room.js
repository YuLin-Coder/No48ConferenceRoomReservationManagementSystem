//显示  lst
function viewdata(data){
	var html = '';
	for(var i = 0; i <data.length; i++){
		html +='<tr class="odd">';
		html +='<td class="roomname">'+data[i].name+'</td>';
		html +='<td class="roomtype">'+data[i].type+'</td>';
		html +='<td class="roomaddress">'+data[i].address+'</td>';
		html +='<td class= "time">'+data[i].availableTime+'</td>';
		html +='<td class="capacity">'+data[i].capacity+'</td>';
		if(data[i].status == 2){
			html +='<td class= "status">维修中</td>';
		}else{
			html +='<td class= "status">可使用</td>';
		}
		html +='<td class= "equipment">'+data[i].equipment+'</td>';
		html += ' <td ><a name="'+data[i].id+'" class="edit" onclick="edit('+data[i].id+')">修改</a></td>';
		html += ' <td ><a name="'+data[i].id+'" class="del" onclick="del('+data[i].id+')">删除</a></td>';
		html +='</tr> ';
	}
	$(".bo").html(html);	
}

//显示数据的ajax
function getdata(){
	var url=Config.host+"/back/roomShow";
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
	AJAX.get(url,data,func);//正常应该是get2
}
getdata();

var EditableTable = function () {
    return {

        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }
                oTable.fnDraw();
            }

            //添加方法
            function editRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[0].innerHTML = '<input type="text" class="form-control small" value="' + aData[0] + '">';
                jqTds[1].innerHTML = '<input type="text" class="form-control small" value="' + aData[1] + '">';
                jqTds[2].innerHTML = '<input type="text" class="form-control small" value="' + aData[2] + '">';
                jqTds[3].innerHTML = '<input type="text" class="form-control small" value="' + aData[3] + '">';
                jqTds[4].innerHTML = '<input type="text" class="form-control small" value="' + aData[4] + '">';
                jqTds[5].innerHTML = '<input type="text" class="form-control small" value="' + aData[5] + '">';
                jqTds[6].innerHTML = '<input type="text" class="form-control small" value="' + aData[6] + '">';
                jqTds[7].innerHTML = '<a class="edit" href="">Save</a>';
                jqTds[8].innerHTML = '<a class="cancel" href="">Cancel</a>';
            }
        
            //保存方法
            function saveRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate(jqInputs[4].value, nRow, 4, false);
                oTable.fnUpdate(jqInputs[5].value, nRow, 5, false);
                oTable.fnUpdate(jqInputs[6].value, nRow, 6, false);
                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 7, false);
                oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow, 8, false);
                oTable.fnDraw();
            }

            function cancelEditRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate(jqInputs[4].value, nRow, 4, false);
                oTable.fnUpdate(jqInputs[5].value, nRow, 5, false);
                oTable.fnUpdate(jqInputs[5].value, nRow, 6, false);
                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 7, false);
                oTable.fnDraw();
            }

            //分页
            var oTable = $('#editable-sample').dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-6'i><'col-lg-6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ 每页的记录",
                    "oPaginate": {
                        "sPrevious": "向前",
                        "sNext": "向后"
                    }
                },
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
            jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown

            var nEditing = null;

            //触发editRow()添加方法
            $('#editable-sample_new').click(function (e) {
                e.preventDefault();
                var aiNew = oTable.fnAddData(['', '', '', '', '', '',  '',
                        '<a class="edit" href="">Edit</a>', '<a class="cancel" data-mode="new" href="">Cancel</a>'
                ]);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                nEditing = nRow;
            });

            //删除
            $('#editable-sample a.delete').live('click', function (e) {
                e.preventDefault();

                if (confirm("确认删除?") == false) {
                    return;
                }

                var nRow = $(this).parents('tr')[0];
                oTable.fnDeleteRow(nRow);
                alert("删除成功");
            });
            //？？？没有作用
            $('#editable-sample a.cancel').live('click', function (e) {
                e.preventDefault();
                if ($(this).attr("data-mode") == "new") {
                    var nRow = $(this).parents('tr')[0];
                    oTable.fnDeleteRow(nRow);
                } else {
                    restoreRow(oTable, nEditing);
                    nEditing = null;
                }
            });
            //编辑
            $('#editable-sample a.edit').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == "Save") {
                    /* Editing this row and want to save it */
                    saveRow(oTable, nEditing);
                    nEditing = null;
                    alert("更新成功");
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow);
                    nEditing = nRow;
                }
            });
        }

    };

}();