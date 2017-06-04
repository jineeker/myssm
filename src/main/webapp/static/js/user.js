var selectRow;
$(function(){
	$("#userListTable").datagrid({
		url:'user.do',
		//width:700,
		method:'post',
		loadMsg:'正在努力加载！',
		toolbar:'#userTb',
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		columns:[[
			{field:'usersId',title:'序号'},
			{field:'userName',title:'用户昵称'},
			{field:'userAccount',title:'账号'},
			{field:'password',title:'密码'},
			{field:'userDesc',title:'用户描述'}
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
		href:'userUpdateView.do?userAccount='+selectRow.userAccount,
		width:400,
		height:250,
		modal:true,
		minimizable:false,
		title:'修改用户信息'
	});
}
//$(function(){
//	$("#memberListWin").datagrid({
//		url:'user.do',
//		method:'post',
//		loadMsg:'正在加载',
//		queryParams:{},
//		remoteSort: false,
//		idField:'reportId',
//		columns:[[
//			{field:'reportId',title:'序号'},
//			{field:'reporterName',title:'举报者',width:40,align:'center'},
//			{field:'reportType',title:'类型',width:100,align:'center'},
//			{field:'link',title:'id号',width:30,align:'center',sortable:true},
//			{field:'reportTime',title:'时间',width:90,align:'center',sortable:true},
//		]],
//		striped:true,
//		pagination:true,
//		fitColumns:true,
//		rownumbers:true,
//		singleSelect:true
//	});
//});
