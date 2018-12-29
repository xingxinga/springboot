<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>通道列表</title>
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
                                        <c:if test="${channelName==null||channelName==''}">
                                            <h3 class="box-title">系统节点安装链码列表<span style="font-size:12px;"></span></h3>
                                        </c:if>
                                        <c:if test="${channelName!=null&&channelName!=''}">
                                            <h3 class="box-title">通道：${channelName} 已实例化链码列表<span style="font-size:12px;"></span></h3>
                                        </c:if>
                                        <div class="box-tools">
                                            <div class="input-group input-group-sm" style="width: 150px;right: 160px;">
                                                <input type="text" name="table_search" class="form-control" placeholder="关键词">

                                                <div class="input-group-btn">
                                                    <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                                </div>
                                            </div>
                                            <!--添加-->
                                        </div>
                                    </div>
                                    <!-- /.box-header -->
                                    <div class="box-body table-responsive no-padding">
                                        <table class="table table-hover life-table">
                                            <tbody>
                                            <tr>
                                                <th>链码名称</th>
                                                <th>链码版本</th>
                                                <th>链码地址</th>
                                                <th>操作</th>
                                            </tr>
                                            <c:forEach items="${chaincodeList}" var="chaincode">
                                                <tr>
                                                    <td>${chaincode.chaincodeName}</td>
                                                    <td>${chaincode.chaincodeVersion}</td>
                                                    <td>${chaincode.chaincodePath}</td>
                                                    <td><a href="${contextPath}/local/toInstantiateChaincode?chaincodeName=${chaincode.chaincodeName}&chaincodeVersion=${chaincode.chaincodeVersion}&chaincodePath=${chaincode.chaincodePath}">实例化链码</a></td>
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