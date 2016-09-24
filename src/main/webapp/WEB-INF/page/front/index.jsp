<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>

<body>
    <form id="loginForm" action="/login.xhtml" method="post">
        用户名ss：<input type="text" id="name" name="name">
        密码：<input type="text" id="psd" name="psd">
        <input type="submit" value="登录">
    </form>
</body>
</html>
