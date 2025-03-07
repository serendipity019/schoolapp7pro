package gr.aueb.cf.schoolapp.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {

    private static final BasicDataSource ds = new BasicDataSource();
    private static Connection connection;

    static{
        ds.setUrl("jdbc:mysql://localhost:3306/school7dbpro?serverTimezone=UTC");
        ds.setUsername("kleidimos");
        ds.setPassword(System.getenv("PASSWD_kleidimos"));
        ds.setInitialSize(10); // in high demand apps we put here how many connections we want
        ds.setMinIdle(10); //The minimum connections that have the pool active.
    }

    /**
     * No instances of this class should be available
     */
    private DBUtil() {}

    public static Connection getConnection() throws SQLException {
        connection = ds.getConnection();
        System.out.println("Connection created");
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) connection.close();
    }
}
