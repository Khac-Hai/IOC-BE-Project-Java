package business;

import model.Customer;
import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
    Customer findCustomerById(int id);
    List<Customer> listAllCustomers();
}
