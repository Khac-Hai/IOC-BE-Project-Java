package presentation;

import model.InvoiceDetails;
import business.InvoiceDetailService;

import java.util.Scanner;

public class InvoiceDetailView {
    private InvoiceDetailService invoiceDetailService;
    private Scanner scanner = new Scanner(System.in);

    public InvoiceDetailView(InvoiceDetailService invoiceDetailService) {
        this.invoiceDetailService = invoiceDetailService;
    }

    public void showMenu(int invoiceId) {
        while (true) {
            System.out.println("===== CHI TIẾT HÓA ĐƠN =====");
            System.out.println("1. Hiển thị chi tiết hóa đơn");
            System.out.println("2. Thêm sản phẩm vào hóa đơn");
            System.out.println("3. Quay lại menu chính");
            System.out.print("Chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    invoiceDetailService.getByInvoiceId(invoiceId).forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Nhập ID sản phẩm: ");
                    int productId = scanner.nextInt();
                    System.out.print("Số lượng: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Đơn giá: ");
                    double unitPrice = scanner.nextDouble();
                    scanner.nextLine();
                    invoiceDetailService.addInvoiceDetails(
                            new InvoiceDetails(0, invoiceId, productId, quantity, unitPrice)
                    );
                    break;
                case 3:
                    return;
            }
        }
    }
}
