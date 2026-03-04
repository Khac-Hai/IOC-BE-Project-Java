package business.impl;

import dao.AdminDAO;
import model.Admin;
import business.AdminService;

public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO;

    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public Admin login(String username, String password) {
        return adminDAO.login(username, password);
    }

    @Override
    public void addAdmin(Admin admin) {
        adminDAO.addAdmin(admin);
    }
}

