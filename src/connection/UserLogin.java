package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import model.UserMessage;

public class UserLogin {

    private final int port;

    private final UserMessageSocket userMessageSocket = new UserMessageSocket();

    public UserLogin(Ports port) {
        this.port = port.getValue();
    }

    public boolean run(UserMessage um) {
        try {
            System.setProperty("javax.net.ssl.trustStore", "clientkeystore.ks");

            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket("localhost", port);

            userMessageSocket.sendUserMessage(socket, um);
            // get from SG if user is valid
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Boolean isValidUser = (Boolean) objectInputStream.readObject();
            socket.close();

            return isValidUser;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
