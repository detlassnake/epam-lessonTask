package ua.epam.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeveloperDemo {
    static final String DATABASE_URL = "jdbc:mysql://localhost/epam";
    static final String USER = "root";
    static final String PASSWORD = "hugo449079";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection;
        Statement statement;
        ArrayList<Developer> arrayListDeveloper = new ArrayList<Developer>();
        System.out.println("Registering JDBC driver...");
        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        statement = connection.createStatement();

        String sql;
        sql = "SELECT d.id, d.fn, d.ln, d.salary FROM developersDemo d;";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("fn");
            String lastName = resultSet.getString("ln");
            int salary = resultSet.getInt("salary");
            Developer developer = new Developer();
            developer.setId(id);
            developer.setFirstName(firstName);
            developer.setLastName(lastName);
            developer.setSalary(salary);
            arrayListDeveloper.add(developer);
        }

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();
        System.out.println(arrayListDeveloper.toString());
    }
}