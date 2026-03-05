package dao;

import model.Customer;
import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
}
