package business.impl;

import dao.InvoiceDAO;
import model.Invoice;
import business.InvoiceService;

import java.util.List;

public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceDAO invoiceDAO;

    public InvoiceServiceImpl(InvoiceDAO invoiceDAO) {
        this.invoiceDAO = invoiceDAO;
    }

    @Override
    public void addInvoice(Invoice invoice) { invoiceDAO.addInvoice(invoice); }

    @Override
    public List<Invoice> listAllInvoices() { return invoiceDAO.getAllInvoices(); }

    @Override
    public List<Invoice> findByCustomerName(String name) { return invoiceDAO.findByCustomerName(name); }

    @Override
    public List<Invoice> findByDate(String date) { return invoiceDAO.findByDate(date); }
}

