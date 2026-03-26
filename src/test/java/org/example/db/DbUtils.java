package org.example.db;

import org.example.config.test_envproperties.TestConfigReaderImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    private static Connection connection;

    public static Connection createConnection() {
        try {
            String host = TestConfigReaderImpl.getDbHost();
            int port = TestConfigReaderImpl.getDbPort();
            String dataBaseName = TestConfigReaderImpl.getDbName();
            String user = TestConfigReaderImpl.getDbUser();
            String password = TestConfigReaderImpl.getDbPassword();
            String tableName = "users";
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + dataBaseName;
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            return createConnection();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
