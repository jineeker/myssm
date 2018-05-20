<%@ page language="java" pageEncoding="UTF-8"%>


<!--工具栏-->
<div id="toolbar">
	<a href="#" onclick="sureBind();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">确认</a>
</div>

<!--menuList主窗口-->
<%--<div id="menuDiv"></div>--%>
<table id="menuDiv" style="width:780px;height:360px"></table>
<input id="roleId" name="roleId" hidden value="${entity.id}">

<script>
    var selectMenuRow;
    $(function(){
        $('#menuDiv').treegrid({
            url:'/k/index/getAllMenuList.do',
            idField:'menuId',
            treeField:'menuName',
            toolbar:'#toolbar',
            rownumbers:true,
            fitColumns:true,
            singleSelect:false,
            columns:[[
                {field:'ck',checkbox:true},
                {title:'菜单名称',field:'menuName',width:200},
                {field:'menuIcon',title:'菜单图标',width:100,align:'right'},
                {field:'menuUrl',title:'访问路径',width:260,align:'right'},
                {field:'menuSort',title:'排序',width:80,align:'right'}
            ]],
            onSelect:function(rowData){
                console.log(rowData);
                selectMenuRow = rowData;
            }
        });
    });
    //判断是否有选中行
    function isSelectRow(){
        if(selectMenuRow==null){
            $.messager.alert("提示", "请选中一行！", "info");
            return;
        }
    }
    //绑定
    function sureBind(){
        var menuIdsVal = "";
        var selRows = $('#menuDiv').datagrid('getChecked');
        $.each(selRows,function(i,row){
            menuIdsVal += ","+row.menuId;
        });
        $.ajax({
            type:'POST',
            url:'roleMenuSaveOrUpdate.do',
            data: {
                menuIds : menuIdsVal.substring(1),
                roleId : $("#roleId").val()
			},
            dataType:'JSON',
            success:function(data){
                if(data.success=='success'){
                    $('#menuWin').dialog('close');
                    $('#roleDiv').datagrid('reload');// 重新载入当前页面数据
                }else{
                    $.messager.alert('错误',data.success);
                }
            }
        });

    }

</script>
</html>