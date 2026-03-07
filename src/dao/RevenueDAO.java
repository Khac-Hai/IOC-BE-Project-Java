package dao;

public interface RevenueDAO {
    double getRevenueByDay(String day);   // yyyy-MM-dd
    double getRevenueByMonth(String month); // yyyy-MM
    double getRevenueByYear(String year);   // yyyy
}
