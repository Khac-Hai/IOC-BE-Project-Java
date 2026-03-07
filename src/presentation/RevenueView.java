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
            System.out.println("4. Quay lại");
            System.out.print("Chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập ngày (yyyy-MM-dd): ");
                    String day = scanner.nextLine();
                    System.out.println("Doanh thu: " + revenueService.getRevenueByDay(day));
                    break;
                case 2:
                    System.out.print("Nhập tháng (yyyy-MM): ");
                    String month = scanner.nextLine();
                    System.out.println("Doanh thu: " + revenueService.getRevenueByMonth(month));
                    break;
                case 3:
                    System.out.print("Nhập năm (yyyy): ");
                    String year = scanner.nextLine();
                    System.out.println("Doanh thu: " + revenueService.getRevenueByYear(year));
                    break;
                case 4:
                    return;
            }
        }
    }
}
