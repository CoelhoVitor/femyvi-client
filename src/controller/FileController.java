package controller;

import connection.FileUpload;
import connection.Ports;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import model.FileMessage;
import utils.FileUtils;

public class FileController {

    public FileController() {
    }

    public static void SendFile(File file) throws IOException {
        // create file message
        FileMessage fm = new FileMessage();

        String filename = file.getName();
        Path path = file.toPath();

        fm.setFilename(filename);
        fm.setFileType(FileUtils.getExtension(filename));
        fm.setOriginPath(file.getAbsolutePath());
        fm.setCreatedDate(FileUtils.getCreationDate(path));
        fm.setContent(FileUtils.getContent(path));
        fm.setFileSize(fm.getContent().length);
        
        // send file
        FileUpload fup = new FileUpload(Ports.UPLOAD);
        fup.run(fm);
    }

}
