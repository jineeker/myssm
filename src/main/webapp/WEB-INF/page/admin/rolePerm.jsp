<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!--更新窗口-->
<div class="easyui-layout" data-options="fit:true">
	<form id="updateForm" method="post" style="padding-left: 90px;padding-top: 30px;">
		<input type="hidden" name="roleId" value="${entity.id}">
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>权限:</label>
			<input class="easyui-validatebox" name="perms" data-options="required:true" value="${entity.rolePerms}"/>
		</div>
		<p/>
		<p/>
		<div style="padding-left: 55px;" >
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveOrupdate();">保存</a>
		</div>
	</form>

</div>

<script>
    function saveOrupdate(){
        if(!$("#updateForm").form('validate')){
            return false;
        }
        $.ajax({
            type:'POST',
            url:'rolePermSaveOrUpdate.do',
            data: $("#updateForm").serialize(),
            dataType:'JSON',
            success:function(data){
                console.log(data);
                console.log(data.success);
                console.log(data.success=='success');
                if(data.success=='success'){
                    $('#win').dialog('close');
                    $('#roleDiv').datagrid('reload');// 重新载入当前页面数据
                }else{
                    $.messager.alert('错误',data.success);
                }
            }
        });
    }
</script>