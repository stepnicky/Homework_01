package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryCustomerRepository implements CustomerRepository {

    private List<Customer> customers;
    private CustomerLogger logger;

    public MemoryCustomerRepository(@Qualifier("fileLogger") CustomerLogger logger) {
        this.customers = new ArrayList<>();
        this.logger = logger;
    }

    @Override
    public void addClient(Customer customer) {
        logger.log();
        this.customers.add(customer);
    }

    @Override
    public void removeClient(Customer customer) {
        logger.log();
        this.customers.remove(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        logger.log();
        return this.customers;
    }
}
