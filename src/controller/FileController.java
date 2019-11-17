package controller;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import model.FileMessage;
import utils.FileUtils;

/**
 *
 * @author coelhovitor
 */
public class FileController {

    public FileController() {
    }

    public static void SendFile(File file) throws IOException {
        
        // create socket
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Conectado!");
        
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        
        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(fileMessage);
        
        System.out.println("Fechando socket and terminando o programa.");
        socket.close();
        
        // create file message
        FileMessage fm = new FileMessage();

        String fileName = file.getName();
        Path path = file.toPath();

        fm.setFilename(fileName);
        fm.setFileType(FileUtils.getExtension(fileName));
        fm.setOriginPath(file.getAbsolutePath());
        fm.setCreatedDate(FileUtils.getCreationDate(path));
        fm.setContent(FileUtils.getContent(path));
        fm.setFileSize(fm.getContent().length);
        
        // send file        

    }

}
