package com.chsoft.webapp.invoice.entity;

public class Invoice {
    private String id;

    private String invoiceCode;

    private String invoiceNo;

    private String invoiceCreatedate;

    private String invoiceAmount;

    private String invoiceTaxtotal;

    private String invoiceTotal;

    private String invoiceAttribution;

    private String invoiceBuyer;

    private String invoiceSeller;

    private String invoiceFinancingBank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    public String getInvoiceCreatedate() {
        return invoiceCreatedate;
    }

    public void setInvoiceCreatedate(String invoiceCreatedate) {
        this.invoiceCreatedate = invoiceCreatedate == null ? null : invoiceCreatedate.trim();
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount == null ? null : invoiceAmount.trim();
    }

    public String getInvoiceTaxtotal() {
        return invoiceTaxtotal;
    }

    public void setInvoiceTaxtotal(String invoiceTaxtotal) {
        this.invoiceTaxtotal = invoiceTaxtotal == null ? null : invoiceTaxtotal.trim();
    }

    public String getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(String invoiceTotal) {
        this.invoiceTotal = invoiceTotal == null ? null : invoiceTotal.trim();
    }

    public String getInvoiceAttribution() {
        return invoiceAttribution;
    }

    public void setInvoiceAttribution(String invoiceAttribution) {
        this.invoiceAttribution = invoiceAttribution == null ? null : invoiceAttribution.trim();
    }

    public String getInvoiceBuyer() {
        return invoiceBuyer;
    }

    public void setInvoiceBuyer(String invoiceBuyer) {
        this.invoiceBuyer = invoiceBuyer == null ? null : invoiceBuyer.trim();
    }

    public String getInvoiceSeller() {
        return invoiceSeller;
    }

    public void setInvoiceSeller(String invoiceSeller) {
        this.invoiceSeller = invoiceSeller == null ? null : invoiceSeller.trim();
    }

    public String getInvoiceFinancingBank() {
        return invoiceFinancingBank;
    }

    public void setInvoiceFinancingBank(String invoiceFinancingBank) {
        this.invoiceFinancingBank = invoiceFinancingBank;
    }
}