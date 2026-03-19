package business;

import model.Invoice;
import model.InvoiceDetail;
import java.util.List;

public interface InvoiceService {
    void addInvoice(Invoice invoice, List<InvoiceDetail> details);
    List<Invoice> listAllInvoices();
    List<Invoice> findByCustomerName(String name);
    List<Invoice> findByDate(String date);
    List<InvoiceDetail> getInvoiceDetails(int invoiceId);
}

