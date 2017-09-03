<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>请假管理</title>
	<jsp:include page="${ctx}/static/common/comm.jsp"/>
<body>
<!--工具栏-->
<div id="processTb">
	<a href="#" onclick="toActive();" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">添加</a>
	&nbsp;
	<a href="#" onclick="toSuspend();" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true">审批</a>
	&nbsp;
	<a href="#" onclick="toDelete();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
</div>


<div id="leaveTable"></div>

<div id="win"></div>
</body>
<script>
	var selectRow;
	$(function(){
		$("#leaveTable").datagrid({
			url:'list',
			method:'post',
			loadMsg:'正在努力加载！',
			toolbar:'#processTb',
			rownumbers:true,
			pagination:true,
			fitColumns:true,
			columns:[[
				{field:'leaveId',title:'ID'},
				{field:'reason',title:'请假原因'},
				{field:'processInstanceId',title:'流程id'},
				{field:'leaveDays',title:'请假天数'},
				{field:'startTime',title:'开始时间',formatter:function(value,row,index){
						var unixTimestamp = new Date(value);
						return unixTimestamp.toLocaleString();
					}
				},
				{field:'endTime',title:'结束时间',formatter:function(value,row,index){
						var unixTimestamp = new Date(value);
						return unixTimestamp.toLocaleString();
					}
				},
				{field:'createDate',title:'创建时间',formatter:function(value,row,index){
						var unixTimestamp = new Date(value);
						return unixTimestamp.toLocaleString();
					}
				},
				{field:'taskName',title:'任务节点名'},
				{field:'taskUser',title:'当前任务人'},
				{field:'taskDesc',title:'任务审批备注'}

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
</script>
</html>