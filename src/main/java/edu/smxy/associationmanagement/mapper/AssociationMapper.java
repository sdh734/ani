package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.Association;

import java.util.List;

public interface AssociationMapper {
    int deleteByPrimaryKey(Integer associationid);

    int insert(Association record);

    int insertSelective(Association record);

    Association selectByPrimaryKey(Integer associationid);

    int updateByPrimaryKeySelective(Association record);

    int updateByPrimaryKey(Association record);

    List<Association> getAll();

    Association query(Association association);
}