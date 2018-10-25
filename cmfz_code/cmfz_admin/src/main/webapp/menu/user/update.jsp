<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<div align="center" style="margin-top: 60px">
<form id="updateFrom" class="easyui-form" method="post">


    <input  name="id" hidden="hidden" />
    展示状态:
    <select class="easyui-combobox" name="status" style="width:200px;">
        <option >冻结</option>
        <option >未冻结</option>
    </select>

</form>

</div>
<script>
    $(function () {
        $("#updateFrom").form('load',"${pageContext.request.contextPath}/user/findOne?id=${param.id}");
    })
</script>