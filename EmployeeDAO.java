import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDAO {
    private final String jdbcUrl;
    private final String username;
    private final String password;

    public EmployeeDAO() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Codes\\Employee Database\\src\\db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcUrl = properties.getProperty("db.jdbcUrl");
        username = properties.getProperty("db.username");
        password = properties.getProperty("db.password");
    }

    // Method to create a new employee record in the database
    public void addEmployee(Employee employee) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO employees (name, designation, salary, hire_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, employee.getName());
                statement.setString(2, employee.getDesignation());
                statement.setDouble(3, employee.getSalary());
                statement.setDate(4, new java.sql.Date(employee.getHireDate().getTime()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all employees from the database
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT * FROM employees";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String designation = resultSet.getString("designation");
                    double salary = resultSet.getDouble("salary");
                    Date hireDate = resultSet.getDate("hire_date");
                    employees.add(new Employee(id, name, designation, salary, hireDate));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Method to update an employee record in the database
    public void updateEmployee(Employee employee) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "UPDATE employees SET name=?, designation=?, salary=?, hire_date=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, employee.getName());
                statement.setString(2, employee.getDesignation());
                statement.setDouble(3, employee.getSalary());
                statement.setDate(4, new java.sql.Date(employee.getHireDate().getTime()));
                statement.setInt(5, employee.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an employee record from the database
    public void deleteEmployee(int employeeId) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "DELETE FROM employees WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

