<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


	<script>

        $(function(){
            $('#userdg').datagrid({
				title:'展示信息',
                width:1150,
                height:500,
                rownumbers:true,
                remoteSort:false,
                nowrap:false,
                loadMsg: '数据正在载入,请稍后.....',
                fitColumns: true,
                resizeHandle: 'left',
                autoRowHeight: true,
                singleSelect: false,
                checkOnSelect: true,
                pagination:true,//分页工具栏
                rownumbers:true,//显示序列
                pageNumber:1,
                pageSize:2,//注意这个数字要求必须是pageList中子元素
                pageList:[2,6,10,20,50],
                toolbar: [{
                    text: '批量导入',
                    iconCls: 'icon-add',
                    handler: openUserSaveDialog
				},'-', {
                    text: '批量导出',
                    iconCls: 'icon-help',
                    handler: function () {
                        var datagrid = $("#userdg").datagrid('getSelections');
                        if (datagrid.length > 0) {
                            //确认是否导出row.id
                            var ids = [];
                            $.each(datagrid, function (idx, row) {
                                ids.push(row.id);
                            });
                            console.log(ids);
                            //发送ajax 导出   注意:ajax在传递数组参数时
                            $.ajax({
                                url: "${pageContext.request.contextPath}/user/batchOut",
                                data: {"ids": ids},
                                type:"post",
                                traditional: true,//使用传统方式传递参数   这样可以传递数组类型参数
                                dataType: "JSON",
                                success: function (result) {
                                    $.messager.show({title: "提示", msg: result.message, width: 300, height: 200});
                                    $("#userdg").datagrid('reload');
                                }
                            });
                        } else {
                            $.messager.alert('提示', '至少勾选一个!!!');
                        }

                    }
                },'-',{
                    text: '自定义导出',
                    iconCls: 'icon-add',
                    handler: opCustom
                }
				],
                url:'${pageContext.request.contextPath}/user/queryAll',
                columns:[[
                    {title: 'cks', field: 'cks', checkbox: true, width:10, align: 'center'},
                    {field:'id',title:'编号',width:40,sortable:true},
                    {field:'phone',title:'电话',width:40,align:'right',sortable:true},
                    {field:'name',title:'姓名',width:40,align:'right',sortable:true},
                    {field:'nickName',title:'发明',width:40,align:'right',sortable:true},
                    {field:'sex',title:'性别',width:40,align:'right',sortable:true},
                    {field:'createDate',title:'创建时间',width:60,align:'right',sortable:true},
                    {field:'img',title:'头像',width:40,align:'right',sortable:true},
                    {field:'signature',title:'签名',width:40,align:'right',sortable:true},
                    {field:'password',title:'密码',width:40,align:'right',sortable:true},
                    {field:'salt',title:'私盐',width:40,align:'right',sortable:true},
                    {field:'status',title:'用户状态',width:60,sortable:true,
                        styler: function(value,row,index){
                            if (value=="冻结"){
                                return 'color:red;';
                            }
                            return 'color:green';
                        }

                    },

                    {field:'location',title:'地址',width:60,sortable:true},
					{field:'cz',title:'操作',width:40,align:'center',
                        formatter:function(value,row,index){
                            return "<a class='upd' id='update' onclick=\"openEditUserInfo('"+ row.id + "')\" data-options=\"iconCls:'icon-edit',plain:true\">修改</a>";
                        }
					}
                ]],
                onLoadSuccess:function(data){
                    $(".upd").linkbutton();
                },
               /* view: detailview,
                detailFormatter: function(rowIndex, rowData){
                    return '<table><tr>' +
                        '<td rowspan=2 style="border:0"><img src="http://192.168.136.132:8888/'+ rowData.groupname + '/' +
						''+rowData.imgurl+'" style="height:150px;width:200px"></td>' +
                        '<td style="border:0">' +
                        '<p>用户名: ' + rowData.name + '</p>' +
                        '<p>用户状态: ' + rowData.status + '</p>' +
                        '</td>' +
                        '</tr></table>';
                }*/
            });
        });
        <%--自定义导出--%>
        function opCustom(){
			$("#custom").dialog({
                closed:false,
				title:'自定义导出',
                href: "${pageContext.request.contextPath}/menu/user/custom.jsp",
                buttons: [{
                    text: '确认',
                    iconCls: 'icon-save',
                    handler: saveEditUserInfo,
                }, {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        $("#custom").dialog({
                            closed:true,
                        })
                    }
                }],
			})
		}
        /*修改*/
        function openEditUserInfo(id) {
            $("#dl").dialog({
				closed:false,
                href: "${pageContext.request.contextPath}/menu/user/update.jsp?id="+id,
                buttons: [{
                    text: '保存',
                    iconCls: 'icon-save',
                    handler: saveEditUserInfo,
                }, {
                    text: '关闭',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        $("#dl").dialog({
							closed:true,
						})
                    }
                }],
            });
        }
        //保存修改
        function saveEditUserInfo() {
            $("#updateFrom").form('submit', {
                url: '${pageContext.request.contextPath}/user/update',
                success: function (result) {//form表单获取的结果为json格式字符串  使用时需要转为js对象
                    //1.转为js对象 $.parseJSON(result);
                    var parseJSON = $.parseJSON(result);
                    //2.提示消息
                    $.messager.show({title: "提示", msg: parseJSON.message, width: 300, height: 200});
                    //3.刷新datagrid
                    if (parseJSON.success) {
                        $("#dl").dialog('close', true);
                        $("#userdg").datagrid('reload');
                    }
                }
            });
        }

        //批量导入用户信息
        function openUserSaveDialog() {
            $("#dladd").dialog({
				title:'批量导入用户信息',
                closed:false,
                href: '${pageContext.request.contextPath}/menu/user/add.jsp',
                buttons: [
                    {
                        iconCls: 'icon-save',
                        text: '保存',
                        handler: saveUserInfo,
                    },
                    {
                        iconCls: 'icon-cancel',
                        text: '取消',
                        handler: function () {
                            $("#dladd").dialog('close', true);
                        }
                    }
                ]
            });
        }
        //保存用户信息
        function saveUserInfo() {
            $("#addFrom").form('submit', {
                url: "${pageContext.request.contextPath}/user/add",
                success: function (result) {
                    //关闭保存对话框
                    $("#dladd").dialog('close', true);
                    //1.转为js对象
                    var jsObject = $.parseJSON(result);
                    //2.展示信息
                    $.messager.show({title: '提示', msg: jsObject.message});
                    if (jsObject.success) {
                        //2.刷新datagrid
                        $("#userdg").datagrid('reload');
                    }
                }
            })
        }
	</script>

<%--轮播图修改--%>
	<div id="dl" class="easyui-dialog" title="修改轮播图信息" style="width:600px;height:300px;"
		 data-options="iconCls:'icon-save',closed:true,resizable:true,modal:true">
	</div>
<%--轮播图添加--%>
	<div id="dladd" class="easyui-dialog" title="My Dialog" style="width:600px;height:300px;"
		 data-options="iconCls:'icon-save',closed:true,resizable:true,modal:true">
	</div>
<%--自定义导出--%>
<div id="custom" class="easyui-dialog" title="My Dialog" style="width:600px;height:300px;"
	 data-options="iconCls:'icon-save',closed:true,resizable:true,modal:true">
</div>
<%--用户信息显示表格--%>
<table id="userdg"></table>


