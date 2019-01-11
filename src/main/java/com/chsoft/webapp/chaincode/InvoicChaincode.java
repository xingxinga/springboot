package com.chsoft.webapp.chaincode;

import com.alibaba.fastjson.JSON;
import com.chsoft.fabric.aop.AopFabricClient;
import com.chsoft.fabric.local.entity.FabricLocal;
import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import com.chsoft.webapp.chaincode.aop.ChaincodeCreate;
import com.chsoft.webapp.invoice.entity.Invoice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoicChaincode implements ChaincodeCreate {

    private final String chaincodeName = "mycc3";

    private final String chaincodePath = "github.com/chaincode/first/";

    private final String chaincodeVersion ="1.0";

    private final String channelName = "mychannel";

    @Resource
    private AopFabricClient aopFabricClient;

    @Resource
    private FabricChaincode fabricChaincode;

    @Resource
    private FabricLocal fabricLocal;

    private List<FabricPeer> fabricPeerList;

    public void uploadInvoice(Invoice invoice) throws Exception{
        String fcn = "create";
        String[] args = new String[8];
        args[0] = invoice.getInvoiceCode();
        args[1] = invoice.getInvoiceNo();
        args[2] = invoice.getInvoiceCreatedate();
        args[3] = invoice.getInvoiceAmount();
        args[4] = invoice.getInvoiceTaxtotal();
        args[5] = invoice.getInvoiceTotal();
        //args[6] = invoice.getInvoiceAttribution();
        args[6] = invoice.getInvoiceBuyer();
        args[7] = invoice.getInvoiceSeller();
        //aopFabricClient.invokeChaincodePeer(channelName,fabricLocal.getLocalFabricOrderer(),fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        aopFabricClient.invokeChaincodePeerList(channelName,fabricLocal.getLocalFabricOrderer(),getFabricPeerList(),fabricChaincode,fcn,args);
    }


    public Invoice getInvoice(String invoiceCode,String invoiceNo) throws Exception{
        String fcn = "getInvoiceInfo";
        String[] args = new String[2];
        args[0] = invoiceCode;
        args[1] = invoiceNo;
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return JSON.parseObject(result, Invoice.class);
    }

    public List<Invoice> getUserInvoiceList() throws Exception{
        String fcn = "getUserInvoiceList";
        String[] args = new String[0];
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return JSON.parseArray(result, Invoice.class);
    }

    public List<Invoice> getBankInvoiceList() throws Exception{
        String fcn = "getBankInvoiceList";
        String[] args = new String[0];
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return JSON.parseArray(result, Invoice.class);
    }

    public void updateInvoiceAttribution(String invoiceCode,String invoiceNo) throws Exception{
        String fcn = "updateInvoiceAttribution";
        String[] args = new String[2];
        args[0] = invoiceCode;
        args[1] = invoiceNo;
        //aopFabricClient.invokeChaincodePeer(channelName,fabricLocal.getLocalFabricOrderer(),fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        aopFabricClient.invokeChaincodePeerList(channelName,fabricLocal.getLocalFabricOrderer(),getFabricPeerList(),fabricChaincode,fcn,args);
    }

    public void updateInvoiceFinancingBank(String invoiceCode,String invoiceNo,String financingBank) throws Exception{
        String fcn = "updateInvoiceFinancingBank";
        String[] args = new String[3];
        args[0] = invoiceCode;
        args[1] = invoiceNo;
        args[2] = financingBank;
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        //aopFabricClient.invokeChaincodePeer(channelName,fabricLocal.getLocalFabricOrderer(),fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        aopFabricClient.invokeChaincodePeerList(channelName,fabricLocal.getLocalFabricOrderer(),getFabricPeerList(),fabricChaincode,fcn,args);
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

    public List<FabricPeer> getFabricPeerList(){
        fabricPeerList = new ArrayList<FabricPeer>();
        fabricPeerList.add(fabricLocal.getLocalFabricPeer());
        FabricPeer fabricPeer2 = new FabricPeer();
        FabricPeer fabricPeer3 = new FabricPeer();
        fabricPeer2.setPeerLocation("grpc://192.168.1.152:9051");
        fabricPeer3.setPeerLocation("grpc://192.168.1.152:10051");
        fabricPeer2.setPeerName("peer0.org2.example.com");
        fabricPeer3.setPeerName("peer0.org3.example.com");
        fabricPeerList.add(fabricPeer2);
        fabricPeerList.add(fabricPeer3);
        return fabricPeerList;
    }
}
