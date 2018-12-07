<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>链码编辑</title>

</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!--星姐需要添加-->
    <%@ include file="/WEB-INF/views/layout/page.jsp" %>

    <!--星姐需要添加-->

    <div class="wrapper">

        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content">
                    <div class="update-tourist-div">
                        <!--添加内容-->
                        <form class="form-horizontal" action="/fabricChaincode/save" method="post">
                            <div class="box-body">
                                <!--标题-->
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>链码名称</label>
                                    <div class="col-sm-4">
                                        <input type="text"  name="chaincodeName" required class="form-control js-title" value="${chaincode.chaincodeName}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>本地文件路径</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="chaincodeFilepath" required class="form-control js-title" value="${chaincode.chaincodeFilepath}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>节点文件路径</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="chaincodePath" required class="form-control js-title" value="${chaincode.chaincodePath}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>版本</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="chaincodeVersion" required class="form-control js-title" value="${chaincode.chaincodeVersion}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label">执行等待时间</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="invokeWatiTime" class="form-control js-title" value="${chaincode.invokeWatiTime}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label">部署等待时间</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="deployWatiTime" class="form-control js-title" value="${chaincode.deployWatiTime}" >
                                    </div>
                                </div>
                                <div hidden>
                                    <input type="hidden"  id="id" name="id"  value="${chaincode.id}" >
                                </div>
                                <a  class="btn btn-block btn-default update-mylife-return" href="javascript:history.back(-1)">返回</a>
                                <button type="submit" class="btn btn-block btn-success update-tourist-but js-save">保存</button>
                            </div>
                        </form>
                    </div>
                </section>
            </div>

        </div>

    </div>

</div>
<!--添加模板-->

</body>

</html>