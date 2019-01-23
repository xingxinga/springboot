package com.chsoft.chaincode.invoice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chsoft.chaincode.aop.ChaincodeCreate;
import com.chsoft.chaincode.sys.LedgerHistory;
import com.chsoft.chaincode.sys.QsccChaincode;
import com.chsoft.fabric.aop.AopFabricClient;
import com.chsoft.fabric.local.entity.FabricLocal;
import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import com.chsoft.webapp.invoice.entity.Invoice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 发票chaincode逻辑
 */
@Service
public class InvoicChaincode implements ChaincodeCreate {

    //chaincode名称
    private final String chaincodeName = "mycc";

    //chaincode地址
    private final String chaincodePath = "github.com/chaincode/first/";

    //chaincode版本
    private final String chaincodeVersion ="1.0";

    //通道名称
    private final String channelName = "mychannel";

    @Resource
    private AopFabricClient aopFabricClient;

    @Resource
    private FabricChaincode fabricChaincode;

    @Resource
    private FabricLocal fabricLocal;

    @Resource
    private QsccChaincode qsccChaincode;

    private List<FabricPeer> fabricPeerList;

    //发票信息上链
    public void uploadInvoice(Invoice invoice) throws Exception{
        //chaincode上方法名称
        String fcn = "create";
        //构建发票信息
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
        //执行chaincode
        aopFabricClient.invokeChaincodePeerList(channelName,fabricLocal.getLocalFabricOrderer(),getFabricPeerList(),fabricChaincode,fcn,args);
    }

    /**
     * 获取节点状态数据库中发票信息
     * @param invoiceCode
     * @param invoiceNo
     * @return
     * @throws Exception
     */
    public Invoice getInvoice(String invoiceCode,String invoiceNo) throws Exception{
        String fcn = "getInvoiceInfo";
        String[] args = new String[2];
        args[0] = invoiceCode;
        args[1] = invoiceNo;
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return JSON.parseObject(result, Invoice.class);
    }

    /**
     * 获取链上当前fabric用户创建的发票列表
     * @return
     * @throws Exception
     */
    public List<Invoice> getUserInvoiceList() throws Exception{
        String fcn = "getUserInvoiceList";
        String[] args = new String[0];
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return JSON.parseArray(result, Invoice.class);
    }

    /**
     * fabric中银行用户获取融资银行蔚当前用户所属银行的发票列表
     * @return
     * @throws Exception
     */
    public List<Invoice> getBankInvoiceList() throws Exception{
        String fcn = "getBankInvoiceList";
        String[] args = new String[0];
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return JSON.parseArray(result, Invoice.class);
    }

    /**
     * 获取买卖方为当前fabric用户的发票列表
     * @return
     * @throws Exception
     */
    public List<Invoice> getRelationInvoiceList() throws Exception{
        String fcn = "getRelationInvoiceList";
        String[] args = new String[0];
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return JSON.parseArray(result, Invoice.class);
    }

    /**
     * 修改发票归属方（融资确认操作）
     * @param invoiceCode
     * @param invoiceNo
     * @throws Exception
     */
    public void updateInvoiceAttribution(String invoiceCode,String invoiceNo) throws Exception{
        String fcn = "updateInvoiceAttribution";
        String[] args = new String[2];
        args[0] = invoiceCode;
        args[1] = invoiceNo;
        //aopFabricClient.invokeChaincodePeer(channelName,fabricLocal.getLocalFabricOrderer(),fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        aopFabricClient.invokeChaincodePeerList(channelName,fabricLocal.getLocalFabricOrderer(),getFabricPeerList(),fabricChaincode,fcn,args);
    }

    /**
     * 发票上链者修改融资银行
     * @param invoiceCode
     * @param invoiceNo
     * @param financingBank
     * @throws Exception
     */
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

    /**
     * 获取某一张发票历史账本的基本信息
     * @param invoiceCode
     * @param invoiceNo
     * @return
     * @throws Exception
     */
    public List<LedgerHistory> getInvoiceHistory(String invoiceCode, String invoiceNo) throws Exception{
        String fcn = "getInvoiceHistory";
        String[] args = new String[2];
        args[0] = invoiceCode;
        args[1] = invoiceNo;
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return JSON.parseArray(result, LedgerHistory.class);
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

    /**
     * 获取账本发票最新信息
     * @param invoiceCode
     * @param invoiceNo
     * @return
     * @throws Exception
     */
    public Invoice getFabricNewestInvoice(String invoiceCode,String invoiceNo) throws Exception{
        List<LedgerHistory> list =  getInvoiceHistory(invoiceCode,invoiceNo);
        LedgerHistory newestInvoice = null;
        int max = 0;
        for(LedgerHistory  ledgerHistory : list){
            String seconds = ledgerHistory.getTimestamp().getSeconds();
            if(seconds!=null){
                int value = Integer.valueOf(seconds);
                if(max<value){
                    newestInvoice = ledgerHistory;
                    max = value;
                }
            }
        }
        String result = qsccChaincode.getTransactionByID(channelName,newestInvoice.getTx_id());
        int left =  result.indexOf("{");
        int right =  result.indexOf("}");
        String invoiceString = result.substring(left, right+1);
        return JSON.parseObject(invoiceString, Invoice.class);
    }

    public String getChannelName() {
        return channelName;
    }
}
