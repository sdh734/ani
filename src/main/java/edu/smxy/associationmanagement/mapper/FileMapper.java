package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.File;

import java.util.List;

public interface FileMapper {
	int deleteByPrimaryKey(final Integer id);

	int insert(final File record);

	int insertSelective(final File record);

	File selectByPrimaryKey(final Integer id);

	int updateByPrimaryKeySelective(final File record);

	int updateByPrimaryKey(final File record);

	List<File> selectByEvent(final int eventId);

	List<File> selectByAuthor(final int authorId);

	File selectByRecord(final File record);

	List<File> getAllFileByAssid(final Integer assid);

	List<File> getAllFile();
}
