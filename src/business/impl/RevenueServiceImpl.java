package business.impl;

import dao.RevenueDAO;
import business.RevenueService;

public class RevenueServiceImpl implements RevenueService {
    private RevenueDAO revenueDAO;

    public RevenueServiceImpl(RevenueDAO revenueDAO) {
        this.revenueDAO = revenueDAO;
    }

    @Override
    public double getRevenueByDay(String day) {
        if (day == null || day.isBlank()) {
            throw new RuntimeException("Ngày không được để trống");
        }
        try {
            return revenueDAO.getRevenueByDay(day);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Ngày nhập không đúng định dạng yyyy-MM-dd", e);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tính doanh thu theo ngày: " + e.getMessage(), e);
        }
    }

    @Override
    public double getRevenueByMonth(String month) {
        if (month == null || month.isBlank()) {
            throw new RuntimeException("Tháng không được để trống");
        }
        try {
            return revenueDAO.getRevenueByMonth(month);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tháng nhập không đúng định dạng yyyy-MM", e);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tính doanh thu theo tháng: " + e.getMessage(), e);
        }
    }

    @Override
    public double getRevenueByYear(String year) {
        if (year == null || year.isBlank()) {
            throw new RuntimeException("Năm không được để trống");
        }
        try {
            return revenueDAO.getRevenueByYear(year);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Năm nhập không đúng định dạng yyyy", e);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tính doanh thu theo năm: " + e.getMessage(), e);
        }
    }
}
