package employees;

import io.*;
import net.*;

import static employees.ServerConfig.*;

public class Main {

    public static void main(String[] args) {
        Company company = new CompanyImpl();
        Protocol protocol = new ProtocolEmployee(company);
        TcpServer server = new TcpServer(protocol, PORT);
        CompanySaver companySaver = new CompanySaver(company);
        if (company instanceof Persistable persistable) {
            persistable.restoreFromFile(FILE_NAME);
            System.out.printf("Company data restored from the file %s\n", FILE_NAME);
        }
        Thread treadTcpServer = new Thread(server);
        Thread treadCompanySaver = new Thread(companySaver);
        treadTcpServer.start();
        treadCompanySaver.start();
    }
}