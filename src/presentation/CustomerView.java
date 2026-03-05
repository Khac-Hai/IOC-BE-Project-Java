package presentation;

import model.Customer;
import business.CustomerService;

import java.util.Scanner;

public class CustomerView {
    private CustomerService customerService;
    private Scanner scanner = new Scanner(System.in);

    public CustomerView(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("===== QUẢN LÝ KHÁCH HÀNG =====");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng theo ID");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customerService.listAllCustomers().forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Tên: ");
                    String name = scanner.nextLine();
                    System.out.print("SĐT: ");
                    String phone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Địa chỉ: ");
                    String address = scanner.nextLine();
                    customerService.addCustomer(new Customer(0, name, phone, email, address));
                    break;
                case 3:
                    System.out.print("Nhập ID khách hàng cần cập nhật: ");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Customer cUpdate = customerService.findCustomerById(idUpdate);
                    if (cUpdate != null) {
                        System.out.print("Tên mới: ");
                        cUpdate.setName(scanner.nextLine());
                        System.out.print("SĐT mới: ");
                        cUpdate.setPhone(scanner.nextLine());
                        System.out.print("Email mới: ");
                        cUpdate.setEmail(scanner.nextLine());
                        System.out.print("Địa chỉ mới: ");
                        cUpdate.setAddress(scanner.nextLine());
                        customerService.updateCustomer(cUpdate);
                    } else {
                        System.out.println("Không tìm thấy khách hàng!");
                    }
                    break;
                case 4:
                    System.out.print("Nhập ID khách hàng cần xóa: ");
                    int idDelete = scanner.nextInt();
                    scanner.nextLine();
                    customerService.deleteCustomer(idDelete);
                    break;
                case 5:
                    return;
            }
        }
    }
}
