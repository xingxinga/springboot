package com.chsoft.webapp.invoice.controller;

import com.chsoft.chaincode.invoice.InvoicChaincode;
import com.chsoft.chaincode.sys.LedgerHistory;
import com.chsoft.chaincode.sys.QsccChaincode;
import com.chsoft.fabric.aop.AopFabricClient;
import com.chsoft.fabric.local.entity.FabricLocal;
import com.chsoft.webapp.invoice.entity.Invoice;
import com.chsoft.webapp.invoice.server.InvoicServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试控制器
 *
 * @author: @我没有三颗心脏
 * @create: 2018-05-08-下午 16:46
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Resource
    private InvoicChaincode invoicChaincode;

    @Resource
    private QsccChaincode qsccChaincode;

    @Resource
    private InvoicServer invoicServer;


    @RequestMapping("/list")
    public String invoiceList(Model model) {
        List<Invoice> list =  invoicServer.findAllInvoice();
        model.addAttribute("invoiceList",list);
        return "invoice/list";
    }

    @RequestMapping("/edit")
    public String invoiceShow(String id,Model model) throws Exception{
        Invoice invoice = invoicServer.findInvoiceById(id);
        List<LedgerHistory> list =  invoicChaincode.getInvoiceHistory(invoice.getInvoiceCode(),invoice.getInvoiceNo());
        String result = qsccChaincode.getTransactionByID(invoicChaincode.getChannelName(),list.get(0).tx_id);
        model.addAttribute("invoice",invoice);
        return "invoice/edit";
    }

    @RequestMapping("/save")
    public String invoiceSave(Invoice invoice) {
        if(invoice.getId().isEmpty()){
            invoicServer.creat(invoice);
        }else{
            invoicServer.update(invoice);
        }
        return "redirect:/invoice/list";
    }

    @RequestMapping("/fabricUploadInvoice")
    public String uploadInvoice(String id) throws Exception{
        Invoice invoice = invoicServer.findInvoiceById(id);
        invoicChaincode.uploadInvoice(invoice);
        return "redirect:/invoice/list";
    }

    @RequestMapping("/tofabricGetInvoice")
    public String toGetInvoice() throws Exception{
        return "/invoice/get";
    }

    @RequestMapping("/fabricGetInvoice")
    @ResponseBody
    public Invoice getInvoice(String invoiceCode,String invoiceNo) throws Exception{
        Invoice invoice = invoicChaincode.getInvoice(invoiceCode,invoiceNo);
        return invoice;
    }

    @RequestMapping("/fabricGetUserInvoiceList")
    public String fabricGetUserInvoiceList(Model model) throws Exception{
        List<Invoice> list = invoicChaincode.getUserInvoiceList();
        model.addAttribute("invoiceList",list);
        return "/invoice/fabricUserInvoiceList";
    }

    @RequestMapping("/fabricGetBankInvoiceList")
    public String fabricGetBankInvoiceList(Model model) throws Exception{
        List<Invoice> list = invoicChaincode.getBankInvoiceList();
        model.addAttribute("invoiceList",list);
        return "/invoice/fabricBankInvoiceList";
    }

    @RequestMapping("/fabricGetRelationInvoiceList")
    public String fabricGetRelationInvoiceList(Model model) throws Exception{
        List<Invoice> list = invoicChaincode.getRelationInvoiceList();
        model.addAttribute("invoiceList",list);
        return "/invoice/fabricRelationInvoiceList";
    }


    @RequestMapping("/fabricUpdateInvoiceFinancingBank")
    @ResponseBody
    public String fabricUpdateInvoiceFinancingBank(String invoiceCode,String invoiceNo,String invoiceFinancingBank) throws Exception{
        invoicChaincode.updateInvoiceFinancingBank(invoiceCode,invoiceNo,invoiceFinancingBank);
        return "success";
    }

    @RequestMapping("/fabricUpdateInvoiceAttribution")
    @ResponseBody
    public String fabricUpdateInvoiceAttribution(String invoiceCode,String invoiceNo) throws Exception{
        invoicChaincode.updateInvoiceAttribution(invoiceCode,invoiceNo);
        return "success";
    }


}