package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import screen.Main;

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
                System.setProperty("javax.net.ssl.trustStore", "clientkeystore.ks");

                SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket("localhost", port);

                socket.startHandshake();

                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                String msg = (String) objectInputStream.readObject();

                boolean serverIsUp = msg.equals(UP_MESSAGE);
                System.out.println(serverIsUp);

                // call updateServerStatus(serverIsUp) to update front
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException | ClassNotFoundException ex) {
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
