<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-md-1.5">
                <img src="${ctx}/static/image/headLogo.png" class="img-rounded navbar-left">
            </div>
            <div class="col-md-4.5">
                <ul class="nav navbar-nav">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">作品</a></li>
                    <li><a href="#">文章</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            3D库 <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">人物模型</a></li>
                            <li><a href="#">场景模型</a></li>
                            <li><a href="#">车辆模型</a></li>
                            <li class="divider"></li>
                            <li><a href="#">植物模型</a></li>
                            <li><a href="#">动物模型</a></li>
                        </ul>
                    </li>
                    <li><a href="#">设计师</a></li>
                    <li><a href="#">招聘</a></li>
                </ul>
            </div>
            <div class="col-md-4">
                <form class="navbar-form navbar-right" role="search">
                    <div class="input-group input-group-sm">
                        <input type="text" class="form-control" placeholder="输入关键字">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown">搜索
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">作品</a>
                                </li>
                                <li>
                                    <a href="#">文章</a>
                                </li>
                                <li>
                                    <a href="#">3D库</a>
                                </li>
                            </ul>
                        </div><!-- /btn-group -->
                    </div>
                </form>
            </div>
            <div class="col-md-2">
                <ul class="nav navbar-nav navbar-left">
                    <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                    <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                </ul>
            </div>

        </div>
    </div>
</nav>

<!-- 模态框弹窗（Modal） -->
<%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    登录
                </h4>
            </div>
            <div class="modal-body">
                在这里添加一些文本
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>--%>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h1 class="text-center text-primary">登录</h1>
            </div>
            <div class="modal-body">
                <form action="" class="form col-md-12 center-block">
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" placeholder="电子邮件">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control input-lg" placeholder="登录密码">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary btn-lg btn-block">立刻登录</button>
                        <span><a href="#">找回密码</a></span>
                        <span><a href="#" class="pull-right">注册</a></span>
                    </div>
                </form>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>