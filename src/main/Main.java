package main;


import connection.HealthChecker;
import connection.Ports;
import java.io.IOException;
import screen.Login;
import utils.SessionServer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        new Login();
        
        HealthChecker hc1 = new HealthChecker(Ports.HEALTHCHECK_1);
        HealthChecker hc2 = new HealthChecker(Ports.HEALTHCHECK_2);

        // init HealthCheckers
        hc1.start();
        hc2.start();      
                
    }
    
}
