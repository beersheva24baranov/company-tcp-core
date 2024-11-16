package employees;

import static employees.CompanyConfigProperties.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;

import view.*;


public class EmployeeItems {

    private static Company company;

    public static Item[] getItems(Company company) {
        EmployeeItems.company = company;

        Item[] items = {
                Item.of("Hire Employee", EmployeeItems.addEmployee(EmployeeItems::enterEmployee)),
                Item.of("Hire Wage Employee", EmployeeItems.addEmployee(EmployeeItems::enterWageEmployee)),
                Item.of("Hire Sales Person", EmployeeItems.addEmployee(EmployeeItems::enterSalesPerson)),
                Item.of("Hire Manager", EmployeeItems.addEmployee(EmployeeItems::enterManager)),
                Item.ofExit()
        };

        return items;
    }

    private static Consumer<InputOutput> addEmployee(Function<InputOutput, Employee> reader) {
        return i -> company.addEmployee(reader.apply(i));
    }

    public static Employee enterEmployee(InputOutput io) {
        return new Employee(
                readId(io),
                readSalary(io),
                readDepartment(io));
    }

    public static WageEmployee enterWageEmployee(InputOutput io) {
        Employee empl = enterEmployee(io);
        return new WageEmployee(
                empl.getId(),
                empl.getSalary(),
                empl.getDepartment(),
                readWage(io),
                readHours(io));
    }

    public static SalesPerson enterSalesPerson(InputOutput io) {
        WageEmployee empl = enterWageEmployee(io);
        return new SalesPerson(
                empl.getId(),
                empl.getSalary(),
                empl.getDepartment(),
                empl.getWage(),
                empl.getHours(),
                readPercent(io),
                readSales(io));
    }

    public static Manager enterManager(InputOutput io) {
        Employee empl = enterEmployee(io);
        return new Manager(
                empl.getId(),
                empl.getSalary(),
                empl.getDepartment(),
                readFactor(io));
    }

    private static long readId(InputOutput io) {
        return io.readNumberRange(
                "Enter ID:",
                "Wrong format for ID",
                MIN_ID,
                MAX_ID).longValue();
    }

    private static int readSalary(InputOutput io) {
        return io.readNumberRange(
                "Enter salary:",
                "Wrong format for salary",
                MIN_SALARY,
                MAX_SALARY).intValue();
    }

    private static String readDepartment(InputOutput io) {
        return io.readStringOptions(
                "Enter department:",
                "Wrong format for department",
                new HashSet<String>(Arrays.asList(DEPARTMENTS)));
    }

    private static float readFactor(InputOutput io) {
        return io.readNumberRange(
                "Enter factor:",
                "Wrong format for factor",
                MIN_FACTOR,
                MAX_FACTOR).floatValue();
    }

    private static int readWage(InputOutput io) {
        return io.readNumberRange(
                "Enter wage:",
                "Wrong format for wage",
                MIN_WAGE,
                MAX_WAGE).intValue();
    }

    private static int readHours(InputOutput io) {
        return io.readNumberRange(
                "Enter hours:",
                "Wrong format for hours",
                MIN_HOURS,
                MAX_HOURS).intValue();
    }

    private static float readPercent(InputOutput io) {
        return io.readNumberRange(
                "Enter percent:",
                "Wrong format for percent",
                MIN_PERCENT,
                MAX_PERCENT).intValue();
    }

    private static long readSales(InputOutput io) {
        return io.readNumberRange(
                "Enter sales:",
                "Wrong format for sales",
                MIN_SALES,
                MAX_SALES).longValue();
    }

}
