package dao.impl;

import dao.RevenueDAO;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RevenueDAOImpl implements RevenueDAO {
    private Connection conn = ConnectionDB.getConnection();

    @Override
    public double getRevenueByDay(String day) {
        String sql = "SELECT SUM(total_amount) AS revenue FROM invoice WHERE DATE(created_at) = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(day));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("revenue");
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public double getRevenueByMonth(String month) {
        String sql = "SELECT SUM(total_amount) AS revenue FROM invoice WHERE TO_CHAR(created_at, 'YYYY-MM') = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("revenue");
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }



    @Override
    public double getRevenueByYear(String year) {
        String sql = "SELECT SUM(total_amount) AS revenue FROM invoice WHERE TO_CHAR(created_at, 'YYYY') = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, year);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("revenue");
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }
}
