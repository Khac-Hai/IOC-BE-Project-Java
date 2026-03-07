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
        return revenueDAO.getRevenueByDay(day);
    }

    @Override
    public double getRevenueByMonth(String month) {
        return revenueDAO.getRevenueByMonth(month);
    }

    @Override
    public double getRevenueByYear(String year) {
        return revenueDAO.getRevenueByYear(year);
    }
}
