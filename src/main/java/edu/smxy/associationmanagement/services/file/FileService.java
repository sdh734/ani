package edu.smxy.associationmanagement.services.file;

import edu.smxy.associationmanagement.domain.File;

import java.util.List;

public interface FileService {
    int deleteByPrimaryKey(Integer id);

    void uploadFile(File file);

    File downloadFile(int fileId);

    File searchFileById(int fileId);

    List<File> searchFileByAuthor(int authorId);

    List<File> searchFileByEvent(int eventId);

    File selectByRecord(File record);

    List<File> getAllFileByAssid(Integer assid);

    int updateByPrimaryKey(File record);

    List<File> getAllFile();
}
