package connection;

import java.io.IOException;
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
    
    public void run(UserMessage um) {
        try {
            Socket socket = new Socket("localhost", port);
            userMessageSocket.sendUserMessage(socket, um);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

}
