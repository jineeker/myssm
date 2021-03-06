<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>后台管理</title>
    <jsp:include page="${ctx}/static/common/comm.jsp"/>
    <script type="text/javascript" src='${ctxPath}/static/common/jquery-easyui-1.3.6/default.js'> </script>

    <script type="text/javascript">

//        var _menus = {
//            "menus": [{
//                "menuid": "1",
//                "icon": "icon-sys",
//                "menuname": "控件使用",
//                "menus": [{
//                    "menuid": "11",
//                    "menuname": "古月流苏",
//                    "icon": "icon-add",
//                    "child": [{
//                        "menuid": "140",
//                        "menuname": "用户查询",
//                        "icon": "icon-role",
//                        "url": "userView.do"
//                    }]
//                },
//                {
//                    "menuid": "12",
//                    "menuname": "流程引擎",
//                    "icon": "icon-add",
//                    "url": "javascript:void(0);",
//                    "child": [{
//                        "menuid": "121",
//                        "menuname": "部署流程",
//                        "icon": "icon-role",
//                        "url": "/k/act/process/deployView"
//                    },
//                    {
//                        "menuid": "121",
//                        "menuname": "流程管理",
//                        "icon": "icon-role",
//                        "url": "/k/act/process/processListView"
//                    },
////                    {
////                        "menuid": "121",
////                        "menuname": "运行中的流程",
////                        "icon": "icon-role",
////                        "url": "/k/act/process/runninglistView"
////                    },
//                    {
//                        "menuid": "121",
//                        "menuname": "请假信息",
//                        "icon": "icon-role",
//                        "url": "/k/leave/leaveListView"
//                    },
//                    {
//                        "menuid": "121",
//                        "menuname": "任务管理",
//                        "icon": "icon-role",
//                        "url": "/k/act/task/listView"
//                    }]
//                }]
//            },
//            {
//                "menuid": "8",
//                "icon": "icon-sys",
//                "menuname": "员工管理",
//                "menus": [{
//                    "menuid": "21",
//                    "menuname": "员工列表",
//                    "icon": "icon-nav",
//                    "url": "demo.html"
//                }]
//            },
//            {
//                "menuid": "56",
//                "icon": "icon-sys",
//                "menuname": "部门管理",
//                "menus": [{
//                    "menuid": "31",
//                    "menuname": "添加部门",
//                    "icon": "icon-nav",
//                    "url": "demo1.html"
//                }]
//            }]
//        };

        //设置密码窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭密码窗口
        function closePwd() {
            $('#w').window('close');
        }



        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })

        }

        $(function() {

            openPwd();

            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

            $('#btnCancel').click(function(){closePwd();})

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                    if (r) {
                        location.href = '/k/index/logout.do';
                    }
                });
            })
        });
    </script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  fit="true"   scroll="no">
    <noscript>
        <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
            <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
        </div>
    </noscript>

    <div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#feeebd; z-index:20000">
        <div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;">
            <img src="${ctxPath}/static/common/jquery-easyui-1.3.6/images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...</div>
    </div>

    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
            background: url(${ctxPath}/static/common/jquery-easyui-1.3.6/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
            line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 <shiro:principal/> <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="${ctxPath}/static/common/jquery-easyui-1.3.6/images/blocks.gif" width="20" height="20" align="absmiddle" /> jQuery.EasyUI- 1.2.6 应用实例</span>
    </div>
    <div region="west" split="true"  title="导航菜单" style="width:180px;" id="west">
        <div id="nav">
            <!--  导航内容 -->
        </div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
            <div title="欢迎使用" style="padding:120px;overflow:hidden; color:red; " >
                <h1 style="font-size:24px;">WELCOME TO ADMIN PAGE</h1>
                <%--<h1 style="font-size:24px;">* 作者：疯狂秀才 QQ：1055818239</h1>
                <h1 style="font-size:24px;">* BLOG: <a style="font-size:24px;color:green;" href="http://hxling.cnblogs.com">疯狂秀才的博客</a></h1>
                <h1 style="font-size:24px;">* 讨论群：112044258、32994605、36534121、56271061</h1>--%>
                <%--<h1 style="font-size:130px;font-family: '仿宋';">jinnee&hiker</h1>--%>
            </div>
        </div>
    </div>
    <div region="south" split="true" style="height: 30px; background: #feeebd; ">
        <%--<div class="footer"><marquee>他们在别有用心的生活里 翩翩舞蹈 你在我后半生的城市里 长生不老</marquee></div>--%>
        <div class="footer">他们在别有用心的生活里 翩翩舞蹈 你在我后半生的城市里 长生不老</div>
    </div>

    <%--<!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
         maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
            background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>--%>

    <div id="mm" class="easyui-menu" style="width:150px;">
        <div id="tabupdate">刷新</div>
        <div class="menu-sep"></div>
        <div id="close">关闭</div>
        <div id="closeall">全部关闭</div>
        <div id="closeother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="closeright">当前页右侧全部关闭</div>
        <div id="closeleft">当前页左侧全部关闭</div>
        <div class="menu-sep"></div>
        <div id="exit">退出</div>
    </div>
</body>
</html>