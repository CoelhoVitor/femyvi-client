package connection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import model.FileMessage;

public class FileUpload {

    private final int port;

    private final FileMessageSocket fileMessageSocket = new FileMessageSocket();

    public FileUpload(Ports port) {
        this.port = port.getValue();
    }

    public void run(FileMessage fm) {
        try {
            System.setProperty("javax.net.ssl.trustStore", "clientkeystore.ks");

            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket("localhost", port);

            socket.startHandshake();

            fileMessageSocket.sendFileMessage(socket, fm);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
