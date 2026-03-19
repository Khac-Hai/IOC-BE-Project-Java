package business.impl;

import dao.InvoiceDAO;
import dao.InvoiceDetailDAO;
import model.Invoice;
import model.InvoiceDetail;
import business.InvoiceService;

import java.util.List;

public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceDAO invoiceDAO;
    private InvoiceDetailDAO invoiceDetailDAO;

    public InvoiceServiceImpl(InvoiceDAO invoiceDAO, InvoiceDetailDAO invoiceDetailDAO) {
        this.invoiceDAO = invoiceDAO;
        this.invoiceDetailDAO = invoiceDetailDAO;
    }

    @Override
    public void addInvoice(Invoice invoice, List<InvoiceDetail> details) {
        try {
            if (details == null || details.isEmpty()) {
                throw new IllegalArgumentException("Hóa đơn phải có ít nhất một sản phẩm");
            }
            if (invoice.getTotalAmount() <= 0) {
                throw new IllegalArgumentException("Tổng tiền phải lớn hơn 0");
            }

            // thêm hóa đơn, lấy ID mới
            int invoiceId = invoiceDAO.addInvoice(invoice);

            // thêm chi tiết hóa đơn
            for (InvoiceDetail detail : details) {
                detail.setInvoiceId(invoiceId);
                invoiceDetailDAO.addInvoiceDetail(detail);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi thêm hóa đơn: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Invoice> listAllInvoices() {
        try {
            return invoiceDAO.getAllInvoices();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách hóa đơn: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Invoice> findByCustomerName(String name) {
        try {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Tên khách hàng không được để trống");
            }
            return invoiceDAO.findByCustomerName(name);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm hóa đơn theo tên khách hàng: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Invoice> findByDate(String date) {
        try {
            if (date == null || date.isBlank()) {
                throw new IllegalArgumentException("Ngày không được để trống");
            }
            return invoiceDAO.findByDate(date);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm hóa đơn theo ngày: " + e.getMessage(), e);
        }
    }
    @Override
    public List<InvoiceDetail> getInvoiceDetails(int invoiceId) {
        try {
            return invoiceDetailDAO.getDetailsByInvoiceId(invoiceId);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy chi tiết hóa đơn: " + e.getMessage(), e);
        }
    }

}
