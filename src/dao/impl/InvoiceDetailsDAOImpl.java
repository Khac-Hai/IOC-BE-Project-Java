package dao.impl;

import dao.InvoiceDetailsDAO;
import model.InvoiceDetails;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailsDAOImpl implements InvoiceDetailsDAO {
    private Connection conn = ConnectionDB.getConnection();

    @Override
    public void addInvoiceDetails(InvoiceDetails details) {
        String sql = "INSERT INTO invoice_details(invoice_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, details.getInvoiceId());
            ps.setInt(2, details.getProductId());
            ps.setInt(3, details.getQuantity());
            ps.setDouble(4, details.getUnitPrice());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<InvoiceDetails> getByInvoiceId(int invoiceId) {
        List<InvoiceDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM invoice_details WHERE invoice_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new InvoiceDetails(
                        rs.getInt("id"),
                        rs.getInt("invoice_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
