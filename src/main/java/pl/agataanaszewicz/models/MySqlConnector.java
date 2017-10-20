package pl.agataanaszewicz.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
    private static final String SQL_LINK = "jdbc:mysql://5.135.218.27:3306/oskar?useUnicode=true&characterEncoding=UTF-8";
    private static final String SQL_USER = "AgataAn";
    private static final String SQL_PASS = "9Y90RiFX1hAphm3C";
    private static final String SQL_CLASS = "com.mysql.jdbc.Driver";

    private static MySqlConnector connector = new MySqlConnector();
    public static MySqlConnector getInstance() {
        return connector;
    }

    private Connection connection;

    private MySqlConnector(){
        connect();
    }

    private void connect() {
        try {
            Class.forName(SQL_CLASS);
            connection = DriverManager.getConnection(SQL_LINK, SQL_USER, SQL_PASS);
            System.out.println("Połączono");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
