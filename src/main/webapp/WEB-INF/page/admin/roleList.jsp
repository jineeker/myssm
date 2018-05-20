<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>角色管理</title>
	<jsp:include page="${ctx}/static/common/comm.jsp"/>
	<script type="text/javascript" src='${ctx}/static/js/user.js'> </script>
</head>
<body>
	<!--工具栏-->
	<div id="roleTb">
		<a href="#" onclick="toAddRole();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
		&nbsp;
		<a href="#" onclick="toEditRole();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		&nbsp;
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		&nbsp;
		<a href="#" onclick="toAddRoleMenu();"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">绑定菜单</a>
		&nbsp;
		<a href="#" onclick="toAddRolePerm();"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加权限</a>
	</div>

	<!--信息列表 datagrid-->
	<div id="roleDiv"></div>

<div id="win"></div>
<div id="menuWin"></div>
</body>
<script>
    var selectRow;
    $(function(){
        $("#roleDiv").datagrid({
            url:'role.do',
            method:'post',
            loadMsg:'正在努力加载！',
            toolbar:'#roleTb',
            rownumbers:true,
            pagination:true,
            fitColumns:true,
            singleSelect:true,
            columns:[[
                {field:'id',title:'id'},
                {field:'roleName',title:'名称'},
                {field:'rolePerms',title:'权限'},
                {field:'available',title:'状态',formatter:function(value,row,index){
						return value=='1'?'可用':'不可用';
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
    function toAddRole(){
        $('#win').dialog({
            href:'roleUpdateView.do',
            width:400,
            height:250,
            modal:true,
            minimizable:false,
            title:'添加角色信息'
        });
    }
    //修改用户
    function toEditRole(){
        isSelectRow();

        $('#win').dialog({
            href:'roleUpdateView.do?id='+selectRow.id,
            width:400,
            height:250,
            modal:true,
            minimizable:false,
            title:'修改角色信息'
        });
    }

    //编辑角色菜单弹窗
    function toAddRoleMenu(){
        $('#menuWin').dialog({
            href:'roleMenuView.do?id='+selectRow.id,
            width:800,
            height:450,
            modal:true,
            minimizable:false,
            title:'绑定菜单'
        });
    }

    //编辑角色权限弹窗
    function toAddRolePerm(){
        $('#win').dialog({
            href:'rolePermView.do?id='+selectRow.id,
            width:400,
            height:250,
            modal:true,
            minimizable:false,
            title:'添加权限'
        });
    }
</script>
</html>