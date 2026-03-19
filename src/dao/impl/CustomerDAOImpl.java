package dao.impl;

import dao.CustomerDAO;
import model.Customer;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private Connection conn = ConnectionDB.getConnection();

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer(name, phone, email, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm khách hàng: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET name=?, phone=?, email=?, address=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setInt(5, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật khách hàng: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("violates foreign key constraint")) {
                throw new IllegalStateException("Khách hàng có đơn hàng không thể xoá!", e);
            }
            throw new RuntimeException("Lỗi khi xóa khách hàng: " + e.getMessage(), e);
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customer WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt("id"), rs.getString("name"),
                        rs.getString("phone"), rs.getString("email"), rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm khách hàng: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Customer(rs.getInt("id"), rs.getString("name"),
                        rs.getString("phone"), rs.getString("email"), rs.getString("address")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách khách hàng: " + e.getMessage(), e);
        }
        return list;
    }
}
