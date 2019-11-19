package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserMessage;

public class UserLogin {

    private final int port;

    private final UserMessageSocket userMessageSocket = new UserMessageSocket();

    public UserLogin(Ports port) {
        this.port = port.getValue();
    }

    public boolean run(UserMessage um) {
        try {
            Socket socket = new Socket("localhost", port);
            userMessageSocket.sendUserMessage(socket, um);
            // get from SG if user is valid
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Boolean isValidUser = (Boolean) objectInputStream.readObject();
            socket.close();

            return isValidUser;
        } catch (IOException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}