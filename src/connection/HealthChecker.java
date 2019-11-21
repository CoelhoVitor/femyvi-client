package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import model.ServerStatus;

public class HealthChecker extends Thread {

    private final String UP_MESSAGE = "Server is up!";

    private int port;

    public HealthChecker(Ports port) {
        this.port = port.getValue();
    }

    @Override
    public void run() {
            while (true) {
                try {
                    System.setProperty("javax.net.ssl.trustStore", "clientkeystore.ks");
                    
                    SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                    SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket("localhost", port);
                    
                    socket.startHandshake();
                    
                    InputStream inputStream = socket.getInputStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    
                    String msg = (String) objectInputStream.readObject();
                    
                    boolean isServerUp = msg.equals(UP_MESSAGE);
                    System.out.println(isServerUp);
                    
                    updateServerStatus(isServerUp);
                    Thread.sleep(2000);
                } catch (IOException | ClassNotFoundException | InterruptedException ex) {
                    Logger.getLogger(HealthChecker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    }

    private void updateServerStatus(boolean isServerUp) {
        ServerStatus ss = new ServerStatus();

        if (port == Ports.HEALTHCHECK_1.getValue()) {
            ss.setServer1(isServerUp);
            System.out.println("server 1:" + isServerUp);
        } else if (port == Ports.HEALTHCHECK_2.getValue()) {
            ss.setServer2(isServerUp);
            System.out.println("server 2:" + isServerUp);
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
