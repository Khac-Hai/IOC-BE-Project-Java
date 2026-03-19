package presentation;

import business.RevenueService;
import java.util.Scanner;

public class RevenueView {
    private RevenueService revenueService;
    private Scanner scanner = new Scanner(System.in);

    public RevenueView(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("===== THỐNG KÊ DOANH THU =====");
            System.out.println("1. Doanh thu theo ngày");
            System.out.println("2. Doanh thu theo tháng");
            System.out.println("3. Doanh thu theo năm");
            System.out.println("4. Quay lại menu chính");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số từ 1 đến 4!");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Nhập ngày (yyyy-MM-dd): ");
                        String day = scanner.nextLine();
                        try {
                            double revenueDay = revenueService.getRevenueByDay(day);
                            System.out.println("Doanh thu: " + revenueDay);
                        } catch (RuntimeException e) {
                            System.out.println("Lỗi: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Nhập tháng (yyyy-MM): ");
                        String month = scanner.nextLine();
                        try {
                            double revenueMonth = revenueService.getRevenueByMonth(month);
                            System.out.println("Doanh thu: " + revenueMonth);
                        } catch (RuntimeException e) {
                            System.out.println("Lỗi: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.print("Nhập năm (yyyy): ");
                        String year = scanner.nextLine();
                        try {
                            double revenueYear = revenueService.getRevenueByYear(year);
                            System.out.println("Doanh thu: " + revenueYear);
                        } catch (RuntimeException e) {
                            System.out.println("Lỗi: " + e.getMessage());
                        }
                        break;

                    case 4:
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            }
        }
    }
}
