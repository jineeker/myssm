<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>古月流苏</title>
    <!--为了让 Bootstrap 开发的网站对移动设备友好，确保适当的绘制和触屏缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="${ctx}/static/common/bootstrap-3.3.0-dist/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!--引入头部文件-->
<%@ include file="../comm/top.jsp"%>

<%--<nav class="navbar navbar-inverse" role="navigation">
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
</nav>--%>

<div class="container">
    <div style="margin-right: auto;margin-left: auto;padding-left: 13px;padding-right: 13px;">
        <!--轮播 开始-->
        <div id="myCarousel" class="carousel slide">
            <!-- 轮播（Carousel）指标 -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>
            <!-- 轮播（Carousel）项目 -->
            <div class="carousel-inner">
                <div class="item active">
                    <img src="${ctx}/static/image/carousel01.jpg" alt="First slide">
                </div>
                <div class="item">
                    <img src="${ctx}/static/image/carousel01.jpg" alt="Second slide">
                </div>
                <div class="item">
                    <img src="${ctx}/static/image/carousel01.jpg" alt="Third slide">
                </div>
            </div>
            <!-- 轮播（Carousel）导航 -->
            <a class="carousel-control left" href="#myCarousel"
               data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="carousel-control right" href="#myCarousel"
               data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
        <!--轮播 结束-->
    </div>
