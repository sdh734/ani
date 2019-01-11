package edu.smxy.associationmanagement.services.association;

import edu.smxy.associationmanagement.domain.Association;

public interface AssociationService {
    int deleteByPrimaryKey(Integer associationid);

    int insert(Association record);

    int insertSelective(Association record);

    Association selectByPrimaryKey(Integer associationid);

    int updateByPrimaryKeySelective(Association record);

    int updateByPrimaryKey(Association record);
}
