package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import utils.SessionServer;

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
                updateServerStatus(isServerUp);

                Thread.sleep(1000);
            } catch (IOException | ClassNotFoundException | InterruptedException ex) {
                updateServerStatus(false);
            }
        }

    }

    private void updateServerStatus(boolean isServerUp) {
        SessionServer session = SessionServer.getInstance();

        if (port == Ports.HEALTHCHECK_1.getValue()) {
            session.getServer1().setOnline(isServerUp);
        } else if (port == Ports.HEALTHCHECK_2.getValue()) {
            session.getServer2().setOnline(isServerUp);
        } else if (port == Ports.HEALTHCHECK_SG.getValue()) {
            session.getServerSg().setOnline(isServerUp);
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
