package dao;

import model.InvoiceDetail;
import java.util.List;

public interface InvoiceDetailDAO {
    void addInvoiceDetail(InvoiceDetail detail);
    List<InvoiceDetail> getDetailsByInvoiceId(int invoiceId);
}
