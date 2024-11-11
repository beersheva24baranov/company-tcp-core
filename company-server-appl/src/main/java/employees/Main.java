package employees;
import io.Persistable;
import net.*;


public class Main {
        private static final String FILE_NAME = "employees.data";

    public static void main(String[] args) {
        Company company = new CompanyImpl();
        Protocol protocol = new CompanyProtocol(company);
        TcpServer server = new TcpServer(protocol, 4000);
        if (company instanceof Persistable persistable) {
            try {
                persistable.restoreFromFile(FILE_NAME);
            } catch (Exception e) {
                persistable.saveToFile(FILE_NAME);
            }
        }
        server.run();
    }
}