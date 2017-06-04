<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>人员管理</title>
	<jsp:include page="${ctx}/static/common/comm.jsp"/>
	<script type="text/javascript" src='${ctx}/static/js/user.js'> </script>
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
	<div id="userListTable"></div>

<div id="win"></div>
</body>
</html>