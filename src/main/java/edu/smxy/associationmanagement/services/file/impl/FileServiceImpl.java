package edu.smxy.associationmanagement.services.file.impl;

import edu.smxy.associationmanagement.services.file.*;
import org.springframework.stereotype.*;
import edu.smxy.associationmanagement.mapper.*;
import org.springframework.beans.factory.annotation.*;
import edu.smxy.associationmanagement.domain.*;
import java.util.*;

@Service("FileService")
public class FileServiceImpl implements FileService
{
    @Autowired
    private FileMapper fileMapper;
    
    @Override
    public int deleteByPrimaryKey(final Integer id) {
        return this.fileMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public void uploadFile(final File file) {
        this.fileMapper.insert(file);
    }
    
    @Override
    public File downloadFile(final int fileId) {
        return this.fileMapper.selectByPrimaryKey(fileId);
    }
    
    @Override
    public File searchFileById(final int fileId) {
        return this.fileMapper.selectByPrimaryKey(fileId);
    }
    
    @Override
    public List<File> searchFileByAuthor(final int authorId) {
        return (List<File>)this.fileMapper.selectByAuthor(authorId);
    }
    
    @Override
    public List<File> searchFileByEvent(final int eventId) {
        return (List<File>)this.fileMapper.selectByEvent(eventId);
    }
    
    @Override
    public File selectByRecord(final File record) {
        return this.fileMapper.selectByRecord(record);
    }
    
    @Override
    public List<File> getAllFileByAssid(final Integer assid) {
        return (List<File>)this.fileMapper.getAllFileByAssid(assid);
    }
    
    @Override
    public int updateByPrimaryKey(final File record) {
        return this.fileMapper.updateByPrimaryKey(record);
    }
    
    @Override
    public List<File> getAllFile() {
        return (List<File>)this.fileMapper.getAllFile();
    }
}
