package pl.coderslab.beans;

import java.util.List;

public interface CustomerRepository {
    void addClient(Customer customer);
    void removeClient(Customer customer);
    List<Customer> getCustomers();
}
