package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FileMessage;

public class FileDownload {
    
    private int port;
    
    private final FileMessageSocket fileMessageSocket = new FileMessageSocket();

    public FileDownload(Ports port) {
        this.port = port.getValue();
    }
    
    public FileMessage run() {
        try {
            Socket socket = new Socket("localhost", port);            
            FileMessage fm = fileMessageSocket.receiveFileMessage(socket);
            System.out.println(fm.toString());
            socket.close();
            return fm;
        } catch (IOException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
}
