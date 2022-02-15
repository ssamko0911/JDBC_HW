package hw001.taskfive.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myjoinsdb", "root", "root");
            System.out.println("Подключение к БД успешно.");
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Подключение к БД остановлено.");
        }
        return null;
    }

    public static void registerDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Драйвер подключен.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Драйвер не подключен.");
        }
    }
}