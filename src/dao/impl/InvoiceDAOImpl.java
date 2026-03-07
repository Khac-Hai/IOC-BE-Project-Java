package dao.impl;

import dao.InvoiceDAO;
import model.Invoice;
import utils.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {
    private Connection conn = ConnectionDB.getConnection();

    @Override
    public void addInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoice(customer_id, created_at, total_amount) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoice.getCustomerId());
            ps.setTimestamp(2, Timestamp.valueOf(invoice.getCreatedAt()));
            ps.setDouble(3, invoice.getTotalAmount());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT * FROM invoice";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Invoice(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getDouble("total_amount")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Invoice> findByCustomerName(String name) {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT i.* FROM invoice i JOIN customer c ON i.customer_id = c.id WHERE c.name ILIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getDouble("total_amount")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Invoice> findByDate(String date) {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT * FROM invoice WHERE DATE(created_at) = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getDouble("total_amount")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
