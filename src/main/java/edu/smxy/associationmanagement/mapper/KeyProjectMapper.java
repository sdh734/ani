package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.KeyProject;

public interface KeyProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KeyProject record);

    int insertSelective(KeyProject record);

    KeyProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KeyProject record);

    int updateByPrimaryKey(KeyProject record);
}