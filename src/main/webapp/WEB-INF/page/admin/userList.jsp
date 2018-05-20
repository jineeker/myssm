<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>人员管理</title>
	<jsp:include page="${ctx}/static/common/comm.jsp"/>
	<script type="text/javascript" src='${ctx}/static/js/user.js'> </script>
	<script type="text/javascript" src='${ctx}/static/common/jquery.form.js'> </script>
</head>
<body>
	<!--工具栏-->
	<div id="userTb">
		<a href="#" onclick="toAddUser();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
		&nbsp;
		<a href="#" onclick="toEditUser();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		&nbsp;
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>

	<!--用户信息 datagrid-->
	<div id="userDiv"></div>
	<div id="win"></div>

	<%--<form id="importForm" action="/k/index/importForm4File" method="post" enctype="multipart/form-data">
        导入文件：
        <input class="type-file-file"
               id="file" name="file" type="file" size="30" hidefocus="true" onchange="chooseFile();">
        <div>
            <input type="button" id="submitBtn" onclick="fileSubmit();" value="导入" />
        </div>
    </form>--%>


</body>
<%--<script type="text/javascript">
    //选择文件按钮事件
    function chooseFile() {
        var file = $("#file").val();
        if (file) {
            var point = file.lastIndexOf(".");
            var type = file.substr(point);
            console.log(type);
            if (type == ".xls" || type == ".xlsx" || type == ".txt") {
                var pos = file.lastIndexOf("\\");
            } else {
                alert("您选择的文件格式不对！");
                $("#file").val("");
            }
        } else {
            alert("请选择文件！");
        }
    }
    //导入
    function fileSubmit() {
        var importForm = new FormData(document.getElementById("importForm"));
        if (true) {
            //阻止默认事件
            event.preventDefault();
            //循环查看状态
            var t = setInterval(function(){
                $.ajax({
                    url: '/k/index/fileProgress',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        formId: $("#formId").val()
                    },
                    success: function (data) {
                        //前台更新进度
//                        console.log(parseInt((data.progress / data.size) * 100));
                        console.log("已上传"+data.progress+"/"+data.size);
                    },
                    error: function(){
                        console.log("error");
                    }
                });
            }, 100);

            $("#importForm").ajaxSubmit({
                success : function(data) {
                    if (null!=data.message && data.message != '') {
                        alert(data.message);
                        $("#submitBtn").attr("disabled", false);
                        parent.window.$('#backMask').hide();
                    } else {
                        //上传完成，清除循环事件
                        clearInterval(t);

                        $("#submitBtn").attr("disabled", true);
                        return;
                    }
                }
            });

        }
    }
</script>--%>

<script>
    var selectRow;
    $(function(){
        $("#userDiv").datagrid({
            url:'user.do',
            method:'post',
            loadMsg:'正在努力加载！',
            toolbar:'#userTb',
            rownumbers:true,
            pagination:true,
            fitColumns:true,
            singleSelect:true,
            columns:[[
                {field:'id',title:'id'},
                {field:'username',title:'用户名'},
                {field:'roleName',title:'角色'},
                {field:'locked',title:'状态',formatter:function(value,row,index){
						return value=='0'?'正常':'锁定';
                	}
				}
            ]],
            onLoadSuccess : function () {//配置首列编号宽度的自适应
                $(this).datagrid("fixRownumber");
            },
            onSelect:function(rowIndex, rowData){
                selectRow = rowData;
            }

        });
    });

    //判断是否有选中行
    function isSelectRow(){
        if(selectRow==null){
            $.messager.alert("提示", "请选中一行！", "info");
            return;
        }
    }
    //增加用户弹窗
    function toAddUser(){
        $('#win').dialog({
            href:'userUpdateView.do',
            width:400,
            height:250,
            modal:true,
            minimizable:false,
            title:'添加用户信息'
        });
    }
    //修改用户
    function toEditUser(){
        isSelectRow();

        $('#win').dialog({
            href:'userUpdateView.do?id='+selectRow.id,
            width:400,
            height:250,
            modal:true,
            minimizable:false,
            title:'修改用户信息'
        });
    }
</script>
</html>