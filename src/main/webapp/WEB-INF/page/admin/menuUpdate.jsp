<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!--修改菜单窗口-->
<div class="easyui-layout" data-options="fit:true">
	<form id="updateForm" method="post" style="padding-left: 90px;padding-top: 30px;">
		<input type="hidden" name="menuId" value="${entity.menuId}">
		<div>
			<label>菜单名称:</label>
			<input class="easyui-validatebox" name="menuName"  type="text" data-options="required:true" value="${entity.menuName}"/>
		</div>
		<p/>
		<div>
			<label>菜单图标:</label>
			<input class="easyui-validatebox" name="menuIcon" type="text" data-options="required:false" value="${entity.menuIcon}"/>
		</div>
		<p/>
		<div>
			<label>访问路径:</label>
			<input class="easyui-validatebox" name="menuUrl" type="text" data-options="required:false" value="${entity.menuUrl}"/>
		</div>
		<p/>
		<div>
			<label>上级菜单:</label>
			<select id="selectP" class="easyui-combobox" name="menuPid" style="width:147px;">
				<option value="0">无</option>
				<c:forEach items="${list4Pentity}" var="list4Pentity">
					<c:if test="${list4Pentity.menuId!=entity.menuId && entity!=null}">
						<option value="${list4Pentity.menuId}" <c:if test="${list4Pentity.menuId==entity.menuPid}">selected</c:if> >${list4Pentity.menuName}</option>
					</c:if>
					<c:if test="${entity==null}">
						<option value="${list4Pentity.menuId}">${list4Pentity.menuName}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<p/>
		<div>
			<label>排序标识:</label>
			<input class="easyui-validatebox" name="menuSort" type="text" data-options="required:true" value="${entity.menuSort}"/>
		</div>
		<p/>
		<div style="padding-left: 55px;" >
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveOrupdate();">保存</a>
		</div>
	</form>

	注：菜单最多设置3个等级，且父菜单的访问路径为空，才会显示在父菜单下拉列表中。
</div>

<script>
	function saveOrupdate(){
		$.ajax({
			type:'POST',
			url:'menuSaveOrUpdate.do',
			data: $("#updateForm").serialize(),
			dataType:'JSON',
			success:function(data){
			    if(data.success=='success'){
                    $('#updateDiv').dialog('close');
                    $('#menuDiv').treegrid('reload');// 重新载入当前页面数据
				}else{
                    $.messager.alert('错误',data.success);
				}
			}
		});
	}
</script>