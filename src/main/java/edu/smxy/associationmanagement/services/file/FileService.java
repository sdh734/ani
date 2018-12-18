package edu.smxy.associationmanagement.services.file;

import edu.smxy.associationmanagement.domain.File;

import java.util.List;

public interface FileService {
    void uploadFile(File file);

    File downloadFile(int fileId);

    File searchFileById(int fileId);

    List<File> searchFileByAuthor(int authorId);

    List<File> searchFileByEvent(int eventId);
}
