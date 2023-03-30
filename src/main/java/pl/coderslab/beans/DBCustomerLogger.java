package pl.coderslab.beans;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;


@Repository
@dbLogger
public class DBCustomerLogger implements CustomerLogger {

    private DbUtil dbUtil;
    private static final String LOG_QUERY = "INSERT INTO logs (time, log) VALUES (?, ?)";

    public DBCustomerLogger(DbUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    @Override
    public void log() {
        try(Connection conn = dbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(LOG_QUERY)) {
            ps.setString(1, String.valueOf(LocalDateTime.now()));
            ps.setString(2, "Customer operation");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
