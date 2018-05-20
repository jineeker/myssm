<%--<%@ page language="java" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}" />--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
	<%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />--%>
	<%--<title>权限管理</title>--%>
	<%--<jsp:include page="${ctx}/static/common/comm.jsp"/>--%>
	<%--<script type="text/javascript" src='${ctx}/static/js/user.js'> </script>--%>
<%--</head>--%>
<%--<body>--%>
	<%--<!--工具栏-->--%>
	<%--<div id="permTb">--%>
		<%--<a href="#" onclick="toAddPerm();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>--%>
		<%--&nbsp;--%>
		<%--<a href="#" onclick="toEditPerm();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>--%>
		<%--&nbsp;--%>
		<%--<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>--%>
	<%--</div>--%>

	<%--<!--信息列表 datagrid-->--%>
	<%--<div id="permDiv"></div>--%>

<%--<div id="win"></div>--%>
<%--</body>--%>
<%--<script>--%>
    <%--var selectRow;--%>
    <%--$(function(){--%>
        <%--$("#permDiv").datagrid({--%>
            <%--url:'perm.do',--%>
            <%--method:'post',--%>
            <%--loadMsg:'正在努力加载！',--%>
            <%--toolbar:'#permTb',--%>
            <%--rownumbers:true,--%>
            <%--pagination:true,--%>
            <%--fitColumns:true,--%>
            <%--columns:[[--%>
                <%--{field:'id',title:'id'},--%>
                <%--{field:'name',title:'名称'},--%>
                <%--{field:'percode',title:'状态'},--%>
                <%--{field:'sort',title:'排序'}--%>
            <%--]],--%>
            <%--onLoadSuccess : function () {//配置首列编号宽度的自适应--%>
                <%--$(this).datagrid("fixRownumber");--%>
            <%--},--%>
            <%--onSelect:function(rowIndex, rowData){--%>
                <%--selectRow = rowData;--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>

    <%--//判断是否有选中行--%>
    <%--function isSelectRow(){--%>
        <%--if(selectRow==null){--%>
            <%--$.messager.alert("提示", "请选中一行！", "info");--%>
            <%--return;--%>
        <%--}--%>
    <%--}--%>
    <%--//增加用户弹窗--%>
    <%--function toAddPerm(){--%>
        <%--$('#win').dialog({--%>
            <%--href:'permUpdateView.do',--%>
            <%--width:400,--%>
            <%--height:250,--%>
            <%--modal:true,--%>
            <%--minimizable:false,--%>
            <%--title:'添加角色信息'--%>
        <%--});--%>
    <%--}--%>
    <%--//修改用户--%>
    <%--function toEditPerm(){--%>
        <%--isSelectRow();--%>

        <%--$('#win').dialog({--%>
            <%--href:'permUpdateView.do?id='+selectRow.id,--%>
            <%--width:400,--%>
            <%--height:250,--%>
            <%--modal:true,--%>
            <%--minimizable:false,--%>
            <%--title:'修改角色信息'--%>
        <%--});--%>
    <%--}--%>
<%--</script>--%>
<%--</html>--%>