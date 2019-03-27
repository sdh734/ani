package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.WFEntity;

public interface WFEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WFEntity record);

    int insertSelective(WFEntity record);

    WFEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WFEntity record);

    int updateByPrimaryKey(WFEntity record);
}