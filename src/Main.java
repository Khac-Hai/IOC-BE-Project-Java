import dao.impl.*;
import business.impl.*;
import presentation.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Khởi tạo các service
        AdminServiceImpl adminService = new AdminServiceImpl(new AdminDAOImpl());
        ProductServiceImpl productService = new ProductServiceImpl(new ProductDAOImpl());
        CustomerServiceImpl customerService = new CustomerServiceImpl(new CustomerDAOImpl());
        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(new InvoiceDAOImpl());
        InvoiceDetailsServiceImpl invoiceDetailsService = new InvoiceDetailsServiceImpl(new InvoiceDetailsDAOImpl());
        RevenueServiceImpl revenueService = new RevenueServiceImpl(new RevenueDAOImpl());

        // ===== GIAO DIỆN BẮT ĐẦU =====
        while (true) {
            System.out.println("======== HỆ THỐNG QUẢN LÝ CỬA HÀNG ========");
            System.out.println("1. Đăng nhập Admin");
            System.out.println("2. Thoát");
            System.out.println("===========================================");
            System.out.print("Nhập lựa chọn: ");
            int startChoice = sc.nextInt();
            sc.nextLine();

            if (startChoice == 1) {
                AdminView adminView = new AdminView(adminService);
                if (adminView.loginMenu() != null) {
                    // ===== MENU CHÍNH SAU ĐĂNG NHẬP =====
                    while (true) {
                        System.out.println("======== MENU CHÍNH ========");
                        System.out.println("1. Quản lý sản phẩm điện thoại");
                        System.out.println("2. Quản lý khách hàng");
                        System.out.println("3. Quản lý hóa đơn");
                        System.out.println("4. Thống kê doanh thu");
                        System.out.println("5. Đăng xuất");
                        System.out.println("=================================");
                        System.out.print("Nhập lựa chọn: ");
                        int mainChoice = sc.nextInt();
                        sc.nextLine();

                        switch (mainChoice) {
                            case 1:
                                new ProductView(productService).showMenu();
                                break;
                            case 2:
                                new CustomerView(customerService).showMenu();
                                break;
                            case 3:
                                new InvoiceView(invoiceService).showMenu();
                                System.out.print("Nhập ID hóa đơn để xem chi tiết (0 để bỏ qua): ");
                                int invoiceId = sc.nextInt();
                                sc.nextLine();
                                if (invoiceId > 0) {
                                    new InvoiceDetailView(invoiceDetailsService).showMenu(invoiceId);
                                }
                                break;
                            case 4:
                                new RevenueView(revenueService).showMenu();
                                break;
                            case 5:
                                System.out.println("Đăng xuất thành công!");
                                break;
                        }
                        if (mainChoice == 5) break;
                    }
                }
            } else if (startChoice == 2) {
                System.out.println("Thoát chương trình!");
                break;
            }
        }
    }
}
