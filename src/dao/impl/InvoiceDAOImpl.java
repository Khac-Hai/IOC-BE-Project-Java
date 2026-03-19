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
    public int addInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoice(customer_id, created_at, total_amount) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, invoice.getCustomerId());
            ps.setTimestamp(2, Timestamp.valueOf(invoice.getCreatedAt()));
            ps.setDouble(3, invoice.getTotalAmount());
            ps.executeUpdate();

            // lấy ID tự tăng
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("Không lấy được ID hóa đơn mới!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm hóa đơn: " + e.getMessage(), e);
        }
    }


    @Override
    public List<Invoice> getAllInvoices() {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT i.id, i.customer_id, c.name AS customer_name, i.created_at, i.total_amount " +
                "FROM invoice i JOIN customer c ON i.customer_id = c.id";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Invoice(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getDouble("total_amount")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách hóa đơn: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Invoice> findByCustomerName(String name) {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT i.id, i.customer_id, c.name AS customer_name, i.created_at, i.total_amount " +
                "FROM invoice i JOIN customer c ON i.customer_id = c.id WHERE c.name ILIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getDouble("total_amount")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm hóa đơn theo tên khách hàng: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Invoice> findByDate(String date) {
        List<Invoice> list = new ArrayList<>();
        String sql = "SELECT i.id, i.customer_id, c.name AS customer_name, i.created_at, i.total_amount " +
                "FROM invoice i JOIN customer c ON i.customer_id = c.id WHERE DATE(i.created_at) = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getDouble("total_amount")
                ));
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Ngày nhập không đúng định dạng yyyy-MM-dd", e);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm hóa đơn theo ngày: " + e.getMessage(), e);
        }
        return list;
    }
}
