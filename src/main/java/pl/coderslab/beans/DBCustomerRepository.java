package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DBCustomerRepository implements CustomerRepository {

    private static final String ADD_QUERY = "INSERT INTO customers (first_name, last_name) VALUES (?, ?)";
    private static final String REMOVE_QUERY = "DELETE FROM customers WHERE id = ?";
    private static final String READ_ALL_QUERY = "SELECT * FROM customers";

    private DbUtil dbUtil;
    private CustomerLogger logger;

    public DBCustomerRepository(@dbLogger CustomerLogger logger, DbUtil dbUtil) {
        this.logger = logger;
        this.dbUtil = dbUtil;
    }

    @Override
    public void addClient(Customer customer) {
        logger.log();
        try(Connection conn = dbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(ADD_QUERY)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeClient(Customer customer) {
        logger.log();
        try(Connection conn = dbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(REMOVE_QUERY)) {
            ps.setLong(1, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        try(Connection conn = dbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(READ_ALL_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    customers.add(new Customer(
                       rs.getLong("id"),
                       rs.getString("first_name"),
                       rs.getString("last_name")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public DbUtil getDbUtil() {
        return dbUtil;
    }

    public void setDbUtil(DbUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    public CustomerLogger getLogger() {
        return logger;
    }

    @dbLogger
    public void setLogger(CustomerLogger logger) {
        this.logger = logger;
    }
}
