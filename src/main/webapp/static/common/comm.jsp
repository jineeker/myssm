<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link href="${ctxPath}/static/common/jquery-easyui-1.3.6/css/default.css" rel="stylesheet" type="text/css" />
<!--换模板修改/themes/black/为其他即可-->
<link rel="stylesheet" type="text/css" href="${ctxPath}/static/common/jquery-easyui-1.3.6/themes/black/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctxPath}/static/common/jquery-easyui-1.3.6/themes/icon.css" />

<script type="text/javascript" src="${ctxPath}/static/common/jquery-easyui-1.3.6/jquery.min.js"></script>
<script type="text/javascript" src="${ctxPath}/static/common/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
<!--显示中文-->
<script type="text/javascript" src="${ctx}/static/common/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>

<script>
    $.extend($.fn.datagrid.methods, {
        fixRownumber : function (jq) {
            return jq.each(function () {
                var panel = $(this).datagrid("getPanel");
                //获取最后一行的number容器,并拷贝一份
                var clone = $(".datagrid-cell-rownumber", panel).last().clone();
                //由于在某些浏览器里面,是不支持获取隐藏元素的宽度,所以取巧一下
                clone.css({
                    "position" : "absolute",
                    left : -1000
                }).appendTo("body");
                var width = clone.width("auto").width();
                //默认宽度是25,所以只有大于25的时候才进行fix
                if (width > 25) {
                    //多加5个像素,保持一点边距
                    $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
                    //修改了宽度之后,需要对容器进行重新计算,所以调用resize
                    $(this).datagrid("resize");
                    //一些清理工作
                    clone.remove();
                    clone = null;
                } else {
                    //还原成默认状态
                    $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
                }
            });
        }
    });
</script>