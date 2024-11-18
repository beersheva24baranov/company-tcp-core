package employees;

import io.Persistable;
import static employees.ServerConfigProperties.*;

public class CompanySaver implements Runnable {
    private Company company;

    public CompanySaver(Company company) {
        this.company = company;
    }

    @Override
    public void run() {
        while (true) {
            if (company instanceof Persistable persistable) {
                if (persistable.saveTofile(FILE_NAME)) {
                    System.out.printf("State of company saved in the file %s\n", FILE_NAME);
                }    
            }
            try {
                Thread.sleep(SAVING_DELAY * 1000);
            } catch (InterruptedException e) {
            }
        }

    }



}
