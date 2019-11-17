
package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HealthChecker extends Thread {
    
    private final String UP_MESSAGE = "Server is up!";
    
    private int port;

    public HealthChecker(Ports port) {
        this.port = port.getValue();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = new Socket("localhost", port);
                InputStream inputStream = socket.getInputStream();
                Scanner entrada = new Scanner(inputStream);
                String input = entrada.nextLine();
                
                boolean serverIsUp = input.equals(UP_MESSAGE);
                
                // call updateServerStatus(serverIsUp) to update front
                
                Thread.sleep(2000);
            }
        } catch (IOException ex) {
            Logger.getLogger(HealthChecker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HealthChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
}
