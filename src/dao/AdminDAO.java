package dao;

import model.Admin;

public interface AdminDAO {
    Admin login(String username, String password);
    void addAdmin(Admin admin);
}
