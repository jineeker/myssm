<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<jsp:include page="${ctx}/static/common/comm.jsp"/>
	<script type="text/javascript" src='${ctx}/static/js/user.js'> </script>
		<%--<script type="text/javascript" src='${ctx}/static/js/admin/plugs/datagrid-detailview.js'> </script>
        <script type="text/javascript" src='${ctx}/static/js/plugs/jqueryDialog/dialog.js'> </script>
        <link href="${ctx}/static/js/plugs/jqueryDialog/dialog.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/static/css/admin/users.css" rel="stylesheet" type="text/css" />--%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>人员管理</title>

<body>
<style>
	.toolbarsSearch{
		float:left;
		padding-left:20px;
	}
</style>
	<%--<div id="toolbar4member">
		<p class="toolbarsSearch">
			用户账号：&nbsp;
			<input name="memberAccount" id="memberAccount" class="combo" style="width:130px;height:20px"/>
			&nbsp;
			用户昵称：&nbsp;
			<input name="memberName" id="memberName" class="combo" style="width:130px;height:20px"/>
			性别：&nbsp;
			<select class="easyui-combobox" id="memberGender" name="memberGender" style="width:140px;">
				<option value="">全部</option>  
			    <option value="2">女</option>  
			    <option value="1">男</option> 
			</select>
			推荐：&nbsp;
			<select class="easyui-combobox" id="recommend" name="recommend" style="width:140px;">
				<option value="">全部</option>  
			    <option value="index">推荐到首页</option>  
			    <option value="designer">推荐到设计师</option> 
			    <option value="attention">推荐到注册关注</option>
			</select>  
		</p>
		<p class="toolbarsSearch">
			<a href="javascript:reloadMemberListGrid()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="height:22px;width:60px">查询</a>
		</p>
	</div>--%>
	<div id="memberListWin"><!--start memberListWin -->
		
	</div>
	
	<div id="memberRightKeyMenu" class="easyui-menu" style="width: 150px; display: none;">
		<div onclick="addUser();">添加数据</div>
	    <%--<div onclick="sendMsg2Member();">发送消息</div>
	    <div id="recomementMemberMenu">设置推荐用户</div>
	    <div id="recomementMemberAttentionMenu">设置推荐关注用户</div>
	    <div id="recomementMemberIndexMenu">推荐到首页</div>
	    <div onclick="resetPassword();">重置密码</div>--%>
	</div>
	
	<div id="sendMsg2MemberWin">
	</div>
</body>
</html>