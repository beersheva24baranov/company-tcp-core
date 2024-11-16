package employees;

import java.util.Iterator;

import org.json.JSONArray;

import net.TcpClient;

public class CompanyNetProxy implements Company {
    TcpClient netClient;

    public CompanyNetProxy(TcpClient netClient) {
        this.netClient = netClient;
    }

    @Override
    public Iterator<Employee> iterator() {
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
    

    @Override
    public void addEmployee(Employee empl) {
        netClient.sendAndReceive("Employees/add", empl.toString());
    }

    @Override
    public Employee getEmployee(long id) {
        String jsonStr = netClient.sendAndReceive("Employees/get", Long.toString(id));
        return Employee.getEmployeeFromJSON(jsonStr);
    }

    @Override
    public Manager[] getManagersWithMostFactor() {
        String jsonStr = netClient.sendAndReceive("Employees/getManagersWithMostFactor", "");
        JSONArray managers = new JSONArray(jsonStr);
        return managers.toList().stream().map(i -> Employee.getEmployeeFromJSON(i.toString())).toArray(Manager[]::new);
    }

    @Override
    public Employee removeEmployee(long id) {
        String jsonStr = netClient.sendAndReceive("Employees/remove", Long.toString(id));
        return Employee.getEmployeeFromJSON(jsonStr);
    }

    @Override
    public int getDepartmentBudget(String department) {
        String budget = netClient.sendAndReceive("Departments/getBudget", department);
        return Integer.parseInt(budget);
    }

    @Override
    public String[] getDepartments() {
        String jsonStr = netClient.sendAndReceive("Departments/getList", "");
        JSONArray jsonArray = new JSONArray(jsonStr);
        String[] res = jsonArray.toList().toArray(String[]::new);
        return res;
    }
}