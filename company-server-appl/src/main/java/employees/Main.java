package employees;
import io.Persistable;
import net.*;


public class Main {
    public static void main(String[] args) {
        Company company = new CompanyImpl();
        TcpServer server = new TcpServer(new CompanyProtocol(company), ServerConfig.PORT);
        if (company instanceof Persistable persistable) {
            persistable.restoreFromFile(ServerConfig.DATA_FILE);
            AutoSaveThread autoSaveThread = new AutoSaveThread(persistable);
            autoSaveThread.start();
        }
        server.run();
    }
}