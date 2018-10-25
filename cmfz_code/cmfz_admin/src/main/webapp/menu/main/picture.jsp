<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

    <script>
        $(function () {
            $('#ww').datagrid({
               /* width:1050,
                height:500,*/
                fit:true,
                toolbar: [{
                    text:'添加',
                    iconCls: 'icon-edit',
                    handler: openUserSaveDialog,
                },'-',{
                    text:'帮助',
                    handler: function(){alert('帮助按钮')}
                }],
                remoteSort:false,
                singleSelect:true,
                pagination: true,//分页工具栏
                rownumbers: true,
                pageNumber: 1,
                pageSize: 3,//注意这个数字要求必须是pageList中子元素
                pageList: [3, 6, 10, 20, 50],
                nowrap:false,
                rowStyler: function (index, row) {
                    if (index % 2 == 0) {
                        return 'background-color:#6293BB;color:#fff;';
                    }
                },
                fitColumns:true,
                url:'${pageContext.request.contextPath}/picture/page',
                columns:[[
                    {field:'id',title:'ID',width:80},
                    {field:'name',title:'文件名',width:60,sortable:true},
                    {field:'imgurl',title:'文件路径',width:120,align:'right',sortable:true},
                    {field:'groups',title:'文件所在组',width:50,align:'right',sortable:true},
                    {field:'status',title:'是否展示',width:20,align:'center',
                        styler: function(value,row,index){
                            if (value == "n"){
                                return 'background-color:#ffee00;color:red;';
                            }
                        }
                    },
                    {field:'createDate',title:'创建时间',width:50,align:'right',sortable:true},
                    {field:'options',title:'操作',width:50,sortable:true,
                        formatter:function(value,row,index){
                               /* console.log(row.id);*/
                            return "<a class='upd'  onclick=\"openEditUserInfo('" + row.id + "')\" data-options=\"iconCls:'icon-edit',plain:true\">修改</a>";
                        }
                    }

                ]],
                onLoadSuccess:function(data){
                   /* $(".del").linkbutton();*/
                    $(".upd").linkbutton();
                },
                view: detailview ,
                detailFormatter: function(rowIndex, rowData){
                    return '<table><tr>' +
                        '<td rowspan=2 style="border:0"><img src="http://192.168.136.132:8888/' + rowData.groups + '/'+rowData.imgurl+'" style="height:150px;width:200"></td>' +
                        /*'<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/picture/download?imgurl=' + rowData.imgurl + '&groups='+rowData.groups+'" style="height:150px;width:200"></td>' +*/
                        '<td style="border:0">' +
                        '<p>名字: ' + rowData.name + '</p>' +
                        '<p>状态: ' + rowData.status + '</p>' +
                        '</td>' +
                        '</tr></table>';
                }
            });

        })
        //用来打开用户编辑信息的对话框
        function openEditUserInfo(id) {
            $("#userEditorDialog").dialog({
                href: "${pageContext.request.contextPath}/menu/main/back/edit.jsp?id=" + id,
                closed:false,
                buttons: [{
                    text: '保存',
                    iconCls: 'icon-save',
                    handler: saveEditUserInfo,
                }, {
                    text: '关闭',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        $("#userEditorDialog").dialog({
                            closed:true,
                        })
                    }
                }],
            });
        }
        //保存编辑的用户信息
        function saveEditUserInfo() {
            $("#userEditForm").form('submit', {
                url: '${pageContext.request.contextPath}/picture/update',
                success: function (result) {//form表单获取的结果为json格式字符串  使用时需要转为js对象
                    //1.转为js对象 $.parseJSON(result);
                    var parseJSON = $.parseJSON(result);
                    //2.提示消息
                    $.messager.show({title: "提示", msg: parseJSON.message, width: 300, height: 200});
                    //3.关闭对话框
                    $("#userEditorDialog").dialog('close', true);
                    //4.刷新datagrid
                    if (parseJSON.success) {
                        $("#ww").datagrid('reload');
                    }
                }
            });
        }
        //用来打开保存用户的对话信息
        function openUserSaveDialog() {

            $("#userSaveDialog").dialog({
                href: '${pageContext.request.contextPath}/menu/main/back/save.jsp',
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
                            $("#userSaveDialog").dialog('close', true);
                        }
                    }
                ]
            });
        }
        //保存用户信息
        function saveUserInfo() {
            $("#userSaveForm").form('submit', {
                url: "${pageContext.request.contextPath}/picture/add",
                success: function (result) {
                    //关闭保存对话框
                    $("#userSaveDialog").dialog('close', true);
                    //1.转为js对象
                    var jsObject = $.parseJSON(result);

                    //2.展示信息
                    $.messager.show({title: '提示', msg: jsObject.message});
                    if (jsObject.success) {
                        //2.刷新datagrid
                        $("#ww").datagrid('reload');
                    }
                }
            })
        }

    </script>

<div id="userEditorDialog" class="easyui-dialog" title="修改状态" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

</div>
<table id="ww"></table>
<%--用户信息展示表格--%>
<table id="dg"></table>
<%--保存用户的对话框--%>
<div id="userSaveDialog" data-options="title:'用户保存',width:600,height:400"></div>