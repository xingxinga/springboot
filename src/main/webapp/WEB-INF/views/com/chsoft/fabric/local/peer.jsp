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

        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content">
                    <div class="update-tourist-div">
                        <!--添加内容-->
                        <form class="form-horizontal">
                            <div class="box-body">
                                <!--标题-->
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>节点名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" readonly class="form-control js-title" value="${fabricPeer.peerName}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>节点地址</label>
                                    <div class="col-sm-4">
                                        <input type="text" readonly class="form-control js-title" value="${fabricPeer.peerLocation}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>管理员名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" readonly class="form-control js-title" value="${fabricUser.username}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>所属组织</label>
                                    <div class="col-sm-4">
                                        <input type="text" readonly class="form-control js-title" value="${fabricUser.affiliation}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>mspID</label>
                                    <div class="col-sm-4">
                                        <input type="text" readonly class="form-control js-title" value="${fabricUser.mspId}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>管理员证书</label>
                                    <div class="col-sm-4">
                                        <input type="text" readonly class="form-control js-title" value="${fabricUser.certificatefile}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>管理员私钥</label>
                                    <div class="col-sm-4">
                                        <input type="text" readonly class="form-control js-title" value="${fabricUser.privatekeyfilepath}" >
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                </section>
            </div>

        </div>

    </div>

</div>
<!--添加模板-->
<div class="js-model" style="display: none;">
    <form id="myform" action="" method="post">
        <textarea class="form-control js-content-text" rows="3" placeholder="内容..."></textarea>
    </form>
</div>
</body>

</html>