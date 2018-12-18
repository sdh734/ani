package edu.smxy.associationmanagement.services.file.impl;

import edu.smxy.associationmanagement.domain.File;
import edu.smxy.associationmanagement.mapper.FileMapper;
import edu.smxy.associationmanagement.services.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "FileService")
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public void uploadFile(File file) {
        fileMapper.insert(file);
    }

    @Override
    public File downloadFile(int fileId) {
        return fileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public File searchFileById(int fileId) {
        return fileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public List<File> searchFileByAuthor(int authorId) {
        return fileMapper.selectByAuthor(authorId);
    }

    @Override
    public List<File> searchFileByEvent(int eventId) {
        return fileMapper.selectByEvent(eventId);
    }
}
