package dao.impl;

import dao.AdminDAO;
import model.Admin;
import utils.ConnectionDB;

import java.sql.*;

public class AdminDAOImpl implements AdminDAO {
    private Connection conn = ConnectionDB.getConnection();

    @Override
    public Admin login(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username=? AND password=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO admin(username, password) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
