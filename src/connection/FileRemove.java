
package connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FileMessage;

public class FileRemove {
    
    private int port;

    public FileRemove(Ports port) {
        this.port = port.getValue();
    }
    
    public void run(FileMessage fm) {
        try {
            Socket socket = new Socket("localhost", port);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(fm);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
