package connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import model.FileMessage;
import model.UserMessage;
import utils.FileUtils;

public class FileFetch {

    private final int port;

    private final UserMessageSocket userMessageSocket = new UserMessageSocket();

    private final FileMessageSocket fileMessageSocket = new FileMessageSocket();

    public FileFetch(Ports port) {
        this.port = port.getValue();
    }

    public ArrayList<FileMessage> run(UserMessage um) {

        while (true) {
            try {
                System.setProperty("javax.net.ssl.trustStore", "clientkeystore.ks");

                SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                SSLSocket socket = (SSLSocket) sslsocketfactory.createSocket("localhost", port);

                socket.startHandshake();

                // send user to SG
                userMessageSocket.sendUserMessage(socket, um);
                System.out.println(um.toString());

                socket.close();

                // get file messages from SG
                System.setProperty("javax.net.ssl.trustStore", "clientkeystore.ks");

                SSLSocket socketToSG = (SSLSocket) sslsocketfactory.createSocket("localhost", Ports.FETCH.getValue());

                socketToSG.startHandshake();

                ArrayList<FileMessage> fileMessages = fileMessageSocket.receiveFileMessageList(socketToSG);
                for (FileMessage fm : fileMessages) {
                    String filename = fm.getFilename();
                    fm.setFilename(FileUtils.getFilenameWithoutServerNum(filename));
                    fm.setOwner(um.getLogin());
                }
                socketToSG.close();

                return fileMessages;
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
