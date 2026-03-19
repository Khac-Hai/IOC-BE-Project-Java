package presentation;

import model.Invoice;
import model.InvoiceDetail;
import model.Product;
import business.InvoiceService;
import business.CustomerService;
import business.ProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiceView {
    private InvoiceService invoiceService;
    private CustomerService customerService;
    private ProductService productService; // thêm để chọn sản phẩm

    private Scanner scanner = new Scanner(System.in);

    public InvoiceView(InvoiceService invoiceService, CustomerService customerService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.customerService = customerService;
        this.productService = productService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("===== QUẢN LÝ HÓA ĐƠN =====");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm mới hóa đơn");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Xem chi tiết hóa đơn");
            System.out.println("5. Quay lại menu chính");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        var invoices = invoiceService.listAllInvoices();
                        if (invoices == null || invoices.isEmpty()) {
                            System.out.println("Không có hóa đơn nào!");
                        } else {
                            invoices.forEach(System.out::println);
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("Nhập ID khách hàng: ");
                            int customerId = Integer.parseInt(scanner.nextLine());
                            if (customerService.findCustomerById(customerId) == null) {
                                System.out.println("Khách hàng với ID " + customerId + " không tồn tại!");
                                break;
                            }

                            double total = 0;
                            List<InvoiceDetail> details = new ArrayList<>();

                            while (true) {
                                System.out.println("Danh sách sản phẩm:");
                                productService.listAllProducts().forEach(System.out::println);

                                System.out.print("Nhập ID sản phẩm (0 để kết thúc): ");
                                int productId = Integer.parseInt(scanner.nextLine());
                                if (productId == 0) break;

                                Product product = productService.findProductById(productId);
                                if (product == null) {
                                    System.out.println("Không tìm thấy sản phẩm với ID này!");
                                    continue;
                                }

                                System.out.print("Nhập số lượng: ");
                                int quantity = Integer.parseInt(scanner.nextLine());
                                if (quantity <= 0) {
                                    System.out.println("Số lượng phải lớn hơn 0!");
                                    continue;
                                }
                                if (quantity > product.getStock()) {
                                    System.out.println("Số lượng vượt quá tồn kho!");
                                    continue;
                                }
                                int newStock = product.getStock() - quantity;
                                product.setStock(newStock);
                                productService.updateProduct(product);

                                double lineTotal = product.getPrice() * quantity;
                                total += lineTotal;


                                // thêm chi tiết vào danh sách
                                details.add(new InvoiceDetail(0, productId, quantity, product.getPrice()));
                                System.out.println("Đã thêm: " + product.getName() + " x " + quantity);
                            }

                            if (details.isEmpty()) {
                                System.out.println("Hóa đơn chưa có sản phẩm nào, hủy thêm!");
                                break;
                            }

                            // tạo hóa đơn
                            Invoice invoice = new Invoice(0, customerId, LocalDateTime.now(), total);

                            // gọi service với 2 tham số
                            invoiceService.addInvoice(invoice, details);
                            System.out.println("Thêm hóa đơn thành công! Tổng tiền: " + String.format("%.0f", total));

                        } catch (NumberFormatException e) {
                            System.out.println("Dữ liệu nhập không hợp lệ, vui lòng nhập số!");
                        } catch (Exception e) {
                            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                        }
                        break;


                    case 3:
                        System.out.println("1. Tìm theo tên khách hàng");
                        System.out.println("2. Tìm theo ngày/tháng/năm");
                        System.out.println("3. Quay lại menu hóa đơn");
                        System.out.print("Chọn: ");
                        int opt = Integer.parseInt(scanner.nextLine());

                        switch (opt) {
                            case 1:
                                System.out.print("Tên khách hàng: ");
                                String name = scanner.nextLine();
                                var resultsByName = invoiceService.findByCustomerName(name);
                                if (resultsByName == null || resultsByName.isEmpty()) {
                                    System.out.println("Không tìm thấy hóa đơn cho khách hàng: " + name);
                                } else {
                                    resultsByName.forEach(System.out::println);
                                }
                                break;
                            case 2:
                                System.out.print("Ngày (yyyy-MM-dd): ");
                                String date = scanner.nextLine();
                                var resultsByDate = invoiceService.findByDate(date);
                                if (resultsByDate == null || resultsByDate.isEmpty()) {
                                    System.out.println("Không tìm thấy hóa đơn cho ngày: " + date);
                                } else {
                                    resultsByDate.forEach(System.out::println);
                                }
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
                        }
                        break;
                    case 4:
                        try {
                            System.out.print("Nhập ID hóa đơn cần xem chi tiết: ");
                            int invoiceId = Integer.parseInt(scanner.nextLine());
                            var details = invoiceService.getInvoiceDetails(invoiceId);
                            if (details == null || details.isEmpty()) {
                                System.out.println("Không có chi tiết cho hóa đơn ID: " + invoiceId);
                            } else {
                                System.out.println("===== CHI TIẾT HÓA ĐƠN =====");
                                for (InvoiceDetail d : details) {
                                    double lineTotal = d.getUnitPrice() * d.getQuantity();
                                    System.out.printf("Sản phẩm ID: %d | Số lượng: %d | Giá: %.0f | Thành tiền: %.0f%n",
                                            d.getProductId(),
                                            d.getQuantity(),
                                            d.getUnitPrice(),
                                            lineTotal);
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("ID hóa đơn không hợp lệ!");
                        }
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            }
        }
    }
}
