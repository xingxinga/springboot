<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

    <head>
        <title>系统节点加入通道</title>

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
                            <form class="form-horizontal" action="/execute/joinChannel" method="post">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="" class="col-sm-2 control-label">选择通道</label>

                                        <div class="col-sm-2">
                                            <select class="form-control" id="channelList">
                                                <c:forEach items="${channelList}" var="channel" varStatus="i">
                                                    <option value="${channel.channelName}">${channel.channelName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <!--标题-->
                                    <div class="form-group">
                                        <label for="" class="col-sm-2 control-label"><span>*</span>通道名称</label>
                                        <div class="col-sm-4">
                                            <input type="text" id="channelName"  name="channelName" required class="form-control js-title" value="${channelList[0].channelName}" >
                                        </div>
                                    </div>
                                    <a  class="btn btn-block btn-default update-mylife-return" href="javascript:history.back(-1)">返回</a>
                                    <button type="submit" class="btn btn-block btn-success update-tourist-but js-save">新建</button>
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
    <script type="text/javascript">
        $(function () {
            $("#channelList").change(function(){
                var opt=$("#channelList").val();
                var value = opt.split("**")
                $("#channelName").val(value[0])
            });
        });
    </script>
</html>