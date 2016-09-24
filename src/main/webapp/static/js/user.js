$(function(){
	$("#memberListWin").datagrid({
		url:'user.do',
		method:'post',
		loadMsg:'正在努力加载会员信息！',
		queryParams:{},
		remoteSort: false,
		toolbar:'#toolbar4member',
		pagination:true,
		fitColumns:true,
		rownumbers:true,
		columns:[[
			{field:'usersId',title:'序号'},
			{field:'userName',title:'用户昵称'}
		]]

	});
});
