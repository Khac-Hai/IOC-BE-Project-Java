package presentation;

import model.Invoice;
import business.InvoiceService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class InvoiceView {
    private InvoiceService invoiceService;
    private Scanner scanner = new Scanner(System.in);

    public InvoiceView(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("===== QUẢN LÝ HÓA ĐƠN =====");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm mới hóa đơn");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Quay lại menu chính");
            System.out.print("Chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    invoiceService.listAllInvoices().forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Nhập ID khách hàng: ");
                    int customerId = scanner.nextInt();
                    System.out.print("Nhập tổng tiền: ");
                    double total = scanner.nextDouble();
                    scanner.nextLine();
                    invoiceService.addInvoice(new Invoice(0, customerId, LocalDateTime.now(), total));
                    System.out.println("Thêm hóa đơn thành công!");
                    break;
                case 3:
                    System.out.println("1. Tìm theo tên khách hàng");
                    System.out.println("2. Tìm theo ngày/tháng/năm");
                    System.out.println("3. Quay lại menu hóa đơn");
                    System.out.print("Chọn: ");
                    int opt = scanner.nextInt();
                    scanner.nextLine();
                    if (opt == 1) {
                        System.out.print("Tên khách hàng: ");
                        String name = scanner.nextLine();
                        var results = invoiceService.findByCustomerName(name);
                        if (results.isEmpty()) {
                            System.out.println("Không tìm thấy hóa đơn cho khách hàng: " + name);
                        } else {
                            results.forEach(System.out::println);
                        }
                    } else if (opt == 2) {
                        System.out.print("Ngày (yyyy-MM-dd): ");
                        String date = scanner.nextLine();
                        var results = invoiceService.findByDate(date);
                        if (results.isEmpty()) {
                            System.out.println("Không tìm thấy hóa đơn cho ngày: " + date);
                        } else {
                            results.forEach(System.out::println);
                        }
                    } else if (opt == 3) {
                        // Thoát khỏi vòng lặp tìm kiếm để quay lại menu hóa đơn
                        break;
                    } else {
                        System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
                    }
                    break;
                case 4:
                    return;
            }
        }
    }
}
