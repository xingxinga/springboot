<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>发票列表</title>
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
                                <h3 class="box-title">节点列表<span style="font-size:12px;"></span></h3>

                                <div class="box-tools">
                                    <div class="input-group input-group-sm" style="width: 150px;right: 160px;">
                                        <input type="text" name="table_search" class="form-control" placeholder="关键词">

                                        <div class="input-group-btn">
                                            <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                        </div>
                                    </div>
                                    <!--添加-->
                                    <a  class="btn btn-block btn-info tech-box-add js_add_link" href="/fabricPeer/edit">添加节点</a>
                                </div>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive no-padding">
                                <table class="table table-hover life-table">
                                    <tbody>
                                    <tr>
                                        <th>节点名称</th>
                                        <th>节点地址</th>
                                        <th>OrgId</th>
                                        <th>操作</th>
                                    </tr>
                                    <c:forEach items="${peerList}" var="peer">
                                        <tr>
                                            <td>${peer.peerName}</td>
                                            <td>${peer.peerLocation}</td>
                                            <td>${peer.orgId}</td>
                                            <td><a href="/fabricPeer/edit?id=${peer.id}">编辑</a></td>
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