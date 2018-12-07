<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>链码列表</title>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@ include file="/WEB-INF/views/layout/page.jsp" %>
    <div class="wrapper">

        <div class="content-wrapper">
            <section class="content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box tech-box">
                            <div class="box-header">
                                <h3 class="box-title">链码列表<span style="font-size:12px;"></span></h3>

                                <div class="box-tools">
                                    <div class="input-group input-group-sm" style="width: 150px;right: 160px;">
                                        <input type="text" name="table_search" class="form-control" placeholder="关键词">

                                        <div class="input-group-btn">
                                            <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                        </div>
                                    </div>
                                    <!--添加-->
                                    <a  class="btn btn-block btn-info tech-box-add js_add_link" href="/fabricChaincode/edit">添加链码</a>
                                </div>
                            </div>
                            <%--<div class="box-header" style="padding: 5px;">
                                <h3 class="box-title"><span style="font-size:10px;color: red">${message}</span></h3>
                            </div>--%>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive no-padding">
                                <table class="table table-hover life-table">
                                    <tbody>
                                    <tr>
                                        <th>名称</th>
                                        <th>本地文件路径</th>
                                        <th>节点文件路径</th>
                                        <th>版本</th>
                                        <th>执行等待时间</th>
                                        <th>部署等待时间</th>
                                        <th>操作</th>
                                    </tr>
                                    <c:forEach items="${chaincodeList}" var="chaincode">
                                        <tr>
                                            <td>${chaincode.chaincodeName}</td>
                                            <td>${chaincode.chaincodeFilepath}</td>
                                            <td>${chaincode.chaincodePath}</td>
                                            <td>${chaincode.chaincodeVersion}</td>
                                            <td>${chaincode.invokeWatiTime}</td>
                                            <td>${chaincode.deployWatiTime}</td>
                                            <td>
                                                <a href="/fabricChaincode/edit?id=${chaincode.id}">查看</a>
                                                <a href="/fabricChaincode/fabricInstallChaincode?id=${chaincode.id}">安装链码</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>

                </div>

            </section>
        </div>

    </div>

</div>

</body>

</html>