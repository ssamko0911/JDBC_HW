package hw001.taskfive.logic;

/*
Задание 5
Используя IntelijIdea и JDBC cделайте выборку при помощи JOIN’s для таких заданий:
1) Получите контактные данные сотрудников (номера телефонов, место жительства).
2) Получите информацию о дате рождения всех холостых сотрудников и их номера.
3) Получите информацию обо всех менеджерах компании: дату рождения и номер телефона.
 */

import hw001.taskfive.configuration.Database;
import hw001.taskfive.entity.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String statementOne = "select cells.mobile, personal_info.adress from cells "
                + "inner join personal_info "
                + "on cells.id = personal_info.person_id";
        String statementTwo = "select personal_info.date_of_birth, cells.mobile from personal_info "
                + "inner join cells "
                + "on personal_info.person_id = cells.id "
                + "where personal_info.family_status = 'сотрудник холост'";
        String statementThree = "select personal_info.date_of_birth, cells.mobile from personal_info "
                + "join cells "
                + "on personal_info.person_id = cells.id "
                + "join salaries "
                + "on cells.id = salaries.name_id "
                + "where salaries.positions = 'менеджер'";
        List<Employee> employeesOne = new ArrayList<>();
        List<Employee> employeesTwo = new ArrayList<>();
        List<Employee> employeesThree = new ArrayList<>();
        Database.registerDriver();
        try (Connection connection = Database.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSetOne = statement.executeQuery(statementOne);
            while (resultSetOne.next()) {
                String mobile = resultSetOne.getString("mobile");
                String adress = resultSetOne.getString("adress");
                Employee employee = new Employee(mobile, adress, null);
                employeesOne.add(employee);
            }
            System.out.println("Первая выборка: ");
            for (Employee someEmployee : employeesOne) {
                System.out.println(someEmployee);
            }

            ResultSet resultSetTwo = statement.executeQuery(statementTwo);
            while (resultSetTwo.next()) {
                String bday = resultSetTwo.getString("date_of_birth");
                String mobile = resultSetTwo.getString("mobile");
                Employee employee = new Employee(mobile, null, bday);
                employeesTwo.add(employee);
            }
            System.out.println("Вторая выборка: ");
            for (Employee someEmployee : employeesTwo) {
                System.out.println(someEmployee);
            }

            ResultSet resultSetThree = statement.executeQuery(statementThree);
            while (resultSetThree.next()) {
                String bday = resultSetThree.getString("date_of_birth");
                String mobile = resultSetThree.getString("mobile");
                Employee employee = new Employee(mobile, null, bday);
                employeesThree.add(employee);
            }
            System.out.println("Третья выборка: ");
            for (Employee someEmployee : employeesThree) {
                System.out.println(someEmployee);
            }
            statement.close();
            resultSetOne.close();
            resultSetTwo.close();
            resultSetThree.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}