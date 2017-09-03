<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>部署流程</title>
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

	<form id="inputForm" action="/k/act/process/deploy" method="post" enctype="multipart/form-data" class="form-horizontal">
		<%--<div class="control-group">
			<label class="control-label">流程分类：</label>
			<div class="controls">
				<select id="category" name="category" class="required input-medium">
					<c:forEach items="${fns:getDictList('act_category')}" var="dict">
						<option value="${dict.value}">${dict.label}</option>
					</c:forEach>
				</select>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">流程文件：</label>
			<div class="controls">
				<input type="file" id="file" name="file" class="required"/>
				<span class="help-inline">支持文件格式：zip、bar、bpmn、bpmn20.xml</span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提 交"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>

<div id="win"></div>
</body>
</html>