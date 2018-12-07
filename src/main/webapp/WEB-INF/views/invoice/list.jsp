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
                                <h3 class="box-title">发票列表<span style="font-size:12px;"></span></h3>

                                <div class="box-tools">
                                    <div class="input-group input-group-sm" style="width: 150px;right: 160px;">
                                        <input type="text" name="table_search" class="form-control" placeholder="关键词">

                                        <div class="input-group-btn">
                                            <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                        </div>
                                    </div>
                                    <!--添加-->
                                    <a  class="btn btn-block btn-info tech-box-add js_add_link" href="/invoice/edit">添加发票</a>
                                </div>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive no-padding">
                                <table class="table table-hover life-table">
                                    <tbody>
                                    <tr>
                                        <th>发票代码</th>
                                        <th>发票号码</th>
                                        <th>总额</th>
                                        <th>税额</th>
                                        <th>含税金额</th>
                                        <th>发票归属方</th>
                                        <th>发票买方</th>
                                        <th>发票卖方</th>
                                        <th>发票创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    <c:forEach items="${invoiceList}" var="invoice">
                                        <tr>
                                            <td>${invoice.invoiceCode}</td>
                                            <td>${invoice.invoiceNo}</td>
                                            <td>${invoice.invoiceAmount}</td>
                                            <td>${invoice.invoiceTaxtotal}</td>
                                            <td>${invoice.invoiceTotal}</td>
                                            <td>${invoice.invoiceAttribution}</td>
                                            <td>${invoice.invoiceBuyer}</td>
                                            <td>${invoice.invoiceSeller}</td>
                                            <td>${invoice.invoiceCreatedate}</td>
                                            <td>
                                                <a href="/invoice/edit?id=${invoice.id}">查看</a>
                                                <a href="/invoice/fabricUploadInvoice?id=${invoice.id}">发票信息上链</a>
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
<!--添加模板-->
<div class="js-model" style="display: none;">
    <form id="myform" action="" method="post">
        <textarea class="form-control js-content-text" rows="3" placeholder="内容..."></textarea>
    </form>
</div>

<!--本页面js-->

</body>

</html>