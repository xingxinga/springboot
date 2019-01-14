<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<aside class="main-sidebar">
    <section class="sidebar">
        <div class="user-panel">
            <div class="pull-left image" onclick="ToPersonSetting()"><img src="${contextStatic}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image"></div>
            <div class="pull-left info">
                [<shiro:principal  property='username'/>] <a href="${pageContext.request.contextPath}/logout"> 注销</a></div>
        </div>
        <ul class="sidebar-menu" data-widget="tree">
            <%--<shiro:hasRole name="admin">--%>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-paw"></i> <span>系统节点信息</span>
                        <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${contextPath}/local/peer">peer节点和用户信息</a></li>
                        <li><a href="${contextPath}/local/orderer">orderer节点列表</a></li>
                        <li><a href="${contextPath}/local/channel">通道列表</a></li>
                        <li><a href="${contextPath}/local/chaincodeInstalled">已安装链码列表</a></li>
                    </ul>
                </li>
                <%--<li class="treeview">
                    <a href="#">
                        <i class="fa fa-paw"></i> <span>系统节点操作</span>
                        <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${contextPath}/execute/toCreateChannel">新建通道</a></li>
                        <li><a href="${contextPath}/execute/toJoinChannel">加入通道</a></li>
                        <li><a href="${contextPath}/execute/toInstall">安装链码</a></li>
                        <li><a href="${contextPath}/execute/toInstantiate">实例化链码</a></li>
                        <li><a href="${contextPath}/execute/toQueryChannel">链码Query</a></li>
                        <li><a href="${contextPath}/execute/toInvokeChannel">链码Invoke</a></li>
                    </ul>
                </li>--%>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-paw"></i> <span>区块链管理</span>
                        <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${contextPath}/fabricPeer/list">peer节点管理</a></li>
                        <li><a href="${contextPath}/fabricOrg/list">组织管理</a></li>
                        <li><a href="${contextPath}/fabricChannel/list">通道管理</a></li>
                        <li><a href="${contextPath}/fabricChaincode/list">链码管理</a></li>
                    </ul>
                </li>
                <%--<li class="treeview">
                    <a href="#">
                        <i class="fa fa-paw"></i> <span>证书平台</span>
                        <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${contextPath}/affiliation/list">用户注册</a></li>
                        <li><a href="${contextPath}/affiliation/list">证书列表</a></li>
                        <li><a href="${contextPath}/affiliation/list">组织管理</a></li>
                    </ul>
                </li>--%>
            <%--</shiro:hasRole>--%>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-paw"></i> <span>发票管理</span>
                        <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${contextPath}/invoice/list">本地发票列表</a></li>
                        <li><a href="${contextPath}/invoice/fabricGetUserInvoiceList">链上发票列表</a></li>
                        <li><a href="${contextPath}/invoice/fabricGetRelationInvoiceList">链上相关发票列表</a></li>
                        <li><a href="${contextPath}/invoice/tofabricGetInvoice">发票验证</a></li>


                    </ul>
                </li>

                <shiro:hasRole name="bank">
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-paw"></i> <span>融资申请管理</span>
                            <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="${contextPath}/invoice/fabricGetBankInvoiceList">查询融资申请</a></li>
                        </ul>
                    </li>
                </shiro:hasRole>

                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-paw"></i> <span>用户管理</span>
                        <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${contextPath}/user/edit">用户详情</a></li>
                    </ul>
                </li>
        </ul>
    </section>

</aside>
<div class="row" style="position: relative;max-height: 100px;z-index: 1030;top: 10px;width: 83%;margin-left: 230px;background-color: #ecf0f5;padding-top: 20px;">
    <div class="col-xs-12">
        <div class="box tech-box" style="color: red;font-size: 20px;box-sizing: border-box;">
            ${message}
        </div>
    </div>
</div>
</body>

</html>