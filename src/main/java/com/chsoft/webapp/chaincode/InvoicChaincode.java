package com.chsoft.webapp.chaincode;

import com.alibaba.fastjson.JSON;
import com.chsoft.fabric.aop.AopFabricClient;
import com.chsoft.fabric.local.entity.FabricLocal;
import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import com.chsoft.webapp.chaincode.aop.ChaincodeCreate;
import com.chsoft.webapp.invoice.dao.InvoiceMapper;
import com.chsoft.webapp.invoice.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoicChaincode implements ChaincodeCreate {

    private final String chaincodeName = "chsoft_invoic2";

    private final String chaincodePath = "github.com/chaincode/chsoftInvoic";

    private final String chaincodeVersion ="1.0";

    private final String channelName = "chsoft";

    @Resource
    private AopFabricClient aopFabricClient;

    @Resource
    private FabricChaincode fabricChaincode;

    @Resource
    private FabricLocal fabricLocal;

    public void uploadInvoice(Invoice invoice) throws Exception{
        String fcn = "creat";
        String[] args = new String[9];
        args[0] = invoice.getInvoiceCode();
        args[1] = invoice.getInvoiceNo();
        args[2] = invoice.getInvoiceCreatedate();
        args[3] = invoice.getInvoiceAmount();
        args[4] = invoice.getInvoiceTaxtotal();
        args[5] = invoice.getInvoiceTotal();
        args[6] = invoice.getInvoiceAttribution();
        args[7] = invoice.getInvoiceBuyer();
        args[8] = invoice.getInvoiceSeller();
        aopFabricClient.invokeChaincodePeer(channelName,fabricLocal.getLocalFabricOrderer(),fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
    }


    public Invoice getInvoice(String invoiceCode,String invoiceNo) throws Exception{
        String fcn = "get";
        String[] args = new String[2];
        args[0] = invoiceCode;
        args[1] = invoiceNo;
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return JSON.parseObject(result, Invoice.class);
    }

    @Override
    public void creatChaincode(FabricChaincode fabricChaincode) {
        fabricChaincode.setChaincodeVersion(chaincodeVersion);
        fabricChaincode.setChaincodeName(chaincodeName);
        fabricChaincode.setChaincodePath(chaincodePath);
    }

    @Override
    public FabricChaincode getChaincode() {
        return this.fabricChaincode;
    }
}
