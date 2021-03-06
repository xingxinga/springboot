<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>组织编辑</title>

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
                        <form class="form-horizontal" action="${contextPath}/fabricOrg/save" method="post">
                            <div class="box-body">
                                <!--标题-->
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>orgName</label>
                                    <div class="col-sm-4">
                                        <input type="text"  name="orgName" required class="form-control js-title" value="${org.orgName}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>orgMspId</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="orgMspId" required class="form-control js-title" value="${org.orgMspId}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>orgDomainName</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="orgDomainName" required class="form-control js-title" value="${org.orgDomainName}" >
                                    </div>
                                </div>
                                <div hidden>
                                    <input type="hidden"  id="id" name="id"  value="${org.id}" >
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