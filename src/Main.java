import dao.AdminDAO;
import dao.impl.AdminDAOImpl;
import business.AdminService;
import business.impl.AdminServiceImpl;
import presentation.AdminView;

public class Main {
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAOImpl();
        AdminService adminService = new AdminServiceImpl(adminDAO);
        AdminView adminView = new AdminView(adminService);

        // Đăng nhập admin
        adminView.loginMenu();

        // Thêm admin mới (tuỳ chọn)
        // adminView.addAdminMenu();
    }
}
