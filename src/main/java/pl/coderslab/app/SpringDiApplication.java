package pl.coderslab.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.coderslab.beans.*;
import pl.coderslab.config.AppConfig;

import java.util.List;

public class SpringDiApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
//        SimpleCustomerLogger logger = context.getBean(SimpleCustomerLogger.class);
//        logger.log();

//        ClassPathXmlApplicationContext cntx = new ClassPathXmlApplicationContext("beans.xml");

        DBCustomerRepository dbCustomerRepository = context.getBean(DBCustomerRepository.class);
        Customer customer = new Customer("Jan", "Kowalski");
        Customer customer2 = new Customer("Andrzej", "Nowak");
        Customer customer3 = new Customer("Bronisław", "Komorowski");
        dbCustomerRepository.addClient(customer);
        dbCustomerRepository.addClient(customer2);
        dbCustomerRepository.addClient(customer3);
        List<Customer> customers = dbCustomerRepository.getCustomers();
        System.out.println(customers);
//        memoryCustomerRepository.removeClient(customer);



    }
}