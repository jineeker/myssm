<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>运行中的流程</title>
	<jsp:include page="${ctx}/static/common/comm.jsp"/>
<body>
	<!--工具栏-->
	<div id="processTb">
		<a href="#" onclick="toAddUser();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
		&nbsp;
		<a href="#" onclick="toEditUser();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		&nbsp;
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>

	<!--用户信息 datagrid-->
	<div id="userListTable"></div>

<div id="win"></div>
</body>
<script>
	var selectRow;
	$(function(){
		$("#userListTable").datagrid({
			url:'running',
			//width:700,
			method:'post',
			loadMsg:'正在努力加载！',
			toolbar:'#processTb',
			rownumbers:true,
			pagination:true,
			fitColumns:true,
			columns:[[
				/*<th>执行ID</th>
				<th>流程实例ID</th>
				<th>流程定义ID</th>
				<th>当前环节</th>
				<th>是否挂起</th>
				<th>操作</th>*/
				{field:'id',title:'执行ID'},
				{field:'processInstanceId',title:'流程实例ID'},
				{field:'processDefinitionId',title:'流程定义ID'},
				{field:'activityId',title:'当前环节'},
				{field:'suspended',title:'是否挂起'}
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
//	function isSelectRow(){
//		if(selectRow==null){
//			$.messager.alert("提示", "请选中一行！", "info");
//			return;
//		}
//	}
//	//增加用户弹窗
//	function toAddUser(){
//		$('#win').dialog({
//			href:'userUpdateView.do',
//			width:400,
//			height:250,
//			modal:true,
//			minimizable:false,
//			title:'添加用户信息'
//		});
//	}
//	//修改用户
//	function toEditUser(){
//		isSelectRow();
//
//		$('#win').dialog({
//			href:'userUpdateView.do?userAccount='+selectRow.userAccount,
//			width:400,
//			height:250,
//			modal:true,
//			minimizable:false,
//			title:'修改用户信息'
//		});
//	}

</script>
</html>