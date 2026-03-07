package dao;

import model.InvoiceDetails;
import java.util.List;

public interface InvoiceDetailsDAO {
    void addInvoiceDetails(InvoiceDetails details);
    List<InvoiceDetails> getByInvoiceId(int invoiceId);
}
