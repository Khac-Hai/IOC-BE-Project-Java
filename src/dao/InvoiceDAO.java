package dao;

import model.Invoice;
import java.util.List;

public interface InvoiceDAO {
    int addInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    List<Invoice> findByCustomerName(String name);
    List<Invoice> findByDate(String date); // yyyy-MM-dd
}
