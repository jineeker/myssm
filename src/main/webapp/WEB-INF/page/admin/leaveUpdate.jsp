<%@ page language="java" pageEncoding="UTF-8"%>
<!--修改窗口-->
<div class="easyui-layout" data-options="fit:true">
	<form id="leaveBillForm" method="post" style="padding-left: 100px;padding-top: 30px;">
		<input type="hidden" name="usersId" value="${leave.leaveId}">
		<div>
			<%--private String leaveId;//主键
			private String userId;//申请人id
			private String reason; 	// 请假原因
			private String processInstanceId; // 流程实例编号
			private Integer leaveDays;//请假天数
			private Date createDate;//创建时间--%>
			<label>请假原因:</label>
			<input class="easyui-validatebox" name="reason"  type="text" data-options="required:true" value="${leave.reason}"/>
		</div>
		<p/>
		<div>
			<label>请假天数:</label>
			<input class="easyui-validatebox" name="leaveDays" type="text" data-options="required:true" value="${leave.leaveDays}"/>
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
			url:'leave/addLeave.do',
			data: $("#leaveBillForm").serialize(),
			dataType:'JSON',
			success:function(data){
				console.log(data);
				alert(data.success);
			}
		});
	}
</script>