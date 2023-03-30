package pl.coderslab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.coderslab.beans.SimpleCustomerLogger;
import pl.coderslab.utils.DbUtil;

@Configuration
@ComponentScan("pl.coderslab")
public class AppConfig {
    @Bean
    public DbUtil dbUtil() {
        DbUtil dbUtil = new DbUtil();
        dbUtil.setUrl("jdbc:mysql://localhost:3306/customerlogs?useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
        dbUtil.setUser("root");
        dbUtil.setPass("coderslab");
        return dbUtil;
    }
}