</div>
<div class="container">

    <div style="margin-right: auto;margin-left: auto;padding-left: 22px;padding-right: 22px;">
        <!--文字上下滚动 开始-->
        <div style="margin-top: 8px;">
            <span class="glyphicon glyphicon-volume-up" style="color: rgb(156, 156, 158); font-size: 19px;padding-left: 8px;float:left;top: 3px;"></span>
            <div class="list_lh" style="height:23px; overflow:hidden; padding-left:10px;font-size: 18px;">
                <ul class="list-unstyled">
                    <li style="margin-top:12px;">
                        jinnee*jinnee*jinnee*jinnee*jinnee*jinnee*jinnee*<em>新年好</em>
                    </li>
                    <li style="margin-top:12px;">
                        hiker*hiker*hiker*hiker*hiker*hiker*hiker*hiker*hiker*<em>加油</em>
                    </li>
                    <li style="margin-top:12px;">
                        6666666666666666666666666<em>溜溜的</em>
                    </li>
                </ul>
            </div>
        </div>
        <!--文字上下滚动 结束-->
        <!--首页广告位 开始-->
        <div style="margin-top: 8px;">
            <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
            <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
            <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
            <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
        </div>
        <!--首页广告位 结束-->
        <!--最新作品推荐 开始-->
        <div style="margin-top: 18px;margin-left: 12px;">
            <div class="row">
                <div class="col-md-2" style="font-size: 21px;padding-left: 20px;width: 143px;">
                    最新作品推
                </div>
                <div class="col-md-2" style="color:#1bbc9b;font-size: 11px;padding-left: 2px;padding-top:10px;">
                    发布
                </div>
                <div class="col-md-8 text-right" style="font-size: 16px;padding-top:10px;float: right;padding-right: 22px;">
                    <em>最近更新：<span style="color: #a45b13;">666</span></em>
                </div>
            </div>
            <div class="row">
                <div style="float: left;">
                    <div style="padding-left:4px;float: left;">
                        <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
                        <p>NWA守夜人艺术图文教程分享</p>
                        <p>
                            <a href="#" class="btn btn-default btn-xs disabled" role="button">作品</a>
                            &nbsp;&nbsp;
                            <span class="glyphicon glyphicon-eye-open" style="color: rgb(180, 181, 180);">123456</span>
                            <span class="glyphicon glyphicon-comment" style="color: rgb(180, 181, 180);">333</span>
                            <span class="glyphicon glyphicon-heart-empty" style="color: rgb(180, 181, 180);">555</span>
                        </p>
                        <p><span>[图片]</span><span>古月流苏</span><span style="margin-left: 100px;float: right;">sdsd</span></p>
                    </div>
                </div>
                <div style="float: left;">
                    <div style="padding-left:4px;float: left;">
                        <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
                        <p>NWA守夜人艺术图文教程分享</p>
                        <p>
                            <a href="#" class="btn btn-default btn-xs disabled" role="button">作品</a>
                            &nbsp;&nbsp;
                            <span class="glyphicon glyphicon-eye-open" style="color: rgb(180, 181, 180);">123456</span>
                            <span class="glyphicon glyphicon-comment" style="color: rgb(180, 181, 180);">333</span>
                            <span class="glyphicon glyphicon-heart-empty" style="color: rgb(180, 181, 180);">555</span>
                        </p>
                        <p><span>[图片]</span><span>古月流苏</span><span style="margin-left: 100px;float: right;">sdsd</span></p>
                    </div>
                </div>
                <div style="float: left;">
                    <div style="padding-left:4px;float: left;">
                        <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
                        <p>NWA守夜人艺术图文教程分享</p>
                        <p>
                            <a href="#" class="btn btn-default btn-xs disabled" role="button">作品</a>
                            &nbsp;&nbsp;
                            <span class="glyphicon glyphicon-eye-open" style="color: rgb(180, 181, 180);">123456</span>
                            <span class="glyphicon glyphicon-comment" style="color: rgb(180, 181, 180);">333</span>
                            <span class="glyphicon glyphicon-heart-empty" style="color: rgb(180, 181, 180);">555</span>
                        </p>
                        <p><span>[图片]</span><span>古月流苏</span><span style="margin-left: 100px;float: right;">sdsd</span></p>
                    </div>
                </div>
                <div style="float: left;">
                    <div style="padding-left:4px;float: left;">
                        <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
                        <p>NWA守夜人艺术图文教程分享</p>
                        <p>
                            <a href="#" class="btn btn-default btn-xs disabled" role="button">作品</a>
                            &nbsp;&nbsp;
                            <span class="glyphicon glyphicon-eye-open" style="color: rgb(180, 181, 180);">123456</span>
                            <span class="glyphicon glyphicon-comment" style="color: rgb(180, 181, 180);">333</span>
                            <span class="glyphicon glyphicon-heart-empty" style="color: rgb(180, 181, 180);">555</span>
                        </p>
                        <p><span>[图片]</span><span>古月流苏</span><span style="margin-left: 100px;float: right;">sdsd</span></p>
                    </div>
                </div>
                <div style="float: left;">
                    <div style="padding-left:4px;float: left;">
                        <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
                        <p>NWA守夜人艺术图文教程分享</p>
                        <p>
                            <a href="#" class="btn btn-default btn-xs disabled" role="button">作品</a>
                            &nbsp;&nbsp;
                            <span class="glyphicon glyphicon-eye-open" style="color: rgb(180, 181, 180);">123456</span>
                            <span class="glyphicon glyphicon-comment" style="color: rgb(180, 181, 180);">333</span>
                            <span class="glyphicon glyphicon-heart-empty" style="color: rgb(180, 181, 180);">555</span>
                        </p>
                        <p><span>[图片]</span><span>古月流苏</span><span style="margin-left: 100px;float: right;">sdsd</span></p>
                    </div>
                </div>
            </div>
        </div>
        <!--最新作品推荐 结束-->

        <!--最新模型推荐 开始-->
        <div style="margin-top: 28px;margin-left: 12px;">
            <div class="row">
                <div class="col-md-2" style="font-size: 21px;padding-left: 20px;width: 143px;">
                    最新模型
                </div>
                <div class="col-md-2" style="color:#1bbc9b;font-size: 11px;padding-left: 2px;padding-top:10px;">
                    发布
                </div>
                <div class="col-md-8 text-right" style="font-size: 16px;padding-top:10px;float: right;padding-right: 22px;">
                    <em>最近更新：<span style="color: #a45b13;">666</span></em>
                </div>
            </div>
            <div class="row">
                <div style="float: left;">
                    <div style="padding-left:4px;float: left;">
                        <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
                        <p>NWA守夜人艺术图文教程分享</p>
                        <p>
                            <a href="#" class="btn btn-default btn-xs disabled" role="button">模型</a>
                            &nbsp;&nbsp;
                            <span class="glyphicon glyphicon-eye-open" style="color: rgb(180, 181, 180);">123456</span>
                            <span class="glyphicon glyphicon-comment" style="color: rgb(180, 181, 180);">333</span>
                            <span class="glyphicon glyphicon-heart-empty" style="color: rgb(180, 181, 180);">555</span>
                        </p>
                        <p><span>[图片]</span><span>古月流苏</span><span style="margin-left: 100px;float: right;">sdsd</span></p>
                    </div>
                </div>
            </div>
        </div>
        <!--最新模型推荐 结束-->

        <!--最新文章推荐 开始-->
        <div style="margin-top: 28px;margin-left: 12px;">
            <div class="row">
                <div class="col-md-2" style="font-size: 21px;padding-left: 20px;width: 143px;">
                    文章文章
                </div>
                <div class="col-md-2" style="color:#1bbc9b;font-size: 11px;padding-left: 2px;padding-top:10px;">
                    发布
                </div>
                <div class="col-md-8 text-right" style="font-size: 16px;padding-top:10px;float: right;padding-right: 22px;">
                    <em>最近更新：<span style="color: #a45b13;">666</span></em>
                </div>
            </div>
            <div class="row">
                <div style="float: left;">
                    <div style="padding-left:4px;float: left;">
                        <img src="${ctx}/static/image/post01.jpg" class="img-thumbnail">
                        <p>NWA守夜人艺术图文教程分享</p>
                        <p>
                            <a href="#" class="btn btn-default btn-xs disabled" role="button">文章</a>
                            &nbsp;&nbsp;
                            <span class="glyphicon glyphicon-eye-open" style="color: rgb(180, 181, 180);">123456</span>
                            <span class="glyphicon glyphicon-comment" style="color: rgb(180, 181, 180);">333</span>
                            <span class="glyphicon glyphicon-heart-empty" style="color: rgb(180, 181, 180);">555</span>
                        </p>
                        <p><span>[图片]</span><span>古月流苏</span><span style="margin-left: 100px;float: right;">sdsd</span></p>
                    </div>
                </div>
            </div>
        </div>
        <!--最新文章推荐 结束-->

        <!--设计师 开始-->
        <div style="margin-top: 58px;">
            <span style="font-size: 28px;">设计师排行</span>
            <div style="height:1px;border-bottom:1px dashed #000;"></div>
            <%--<hr style="border:1px dashed; height:1px" color="#DDDDDD">--%>
            <p>想出现在这里，那就上传您的资源成为每周活跃用户吧！</p>
            <div class="row">
                <div style="margin-left:43px;float: left;">
                    <img src="${ctx}/static/image/user.png" class="img-circle" style="height:160px;width: 160px;">
                </div>
                <div style="margin-left:18px;float: left;padding-top: 40px;">
                    <p>作者作者</p>
                    <p>职业</p>
                    <p>简介简介</p>
                </div>

                <div style="margin-left:43px;float: left;">
                    <img src="${ctx}/static/image/user.png" class="img-circle" style="height:160px;width: 160px;">
                </div>
                <div style="margin-left:18px;float: left;padding-top: 40px;">
                    <p>作者作者</p>
                    <p>职业</p>
                    <p>简介简介</p>
                </div>

                <div style="margin-left:43px;float: left;">
                    <img src="${ctx}/static/image/user.png" class="img-circle" style="height:160px;width: 160px;">
                </div>
                <div style="margin-left:18px;float: left;padding-top: 40px;">
                    <p>作者作者</p>
                    <p>职业</p>
                    <p>简介简介</p>
                </div>

                <div style="margin-left:43px;float: left;">
                    <img src="${ctx}/static/image/user.png" class="img-circle" style="height:160px;width: 160px;">
                </div>
                <div style="margin-left:18px;float: left;padding-top: 40px;">
                    <p>作者作者</p>
                    <p>职业</p>
                    <p>简介简介</p>
                </div>
            </div>
        </div>
        <!--设计师 结束-->
    </div>
