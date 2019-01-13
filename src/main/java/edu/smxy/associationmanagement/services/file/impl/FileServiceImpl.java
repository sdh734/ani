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
    public int deleteByPrimaryKey(Integer id) {
        return fileMapper.deleteByPrimaryKey(id);
    }

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

    @Override
    public File selectByRecord(File record) {
        return fileMapper.selectByRecord(record);
    }

    @Override
    public List<File> getAllFileByAssid(Integer assid) {
        return fileMapper.getAllFileByAssid(assid);
    }

    @Override
    public int updateByPrimaryKey(File record) {
        return fileMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<File> getAllFile() {
        return fileMapper.getAllFile();
    }
}
