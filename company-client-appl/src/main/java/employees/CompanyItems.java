package employees;

import view.*;

public class CompanyItems {
    private static Company company;

    public static Item[] getItems(Company company) {
        CompanyItems.company = company;

        Item[] items = {
                Item.of("Add Employee", CompanyItems::addEmployee),
                Item.of("Display Employee Data", CompanyItems::displayEmployee),
                Item.of("Fire Employee", CompanyItems::fireEmployee),
                Item.of("Department Salary Budget", CompanyItems::displayDepartmentSalaryBudget),
                Item.of("List of Departments", CompanyItems::displayDepartments),
                Item.of("Display Managers with Most Factor", CompanyItems::displayManagersMostFactor),
        };

        return items;
    };

    private static void addEmployee(InputOutput io) {
        Menu menu = new Menu("Select Employee type", EmployeeItems.getItems(company));
        menu.perform(io);
    }

    private static void displayEmployee(InputOutput io) {
        long id = io.readLong("Enter ID:", null);
        Employee empl = company.getEmployee(id);
        io.writeLine(empl == null ? "Employee not found" : empl);
    }

    private static void fireEmployee(InputOutput io) {
        long id = io.readLong("Enter ID:", null);
        company.removeEmployee(id);
        io.writeLine("Employee fired");
    }

    private static void displayDepartmentSalaryBudget(InputOutput io) {
        String department = io.readString("Enter department:");
        io.writeLine(company.getDepartmentBudget(department));
    }

    private static void displayDepartments(InputOutput io) {
        io.writeLine(String.join(", ", company.getDepartments()));
    }

    private static void displayManagersMostFactor(InputOutput io) {
        for (Manager manager : company.getManagersWithMostFactor()) {
            io.writeLine(manager);
        }
    }
}