</div>

<%--<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    模态框（Modal）标题
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

<!--引入footer布局-->
<%@ include file="../comm/footer.jsp"%>

<!--footer布局-->
<%--<nav  role="navigation" style="background-color: #40515c;margin-top: 40px;">
    <div class="container">
        <div class="row" style="margin-right: auto;margin-left: auto;padding-left: 30px;padding-right: 22px;padding-top: 28px;background-color: #40515c;">
            <div style="float: left;">
                <div style="padding-left:4px;float: left;width: 160px;">
                    <img width="120" height="120" src="${ctx}/static/image/cgke_wx.jpg" class="img-thumbnail">
                </div>
            </div>
            <div style="float: left;width: 120px;">
                <div style="padding-left:14px;float: left;">
                    <p style="color: white;">关于</p>
                    <p style="color:#869397;">关于CGKE</p>
                    <p style="color:#869397;">版权声明</p>
                    <p style="color:#869397;">关于隐私</p>
                    <p style="color:#869397;">免责声明</p>
                    <p style="color:#869397;">网站地图</p>
                </div>
            </div>
            <div style="float: left;width: 120px;">
                <div style="padding-left:14px;float: left;">
                    <p style="color: white;">关于</p>
                    <p style="color:#869397;">关于CGKE</p>
                    <p style="color:#869397;">版权声明</p>
                    <p style="color:#869397;">关于隐私</p>
                    <p style="color:#869397;">免责声明</p>
                    <p style="color:#869397;">网站地图</p>
                </div>
            </div>
            <div style="float: left;width: 120px;">
                <div style="padding-left:14px;float: left;">
                    <p style="color: white;">关于</p>
                    <p style="color:#869397;">关于CGKE</p>
                    <p style="color:#869397;">版权声明</p>
                    <p style="color:#869397;">关于隐私</p>
                    <p style="color:#869397;">免责声明</p>
                    <p style="color:#869397;">网站地图</p>
                </div>
            </div>
            <div style="float: left;width: 120px;">
                <div style="padding-left:14px;float: left;">
                    <p style="color: white;">关于</p>
                    <p style="color:#869397;">关于CGKE</p>
                    <p style="color:#869397;">版权声明</p>
                    <p style="color:#869397;">关于隐私</p>
                    <p style="color:#869397;">免责声明</p>
                    <p style="color:#869397;">网站地图</p>
                </div>
            </div>
            <div style="float: left;width: 300px;">
                <div style="padding-left:14px;float: left;">
                    <p style="color: white;">免责声明</p>
                    <p style="color:#869397;">CGKE是开放社区交流平台，鼓励会员平等合法交流，但并不承担因会员个人行为导致的相关法律责任。</p>
                    <p style="color:#869397;">湘ICP备14003982号-1 </p>
                    <p style="color:#869397;">Copyright © 2014 CGKE </p>
                    <p style="color:#869397;">湖南我和小伙伴们网络科技有限公司</p>
                </div>
            </div>
        </div>
    </div>
</nav>--%>



<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="${ctx}/static/common/jquery.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="${ctx}/static/common/bootstrap-3.3.0-dist/dist/js/bootstrap.min.js"></script>

<script src="${ctx}/static/js/front/index.js"></script>
<script type="text/javascript">
    $(function(){
        $("div.list_lh").myScroll({
            speed:60, //数值越大，速度越慢
            rowHeight:26 //li的高度
        });
    });
</script>
</body>
</html>