package com.chsoft.webapp.invoice.server;

import com.chsoft.webapp.invoice.dao.InvoiceMapper;
import com.chsoft.webapp.invoice.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoicServer {

    @Autowired
    private InvoiceMapper invoiceMapper;

    public List<Invoice> findAllInvoice(){
       return invoiceMapper.findAllInvoice();
    }

    public Invoice findInvoiceById(String id){
        return invoiceMapper.selectByPrimaryKey(id);
    }

    public void creat(Invoice invoice){
        invoiceMapper.insert(invoice);
    }

    public void update(Invoice invoice){
        invoiceMapper.updateByPrimaryKey(invoice);
    }
}
