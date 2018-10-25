<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style type="text/css">
        .toolbar {
            background-color: #f1f1f1;
            border: 1px solid #ccc;
        }

        .text {
            border: 1px solid #ccc;
            height: 200px;
        }
    </style>
</head>
<body>

<!-- 引用js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/menu/js/wangEditor.js"></script>
<script type="text/javascript">
    var E = window.wangEditor
    var editor1 = new E('#div1')
    editor1.customConfig.uploadImgServer = '${pageContext.request.contextPath}/editor/upload'  // 上传图片到服务器
    editor1.customConfig.uploadFileName = 'keyName';
    editor1.create()

   $(function () {
       $.get("${pageContext.request.contextPath}/person/findAll",function (result) {
               //console.log(result);
               //console.log(menu); 这是获得的对象
               $('#cc').combobox({
                  valueField:'id',
                  textField:'name',
                   data:result
               });
       })


      /* $("#btn").linkbutton({
           onClick: function () {
               $("#for").form("submit", {
                   url: "${pageContext.request.contextPath}/editor/articleUpload",
                    onSubmit: function (param) { // 提交表单时，提交额外的请求参数：
                        param.console = console;
                      //  param.columns = columns;
                    }
               });
           }
       });*/

   })

    /* 这是清空的事件*/
    function reset() {

            alert("自己写！！")

    }
    function createArticle() {
        var content = editor1.txt.html();
        //console.log(content);
        $("#for").form("submit", {
            url: "${pageContext.request.contextPath}/editor/articleUpload",
            onSubmit: function (param) { // 提交表单时，提交额外的请求参数：
                param.content = content;
                //  param.columns = columns;
            }
        });
    }
</script>
   <div style="background-color: #95B8E7 ">
       <div style="margin-left: 50px">
           <form id="for" method="post" >
               <br><br><br>
               文章标题 ： <input name="title" class="easyui-textbox" data-options="required:true"><br><br>
               文章作者 ： <input name="author" id="cc" class="easyui-combobox" ><br><br>
               文章状态 ： <input name="status" class="easyui-switchbutton" data-options="onText:'上架',offText:'下架'"><br><br>
               文章内容 ：<br>
             <div style="background-color: white">
                 <div id="div1">

                 </div>
             </div>
           </form>
           <button id="btn" onclick="createArticle()">创建文章</button>
           <button id="clear" onclick="reset()" >重置内容</button>
       </div>
   </div>

</body>
</html>