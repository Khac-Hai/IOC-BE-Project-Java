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
            System.out.println("5. Quay lại menu chính");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Chọn");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        customerService.listAllCustomers().forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println("Có lỗi xảy ra: " + e.getMessage());
                    }
                    break;
                case 2:
                    String name;
                    while (true) {
                        System.out.print("Tên: ");
                        name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("Tên không được để trống, vui lòng nhập lại!");
                        } else {
                            break;
                        }
                    }
                    String phone;
                    while (true) {
                        System.out.print("SĐT: ");
                        phone = scanner.nextLine().trim();
                        if (phone.isEmpty()) {
                            System.out.println("SĐT không được để trống, vui lòng nhập lại!");
                        } else {
                            break;
                        }
                    }
                    String email;
                    while (true) {
                        System.out.print("Email: ");
                        email = scanner.nextLine().trim();
                        if (email.isEmpty()) {
                            System.out.println("Email không được để trống, vui lòng nhập lại!");
                        } else {
                            break;
                        }
                    }
                    System.out.print("Địa chỉ: ");
                    String address = scanner.nextLine().trim();
                    try {
                        customerService.addCustomer(new Customer(0, name, phone, email, address));
                        System.out.println("Thêm khách hàng thành công!");
                    } catch (Exception e) {
                        System.out.println("Có lỗi xảy ra: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID khách hàng cần cập nhật: ");
                    int idUpdate;
                    try {
                        idUpdate = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID phải là số!");
                        break;
                    }

                    Customer cUpdate = customerService.findCustomerById(idUpdate);
                    if (cUpdate != null) {
                        System.out.print("Tên mới: ");
                        cUpdate.setName(scanner.nextLine().trim());
                        System.out.print("SĐT mới: ");
                        cUpdate.setPhone(scanner.nextLine().trim());
                        System.out.print("Email mới: ");
                        cUpdate.setEmail(scanner.nextLine().trim());
                        System.out.print("Địa chỉ mới: ");
                        cUpdate.setAddress(scanner.nextLine().trim());
                        try {
                            customerService.updateCustomer(cUpdate);
                            System.out.println("Cập nhật thành công!");
                        } catch (Exception e) {
                            System.out.println("Có lỗi xảy ra: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Không tìm thấy khách hàng!");
                    }
                    break;
                case 4:
                    System.out.print("Nhập ID khách hàng cần xóa: ");
                    int idDelete;
                    try {
                        idDelete = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID phải là số!");
                        break;
                    }
                    System.out.print("Bạn có chắc chắn muốn xóa khách hàng ID " + idDelete + "? (y/n): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        try {
                            customerService.deleteCustomer(idDelete);
                            System.out.println("Xóa thành công!");
                        } catch (IllegalStateException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Có lỗi xảy ra: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Hủy xóa.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Chọn:");
            }
        }
    }
}
