<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>orderer列表</title>
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
                                <h3 class="box-title">orderer列表列表<span style="font-size:12px;"></span></h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive no-padding">
                                <table class="table table-hover life-table">
                                    <tbody>
                                    <tr>
                                        <th>节点名称</th>
                                        <th>节点地址</th>
                                        <th>domainName</th>

                                    </tr>
                                    <c:forEach items="${fabricOrdererList}" var="orderer">
                                        <tr>
                                            <td>${orderer.ordererName}</td>
                                            <td>${orderer.ordererLocation}</td>
                                            <td>${orderer.ordererDomainName}</td>
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
<!--添加模板-->
<div class="js-model" style="display: none;">
    <form id="myform" action="" method="post">
        <textarea class="form-control js-content-text" rows="3" placeholder="内容..."></textarea>
    </form>
</div>

<!--本页面js-->

</body>

</html>