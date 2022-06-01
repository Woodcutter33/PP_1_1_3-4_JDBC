package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/userkata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "!25koSmoS12";
//    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static Connection connection;
//    Driver driver = new FabricMySQLDriver();
    public Util() {
    }

    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

