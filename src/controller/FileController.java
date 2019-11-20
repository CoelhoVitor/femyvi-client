package controller;

import connection.FileFetch;
import connection.FileUpload;
import connection.Ports;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import model.FileMessage;
import model.UserMessage;
import utils.FileUtils;
import utils.SessionUser;

public class FileController {

    public FileController() {
    }

    public static void SendFile(File file) throws IOException {
        // create file message
        FileMessage fm = new FileMessage();

        String filename = file.getName();
        Path path = file.toPath();
        UserMessage sessionUser = SessionUser.getInstance();

        fm.setFilename(FileUtils.getFilenameWithoutExtension(filename));
        fm.setFileType(FileUtils.getExtension(filename));
        fm.setOriginPath(file.getAbsolutePath());
        fm.setCreatedDate(FileUtils.getCreationDate(path));
        fm.setContent(FileUtils.getContent(path));
        fm.setFileSize(fm.getContent().length);
        fm.setOwner(sessionUser.getLogin());

        // send file
        FileUpload fup = new FileUpload(Ports.UPLOAD);
        fup.run(fm);
    }

    public static ArrayList<FileMessage> ReceiveFiles(UserMessage um) {
        FileFetch ff = new FileFetch(Ports.FETCH);
        ArrayList<FileMessage> fms = ff.run(um);

        return fms;
    }

}
