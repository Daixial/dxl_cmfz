<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/menu/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/menu/themes/IconExtension.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/menu/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/menu/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/menu/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/form.validator.rules.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/menu/js/datagrid-detailview.js"></script>
	<script>
        $(function(){
            //发送ajax请求查询菜单数据
            $.get("${pageContext.request.contextPath}/menu/queryAll",function (result) {
                //遍历菜单数据
                $.each(result,function (idx,menu) {
                    //遍历二级孩子菜单
                    var content = "<div style='text-align: center;'>";
                    $.each(menu.list,function(idx,child){
                        var json = JSON.stringify(child);
                        content += "<div onclick='addTabs("+json+")' style='border:1px #95B8E7 solid;width:90%;margin:5px 0px 5px 0px;' class='easyui-linkbutton' " +
                            "data-options=\"plain:true,iconCls:'"+ child.iconCls +"'\">"+child.title+"</div><br/>";
                    });
                    content +="</div>"
                    //渲染一级菜单
                    $("#menus").accordion('add',{
                        title:menu.title,
                        iconCls:menu.iconCls,
                        content:content,
                    });
                });
            },"JSON");
            
            $("#update").click(function () {
				$("#div").dialog({
					closed:false,
					buttons:[{
					    text:'保存',
						handler:upda,
					},{
					    text:'关闭',
                        handler:function () {
                            $("#div").dialog({
								closed:true,
							})
                        }
						
					}]
				})
            })

            function upda() {

                $("#updateForm").form("submit",{
                        url:'${pageContext.request.contextPath}/manager/update',
                        success: function(result){
                           /* var parseJSON = $.parseJSON(result);
                            if(parseJSON.success){

							}*/
                        }
                });
                $("#div").dialog('close',true);
            }


        })

      //点击链接添加选项卡
        function addTabs(child){

            if(!$("#tt").tabs('exists',child.title)){
                console.log(child);
                $("#tt").tabs('add',{
                    title:child.title,
                    iconCls:child.iconCls,
                    closable:true,
                    href:"${pageContext.request.contextPath}"+child.href,
                    fit:true,
                });
            }else{
                $("#tt").tabs('select',child.title);
            }
        }
        
	</script>

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:${sessionScope.mana.name} &nbsp;<a href="#" id="update" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/manager/esc" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 gaozhy@zparkhr.com.cn</div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="menus" class="easyui-accordion" data-options="fit:true">
	</div>
    </div>   
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">   
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/IMG_1440.JPG);background-repeat: no-repeat;background-size:100% 100%;"></div>
			
		</div>
    </div>
	<div id="div" class="easyui-dialog" title="My Dialog" style="width:500px;height:300px;"
		 data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,href:'${pageContext.request.contextPath}/menu/main/in.jsp'">

	</div>
</body> 
</html>