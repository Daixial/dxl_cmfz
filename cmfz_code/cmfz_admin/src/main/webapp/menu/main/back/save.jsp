<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<form id="userSaveForm" class="easyui-form" method="post" enctype="multipart/form-data">

    <br>
    图片路径: <input  name="uploadFile" class="easyui-filebox"/><br>
     文件名: &nbsp;&nbsp;<input type="text" name="name"  class="easyui-textbox" /><br>
    图片状态：<select id="cc" class="easyui-combobox" name="status" style="width:200px;">
                    <option value="y">展示</option>
                    <option value="n" >不展示</option>
              </select>
</form>
