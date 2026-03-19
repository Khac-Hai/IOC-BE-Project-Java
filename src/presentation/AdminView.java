package presentation;

import model.Admin;
import business.AdminService;

import java.util.Scanner;

public class AdminView {
    private AdminService adminService;
    private Scanner scanner = new Scanner(System.in);

    public AdminView(AdminService adminService) {
        this.adminService = adminService;
    }

    public Admin loginMenu() {
        System.out.println("===== ĐĂNG NHẬP QUẢN TRỊ =====");
        System.out.print("Tài khoản: ");
        String username = scanner.nextLine();
        if (username.trim().isEmpty()) {
            System.out.println("Tài khoản không được để trống!");
            return null;
        }
        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();
        if ( password.trim().isEmpty()) {
            System.out.println("Tài khoản và mật khẩu không được để trống!");
            return null;
        }
        Admin admin = adminService.login(username, password);
        if (admin != null) {
            System.out.println("Đăng nhập thành công!");
            return admin;
        } else {
            System.out.println("Sai tài khoản hoặc mật khẩu!");
            return null;
        }
    }
}
