<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<html>

    <body>
    <div align="center" style="margin-top: 50px">
        <form id="updateForm" action="" method="post">

            新密码： &nbsp;&nbsp;&nbsp;<input type="password" class="easyui-textbox" data-options="iconCls:'icon-search'"
                                        required="true"  missingMessage="请填写新密码" name="password" style="width:200px"><br><br>


               确认密码： <input type="password" class="easyui-textbox" data-options="iconCls:'icon-search'"
                            required="true"  missingMessage="请填写新密码" validType="cofirmPwd['#updateForm input[name=password]']" style="width:200px"><br><br><br><br>
        <%--<input id="btn" type="submit" class="easyui-linkbutton" ></input>
        <input id="bt" type="reset" class="easyui-linkbutton" ></input>--%>



        </form>
    </div>
    </body>
</html>
