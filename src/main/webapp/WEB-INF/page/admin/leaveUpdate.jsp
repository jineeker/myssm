<%@ page language="java" pageEncoding="UTF-8"%>
<!--修改用户窗口-->
<div class="easyui-layout" data-options="fit:true">
	<form id="userForm" method="post" style="padding-left: 100px;padding-top: 30px;">
		<input type="hidden" name="usersId" value="${user.usersId}">
		<div>
			<label>昵称:</label>
			<input class="easyui-validatebox" name="userName"  type="text" data-options="required:true" value="${user.userName}"/>
		</div>
		<p/>
		<div>
			<label>描述:</label>
			<input class="easyui-validatebox" name="userDesc" type="text" data-options="required:true" value="${user.userDesc}"/>
		</div>
		<p/>
		<div style="padding-left: 34px;" >
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveOrupdate();">保存</a>
		</div>
	</form>
</div>

<script>
	function saveOrupdate(){
		$.ajax({
			type:'POST',
			url:'userUpdate.do',
			data: $("#userForm").serialize(),
			dataType:'JSON',
			success:function(data){
				console.log(data);
				alert(data.success);
			}
		});
	}
</script>