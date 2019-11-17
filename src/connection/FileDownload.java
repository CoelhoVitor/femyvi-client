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

    public FileDownload(Ports port) {
        this.port = port.getValue();
    }
    
    public FileMessage run() {
        try {
            Socket socket = new Socket("localhost", port);
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            
            FileMessage fm = (FileMessage) objectInputStream.readObject();
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
