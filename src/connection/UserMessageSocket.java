
package connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import model.UserMessage;

public class UserMessageSocket {
    
    public void sendUserMessage(Socket socket, UserMessage um) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(um);
    }
    
}
