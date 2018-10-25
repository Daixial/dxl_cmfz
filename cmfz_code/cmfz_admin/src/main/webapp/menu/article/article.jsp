<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<script>
    $(function () {
        $('#ww').datagrid({
            /* width:1050,
             height:500,*/
            fit:true,
            toolbar: [{
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
            url:'${pageContext.request.contextPath}/editor/queryAll',
            columns:[[
                {field:'id',title:'ID',width:80},
                {field:'title',title:'标题',width:120,sortable:true},
                {field:'createDate',title:'创建时间',width:150,align:'right',sortable:true},
                {field:'author',title:'作者',width:80,align:'right',sortable:true},
                {field:'status',title:'是否展示',width:60,align:'center',
                    styler: function(value,row,index){
                        if (value == "n"){
                            return 'background-color:#ffee00;color:red;';
                        }
                    }
                },
                {field:'options',title:'操作',width:90,sortable:true,
                    formatter:function(value,row,index){
                        /* console.log(row.id);*/
                        return "<a class='upd'  onclick=\"openEditUserInfo('" + row.id + "')\" data-options=\"iconCls:'icon-edit',plain:true\">修改</a>";
                    }
                }

            ]],
            onLoadSuccess:function(data){
                /* $(".del").linkbutton();*/
                $(".upd").linkbutton();
            }
        });

    })
    //用来打开用户编辑信息的对话框
    function openEditUserInfo(id) {
        $("#userEditorDialog").dialog({
            href: "${pageContext.request.contextPath}/menu/article/edit.jsp?id=" + id,
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
            url: '${pageContext.request.contextPath}/editor/updateArticle',
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



</script>

<div id="userEditorDialog" class="easyui-dialog" title="修改状态" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

</div>
<table id="ww"></table>
<%--用户信息展示表格--%>
<table id="dg"></table>
<%--保存用户的对话框--%>
<div id="userSaveDialog" data-options="titl