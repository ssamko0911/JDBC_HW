package hw001.tasktwo;

/*
Задание 2
Используя дополнительное задания все вопросы записать в текстовом файле с новой строки каждый и
используя потоки ввода-вывода считать с файла все запросы и выполнить.
 */

import hw001.taskadditional.Connector;
import hw001.taskadditional.Driver;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Run {
    private static final String URL = "jdbc:mysql://localhost:3306/hr_db";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        String createTable = "create table test_table ("
                + "id int not null,"
                + "title VARCHAR (30) not null,"
                + "primary key (id))\n";
        String firstInsert = "INSERT INTO test_table(id, title) VALUES (1, 'АК-47')\n";
        String secondInsert = "INSERT INTO test_table(id, title) VALUES (2, 'Гром20')\n";
        String thirdInsert = "INSERT INTO test_table(id, title) VALUES (3, 'АК-74')\n";
        List<String> lines = new ArrayList<>();

        Driver.loadDriver();
        Connection connection = Connector.connectToDB(URL, LOGIN, PASSWORD);

        try (FileWriter fileWriter = new FileWriter("C:\\Users\\samko.s\\IdeaProjects\\JDBC_HW\\src\\main\\resources\\test.txt", true);
             FileReader fileReader = new FileReader("C:\\Users\\samko.s\\IdeaProjects\\JDBC_HW\\src\\main\\resources\\test.txt");
             Statement statement = connection.createStatement()) {
            fileWriter.write(createTable);
            fileWriter.write(firstInsert);
            fileWriter.write(secondInsert);
            fileWriter.write(thirdInsert);
            fileWriter.flush();
            System.out.println("Данные записаны в файл.");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            for (String element : lines) {
                statement.execute(element);
            }
            System.out.println("Стейтменты выполнены.");
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }
}