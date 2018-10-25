<%@ page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        // 当前所选中的字段和字段对应的中文名称传给后台
        // 假如用户选择的是编号和姓名
        // 1. 编号,姓名
        // 2. id,name
        $("#cc").combotree({
            animate: true,
            lines: true,
            multiple: true,
            data: [{
                text: '自定义导出设置',
                // state: 'closed',
                children: [{
                    text: '编号',
                    iconCls: "icon-bug",
                    id: "id"
                }, {
                    text: '手机号',
                    iconCls: "icon-bug",
                    id: "phone"
                }, {
                    text: '名字',
                    iconCls: "icon-bug",
                    id: "name",
                }, {
                    text: '法名',
                    iconCls: "icon-bug",
                    id: "nickName"
                }, {
                    text: '性别',
                    iconCls: "icon-bug",
                    id: "sex"
                },{
                    text: '创建时间',
                    iconCls: "icon-bug",
                    id: "createDate"
                },{
                    text: '签名',
                    iconCls: "icon-bug",
                    id: "signature"
                }, {
                    text: '私盐',
                    iconCls: "icon-bug",
                    id: "salt"
                }, {
                    text: '密码',
                    iconCls: "icon-bug",
                    id: "password"
                }, {
                    text: '状态',
                    iconCls: "icon-bug",
                    id: "status"
                },{
                    text: '位置',
                    iconCls: "icon-bug",
                    id: "location"
                }, ]
            }],
            checkbox: true,
        });

        $("#btn").linkbutton({
            onClick: function () {
                var titles = $("#cc").combotree("getText");
                var columns = $("#cc").combotree("getValues");
                console.log(titles + "|" + columns);
                $("#ff").form("submit", {
                    url: "${pageContext.request.contextPath}/user/customExport",
                    onSubmit: function (param) { // 提交表单时，提交额外的请求参数：
                        param.titles = titles;
                        param.columns = columns;
                    }
                });
            }
        });
    });
</script>
<form id="ff" method="post">
    <div style="text-align: center;margin-top: 30px">
        自定义导出设置：<select id="cc" class="easyui-combotree" style="width:200px;" data-options="required:true"></select>
    </div>
</form>
<div style="text-align: center;margin-top: 30px">
    <a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-cdr'">导出</a>
</div>