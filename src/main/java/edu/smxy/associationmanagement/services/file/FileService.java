package edu.smxy.associationmanagement.services.file;

import edu.smxy.associationmanagement.domain.File;

import java.util.List;

public interface FileService {
	int deleteByPrimaryKey(final Integer id);

	void uploadFile(final File file);

	File downloadFile(final int fileId);

	File searchFileById(final int fileId);

	List<File> searchFileByAuthor(final int authorId);

	List<File> searchFileByEvent(final int eventId);

	File selectByRecord(final File record);

	List<File> getAllFileByAssid(final Integer assid);

	int updateByPrimaryKey(final File record);

	List<File> getAllFile();
}
