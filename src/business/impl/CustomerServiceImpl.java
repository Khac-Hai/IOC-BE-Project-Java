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
    public void addCustomer(Customer customer) { customerDAO.addCustomer(customer); }

    @Override
    public void updateCustomer(Customer customer) { customerDAO.updateCustomer(customer); }

    @Override
    public void deleteCustomer(int id) { customerDAO.deleteCustomer(id); }

    @Override
    public Customer findCustomerById(int id) { return customerDAO.getCustomerById(id); }

    @Override
    public List<Customer> listAllCustomers() { return customerDAO.getAllCustomers(); }
}

