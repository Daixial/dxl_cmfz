<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<form id="userEditForm" class="easyui-form" method="post">

    ID: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="id" readonly value="id" class="easyui-textbox" data-options=""/><br>
    文件名:&nbsp;&nbsp; &nbsp;<input type="text" name="name" value="name" readonly class="easyui-textbox" data-options=""/><br>
    状态: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<input type="text" name="status" value="status" class="easyui-textbox" data-options=""/><br>
    创建时间: <input type="text" name="createDate" value="createDate" readonly class="easyui-datebox" data-options=""/><br>

</form>
<script>
    $(function () {
        $("#userEditForm").form('load',"${pageContext.request.contextPath}/picture/findOne?id=${param.id}");
    })
</script>