package tr.com.nemesis.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ObjectHelper {

    private String userName = "****";
    private String password = "****";
    private String url = "jdbc:mysql://localhost:3306/smartenergysystems?useUnicode=true&characterEncoding=utf8";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
