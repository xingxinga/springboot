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
                                            <%--<td>
                                                <a href="${contextPath}/invoice/fabricUpdateInvoiceAttribution?invoiceCode=${invoice.invoiceCode}&invoiceNo=${invoice.invoiceNo}" onclick="swal()">质押物信息确权</a>
                                            </td>--%>
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
    function getswal(invoiceCode,invoiceNo) {
        swal({
            title: "操作提示",      //弹出框的title
            text: "确定质押物信息确权吗？",   //弹出框里面的提示文本
            type: "warning",        //弹出框类型
            showCancelButton: true, //是否显示取消按钮
            confirmButtonColor: "#DD6B55",//确定按钮颜色
            cancelButtonText: "取消",//取消按钮文本
            confirmButtonText: "确定！",//确定按钮上面的文档
            closeOnConfirm: true
        }, function(){
            $.ajax({
                type : "POST",//提交方式
                url  : "${contextPath}/invoice/fabricUpdateInvoiceAttribution",//提交地址 
                async:true,
                data : {
                    invoiceCode:invoiceCode,
                    invoiceNo:invoiceNo
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
        });
    }

</script>

<!--本页面js-->

</body>

</html>