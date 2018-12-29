<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>发票验证</title>

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
                        <div class="form-horizontal" >
                            <div class="box-body">
                                <!--标题-->
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>发票代码</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="code" name="code"   >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>发票号码</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="number" name="number"   >
                                    </div>
                                </div>
                                <a  class="btn btn-block btn-default update-mylife-return" href="javascript:history.back(-1)">返回</a>
                                <button  class="btn btn-block btn-success update-tourist-but js-save" onclick="getInvoice()">查验</button>
                            </div>

                        </div>
                        <form class="form-horizontal" id="invoiceForm" hidden>
                            <div class="box-body">
                                <!--标题-->
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>发票代码</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="invoiceCode" name="invoiceCode"  value="${invoice.invoiceCode}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>发票号码</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="invoiceNo" name="invoiceNo"  value="${invoice.invoiceNo}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>总额</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="invoiceAmount" name="invoiceAmount"  value="${invoice.invoiceAmount}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>税额</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="invoiceTaxtotal" name="invoiceTaxtotal"  value="${invoice.invoiceTaxtotal}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>含税金额</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="invoiceTotal" name="invoiceTotal"  value="${invoice.invoiceTotal}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>发票归属方</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="invoiceAttribution" name="invoiceAttribution"  value="${invoice.invoiceAttribution}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>发票买方</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="invoiceBuyer" name="invoiceBuyer"  value="${invoice.invoiceBuyer}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>发票卖方</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="invoiceSeller" name="invoiceSeller"  value="${invoice.invoiceSeller}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 control-label"><span>*</span>创建时间</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control js-title" id="invoiceCreatedate" name="invoiceCreatedate"  value="${invoice.invoiceCreatedate}" >
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
<script type="text/javascript">
    function getInvoice() {
        $.ajax({
            type : "POST",//提交方式
            url  : "${contextPath}/invoice/fabricGetInvoice",//提交地址 
            data : {
                invoiceCode:$("#code").val(),
                invoiceNo:$("#number").val()
            },//提交的数据
            dataType : "json",
            success  : function(data){  //返回结果
                if(data!=null){
                    $("#invoiceForm").show()
                }
                $("#invoiceCode").val(data.invoiceCode),
                $("#invoiceNo").val(data.invoiceNo),
                $("#invoiceCode").val(data.invoiceCode),
                $("#invoiceCreatedate").val(data.invoiceCreatedate),
                $("#invoiceAmount").val(data.invoiceAmount),
                $("#invoiceTaxtotal").val(data.invoiceTaxtotal),
                $("#invoiceTotal").val(data.invoiceTotal),
                $("#invoiceAttribution").val(data.invoiceAttribution),
                $("#invoiceBuyer").val(data.invoiceBuyer),
                $("#invoiceSeller").val(data.invoiceSeller)
            },
            //错误信息提示
            error : function(data){
            }
        });
    }
</script>
</body>

</html>