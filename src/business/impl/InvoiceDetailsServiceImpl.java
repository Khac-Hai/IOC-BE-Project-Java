package business.impl;

import dao.InvoiceDetailsDAO;
import model.InvoiceDetails;
import business.InvoiceDetailService;

import java.util.List;

public class InvoiceDetailsServiceImpl implements InvoiceDetailService {
    private InvoiceDetailsDAO invoiceDetailsDAO;

    public InvoiceDetailsServiceImpl(InvoiceDetailsDAO invoiceDetailsDAO) {
        this.invoiceDetailsDAO = invoiceDetailsDAO;
    }

    @Override
    public void addInvoiceDetails(InvoiceDetails details) {
        invoiceDetailsDAO.addInvoiceDetails(details);
    }

    @Override
    public List<InvoiceDetails> getByInvoiceId(int invoiceId) {
        return invoiceDetailsDAO.getByInvoiceId(invoiceId);
    }
}
