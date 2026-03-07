package business;

import model.Invoice;
import java.util.List;

public interface InvoiceService {
    void addInvoice(Invoice invoice);
    List<Invoice> listAllInvoices();
    List<Invoice> findByCustomerName(String name);
    List<Invoice> findByDate(String date);
}

