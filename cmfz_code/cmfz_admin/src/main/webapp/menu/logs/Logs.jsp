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
                url:'${pageContext.request.contextPath}/logs/page',
                columns:[[
                    {field:'id',title:'ID',width:80},
                    {field:'user',title:'用户',width:60,sortable:true,
                        styler: function(value,row,index){
                            if (value == "null"){
                                return 'background-color:#ffee00;color:red;';
                            }
                        }
                    },
                    {field:'time',title:'操作时间',width:120,align:'right',sortable:true},
                    {field:'resource',title:'功能',width:50,align:'right',sortable:true},
                    {field:'action',title:'操作',width:20,align:'center'},
                    {field:'message',title:'信息',width:50,align:'right',sortable:true},
                    {field:'result',title:'操作结果',width:50,align:'right',sortable:true},
                ]],
            });

        })




    </script>

<div id="userEditorDialog" class="easyui-dialog" title="修改状态" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

</div>
<table id="ww"></table>
<%--用户信息展示表格--%>
<table id="dg"></table>
<%--保存用户的对话框--%>
<div id="userSaveDialog" data-options="title:'用户保存',width:600,height:400"></div>