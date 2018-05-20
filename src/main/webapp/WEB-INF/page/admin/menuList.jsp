<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="${ctx}/static/common/comm.jsp"/>
	<title>菜单管理</title>
</head>
<body>

	<!--工具栏-->
	<div id="toolbar">
		<a href="#" onclick="toAdd();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
		&nbsp;
		<a href="#" onclick="toEdit();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		&nbsp;
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>

	<!--menuList主窗口-->
	<%--<div id="menuDiv"></div>--%>
	<table id="menuDiv" style="width:800px;height:360px"></table>


	<div id="updateDiv"></div>
</body>
<script>
    var selectRow;
    $(function(){
        $('#menuDiv').treegrid({
            url:'getAllMenuList.do',
            idField:'menuId',
            treeField:'menuName',
            toolbar:'#toolbar',
            rownumbers:true,
            pagination:true,
            fitColumns:true,
            singleSelect:true,
            columns:[[
                {title:'菜单名称',field:'menuName',width:200},
                {field:'menuIcon',title:'菜单图标',width:100,align:'right'},
                {field:'menuUrl',title:'访问路径',width:260,align:'right'},
                {field:'menuSort',title:'排序',width:80,align:'right'}
            ]],
            onLoadSuccess : function () {//配置首列编号宽度的自适应
                $(this).datagrid("fixRownumber");
            },
            onSelect:function(rowData){
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
    function toAdd(){
        $('#updateDiv').dialog({
            href:'menuUpdateView.do',
            width:380,
            height:400,
            modal:true,
            minimizable:false,
            title:'添加用户信息'
        });
    }
    //修改用户
    function toEdit(){
        isSelectRow();

        $('#updateDiv').dialog({
            href:'menuUpdateView.do?menuId='+selectRow.menuId,
            width:380,
            height:400,
            modal:true,
            minimizable:false,
            title:'修改菜单信息'
        });
    }
</script>
</html>