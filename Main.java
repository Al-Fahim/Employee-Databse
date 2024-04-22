import java.util.Scanner;
import java.util.List;

public class Main {
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Employee Database Menu");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.next();
        System.out.print("Enter employee designation: ");
        String designation = scanner.next();
        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();
        // Assuming hire date is today's date
        // You can modify this according to your requirements
        java.util.Date hireDate = new java.util.Date();
        Employee employee = new Employee(0, name, designation, salary, hireDate);
        employeeDAO.addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private static void viewAllEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new employee name: ");
        String name = scanner.next();
        System.out.print("Enter new employee designation: ");
        String designation = scanner.next();
        System.out.print("Enter new employee salary: ");
        double salary = scanner.nextDouble();
        // Assuming hire date is today's date
        // You can modify this according to your requirements
        java.util.Date hireDate = new java.util.Date();
        Employee employee = new Employee(id, name, designation, salary, hireDate);
        employeeDAO.updateEmployee(employee);
        System.out.println("Employee updated successfully.");
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        employeeDAO.deleteEmployee(id);
        System.out.println("Employee deleted successfully.");
    }
}
