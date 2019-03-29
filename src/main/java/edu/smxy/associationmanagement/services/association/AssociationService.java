package edu.smxy.associationmanagement.services.association;

import edu.smxy.associationmanagement.domain.Association;

import java.util.List;

public interface AssociationService {
	int deleteByPrimaryKey(final Integer associationid);

	int insert(final Association record);

	int insertSelective(final Association record);

	Association selectByPrimaryKey(final Integer associationid);

	int updateByPrimaryKeySelective(final Association record);

	int updateByPrimaryKey(final Association record);

	List<Association> getAll();

	Association query(final Association association);
}
