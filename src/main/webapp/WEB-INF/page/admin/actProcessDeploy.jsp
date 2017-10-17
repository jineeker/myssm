<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>部署流程</title>
	<jsp:include page="${ctx}/static/common/comm.jsp"/>
<body>

	<div class="easyui-panel" title="部署流程" style="width:500px">
	<div style="padding:10px 60px 20px 60px">
	<form id="inputForm" action="/k/act/process/deploy" method="post" enctype="multipart/form-data" class="form-horizontal">
		<table cellpadding="8">
			<tr>
				<td>流程文件:</td>
				<td><input type="file" id="file" name="file" class="required"/></td>
			</tr>
			<tr>
				<td>提示:</td>
				<td><span class="help-inline" style="font-size: 10px;">支持文件格式：zip、bar、bpmn、bpmn20.xml</span></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<div class="form-actions">
						<%--<input id="btnSubmit" class="btn btn-primary" type="submit" value="提 交"/>
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
					</div>
				</td>
			</tr>
		</table>

	</form>
	</div>
	</div>

	<script>
        function submitForm(){
            $("#inputForm").submit();
        }
        function clearForm(){
            $('#inputForm').form('clear');
        }
	</script>
</body>
</html>