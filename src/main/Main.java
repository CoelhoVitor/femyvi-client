package main;


import connection.HealthChecker;
import connection.Ports;
import java.io.IOException;
import screen.LoginScreen;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        HealthChecker hc1 = new HealthChecker(Ports.HEALTHCHECK_1);
        HealthChecker hc2 = new HealthChecker(Ports.HEALTHCHECK_2);

        // init HealthCheckers
        hc1.start();
        hc2.start();
        
        new LoginScreen();
                
    }
    
}
