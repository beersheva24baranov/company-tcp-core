package employees;

import java.util.Arrays;
import java.util.Iterator;

import org.json.JSONArray;

import net.TcpClient;

public class CompanyTcpProxy implements Company{
    TcpClient tcpClient;
    public CompanyTcpProxy (TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }
    @Override
    public Iterator<Employee> iterator() {
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public void addEmployee(Employee empl) {
        tcpClient.sendAndReceive("addEmployee", empl.toString());
    }

    @Override
    public int getDepartmentBudget(String department) {
        return Integer.valueOf(tcpClient.sendAndReceive("getDepartmentBudget", department));
    }
    @Override
    public String[] getDepartments() {
        String jsonStr = tcpClient.sendAndReceive("getDepartments", "");
        JSONArray jsonArray = new JSONArray(jsonStr) ;
        String[]res = jsonArray.toList().toArray(String[]::new);
        return res;
    }

    @Override
    public Employee getEmployee(long id) {
        String employeeJSON = tcpClient.sendAndReceive("getEmployee", id + "");
        Employee employee = Employee.getEmployeeFromJSON(employeeJSON);
        return employee;
    }

   @Override
    public Manager[] getManagersWithMostFactor() {
        String jsonArrayString = tcpClient.sendAndReceive("getManagersWithMostFactor", "");
        JSONArray jsonArray = new JSONArray(jsonArrayString);
        String[] jsonObjectsStrings = jsonArray.toList().toArray(String[]::new);
        return Arrays.stream(jsonObjectsStrings).map(Employee::getEmployeeFromJSON).toArray(Manager[]::new);
    }

    @Override
    public Employee removeEmployee(long id) {
        String employeeJSON = tcpClient.sendAndReceive("removeEmployee", id + "");
        Employee employee = Employee.getEmployeeFromJSON(employeeJSON);
        return employee;
    }

    public void saveCompany() {
        tcpClient.sendAndReceive("saveCompany", "");
    }
}