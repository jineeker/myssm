<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!--修改用户窗口-->
<div class="easyui-layout" data-options="fit:true">
	<form id="updateForm" method="post" style="padding-left: 90px;padding-top: 30px;">
		<input type="hidden" name="id" value="${entity.id}">
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>帐号:</label>
			<c:if test="${entity.username!=null}">
				${entity.username}
			</c:if>
			<c:if test="${entity.username==null}">
				<input class="easyui-validatebox" name="username" data-options="required:true"/>
			</c:if>
		</div>
		<p/>
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>密码:</label>
			<input class="easyui-validatebox" name="password" type="password"/>
		</div>
		<p/>
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>角色:</label>
			<select id="roleId" class="easyui-combobox" name="roleId" style="width:145px;">
				<c:forEach items="${roleList}" var="role">
					<option value="${role.id}">${role.roleName}</option>
				</c:forEach>
			</select>
		</div>
		<p/>
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>状态:</label>
			<select id="locked" class="easyui-combobox" name="locked" style="width:145px;">
				<option value="0">正常</option>
				<option value="1">锁定</option>
			</select>
		</div>
		<p/>
		<p/>
		<div style="padding-left: 55px;" >
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveOrupdate();">保存</a>
		</div>
	</form>

</div>

<script>
	$(function(){
    });

    function saveOrupdate(){
        if(!$("#updateForm").form('validate')){
            return false;
        }
        $.ajax({
            type:'POST',
            url:'userSaveOrUpdate.do',
            data: $("#updateForm").serialize(),
            dataType:'JSON',
            success:function(data){
                console.log(data);
                console.log(data.success);
                console.log(data.success=='success');
                if(data.success=='success'){
                    $('#win').dialog('close');
                    $('#userDiv').datagrid('reload');// 重新载入当前页面数据
                }else{
                    $.messager.alert('错误',data.success);
                }
            }
        });
    }
</script>