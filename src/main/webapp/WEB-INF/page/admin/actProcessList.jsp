<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>流程管理</title>
	<jsp:include page="${ctx}/static/common/comm.jsp"/>
<body>
	<!--工具栏-->
	<div id="processTb">
		<a href="#" onclick="toActive();" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">激活</a>
		&nbsp;
		<a href="#" onclick="toSuspend();" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true">挂起</a>
		&nbsp;
		<a href="#" onclick="toDelete();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>

	<!--用户信息 datagrid-->
	<div id="processTable"></div>

<div id="win"></div>
</body>
<script>
	var selectRow;
	$(function(){
		$("#processTable").datagrid({
			url:'processList',
//			width:100,
			method:'post',
			loadMsg:'正在努力加载！',
			toolbar:'#processTb',
			rownumbers:true,
			pagination:true,
			fitColumns:true,
			columns:[[
				{field:'id',title:'流程ID'},
				{field:'key',title:'流程标识'},
				{field:'name',title:'流程名称'},
				{field:'version',title:'流程版本'},
				{field:'deploymentTime',title:'部署时间',formatter:function(value,row,index){
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
					}
				},
				{field:'resourceName',title:'流程XML',formatter: function(value,row,index){
						return '<span style="color: blue"><a target="_blank" href="/k/act/process/resource/read?procDefId='+row.id+'&resType=xml">'+
								row.resourceName+'</a></span>';
					}
				},
				{field:'diagramResourceName',title:'流程图片',formatter: function(value,row,index){
						return '<span style="color: blue"><a target="_blank" href="/k/act/process/resource/read?procDefId='+row.id+'&resType=image">'+
								row.diagramResourceName+'</a></span>';
					}
				},
				{field:'suspended',title:'状态',formatter: function(value,row,index){
						return row.suspended==false?'激活':'挂起';
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
	//激活
	function toActive(){
		isSelectRow();
		//如果是挂起状态
		if(selectRow.suspended==true){
			$.ajax({
				type: 'POST',
				url:'/k/act/process/update',
				data: {
					state:'active',
					procDefId:selectRow.id
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
		}else{
			alert('不能重复激活');
		}

	}

	//挂起
	function toSuspend(){
		isSelectRow();
		//如果是挂起状态
		if(selectRow.suspended==false){
			$.ajax({
				type: 'POST',
				url:'/k/act/process/update',
				data: {
					state:'suspend',
					procDefId:selectRow.id
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
		}else{
			alert('不能重复挂起');
		}

	}

	//删除
	function toDelete(){
		isSelectRow();

		$.ajax({
			type: 'POST',
			url:'/k/act/process/delete',
			data: {
				deploymentId:process.deploymentId
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