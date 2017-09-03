<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>任务管理</title>
	<jsp:include page="${ctx}/static/common/comm.jsp"/>
<body>
<!--工具栏-->
<div id="processTb">
	<a href="#" onclick="toAgree();" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">通过</a>
	&nbsp;
	<a href="#" onclick="toSuspend();" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true">驳回</a>
</div>


<div id="taskTable"></div>

<div id="win"></div>
</body>
<script>
	var selectRow;
	$(function(){
		$("#taskTable").datagrid({
			url:'list',
			method:'post',
			loadMsg:'正在努力加载！',
			toolbar:'#processTb',
			rownumbers:true,
			pagination:true,
			fitColumns:true,
			columns:[[
				{field:'businessType',title:'单据类型'},
				{field:'user_name',title:'申请人'},
				{field:'title',title:'标题'},
				{field:'taskName',title:'当前节点'},
				{field:'createTime',title:'任务创建时间'},
				{field:'createTime',title:'流程状态'},
				{field:'op',title:'操作'}

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
	//通过申请
	function toAgree(){
		isSelectRow();

		$.ajax({
			type: 'POST',
			url:'/k/act/task/complete',
			data: {
				taskId:selectRow.taskId
			},
			dataType: 'json',
			success: function(data){
				alert(data.message);
				$('#processTable').datagrid('reload');
			},
			error: function(){
				alert('网络异常');
			}
		});
	}
</script>
</html>