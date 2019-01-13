package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.File;

import java.util.List;

public interface FileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);

    List<File> selectByEvent(int eventId);

    List<File> selectByAuthor(int authorId);

    File selectByRecord(File record);

    List<File> getAllFileByAssid(Integer assid);

    List<File> getAllFile();
}