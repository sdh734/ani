package edu.smxy.associationmanagement.services.suggest;

import edu.smxy.associationmanagement.domain.Suggest;

import java.util.List;

public interface SuggestService {
	int deleteByPrimaryKey(final Integer id);

	int insert(final Suggest record);

	int insertSelective(final Suggest record);

	Suggest selectByPrimaryKey(final Integer id);

	int updateByPrimaryKeySelective(final Suggest record);

	int updateByPrimaryKeyWithBLOBs(final Suggest record);

	int updateByPrimaryKey(final Suggest record);

	List<Suggest> getAllSuggestByAuthorId(final Integer authorid);

	List<Suggest> getAllSuggestByReceiveId(final Integer receiveid);
}
