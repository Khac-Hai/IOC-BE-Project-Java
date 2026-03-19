package dao.impl;

import dao.InvoiceDetailDAO;
import model.InvoiceDetail;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailDAOImpl implements InvoiceDetailDAO {
    private Connection conn = ConnectionDB.getConnection();

    @Override
    public void addInvoiceDetail(InvoiceDetail detail) {
        String sql = "INSERT INTO invoice_details(invoice_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getInvoiceId());
            ps.setInt(2, detail.getProductId());
            ps.setInt(3, detail.getQuantity());
            ps.setDouble(4, detail.getUnitPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm chi tiết hóa đơn: " + e.getMessage(), e);
        }
    }

    @Override
    public List<InvoiceDetail> getDetailsByInvoiceId(int invoiceId) {
        List<InvoiceDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM invoice_details WHERE invoice_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InvoiceDetail detail = new InvoiceDetail(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                );
                detail.setInvoiceId(rs.getInt("invoice_id"));
                list.add(detail);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy chi tiết hóa đơn: " + e.getMessage(), e);
        }
        return list;
    }

}
