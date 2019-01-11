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
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">提交质押融资申请</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-4" style="text-align:center; height:30px; line-height:30px;">
                            <label ><span>*</span>发票代码</label>
                        </div>
                        <div class="col-md-8">
                            <input readonly type="text" class="col-sm-10 form-control js-title" id="invoiceCode" name="invoiceCode"  >
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4" style="text-align:center; height:30px; line-height:30px;">
                            <label><span>*</span>发票号码</label>
                        </div>
                        <div class="col-md-8">
                            <input readonly type="text" class="form-control js-title" id="invoiceNo" name="invoiceNo"  >
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4" style="text-align:center; height:30px; line-height:30px;">
                            <label ><span>*</span>融资银行</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" class="form-control js-title" id="invoiceFinancingBank" name="invoiceFinancingBank" >
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="updateInvoiceFinancingBank()">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>


    <div class="wrapper">

        <div class="content-wrapper">
            <section class="content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box tech-box">
                            <div class="box-header">
                                <h3 class="box-title">发票列表<span style="font-size:12px;"></span></h3>
                                <%--<div class="box-tools">
                                    <div class="input-group input-group-sm" style="width: 150px;right: 160px;">
                                        <input type="text" name="table_search" class="form-control" placeholder="关键词">

                                        <div class="input-group-btn">
                                            <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                        </div>
                                    </div>
                                    <!--添加-->
                                    <a  class="btn btn-block btn-info tech-box-add js_add_link" href="${contextPath}/invoice/edit">添加发票</a>
                                </div>--%>
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
                                        <th>融资银行</th>
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
                                            <td>${invoice.invoiceFinancingBank}</td>
                                            <td>${invoice.invoiceCreatedate}</td>
                                            <td>
                                                <button  class="btn btn-primary btn-sm"data-toggle="modal" data-target="#myModal" onclick="setInvoice(${invoice.invoiceCode},${invoice.invoiceNo})" >提交质押融资申请</button>
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
<script type="text/javascript">
    function updateInvoiceFinancingBank() {
        $.ajax({
            type : "POST",//提交方式
            url  : "${contextPath}/invoice/fabricUpdateInvoiceFinancingBank",//提交地址 
            data : {
                invoiceCode:$("#invoiceCode").val(),
                invoiceNo:$("#invoiceNo").val(),
                invoiceFinancingBank:$("#invoiceFinancingBank").val()
            },//提交的数据
            dataType : "text",
            success  : function(data){  //返回结果
                if(data!=null){
                    location.reload()
                }
            },
            //错误信息提示
            error : function(data){
            }
        });
    }

    function setInvoice(invoiceCode,invoiceNo) {
        $("#invoiceCode").val(invoiceCode)
        $("#invoiceNo").val(invoiceNo)
    }
</script>

<!--本页面js-->

</body>

</html>