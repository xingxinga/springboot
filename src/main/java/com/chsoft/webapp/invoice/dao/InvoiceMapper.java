package com.chsoft.webapp.invoice.dao;


import com.chsoft.webapp.invoice.entity.Invoice;

import java.util.List;

public interface InvoiceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Invoice record);

    int insertSelective(Invoice record);

    Invoice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Invoice record);

    int updateByPrimaryKey(Invoice record);

    List<Invoice> findAllInvoice();
}