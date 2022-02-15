package hw001.taskadditional;

/*
Задание
Создать базу данных в Workbench и подключить к IntelijIdea и создать тестовую таблицу. Заполнить ее
данными с помощью запросов MySQL в IntelijIdea. Используя JDBC написать пример выполнения всех
запросов.
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/hr_db";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        String createTable = "create table test_table ("
                + "id int not null,"
                + "title VARCHAR (30) not null,"
                + "primary key (id))";
        Driver.loadDriver();
        Connection connection = Connector.connectToDB(URL, LOGIN, PASSWORD);
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable);
            statement.addBatch("INSERT INTO test_table(id, title) VALUES (1, 'АК-47')");
            statement.addBatch("INSERT INTO test_table(id, title) VALUES (2, 'Гром20')");
            statement.addBatch("INSERT INTO test_table(id, title) VALUES (3, 'АК-74')");

            statement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}