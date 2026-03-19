package business.impl;

import dao.CustomerDAO;
import model.Customer;
import business.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public void addCustomer(Customer customer) {
        // Kiểm tra dữ liệu trống
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên khách hàng không được để trống, vui lòng nhập lại!");
        }
        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("SĐT không được để trống, vui lòng nhập lại!");
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống, vui lòng nhập lại!");
        }

        // Kiểm tra trùng SĐT hoặc Email
        List<Customer> all = customerDAO.getAllCustomers();
        for (Customer c : all) {
            if (c.getPhone().equalsIgnoreCase(customer.getPhone())) {
                throw new IllegalStateException("SĐT đã tồn tại, vui lòng nhập lại!");
            }
            if (c.getEmail().equalsIgnoreCase(customer.getEmail())) {
                throw new IllegalStateException("Email đã tồn tại, vui lòng nhập lại!");
            }
        }

        customerDAO.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        Customer existing = customerDAO.getCustomerById(customer.getId());
        if (existing == null) {
            throw new IllegalStateException("Không tìm thấy khách hàng để cập nhật");
        }

        // Kiểm tra trùng SĐT/Email với khách hàng khác
        List<Customer> all = customerDAO.getAllCustomers();
        for (Customer c : all) {
            if (c.getId() != customer.getId()) {
                if (c.getPhone().equalsIgnoreCase(customer.getPhone())) {
                    throw new IllegalStateException("SĐT đã tồn tại, vui lòng nhập lại!");
                }
                if (c.getEmail().equalsIgnoreCase(customer.getEmail())) {
                    throw new IllegalStateException("Email đã tồn tại, vui lòng nhập lại!");
                }
            }
        }

        customerDAO.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        Customer existing = customerDAO.getCustomerById(id);
        if (existing == null) {
            throw new IllegalStateException("Không tìm thấy khách hàng với ID " + id);
        }
        customerDAO.deleteCustomer(id);
    }


    @Override
    public Customer findCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public List<Customer> listAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}
